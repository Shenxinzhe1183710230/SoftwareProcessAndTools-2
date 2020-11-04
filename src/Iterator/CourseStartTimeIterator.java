package Iterator;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import Entry.CourseEntry;

public class CourseStartTimeIterator<T> implements Iterator<CourseEntry<T>> {
	private final Map<CourseEntry<T>, Date> map = new HashMap<CourseEntry<T>, Date>();
	private final List<Date> startDates = new LinkedList<Date>();
	private final Map<CourseEntry<T>, Boolean> flag = new HashMap<CourseEntry<T>, Boolean>();
	private int count = 0;

	public CourseStartTimeIterator(List<CourseEntry<T>> list) {
		super();
		for (CourseEntry<T> xEntry : list) {
			map.put(xEntry, xEntry.getTimeslot().getStartDate());
			startDates.add(xEntry.getTimeslot().getStartDate());
			flag.put(xEntry, false);
		}
		Collections.sort(startDates);
	}

	private CourseEntry<T> getKey(Date date) {
		for (CourseEntry<T> xCourseEntry : map.keySet()) {
			if (map.get(xCourseEntry).equals(date) && flag.get(xCourseEntry) == false) {
				flag.put(xCourseEntry, true);
				return xCourseEntry;
			}
		}
		return null;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return count < startDates.size();
	}

	@Override
	public CourseEntry<T> next() {
		// TODO Auto-generated method stub
		Date xDate = startDates.get(count);
		count++;
		return getKey(xDate);
	}

}
