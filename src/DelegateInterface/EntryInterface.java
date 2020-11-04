package DelegateInterface;

import java.util.Date;
import java.util.List;
import java.util.Map;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public interface EntryInterface {

	// λ�ýӿ�
	public interface ChangeableSpecificLocationEntry {// ���ú���Ը��ĵľ���λ�õĽӿ�
		/**
		 * �ı���������λ��
		 * 
		 * @param location ��Ҫ�ı�ɵ�����
		 */
		public void ChangeLocation(String location);// ����λ����Ϣ

		/**
		 * �õ������ַ
		 * 
		 * @return Other.Location���͵ĵ�ַ
		 */
		public Location getLocation();

		public Timeslot getTimeslot();

		public Boolean onehourDatas(Date date, Location nowLocation);

	}

	public interface NotChangeableHaveMiddleLocationEntry {// ���ú󲻿��Ը��ĵ����м�λ�õĽӿ�
		public Map<Location, Timeslot> getLocationAndTime();

		public Location getBeginLocation();

		public Location getEndLocation();

		public Boolean isMiddleLocation(Location nowLocation);

		public Timeslot getBeginAndEndTimeslot();

		// �Ƿ���dataһСʱ֮��
		public Boolean onehourDatas(Date date, Location nowLocation);
	}

	public interface NotChangeableOneBeginAndEndLocationEntry {// ���ú󲻿��Ը��ĵ�һ������յ�λ�õĽӿ�
		public Location getBeginLocation();

		public Location getEndLocation();

		public Timeslot getTimeslot();

		// �жϿ�ʼʱ���Ƿ���dataһСʱ֮ǰ֮��
		/**
		 * ����ʱ����data֮ǰ
		 * 
		 * @param date
		 * @return
		 */
		public Boolean beforeBeginDatas(Date date);

		// �жϽ���ʱ���Ƿ���dataһСʱ֮ǰ֮��
		/**
		 * �ִ�ʱ����data֮��
		 * 
		 * @param date
		 * @return
		 */
		public Boolean afterEndDatas(Date date);
	}

	// ��Դ�ӿ�
	public interface SingleDistinguishleResourceEntry<R> {// ������������Դ�Ľӿ�
		public R getResourse();
	}

	public interface MultipleDustinguishResourseEntry<R> {// ����������������Դ�Ľӿ�

		public List<R> getResourses();

		public Boolean addResourse(R r);

		public void removeResourse(R r);

		public R getResourse(String trainNumber);

		public Boolean resourseSame();
	}

	// blocked�ӿ�
	public interface BlockedStateEntry extends EntryState {// ״̬��blocked�Ľӿ�

		public void Blocked();

	}

	public interface UnblockedStateEntry extends EntryState {// ״̬����blocked�Ľӿ�
	}

	public interface FlightPlanningEntry<R>
			extends NotChangeableOneBeginAndEndLocationEntry, UnblockedStateEntry, SingleDistinguishleResourceEntry<R> {// �ɻ�entry�Ľӿ�
		public void AssignResourse(R r);
	}

	public interface TrainPlanningEntry<R>
			extends NotChangeableHaveMiddleLocationEntry, MultipleDustinguishResourseEntry<R>, BlockedStateEntry {// ��Entry�Ľӿ�
	}

	public interface CoursePlanningEntry<R>
			extends ChangeableSpecificLocationEntry, UnblockedStateEntry, SingleDistinguishleResourceEntry<R> {// �γ�Entry�Ľӿ�
		public void AssignResourse(R r);
	}
}
