package Entry;

import java.util.Date;

import DelegateClass.CommonPlanningEntryImpl;
import DelegateClass.SingleDistinguishleResourceEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.FlightPlanningEntry;
import DelegateInterface.EntryInterface.NotChangeableOneBeginAndEndLocationEntry;
import DelegateInterface.EntryInterface.SingleDistinguishleResourceEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public class FlightEntry<R> extends CommonPlanningEntryImpl<R> implements FlightPlanningEntry<R>, Cloneable {
	private final NotChangeableOneBeginAndEndLocationEntry ncobaele;
	private SingleDistinguishleResourceEntry<R> sdre;
	private Boolean flagBoolean = false;

	public FlightEntry(NotChangeableOneBeginAndEndLocationEntry ncobaele, EntryState use, String name) {
		super(name, use);
		this.ncobaele = ncobaele;
	}

	@Override
	public Location getBeginLocation() {
		// TODO Auto-generated method stub
		return ncobaele.getBeginLocation();
	}

	@Override
	public Location getEndLocation() {
		// TODO Auto-generated method stub
		return ncobaele.getEndLocation();
	}

	@Override
	public R getResourse() {
		// TODO Auto-generated method stub
		if (sdre != null) {
			return sdre.getResourse();
		}
		return null;
	}

	@Override
	public Timeslot getTimeslot() {
		// TODO Auto-generated method stub
		return ncobaele.getTimeslot();
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
	public state getState() {
		// TODO Auto-generated method stub
		return super.getEntryState().getState();
	}

	@Override
	public Boolean setState(state myState) {
		return super.getEntryState().setState(myState);
	}

	@Override
	public void AssignResourse(R r) {// 分配航班
		// TODO Auto-generated method stub
		if (!flagBoolean) {
			this.sdre = new SingleDistinguishleResourceEntryImpl<R>(r);
			flagBoolean = true;
			super.getEntryState().setState(state.ALLOCATED);
		} else {
			System.out.println("已经分配过资源");
		}
	}

	@Override
	public Boolean beforeBeginDatas(Date date) {
		// TODO Auto-generated method stub
		return ncobaele.beforeBeginDatas(date);
	}

	@Override
	public Boolean afterEndDatas(Date date) {
		// TODO Auto-generated method stub
		return ncobaele.afterEndDatas(date);
	}

	public FlightEntry<R> clone() {
		FlightEntry<R> flightEntry = new FlightEntry<R>(ncobaele, new UnblockedStateEntryImpl(),
				GetPlanningEntryName());
		return flightEntry;
	}

}
