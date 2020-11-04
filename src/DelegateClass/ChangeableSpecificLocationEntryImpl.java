package DelegateClass;

//��B3��֧�Ͻ����޸�
//��R4�����Ͻ����޸�

import java.util.Date;

import DelegateInterface.EntryInterface.ChangeableSpecificLocationEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class ChangeableSpecificLocationEntryImpl implements ChangeableSpecificLocationEntry {// ���ú���Ը��ĵľ���λ��
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