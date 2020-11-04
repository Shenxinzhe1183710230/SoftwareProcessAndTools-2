package PlanningEntryCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Entry.TrainEntry;
import Iterator.TrainPlanningEntryIterator;
import Iterator.TrainStartTimeIterator;
import LocationAndTimeslot.Location;
import Resourse.TrainCarriage;

public class TrainPlanningEntryCollection {
	private List<TrainEntry<TrainCarriage>> list = new ArrayList<TrainEntry<TrainCarriage>>();

	public List<TrainEntry<TrainCarriage>> getList() {
		return list;
	}

	public void add(TrainEntry<TrainCarriage> trainEntry) {
		list.add(trainEntry);
	}

	// �����Դ
	public Boolean addResourse(String name, String number, String type, String peopleNumber, String data) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				TrainEntry<TrainCarriage> xEntry = list.get(i).clone();
				if (!list.get(i).addResourse(new TrainCarriage(number, type, Integer.parseInt(peopleNumber), data))) {
					return false;
				}
				if (checkResourceExclusiveConflict(list)) {
					list.set(i, xEntry);
					return false;
				}
				break;
			}
		}
		return true;
	}

	// �Ƴ���Դ
	public Boolean removeResourse(String name, String number) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				if (list.get(i).getResourse(number) != null) {
					list.get(i).removeResourse(list.get(i).getResourse(number));
					return true;
				}
				break;
			}
		}
		return false;
	}

	// ��ѯĳ���ƻ���
	public String seek(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				String[] xString = list.get(i).GetPlanningEntryName().split(",");
				String string = xString[1] + " " + list.get(i).getBeginLocation().getName() + " "
						+ list.get(i).getEndLocation().getName() + " "
						+ list.get(i).getBeginAndEndTimeslot().toString();
				return string;
			}
		}
		return null;
	}

	// location�ִ���߷���ʱ����ĳһʱ��ǰ��һСʱ�ļƻ���
	public List<TrainEntry<TrainCarriage>> OnehourPlanningEntries(Date date, Location location) {
		List<TrainEntry<TrainCarriage>> list1 = new ArrayList<TrainEntry<TrainCarriage>>();
		Iterator<TrainEntry<TrainCarriage>> iterator = startTimeIterator(location);
		while (iterator.hasNext()) {
			TrainEntry<Resourse.TrainCarriage> trainEntry = (TrainEntry<Resourse.TrainCarriage>) iterator.next();
			if (trainEntry.onehourDatas(date, location)) {
				list1.add(trainEntry);
			}
		}
		return list1;
	}

	// ���ͬһ�쳵���Ƿ��ظ�
	public Boolean checkPlanningEntryName(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	// ��ʼĳ���ƻ���
	public Boolean begin(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				list.get(i).Begin();
				return true;
			}
		}
		return false;
	}

	// ȡ��ĳ���ƻ���
	public Boolean cancel(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				list.get(i).Cancel();
				return true;
			}
		}
		return false;
	}

	// ����ĳ���ƻ���
	public Boolean end(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				list.get(i).Compelete();
				return true;
			}
		}
		return false;
	}

	// ����ĳ���ƻ���
	public Boolean blocked(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				list.get(i).Blocked();
				return true;
			}
		}
		return false;
	}

	// ��ѯ�ƻ���״̬
	public String getState(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).GetPlanningEntryName().equals(name)) {
				return list.get(i).getState().toString();
			}
		}
		return null;
	}

	// �����Դ�Ƿ��ͻ,��ͻ����true
	public boolean checkResourceExclusiveConflict(List<TrainEntry<TrainCarriage>> copyList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				for (int j2 = 0; j2 < list.get(i).getResourses().size(); j2++) {
					for (int k = 0; k < list.get(j).getResourses().size(); k++) {
						if (list.get(i).getResourses().get(j2).equals(list.get(i).getResourses().get(k))) {
							if (timeSame(list.get(i), list.get(j))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	// �鿴һ���𳵵����мƻ���
	public List<TrainEntry<TrainCarriage>> findEntryByResourse(String number) {
		List<TrainEntry<TrainCarriage>> xList = new ArrayList<TrainEntry<TrainCarriage>>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).getResourses().size(); j++) {
				if (list.get(i).getResourses().get(j).getNumber().equals(number)) {
					xList.add(list.get(i));
					break;
				}
			}
		}
		return xList;
	}

	// ��ȡ�����ض���Դ��ǰ��ƻ��
	public TrainEntry<TrainCarriage> findPreEntryPerResource(TrainCarriage r, TrainEntry<TrainCarriage> e,
			List<TrainEntry<TrainCarriage>> entries) {
		TrainEntry<TrainCarriage> f = null;
		Iterator<TrainEntry<TrainCarriage>> iterator = planningEntrystartTimeIterator();
		while (iterator.hasNext()) {
			TrainEntry<TrainCarriage> trainEntry = (TrainEntry<TrainCarriage>) iterator.next();
			if (trainEntry.getBeginAndEndTimeslot().getStartDate().getTime() >= e.getBeginAndEndTimeslot()
					.getStartDate().getTime()) {
				break;
			} else {
				for (int i = 0; i < trainEntry.getResourses().size(); i++) {
					if (trainEntry.getResourses().get(i).equals(r)) {
						f = trainEntry;
						break;
					}
				}
			}
		}
		return f;
	}

	private Boolean timeSame(TrainEntry<TrainCarriage> x, TrainEntry<TrainCarriage> y) {
		if (y.getBeginAndEndTimeslot().getStartDate().getTime() >= x.getBeginAndEndTimeslot().getEndDate().getTime()) {
			return false;
		} else if (y.getBeginAndEndTimeslot().getEndDate().getTime() <= x.getBeginAndEndTimeslot().getStartDate()
				.getTime()) {
			return false;
		}
		return true;
	}

	public Iterator<TrainEntry<TrainCarriage>> startTimeIterator(Location location) {
		// TODO Auto-generated method stub
		return new TrainStartTimeIterator<TrainCarriage>(list, location);
	}

	public Iterator<TrainEntry<TrainCarriage>> planningEntrystartTimeIterator() {
		// TODO Auto-generated method stub
		return new TrainPlanningEntryIterator<TrainCarriage>(list);
	}

}
