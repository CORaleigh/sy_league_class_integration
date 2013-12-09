package gov.raleighnc.switchyard.integration.service.classdb.league;

import gov.raleighnc.switchyard.integration.domain.classdb.booking.BookingWorkOrder;

/**
 * Switchyard SQL-based "Composite Reference" for all league-based access to the Cityworks system.
 * 
 * @author mikev
 */
public interface LeagueCwSqlInterface {
	BookingWorkOrder[] getBookingWoMapping(int bookingId); 
}