package APIs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DelegateClass.BlockedStateEntryImpl;
import DelegateClass.MultipleDustinguishResourseEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.PlanningEntry;
import Entry.CourseEntry;
import Entry.FlightEntry;
import Entry.TrainEntry;
import Iterator.CourseStartTimeIterator;
import PlanningEntryCollection.CoursePlanningEntryCollection;
import Resourse.Plane;
import Resourse.Teacher;
import Resourse.TrainCarriage;

public class PlanningEntryAPIs {
	private static FlightEntry<Plane> flightEntry = new FlightEntry<Plane>(null, new UnblockedStateEntryImpl(), "1");
	private static List<TrainCarriage> entries = new ArrayList<TrainCarriage>();
	private static TrainEntry<TrainCarriage> trainEntry = new TrainEntry<TrainCarriage>(null,
			new BlockedStateEntryImpl(), new MultipleDustinguishResourseEntryImpl<TrainCarriage>(entries), "2");
	private static CourseEntry<Teacher> courseEntry = new CourseEntry<Teacher>(null, new UnblockedStateEntryImpl(),
			"3");

	@SuppressWarnings("unchecked")
	public static <R> boolean checkLocationConflict(List<? extends PlanningEntry<R>> entries) { // 检查一组计划项之间是否存在位置独占冲突
		if (!entries.isEmpty()) {
			if (entries.get(0).getClass().getSimpleName().equals(flightEntry.getClass().getSimpleName())) {
				return false;
			} else if (entries.get(0).getClass().getSimpleName().equals(trainEntry.getClass().getSimpleName())) {
				return false;
			} else if (entries.get(0).getClass().getSimpleName().equals(courseEntry.getClass().getSimpleName())) {
				for (int i = 0; i < entries.size(); i++) {
					for (int j = i + 1; j < entries.size(); j++) {
						CourseEntry<Teacher> xCourseEntry = (CourseEntry<Teacher>) entries.get(i);
						CourseEntry<Teacher> yCourseEntry = (CourseEntry<Teacher>) entries.get(j);
						if (xCourseEntry.getLocation().equals(yCourseEntry.getLocation())) {
							if (timeSame(xCourseEntry, yCourseEntry)) {
								return true;
							}
						}
					}
				}
				return false;
			}
		}
		return false;
	}

	private static Boolean timeSame(CourseEntry<Teacher> x, CourseEntry<Teacher> y) {
		if (y.getTimeslot().getStartDate().getTime() >= x.getTimeslot().getEndDate().getTime()) {
			return false;
		} else if (y.getTimeslot().getEndDate().getTime() <= x.getTimeslot().getStartDate().getTime()) {
			return false;
		}
		return true;
	}

	private static Boolean trainTimeSame(TrainEntry<TrainCarriage> x, TrainEntry<TrainCarriage> y) {
		if (y.getBeginAndEndTimeslot().getStartDate().getTime() >= x.getBeginAndEndTimeslot().getEndDate().getTime()) {
			return false;
		} else if (y.getBeginAndEndTimeslot().getEndDate().getTime() <= x.getBeginAndEndTimeslot().getStartDate()
				.getTime()) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <R> boolean checkResourceExclusiveConflict(List<? extends PlanningEntry<R>> entries) {
		if (!entries.isEmpty()) {
			if (entries.get(0).getClass().getSimpleName().equals(flightEntry.getClass().getSimpleName())) {

			} else if (entries.get(0).getClass().getSimpleName().equals(trainEntry.getClass().getSimpleName())) {

			} else if (entries.get(0).getClass().getSimpleName().equals(courseEntry.getClass().getSimpleName())) {
				for (int i = 0; i < entries.size(); i++) {
					for (int j = i + 1; j < entries.size(); j++) {
						CourseEntry<Teacher> xCourseEntry = (CourseEntry<Teacher>) entries.get(i);
						CourseEntry<Teacher> yCourseEntry = (CourseEntry<Teacher>) entries.get(j);
						if (xCourseEntry.getResourse().equals(yCourseEntry.getResourse())) {
							if (timeSame(xCourseEntry, yCourseEntry)) {
								return true;
							}
						}
					}
				}
				return false;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <R> PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e,
			List<PlanningEntry<R>> entries) {
		if (!entries.isEmpty()) {
			if (entries.get(0).getClass().getSimpleName().equals(flightEntry.getClass().getSimpleName())) {
			} else if (entries.get(0).getClass().getSimpleName().equals(trainEntry.getClass().getSimpleName())) {
			} else if (entries.get(0).getClass().getSimpleName().equals(courseEntry.getClass().getSimpleName())) {
				CourseEntry<Teacher> f = null;
				List<CourseEntry<Teacher>> copyList = new ArrayList<CourseEntry<Teacher>>();
				for (PlanningEntry<R> x : entries) {
					copyList.add((CourseEntry<Teacher>) x);
				}
				Iterator<CourseEntry<Teacher>> iterator = new CourseStartTimeIterator<Teacher>(copyList);
				while (iterator.hasNext()) {
					CourseEntry<Teacher> courseEntry = (CourseEntry<Teacher>) iterator.next();
					if (courseEntry.getTimeslot().getStartDate().getTime() >= ((CourseEntry<Teacher>) e).getTimeslot()
							.getStartDate().getTime()) {
						break;
					} else if (courseEntry.getResourse().equals(r)) {
						f = courseEntry;
					}
				}
				return (PlanningEntry<R>) f;
			}
		}
		return e;
	}
}
