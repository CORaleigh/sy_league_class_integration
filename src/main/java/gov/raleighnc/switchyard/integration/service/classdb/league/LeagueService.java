package gov.raleighnc.switchyard.integration.service.classdb.league;

import gov.raleighnc.switchyard.integration.domain.classdb.league.League;

/**
 * A Switchyard "Composite Service" consisting of league services (from the Class database) usable by the outside world.
 * 
 * @author mikev
 *
 */
public interface LeagueService {
	/**
	 * Create a Cityworks work order for each league passed.
	 * 
	 * @param leagues An array of league information to create work orders from
	 */
	void createWorkOrder(League[] leagues);
}