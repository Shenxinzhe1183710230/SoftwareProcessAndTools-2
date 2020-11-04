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

	// ��ѯĳ���ƻ���
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

	public List<FlightEntry<Plane>> beforeBeginTimePlanningEntries(Date date, Location location) {// ����ʱ����ĳһʱ��֮ǰ�ļƻ���,���ҳ�������location
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

	public List<FlightEntry<Plane>> afterEndTimePlanningEntries(Date date, Location location) {// ����ʱ����ĳһʱ��֮��ļƻ�����ҵִ����location
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

	// Ϊ�ƻ��������Դ
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

	// ��ʼĳ���ƻ���
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

	// ȡ��ĳ���ƻ���
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

	// ����ĳ���ƻ���
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

	// ��ѯ�ƻ���״̬
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
	 * ���һ��ƻ���֮���Ƿ������Դ��ռ��ͻ;
	 * 
	 * @param entries
	 * @return ��ͻ����true��
	 */
	public boolean checkResourceExclusiveConflict(List<FlightEntry<Plane>> entries) {// ���һ��ƻ���֮���Ƿ������Դ��ռ��ͻ;
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

	// ��ȡ�����ض���Դ��ǰ��ƻ��
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

	// �鿴һ���ɻ������мƻ���
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

	// ���ͬһ�캽����Ƿ��ظ�
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
	 * �Ƿ����ʱ���ͻ
	 * 
	 * @param x
	 * @param y
	 * @return ����ͻ����false
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