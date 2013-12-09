package gov.raleighnc.switchyard.integration.service.classdb.league;

import gov.raleighnc.switchyard.integration.domain.cityworks.workorder.WorkOrder;
import gov.raleighnc.switchyard.integration.domain.classdb.booking.BookingWorkOrder;
import gov.raleighnc.switchyard.integration.domain.classdb.league.League;

import javax.inject.Inject;

import org.switchyard.component.bean.Property;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

/**
 * Switchyard "Component" that is a "Bean Implementation" and implements the LeagueService contract.
 * 
 * @author mikev
 *
 */
@Service(LeagueService.class)
public class LeagueServiceBean implements LeagueService {
	@Inject
	@Reference
	private LeagueCwRestInterface cwRestInterface;
	
	@Inject
	@Reference
	private LeagueCwJpaInterface cwJpaInterface;
	
	@Inject
	@Reference
	private LeagueCwSqlInterface cwSqlInterface;
	
	@Property(name = "supervisor")
	private String supervisor;
	
	@Property(name = "requestedBy")
	private String requestedBy;
	
	@Property(name = "initiatedBy")
	private String initiatedBy;
	
	@Property(name = "priority")
	private String priority;
	
	@Property(name = "numDaysBefore")
	private String numDaysBefore;
	
	@Property(name = "woCategory")
	private String woCategory;
	
	@Property(name = "submitTo")
	private String submitTo;
	
	@Property(name = "status")
	private String status;
	
	@Property(name = "woTemplateId")
	private String woTemplateId;
	
	@Override
	public void createWorkOrder(League[] leagues) {
		for (League league : leagues) {
			// first check to see if a WO has already been created for this league
			BookingWorkOrder[] results = cwSqlInterface.getBookingWoMapping(league.getMaintenanceBooking());
			
			if (results != null && results.length > 0) {
				// since we found at least one BookingWorkOrder record (should only be one record) from the 
				// mapping table, that means we already created a WO for this booking hence skip this particular
				// record from being created as a new WO
				continue;
			}
			
			// create the WO from league information
			WorkOrder wo = new WorkOrder();
			wo.setWorkOrderId(league.getMaintenanceBooking().toString());
			wo.setLocation(league.getBarcodeNumber());
			wo.setDescription(league.getGroupTitle());
			wo.setProjectStartDate(league.getMaintenanceStart());
			wo.setProjectFinishDate(league.getMaintenanceEnd());
			wo.setInitiateDate(league.getMaintenanceStart());
			wo.setSupervisor(supervisor);
			wo.setRequestedBy(requestedBy);
			wo.setInitiatedBy(initiatedBy);
			wo.setPriority(priority);
			wo.setNumDaysBefore(Integer.parseInt(numDaysBefore));
			wo.setWoCategory(woCategory);
			wo.setSubmitTo(submitTo);
			wo.setStatus(status);
			wo.setWoTemplateId(woTemplateId);
			
			String cwWoId = cwRestInterface.createWorkOrder(wo);
			
			// store the mapping between WO id and booking id
			if (cwWoId != null && cwWoId.length() > 0) {
				BookingWorkOrder bwo = new BookingWorkOrder(league.getMaintenanceBooking(), cwWoId);
				cwJpaInterface.createBookingWoMapping(bwo);
			}
		}
	}
}