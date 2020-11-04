package Factory;

import DelegateClass.NotChangeableOneBeginAndEndLocationEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.NotChangeableOneBeginAndEndLocationEntry;
import Entry.FlightEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class FlightFactory {

	public static <R> FlightEntry<R> emptyEntry(String beginLocationString, String endLocationString, String name,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		Timeslot timeslot = new Timeslot(startTime, endTime);
		Location beginLocation = new Location(beginLocationString, true);
		Location endLocation = new Location(endLocationString, true);
		NotChangeableOneBeginAndEndLocationEntry ncobaele = new NotChangeableOneBeginAndEndLocationEntryImpl(
				beginLocation, endLocation, timeslot);
		EntryState use = new UnblockedStateEntryImpl();
		return new FlightEntry<R>(ncobaele, use, name);
	}

}
