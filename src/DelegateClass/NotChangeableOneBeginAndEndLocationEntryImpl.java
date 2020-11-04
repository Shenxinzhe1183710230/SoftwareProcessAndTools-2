package DelegateClass;

import java.util.Date;

import DelegateInterface.EntryInterface.NotChangeableOneBeginAndEndLocationEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class NotChangeableOneBeginAndEndLocationEntryImpl implements NotChangeableOneBeginAndEndLocationEntry {
	private final Location beginLocation;
	private final Location endLocation;
	private final Timeslot timeslot;

	public NotChangeableOneBeginAndEndLocationEntryImpl(Location beginLocation, Location endLocation,
			Timeslot timeslot) {
		super();
		this.beginLocation = beginLocation;
		this.endLocation = endLocation;
		this.timeslot = timeslot;
	}

	@Override
	public Location getBeginLocation() {
		return beginLocation.clone();
	}

	@Override
	public Location getEndLocation() {
		return endLocation.clone();
	}

	@Override
	public Timeslot getTimeslot() {
		// TODO Auto-generated method stub
		return timeslot.clone();
	}

	@Override
	public Boolean beforeBeginDatas(Date date) {
		// TODO Auto-generated method stub
		if (Math.abs((timeslot.getStartDate().getTime() - date.getTime())) <= 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean afterEndDatas(Date date) {
		// TODO Auto-generated method stub
		if (Math.abs((timeslot.getEndDate().getTime() - date.getTime())) <= 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

}