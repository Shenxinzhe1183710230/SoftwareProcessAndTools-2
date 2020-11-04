package DelegateClass;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import DelegateInterface.EntryInterface.NotChangeableHaveMiddleLocationEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class NotChangeableHaveMiddleLocationEntryImpl implements NotChangeableHaveMiddleLocationEntry {
	Location beginLocation;
	Location endLocation;
	Timeslot beginAndEndtimeslot;
	Map<Location, Timeslot> map = new HashMap<Location, Timeslot>();

	public NotChangeableHaveMiddleLocationEntryImpl(Location beginLocation, Location endLocation, Timeslot timeslot,
			Map<Location, Timeslot> map) {
		super();
		this.beginLocation = beginLocation;
		this.endLocation = endLocation;
		this.beginAndEndtimeslot = timeslot;
		this.map = map;
	}

	public Location getBeginLocation() {
		return beginLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public Timeslot getBeginAndEndTimeslot() {
		return beginAndEndtimeslot;
	}

	@Override
	public Map<Location, Timeslot> getLocationAndTime() {
		// TODO Auto-generated method stub
		return new HashMap<Location, Timeslot>(map);
	}

	@Override
	public Boolean onehourDatas(Date date, Location nowLocation) {
		// TODO Auto-generated method stub
		if (isMiddleLocation(nowLocation)) {
			if (Math.abs(map.get(nowLocation).getStartDate().getTime() - date.getTime()) <= 60 * 60 * 1000) {
				return true;
			} else if (Math.abs(map.get(nowLocation).getEndDate().getTime() - date.getTime()) <= 60 * 60 * 1000) {
				return true;
			}
		} else if (nowLocation.equals(beginLocation)) {
			if (Math.abs(beginAndEndtimeslot.getStartDate().getTime() - date.getTime()) <= 60 * 60 * 1000) {
				return true;
			}
		} else if (nowLocation.equals(endLocation)) {
			if (Math.abs(beginAndEndtimeslot.getEndDate().getTime() - date.getTime()) <= 60 * 60 * 1000) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean isMiddleLocation(Location nowLocation) {
		// TODO Auto-generated method stub
		if (map.keySet().contains(nowLocation)) {
			return true;
		}
		return false;
	}

}