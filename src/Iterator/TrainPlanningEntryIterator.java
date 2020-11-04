package Iterator;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Entry.TrainEntry;

public class TrainPlanningEntryIterator<T> implements Iterator<TrainEntry<T>> {
	private final Map<TrainEntry<T>, Date> map = new HashMap<TrainEntry<T>, Date>();
	private final List<Date> startList = new LinkedList<Date>();
	private final Map<TrainEntry<T>, Boolean> flag = new HashMap<TrainEntry<T>, Boolean>();
	private int count = 0;

	public TrainPlanningEntryIterator(List<TrainEntry<T>> list) {
		super();
		for (TrainEntry<T> xEntry : list) {
			map.put(xEntry, xEntry.getBeginAndEndTimeslot().getStartDate());
			startList.add(xEntry.getBeginAndEndTimeslot().getStartDate());
			flag.put(xEntry, false);
		}
		Collections.sort(startList);
	}

	private TrainEntry<T> getKey(Date date) {
		for (TrainEntry<T> xFlightEntry : map.keySet()) {
			if (map.get(xFlightEntry).equals(date) && flag.get(xFlightEntry) == false) {
				flag.put(xFlightEntry, true);
				return xFlightEntry;
			}
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return count < startList.size();
	}

	@Override
	public TrainEntry<T> next() {
		// TODO Auto-generated method stub
		Date xDate = startList.get(count);
		count++;
		return getKey(xDate);
	}
}
