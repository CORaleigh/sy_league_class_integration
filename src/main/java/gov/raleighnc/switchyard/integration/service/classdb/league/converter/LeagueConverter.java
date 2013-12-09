package gov.raleighnc.switchyard.integration.service.classdb.league.converter;

import gov.raleighnc.switchyard.integration.domain.classdb.league.League;
import gov.raleighnc.switchyard.integration.service.classdb.league.iterator.LeagueIterator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.camel.Converter;

/**
 * Custom type converter used to convert from {@link League} into {@link Iterator}
 * as well as the creation of Leagues from the Class database record query.
 * 
 * @author mikev
 */
@Converter
public class LeagueConverter {
	 /**
     * Wraps League into iterator.
     * 
     * @param league League.
     * @return
     */
    @Converter
    public static Iterator<Object> from(League league) {
        return new LeagueIterator(league);
    }
    
    @Converter
    public static League[] from(List<Map<String, Object>> objects) {
    	League[] leagues = new League[objects.size()];
        int position = 0;
        for (Map<String, Object> league : objects) {
        	leagues[position++] = new League(
                (Integer)league.get("Maintenance Booking"),
                (Date)league.get("Maintenance Start"),
                (Date)league.get("Maintenance End"),
                (Integer)league.get("Related Booking"),
                (String)league.get("Related Booking Type"),
                (Integer)league.get("Related Booking Reference"),
                (String)league.get("League Barcode Number"),
                (String)league.get("League Group Title")
            );
        }
        
        return leagues;
    }
}