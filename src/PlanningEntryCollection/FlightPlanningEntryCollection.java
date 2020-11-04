package PlanningEntryCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import Entry.FlightEntry;
import Iterator.FlightEndTimeIterator;
import Iterator.FlightStartTimeIterator;
import LocationAndTimeslot.Location;
import Resourse.Plane;

public class FlightPlanningEntryCollection {
	private List<FlightEntry<Plane>> list = new ArrayList<FlightEntry<Plane>>();

	public List<FlightEntry<Plane>> getList() {
		List<FlightEntry<Plane>> copyList = new ArrayList<FlightEntry<Plane>>();
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			copyList.add(flightEntry.clone());
		}
		return copyList;
	}

	// 查询某个计划项
	public String seek(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				String[] xString = flightEntry.GetPlanningEntryName().split(",");
				String string = xString[1] + " " + flightEntry.getBeginLocation().getName() + " "
						+ flightEntry.getEndLocation().getName() + " " + flightEntry.getTimeslot().toString();
				return string;
			}
		}
		return null;
	}

	public void add(FlightEntry<Plane> planningEntry) {
		if (!checkResourceExclusiveConflict(list)) {
			list.add(planningEntry);
		}
	}

	public List<FlightEntry<Plane>> beforeBeginTimePlanningEntries(Date date, Location location) {// 出发时间在某一时间之前的计划项,并且出发地是location
		List<FlightEntry<Plane>> list1 = new ArrayList<FlightEntry<Plane>>();
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Plane> flightEntry = (FlightEntry<Plane>) iterator.next();
			if (flightEntry.getBeginLocation().equals(location)) {
				if (flightEntry.beforeBeginDatas(date)) {
					list1.add(flightEntry);
				}
			}
		}
		return list1;
	}

	public List<FlightEntry<Plane>> afterEndTimePlanningEntries(Date date, Location location) {// 结束时间在某一时间之后的计划项，并且抵达地是location
		List<FlightEntry<Plane>> list1 = new ArrayList<FlightEntry<Plane>>();
		Iterator<FlightEntry<Plane>> iterator = endTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Plane> flightEntry = (FlightEntry<Plane>) iterator.next();
			if (flightEntry.getEndLocation().equals(location)) {
				if (flightEntry.afterEndDatas(date)) {
					list1.add(flightEntry);
				}
			}
		}
		return list1;
	}

	// 为计划项分配资源
	public Boolean AssignResourse(String name, String planeNumber, String planeModelNumber, int seatNumber,
			double planeAge) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				FlightEntry<Plane> xEntry = list.get(i).clone();
				list.get(i).AssignResourse(new Plane(planeNumber, planeModelNumber, seatNumber, planeAge));
				if (checkResourceExclusiveConflict(list)) {
					list.set(i, xEntry);
					return false;
				}
				break;
			}
		}
		return true;
	}

	// 开始某个计划项
	public Boolean begin(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				flightEntry.Begin();
				return true;
			}
		}
		return false;
	}

	// 取消某个计划项
	public Boolean cancel(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				flightEntry.Cancel();
				return true;
			}
		}
		return false;
	}

	// 结束某个计划项
	public Boolean end(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				flightEntry.Compelete();
				return true;
			}
		}
		return false;
	}

	// 查询计划项状态
	public String getState(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				return flightEntry.getState().toString();
			}
		}
		return null;
	}

	/**
	 * 检查一组计划项之间是否存在资源独占冲突;
	 * 
	 * @param entries
	 * @return 冲突返回true；
	 */
	public boolean checkResourceExclusiveConflict(List<FlightEntry<Plane>> entries) {// 检测一组计划项之间是否存在资源独占冲突;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getResourse().equals(list.get(j).getResourse())) {
					if (timeSame(list.get(i), list.get(j))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// 提取面向特定资源的前序计划项：
	public FlightEntry<Plane> findPreEntryPerResource(Plane r, FlightEntry<Plane> e, List<FlightEntry<Plane>> entries) {
		FlightEntry<Plane> f = null;
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Plane> flightEntry = (FlightEntry<Plane>) iterator.next();
			if (flightEntry.getTimeslot().getStartDate().getTime() >= e.getTimeslot().getStartDate().getTime()) {
				break;
			} else if (flightEntry.getResourse().equals(r)) {
				f = flightEntry;
			}
		}
		return f;
	}

	// 查看一个飞机的所有计划项
	public List<FlightEntry<Plane>> findEntryByResourse(String planeNumber) {
		List<FlightEntry<Plane>> xList = new ArrayList<FlightEntry<Plane>>();
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Plane> flightEntry = (FlightEntry<Plane>) iterator.next();
			if (flightEntry.getResourse().getPlaneNumber().equals(planeNumber)) {
				xList.add(flightEntry);
			}
		}
		return xList;
	}

	// 检查同一天航班号是否重复
	public Boolean checkPlanningEntryName(String name) {
		Iterator<FlightEntry<Plane>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
			if (flightEntry.GetPlanningEntryName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否存在时间冲突
	 * 
	 * @param x
	 * @param y
	 * @return 不冲突返回false
	 */
	private Boolean timeSame(FlightEntry<Plane> x, FlightEntry<Plane> y) {
		if (y.getTimeslot().getStartDate().getTime() >= x.getTimeslot().getEndDate().getTime()) {
			return false;
		} else if (y.getTimeslot().getEndDate().getTime() <= x.getTimeslot().getStartDate().getTime()) {
			return false;
		}
		return true;
	}

	public Iterator<FlightEntry<Plane>> startTimeIterator() {
		// TODO Auto-generated method stub
		return new FlightStartTimeIterator<Plane>(list);
	}

	public Iterator<FlightEntry<Plane>> endTimeIterator() {
		// TODO Auto-generated method stub
		return new FlightEndTimeIterator<Plane>(list);
	}
}