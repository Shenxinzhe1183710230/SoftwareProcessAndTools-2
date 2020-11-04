package Iterator;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Entry.TrainEntry;
import LocationAndTimeslot.Location;

public class TrainStartTimeIterator<T> implements Iterator<TrainEntry<T>> {
	private final Map<TrainEntry<T>, Date> map = new HashMap<TrainEntry<T>, Date>();
	private final List<Date> endDates = new LinkedList<Date>();
	private final Map<TrainEntry<T>, Boolean> flag = new HashMap<TrainEntry<T>, Boolean>();
	private int count = 0;

	public TrainStartTimeIterator(List<TrainEntry<T>> list, Location location) {
		super();
		for (TrainEntry<T> xEntry : list) {
			map.put(xEntry, xEntry.getLocationAndTime().get(location).getStartDate());
			endDates.add(xEntry.getLocationAndTime().get(location).getStartDate());
			flag.put(xEntry, false);
		}
		Collections.sort(endDates);
	}

	private TrainEntry<T> getKey(Date date) {
		for (TrainEntry<T> trainEntry : map.keySet()) {
			if (map.get(trainEntry).equals(date) && flag.get(trainEntry) == false) {
				flag.put(trainEntry, true);
				return trainEntry;
			}
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return count < endDates.size();
	}

	@Override
	public TrainEntry<T> next() {
		// TODO Auto-generated method stub
		Date xDate = endDates.get(count);
		count++;
		return getKey(xDate);
	}
}
