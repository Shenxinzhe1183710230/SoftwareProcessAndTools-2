package Factory;

import DelegateClass.ChangeableSpecificLocationEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.EntryState;
import DelegateInterface.EntryInterface.ChangeableSpecificLocationEntry;
import Entry.CourseEntry;
import LocationAndTimeslot.Location;
import LocationAndTimeslot.Timeslot;
import Resourse.Teacher;

public class CourseFactory {

	public static <R> CourseEntry<Teacher> emptyEntry(String name, String classroomString, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		Location specificLocation = new Location(classroomString, false);
		Timeslot timeslot = new Timeslot(startTime, endTime);
		ChangeableSpecificLocationEntry csle = new ChangeableSpecificLocationEntryImpl(specificLocation, timeslot);
		EntryState use = new UnblockedStateEntryImpl();
		return new CourseEntry<Teacher>(csle, use, name);
	}

}
