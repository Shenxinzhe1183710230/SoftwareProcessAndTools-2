package Iterator;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Entry.FlightEntry;

public class FlightEndTimeIterator<T> implements Iterator<FlightEntry<T>> {
	private final Map<FlightEntry<T>, Date> map = new HashMap<FlightEntry<T>, Date>();
	private final List<Date> endDates = new LinkedList<Date>();
	private final Map<FlightEntry<T>, Boolean> flag = new HashMap<FlightEntry<T>, Boolean>();
	private int count = 0;

	public FlightEndTimeIterator(List<FlightEntry<T>> list) {
		super();
		for (FlightEntry<T> xEntry : list) {
			map.put(xEntry, xEntry.getTimeslot().getEndDate());
			endDates.add(xEntry.getTimeslot().getEndDate());
			flag.put(xEntry, false);
		}
		Collections.sort(endDates);
	}

	private FlightEntry<T> getKey(Date date) {
		for (FlightEntry<T> xFlightEntry : map.keySet()) {
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
		return count < endDates.size();
	}

	@Override
	public FlightEntry<T> next() {
		// TODO Auto-generated method stub
		Date xDate = endDates.get(count);
		count++;
		return getKey(xDate);
	}

}
