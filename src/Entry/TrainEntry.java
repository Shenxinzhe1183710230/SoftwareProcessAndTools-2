package Entry;

import java.util.Date;
import java.util.List;
import java.util.Map;
import DelegateClass.BlockedStateEntryImpl;
import DelegateClass.CommonPlanningEntryImpl;
import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.MultipleDustinguishResourseEntry;
import DelegateInterface.EntryInterface.NotChangeableHaveMiddleLocationEntry;
import DelegateInterface.EntryInterface.TrainPlanningEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class TrainEntry<R> extends CommonPlanningEntryImpl<R> implements TrainPlanningEntry<R> {
	private final NotChangeableHaveMiddleLocationEntry nchmle;
	private MultipleDustinguishResourseEntry<R> mdre;

	public TrainEntry(NotChangeableHaveMiddleLocationEntry nchmle, EntryState bse,
			MultipleDustinguishResourseEntry<R> mdre, String name) {
		super(name, bse);
		this.nchmle = nchmle;
		this.mdre = mdre;
	}

	@Override
	public List<R> getResourses() {
		// TODO Auto-generated method stub
		return mdre.getResourses();
	}

	@Override
	public Map<Location, Timeslot> getLocationAndTime() {
		// TODO Auto-generated method stub
		return nchmle.getLocationAndTime();
	}

	@Override
	public void Begin() {
		// TODO Auto-generated method stub
		super.Begin();
	}

	@Override
	public void Cancel() {
		// TODO Auto-generated method stub
		super.Cancel();
	}

	@Override
	public void Compelete() {
		// TODO Auto-generated method stub
		super.Compelete();
	}

	@Override
	public String GetPlanningEntryName() {
		// TODO Auto-generated method stub
		return super.GetPlanningEntryName();
	}

	@Override
	public state GetNowstate() {
		// TODO Auto-generated method stub
		return super.GetNowstate();
	}

	@Override
	public void Blocked() {
		// TODO Auto-generated method stub
		super.getEntryState().setState(EntryState.state.BLOCKED);
	}

	@Override
	public state getState() {
		// TODO Auto-generated method stub
		return super.getEntryState().getState();
	}

	@Override
	public Boolean setState(state myState) {
		return super.getEntryState().setState(myState);
	}

	@Override
	public EntryState getEntryState() {
		// TODO Auto-generated method stub
		return super.getEntryState();
	}

	@Override
	public Boolean onehourDatas(Date date, Location nowLocation) {
		// TODO Auto-generated method stub
		return nchmle.onehourDatas(date, nowLocation);
	}

	@Override
	public Boolean addResourse(R r) {
		// TODO Auto-generated method stub
		super.getEntryState().setState(state.ALLOCATED);
		return mdre.addResourse(r);
	}

	@Override
	public void removeResourse(R r) {
		// TODO Auto-generated method stub
		mdre.removeResourse(r);
	}

	public TrainEntry<R> clone() {
		TrainEntry<R> trainEntry = new TrainEntry<R>(nchmle, new BlockedStateEntryImpl(), mdre, GetPlanningEntryName());
		return trainEntry;
	}

	@Override
	public Location getBeginLocation() {
		// TODO Auto-generated method stub
		return nchmle.getBeginLocation();
	}

	@Override
	public Location getEndLocation() {
		// TODO Auto-generated method stub
		return nchmle.getEndLocation();
	}

	@Override
	public Timeslot getBeginAndEndTimeslot() {
		// TODO Auto-generated method stub
		return nchmle.getBeginAndEndTimeslot();
	}

	@Override
	public Boolean isMiddleLocation(Location nowLocation) {
		// TODO Auto-generated method stub
		return nchmle.isMiddleLocation(nowLocation);
	}

	@Override
	public R getResourse(String trainNumber) {
		// TODO Auto-generated method stub
		return mdre.getResourse(trainNumber);
	}

	@Override
	public Boolean resourseSame() {
		// TODO Auto-generated method stub
		return mdre.resourseSame();
	}

}
