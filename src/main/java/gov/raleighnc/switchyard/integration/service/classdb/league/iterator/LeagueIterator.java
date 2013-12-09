package gov.raleighnc.switchyard.integration.service.classdb.league.iterator;

import gov.raleighnc.switchyard.integration.domain.classdb.league.League;

import java.util.Iterator;

/**
 * Iterator implementation for {@link League}.
 * 
 * @author mikev
 */
public class LeagueIterator implements Iterator<Object> {
    private final League league;
    private int pointer;

    public LeagueIterator(League league) {
        this.league = league;
    }

    @Override
    public boolean hasNext() {
        return pointer < 8;
    }

    @Override
    public Object next() {
        switch (pointer++) {
	        case 0: return league.getMaintenanceBooking();
	        case 1: return league.getMaintenanceStart();
	        case 2: return league.getMaintenanceEnd();
	        case 3: return league.getRelatedBooking();
	        case 4: return league.getRelatedBookingType();
	        case 5: return league.getRelatedBookingReference();
	        case 6: return league.getBarcodeNumber();
	        case 7: return league.getGroupTitle();
        }
        
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}