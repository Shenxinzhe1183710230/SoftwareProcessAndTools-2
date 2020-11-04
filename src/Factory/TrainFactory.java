package Factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import DelegateClass.BlockedStateEntryImpl;
import DelegateClass.MultipleDustinguishResourseEntryImpl;
import DelegateClass.NotChangeableHaveMiddleLocationEntryImpl;
import DelegateInterface.EntryInterface.BlockedStateEntry;
import DelegateInterface.EntryInterface.NotChangeableHaveMiddleLocationEntry;
import Entry.TrainEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;
import Resourse.TrainCarriage;

public class TrainFactory {

	public static <R> TrainEntry<TrainCarriage> emptyEntry(String name, List<String> locationList,
			List<String> beginTimeList, List<String> endTimeList) {
		// TODO Auto-generated method stub
		Map<Location, Timeslot> map = new HashMap<Location, Timeslot>();
		if (locationList.size() == (beginTimeList.size() + 1) && beginTimeList.size() == endTimeList.size()) {
			Timeslot timeslot = new Timeslot(beginTimeList.get(0), endTimeList.get(endTimeList.size() - 1));
			for (int i = 1; i < locationList.size() - 1; i++) {
				map.put(new Location(locationList.get(i), true),
						new Timeslot(beginTimeList.get(i), endTimeList.get(i - 1)));
			}
			NotChangeableHaveMiddleLocationEntry nchmle = new NotChangeableHaveMiddleLocationEntryImpl(
					new Location(locationList.get(0), true),
					new Location(locationList.get(locationList.size() - 1), true), timeslot, map);
			BlockedStateEntry bse = new BlockedStateEntryImpl();
			List<TrainCarriage> list = new ArrayList<TrainCarriage>();
			return new TrainEntry<TrainCarriage>(nchmle, bse,
					new MultipleDustinguishResourseEntryImpl<TrainCarriage>(list), name);
		}
		return null;
	}

}
