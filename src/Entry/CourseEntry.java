package Entry;

import java.util.Date;

import DelegateClass.CommonPlanningEntryImpl;
import DelegateClass.SingleDistinguishleResourceEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.ChangeableSpecificLocationEntry;
import DelegateInterface.EntryInterface.CoursePlanningEntry;
import DelegateInterface.EntryInterface.SingleDistinguishleResourceEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class CourseEntry<R> extends CommonPlanningEntryImpl<R> implements CoursePlanningEntry<R> {
	private final ChangeableSpecificLocationEntry csle;
	private SingleDistinguishleResourceEntry<R> sdre;
	private boolean flagBoolean = false;

	public CourseEntry(ChangeableSpecificLocationEntry csle, EntryState use, String name) {
		super(name, use);
		this.csle = csle;
	}

	@Override
	public void ChangeLocation(String location) {
		// TODO Auto-generated method stub
		csle.ChangeLocation(location);
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return csle.getLocation();
	}

	@Override
	public Timeslot getTimeslot() {
		// TODO Auto-generated method stub
		return csle.getTimeslot();
	}

	@Override
	public R getResourse() {
		// TODO Auto-generated method stub
		return sdre.getResourse();
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
	public state getState() {
		// TODO Auto-generated method stub
		return super.getEntryState().getState();
	}

	@Override
	public Boolean setState(state myState) {
		return super.getEntryState().setState(myState);
	}

	@Override
	public void AssignResourse(R r) {
		// TODO Auto-generated method stub
		if (!flagBoolean) {
			this.sdre = new SingleDistinguishleResourceEntryImpl<R>(r);
		}
	}

	public CourseEntry<R> clone() {
		CourseEntry<R> courseEntry = new CourseEntry<R>(csle, new UnblockedStateEntryImpl(), GetPlanningEntryName());
		return courseEntry;
	}

	@Override
	public Boolean onehourDatas(Date date, Location nowLocation) {
		// TODO Auto-generated method stub
		return csle.onehourDatas(date, nowLocation);
	}

}
