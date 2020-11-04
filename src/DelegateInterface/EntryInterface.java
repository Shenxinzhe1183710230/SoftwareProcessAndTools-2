package DelegateInterface;

import java.util.Date;
import java.util.List;
import java.util.Map;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;

public interface EntryInterface {

	// 位置接口
	public interface ChangeableSpecificLocationEntry {// 设置后可以更改的具体位置的接口
		/**
		 * 改变这个具体的位置
		 * 
		 * @param location 想要改变成的名字
		 */
		public void ChangeLocation(String location);// 更改位置信息

		/**
		 * 得到这个地址
		 * 
		 * @return Other.Location类型的地址
		 */
		public Location getLocation();

		public Timeslot getTimeslot();

		public Boolean onehourDatas(Date date, Location nowLocation);

	}

	public interface NotChangeableHaveMiddleLocationEntry {// 设置后不可以更改的有中间位置的接口
		public Map<Location, Timeslot> getLocationAndTime();

		public Location getBeginLocation();

		public Location getEndLocation();

		public Boolean isMiddleLocation(Location nowLocation);

		public Timeslot getBeginAndEndTimeslot();

		// 是否在data一小时之中
		public Boolean onehourDatas(Date date, Location nowLocation);
	}

	public interface NotChangeableOneBeginAndEndLocationEntry {// 设置后不可以更改的一个起点终点位置的接口
		public Location getBeginLocation();

		public Location getEndLocation();

		public Timeslot getTimeslot();

		// 判断开始时间是否在data一小时之前之后
		/**
		 * 出发时间在data之前
		 * 
		 * @param date
		 * @return
		 */
		public Boolean beforeBeginDatas(Date date);

		// 判断结束时间是否在data一小时之前之后
		/**
		 * 抵达时间在data之后
		 * 
		 * @param date
		 * @return
		 */
		public Boolean afterEndDatas(Date date);
	}

	// 资源接口
	public interface SingleDistinguishleResourceEntry<R> {// 单个可区分资源的接口
		public R getResourse();
	}

	public interface MultipleDustinguishResourseEntry<R> {// 多个带次序可区分资源的接口

		public List<R> getResourses();

		public Boolean addResourse(R r);

		public void removeResourse(R r);

		public R getResourse(String trainNumber);

		public Boolean resourseSame();
	}

	// blocked接口
	public interface BlockedStateEntry extends EntryState {// 状态可blocked的接口

		public void Blocked();

	}

	public interface UnblockedStateEntry extends EntryState {// 状态不可blocked的接口
	}

	public interface FlightPlanningEntry<R>
			extends NotChangeableOneBeginAndEndLocationEntry, UnblockedStateEntry, SingleDistinguishleResourceEntry<R> {// 飞机entry的接口
		public void AssignResourse(R r);
	}

	public interface TrainPlanningEntry<R>
			extends NotChangeableHaveMiddleLocationEntry, MultipleDustinguishResourseEntry<R>, BlockedStateEntry {// 火车Entry的接口
	}

	public interface CoursePlanningEntry<R>
			extends ChangeableSpecificLocationEntry, UnblockedStateEntry, SingleDistinguishleResourceEntry<R> {// 课程Entry的接口
		public void AssignResourse(R r);
	}
}
