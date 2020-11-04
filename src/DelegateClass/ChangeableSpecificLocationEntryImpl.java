package DelegateClass;

//在B3分支上进行修改
//在R4基础上进行修改

import java.util.Date;

import DelegateInterface.EntryInterface.ChangeableSpecificLocationEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class ChangeableSpecificLocationEntryImpl implements ChangeableSpecificLocationEntry {// 设置后可以更改的具体位置
	private Location specificLocation;
	private Timeslot timeslot;

	public ChangeableSpecificLocationEntryImpl(Location specificLocation, Timeslot timeslot) {
		super();
		this.specificLocation = specificLocation;
		this.timeslot = timeslot;
	}

	@Override
	public void ChangeLocation(String location) {
		// TODO Auto-generated method stub
		specificLocation = new Location(location, false);
	}

	@Override
	public Location getLocation() {
		return specificLocation.clone();
	}

	@Override
	public Timeslot getTimeslot() {
		// TODO Auto-generated method stub
		return timeslot.clone();
	}

	@Override
	public Boolean onehourDatas(Date date, Location nowLocation) {
		// TODO Auto-generated method stub
		if (Math.abs((timeslot.getStartDate().getTime() - date.getTime())) <= 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

}