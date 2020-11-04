package PlanningEntryCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import Entry.CourseEntry;
import Iterator.CourseStartTimeIterator;
import LocationAndTimeslot.Location;
import Resourse.Teacher;

public class CoursePlanningEntryCollection {
	private List<CourseEntry<Teacher>> list = new ArrayList<CourseEntry<Teacher>>();

	public List<CourseEntry<Teacher>> getList() {
		List<CourseEntry<Teacher>> copyList = new ArrayList<CourseEntry<Teacher>>();
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			copyList.add(courseEntry.clone());
		}
		return copyList;
	}

	// ��ѯĳ���ƻ���
	public String seek(String name) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				String[] xString = courseEntry.GetPlanningEntryName().split(",");
				String string = xString[1] + " " + courseEntry.getLocation().getName() + " " + " "
						+ courseEntry.getTimeslot().toString();
				return string;
			}
		}
		return null;
	}

	public void add(CourseEntry<Teacher> planningEntry) {
		if (!checkResourceExclusiveConflict(list)) {
			list.add(planningEntry);
		}
	}

	// �����Ͽεص�
	public Boolean courseChangeLocation(String name, String location) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Resourse.Teacher> courseEntry = (CourseEntry<Resourse.Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				courseEntry.ChangeLocation(location);
				return true;
			}
		}
		return false;
	}

	// ��ʦlocationĳһʱ��ǰ��һСʱ�����мƻ���
	public List<CourseEntry<Teacher>> OnehourPlanningEntries(Date date, Location location) {
		List<CourseEntry<Teacher>> list1 = new ArrayList<CourseEntry<Teacher>>();
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.onehourDatas(date, location)) {
				list1.add(courseEntry);
			}
		}
		return list1;
	}

	// Ϊ�ƻ��������Դ
	public Boolean AssignResourse(String name, String teachername, String iDnumber, String gender,
			String teacherTitle) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(teachername)) {
				CourseEntry<Teacher> xEntry = list.get(i).clone();
				list.get(i).AssignResourse(new Teacher(iDnumber, teachername, gender, teacherTitle));
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
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				courseEntry.Begin();
				return true;
			}
		}
		return false;
	}

	// ȡ��ĳ���ƻ���
	public Boolean cancel(String name) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				courseEntry.Cancel();
				return true;
			}
		}
		return false;
	}

	// ����ĳ���ƻ���
	public Boolean end(String name) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				courseEntry.Compelete();
				return true;
			}
		}
		return false;
	}

	// ��ѯ�ƻ���״̬
	public String getState(String name) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.GetPlanningEntryName().equals(name)) {
				return courseEntry.getState().toString();
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
	public boolean checkResourceExclusiveConflict(List<CourseEntry<Teacher>> entries) {// ���һ��ƻ���֮���Ƿ������Դ��ռ��ͻ;
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

	// ���ڳ�ͻ����true
	public boolean checkLocationConflict(List<CourseEntry<Teacher>> entries) {// ���һ��ƻ���֮���Ƿ����λ�ó�ͻ;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getLocation().equals(list.get(j).getLocation())) {
					if (timeSame(list.get(i), list.get(j))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// ��ȡ�����ض���Դ��ǰ��ƻ��
	public CourseEntry<Teacher> findPreEntryPerResource(Teacher r, CourseEntry<Teacher> e,
			List<CourseEntry<Teacher>> entries) {
		CourseEntry<Teacher> f = null;
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.getTimeslot().getStartDate().getTime() >= e.getTimeslot().getStartDate().getTime()) {
				break;
			} else if (courseEntry.getResourse().equals(r)) {
				f = courseEntry;
			}
		}
		return f;
	}

	// �鿴һ���ɻ������мƻ���
	public List<CourseEntry<Teacher>> findEntryByResourse(String name) {
		List<CourseEntry<Teacher>> xList = new ArrayList<CourseEntry<Teacher>>();
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
			if (courseEntry.getResourse().getName().equals(name)) {
				xList.add(courseEntry);
			}
		}
		return xList;
	}

	// ���ͬһ�캽����Ƿ��ظ�
	public Boolean checkPlanningEntryName(String name) {
		Iterator<CourseEntry<Teacher>> iterator = startTimeIterator();
		while (iterator.hasNext()) {
			CourseEntry<Teacher> flightEntry = (CourseEntry<Teacher>) iterator.next();
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
	private Boolean timeSame(CourseEntry<Teacher> x, CourseEntry<Teacher> y) {
		if (y.getTimeslot().getStartDate().getTime() >= x.getTimeslot().getEndDate().getTime()) {
			return false;
		} else if (y.getTimeslot().getEndDate().getTime() <= x.getTimeslot().getStartDate().getTime()) {
			return false;
		}
		return true;
	}

	public Iterator<CourseEntry<Teacher>> startTimeIterator() {
		// TODO Auto-generated method stub
		return new CourseStartTimeIterator<Teacher>(list);
	}

}
