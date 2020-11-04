package APIs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Board.CourseBoard;
import Board.FlightBoard;
import Board.TrainBoard;
import DelegateClass.BlockedStateEntryImpl;
import DelegateClass.MultipleDustinguishResourseEntryImpl;
import DelegateClass.UnblockedStateEntryImpl;
import DelegateInterface.PlanningEntry;
import Entry.CourseEntry;
import Entry.FlightEntry;
import Entry.TrainEntry;
import Factory.CourseFactory;
import Factory.FlightFactory;
import Factory.TrainFactory;
import LocationAndTimeslot.Location;
import PlanningEntryCollection.CoursePlanningEntryCollection;
import PlanningEntryCollection.FlightPlanningEntryCollection;
import PlanningEntryCollection.TrainPlanningEntryCollection;
import Resourse.Plane;
import Resourse.Teacher;
import Resourse.TrainCarriage;

public class facadePlanningEntry {
	private static FlightEntry<Plane> flightEntry = new FlightEntry<Plane>(null, new UnblockedStateEntryImpl(), "1");
	private static List<TrainCarriage> list = new ArrayList<TrainCarriage>();
	private static TrainEntry<TrainCarriage> trainEntry = new TrainEntry<TrainCarriage>(null,
			new BlockedStateEntryImpl(), new MultipleDustinguishResourseEntryImpl<TrainCarriage>(list), "2");
	private static CourseEntry<Teacher> courseEntry = new CourseEntry<Teacher>(null, new UnblockedStateEntryImpl(),
			"3");
	private static FlightBoard flightBoard;
	private static TrainBoard trainBoard;
	private static CourseBoard courseBoard;
	private static final FlightPlanningEntryCollection flightPlanningEntryCollection = new FlightPlanningEntryCollection();
	private static final TrainPlanningEntryCollection trainPlanningEntryCollection = new TrainPlanningEntryCollection();
	private static final CoursePlanningEntryCollection coursePlanningEntryCollection = new CoursePlanningEntryCollection();

	// �����µļƻ���
	public static void CreateNewFlightEntry(String beginLocationString, String endLocationString, String name,
			String startTime, String endTime) {
		new FlightFactory();
		flightPlanningEntryCollection
				.add(FlightFactory.emptyEntry(beginLocationString, endLocationString, name, startTime, endTime));
		update("flight");
	}

	public static void CreateNewTrainEntry(String name, List<String> locationList, List<String> beginTimeList,
			List<String> endTimeList) {
		trainPlanningEntryCollection.add(TrainFactory.emptyEntry(name, locationList, beginTimeList, endTimeList));
		update("train");
	}

	public static CourseEntry<Teacher> CreateNewCrouseEntry(String name, String classroomString, String startTime,
			String endTime) {
		new CourseFactory();
		return CourseFactory.emptyEntry(name, classroomString, startTime, endTime);
	}

	// �ɻ�������Դ
	public static Boolean flightAssignResourse(String name, String planeNumber, String planeModelNumber,
			String seatNumber, String planeAge) {
		Boolean flagBoolean = flightPlanningEntryCollection.AssignResourse(name, planeNumber, planeModelNumber,
				Integer.parseInt(seatNumber), Double.parseDouble(planeAge));
		update("flight");
		return flagBoolean;
	}

	// ��������Դ
	public static Boolean trainAddResourse(String name, String number, String type, String peopleNumber, String data) {
		Boolean flagBoolean = trainPlanningEntryCollection.addResourse(name, number, type, peopleNumber, data);
		update("train");
		return flagBoolean;
	}

	// ���Ƴ���Դ
	public static Boolean trainRemoveResourse(String name, String number) {
		Boolean flagBoolean = trainPlanningEntryCollection.removeResourse(name, number);
		update("train");
		return flagBoolean;
	}

	// �γ̷�����Դ
	public static Boolean courseAssignResourse(String name, String teachername, String gender, String iDnumber,
			String teacherTitle) {
		Boolean flagBoolean = coursePlanningEntryCollection.AssignResourse(name, teachername, iDnumber, gender,
				teacherTitle);
		update("course");
		return flagBoolean;
	}

	// �γ̸����Ͽεص�
	public static Boolean courseChangeLocation(String name, String location) {
		return coursePlanningEntryCollection.courseChangeLocation(name, location);
	}

	// ȡ���ƻ���
	public static void cancel(String type, String name) {
		switch (type) {
		case "flight":
			flightPlanningEntryCollection.cancel(name);
			update("flight");
			break;
		case "train":
			trainPlanningEntryCollection.cancel(name);
			update("train");
			break;
		case "course":
			coursePlanningEntryCollection.cancel(name);
			update("course");
			break;
		}
	}

	// ��ʼ�ƻ���
	public static void begin(String type, String name) {
		switch (type) {
		case "flight":
			flightPlanningEntryCollection.begin(name);
			update("flight");
			break;
		case "train":
			trainPlanningEntryCollection.begin(name);
			update("train");
			break;
		case "course":
			coursePlanningEntryCollection.begin(name);
			update("course");
			break;
		}
	}

	// �����ƻ���
	public static void end(String type, String name) {
		switch (type) {
		case "flight":
			flightPlanningEntryCollection.end(name);
			update("flight");
			break;
		case "train":
			trainPlanningEntryCollection.end(name);
			update("train");
			break;
		case "course":
			coursePlanningEntryCollection.end(name);
			update("course");
			break;
		}
	}

	// �� �����ƻ���
	public static void blocked(String name) {
		trainPlanningEntryCollection.blocked(name);
		update("train");
	}

	// ������Ϣ��ʱ��Ϊ����ʱ��
	public static void BoardSetInputTime(String type, String location, String time) {
		switch (type) {
		case "flight":
			flightBoard = new FlightBoard(flightPlanningEntryCollection, new Location(location, true), time);
			flightBoard.setTable();
			break;
		case "train":
			trainBoard = new TrainBoard(trainPlanningEntryCollection, new Location(location, true), time);
			trainBoard.setTable();
			break;
		case "course":
			courseBoard = new CourseBoard(coursePlanningEntryCollection, new Location(location, false), time);
			update("course");
			break;
		}
	}

	// ����ʱ��Ϊϵͳʱ��
	public static void BoardSetSystemTime(String type, String location) {
		Calendar calendar = Calendar.getInstance();
		Date date = (Date) calendar.getTime();
		switch (type) {
		case "flight":
			flightBoard = new FlightBoard(flightPlanningEntryCollection, new Location(location, true),
					new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
			flightBoard.setTable();
			break;
		case "train":
			trainBoard = new TrainBoard(trainPlanningEntryCollection, new Location(location, true),
					new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
			trainBoard.setTable();
		case "course":
			courseBoard = new CourseBoard(coursePlanningEntryCollection, new Location(location, false),
					new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
			courseBoard.setTable();
			break;
		}
	}

	// ��ѯ�ƻ���״̬
	public static String getState(String type, String name) {
		switch (type) {
		case "flight":
			return flightPlanningEntryCollection.getState(name);
		case "train":
			return trainPlanningEntryCollection.getState(name);
		case "course":
			return coursePlanningEntryCollection.getState(name);
		}
		return null;
	}

	// ������Ϣ��
	private static void update(String type) {
		switch (type) {
		case "flight":
			flightBoard.Update(flightPlanningEntryCollection);
			break;
		case "train":
			trainBoard.Update(trainPlanningEntryCollection);
			break;
		case "course":
			courseBoard.Update(coursePlanningEntryCollection);
		}
	}

	// ��ѯĳ���ƻ���
	public static String seek(String type, String name) {
		switch (type) {
		case "flight":
			return flightPlanningEntryCollection.seek(name);
		case "train":
			return trainPlanningEntryCollection.seek(name);
		case "course":
			return coursePlanningEntryCollection.seek(name);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <R> boolean checkLocationConflict(List<? extends PlanningEntry<R>> entries) { // ���һ��ƻ���֮���Ƿ����λ�ö�ռ��ͻ
		if (!entries.isEmpty()) {
			if (entries.get(0).getClass().getSimpleName().equals(flightEntry.getClass().getSimpleName())) {
				return false;
			} else if (entries.get(0).getClass().getSimpleName().equals(trainEntry.getClass().getSimpleName())) {
				return false;
			} else if (entries.get(0).getClass().getSimpleName().equals(courseEntry.getClass().getSimpleName())) {
				List<CourseEntry<Teacher>> copyList = new ArrayList<CourseEntry<Teacher>>();
				for (PlanningEntry<R> x : entries) {
					copyList.add((CourseEntry<Teacher>) x);
				}
				return coursePlanningEntryCollection.checkLocationConflict(copyList);
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <R> boolean checkResourceExclusiveConflict(List<? extends PlanningEntry<R>> entries) {
		if (!entries.isEmpty()) {
			if (entries.get(0).getClass().getSimpleName().equals(flightEntry.getClass().getSimpleName())) {
				List<FlightEntry<Plane>> copyList = new ArrayList<FlightEntry<Plane>>();
				for (PlanningEntry<R> x : entries) {
					copyList.add((FlightEntry<Plane>) x);
				}
				return flightPlanningEntryCollection.checkResourceExclusiveConflict(copyList);
			} else if (entries.get(0).getClass().getSimpleName().equals(trainEntry.getClass().getSimpleName())) {
				List<TrainEntry<TrainCarriage>> copyList = new ArrayList<TrainEntry<TrainCarriage>>();
				for (PlanningEntry<R> x : entries) {
					copyList.add((TrainEntry<TrainCarriage>) x);
				}
				return trainPlanningEntryCollection.checkResourceExclusiveConflict(copyList);
			} else if (entries.get(0).getClass().getSimpleName().equals(courseEntry.getClass().getSimpleName())) {

				return true;
			}
		}
		return false;
	}

	public static String findPreEntryPerResource(String Number, String name, String type) {
		switch (type) {
		case "flight":
			if (!flightPlanningEntryCollection.getList().isEmpty()) {
				String zString = "";
				FlightEntry<Plane> e = null;
				Plane r = null;
				Iterator<FlightEntry<Plane>> iterator = flightPlanningEntryCollection.startTimeIterator();
				while (iterator.hasNext()) {
					FlightEntry<Resourse.Plane> flightEntry = (FlightEntry<Resourse.Plane>) iterator.next();
					if (flightEntry.GetPlanningEntryName().equals(name)) {
						e = flightEntry;
						r = flightEntry.getResourse();
						break;
					}
				}
				e = flightPlanningEntryCollection.findPreEntryPerResource(r, e,
						flightPlanningEntryCollection.getList());
				return zString + e.GetPlanningEntryName() + " " + e.getBeginLocation().getName() + "-"
						+ e.getEndLocation().getName() + " " + e.getTimeslot().toString() + " "
						+ e.getState().toString();
			}
			break;
		case "train":
			if (!trainPlanningEntryCollection.getList().isEmpty()) {
				String zString = "";
				TrainEntry<TrainCarriage> e = null;
				TrainCarriage r = null;
				Iterator<TrainEntry<TrainCarriage>> iterator = trainPlanningEntryCollection
						.planningEntrystartTimeIterator();
				while (iterator.hasNext()) {
					TrainEntry<TrainCarriage> trainEntry = (TrainEntry<TrainCarriage>) iterator.next();
					if (trainEntry.GetPlanningEntryName().equals(name)) {
						e = trainEntry;
						for (int i = 0; i < trainEntry.getResourses().size(); i++) {
							if (trainEntry.getResourses().get(i).getNumber().equals(Number)) {
								r = trainEntry.getResourses().get(i);
								break;
							}
						}
						break;
					}
				}
				e = trainPlanningEntryCollection.findPreEntryPerResource(r, e, trainPlanningEntryCollection.getList());
				return zString + e.GetPlanningEntryName() + " " + e.getBeginLocation().getName() + "-"
						+ e.getEndLocation().getName() + " " + e.getBeginAndEndTimeslot().toString() + " "
						+ e.getState().toString();
			}
		case "course":
			break;
		}
		return null;
	}

	public static Boolean checkPlanningEntryName(String name, String type) {
		switch (type) {
		case "flight":
			return flightPlanningEntryCollection.checkPlanningEntryName(name);
		case "train":
			return trainPlanningEntryCollection.checkPlanningEntryName(name);
		case "course":
			return coursePlanningEntryCollection.checkPlanningEntryName(name);
		}
		return null;

	}

	// ��ѯȫ��ĳһ��Դȫ���ƻ���
	public static JPanel flightJPanel(String planeNumber) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// ����������壬ʹ�ñ߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ��ͷ��������
		Vector<String> vName = new Vector<String>();
		vName.add("����ʱ��");
		vName.add("�ִ�ʱ��");
		vName.add("�����");
		vName.add("����������ص�");
		vName.add("״̬");

		// �������������
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (FlightEntry<Plane> xFlightEntry : flightPlanningEntryCollection.findEntryByResourse(planeNumber)) {
			Vector<Object> v = new Vector<Object>();
			v.add(format.format(xFlightEntry.getTimeslot().getStartDate()));
			v.add(format.format(xFlightEntry.getTimeslot().getEndDate()));
			String xString[] = xFlightEntry.GetPlanningEntryName().split(",");
			v.add(xString[1]);
			v.add(xFlightEntry.getBeginLocation().getName() + "-" + xFlightEntry.getEndLocation().getName());
			switch (xFlightEntry.getState()) {
			case WAITING:
				v.add("δ������Դ");
				break;
			case ALLOCATED:
				v.add("δ����(�ѷ�����Դ)");
				break;
			case CANCELLED:
				v.add("��ȡ��");
				break;
			case ENDED:
				v.add("�ѽ���");
				break;
			case RUNNING:
				try {
					if (xFlightEntry.getTimeslot().getEndDate().getTime() >= format.parse(flightBoard.getTime())
							.getTime()) {
						v.add("δִ�����");
					} else if (xFlightEntry.getTimeslot().getEndDate().getTime() < format.parse(flightBoard.getTime())
							.getTime()) {
						v.add("��ִ�����");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			vData.add(v);
		}

		// ����һ�����ָ�� ���������� �� ��ͷ
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		// �� ������� ��ӵ���������
		panel.add(table, BorderLayout.CENTER);
		return panel;
	}

	// ��ѯ�𳵵�ȫ���ƻ���
	public static JScrollPane trainJPanel(String number) {
		// ����������壬ʹ�ñ߽粼��
		JScrollPane jScrollPane = new JScrollPane();
		// ��ͷ��������
		Vector<String> vName = new Vector<String>();
		vName.add("ʱ��");
		vName.add("����");
		vName.add("ʼ�����յ�վ");
		vName.add("״̬");

		// �������������
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (TrainEntry<TrainCarriage> xTrainEntry : trainPlanningEntryCollection.findEntryByResourse(number)) {
			Vector<Object> v = new Vector<Object>();
			v.add(xTrainEntry.getBeginAndEndTimeslot().toString());
			String xString[] = xTrainEntry.GetPlanningEntryName().split(",");
			v.add(xString[1]);
			v.add(xTrainEntry.getBeginLocation().getName() + "-" + xTrainEntry.getEndLocation().getName());
			switch (xTrainEntry.getState()) {
			case WAITING:
				v.add("δ������Դ");
				break;
			case ALLOCATED:
				v.add("δ����(�ѷ�����Դ)");
				break;
			case CANCELLED:
				v.add("��ȡ��");
				break;
			case ENDED:
				v.add("�ѽ���");
				break;
			case BLOCKED:
				v.add("�ѹ���");
				break;
			case RUNNING:
				v.add("��������");
			}

			vData.add(v);

			// ����һ�����ָ�� ���������� �� ��ͷ
			DefaultTableModel model = new DefaultTableModel(vData, vName);
			JTable table = new JTable();
			table.setModel(model);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setPreferredScrollableViewportSize(new Dimension(520, 203));
			jScrollPane = new JScrollPane(table);
		}
		return jScrollPane;
	}

	// ��ѯ�γ̵�ȫ���ƻ���
	public static JScrollPane courseJPanel(String teacherName) {
		// ����������壬ʹ�ñ߽粼��
		JScrollPane jScrollPane = new JScrollPane();
		// ��ͷ��������
		Vector<String> vName = new Vector<String>();
		vName.add("�Ͽ�ʱ��");
		vName.add("�γ�����");
		vName.add("�Ͽν�ʦ");
		vName.add("״̬");

		// �������������
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (CourseEntry<Teacher> xCourseEntry : coursePlanningEntryCollection.findEntryByResourse(teacherName)) {
			Vector<Object> v = new Vector<Object>();
			v.add(xCourseEntry.getTimeslot().toString());
			String xString[] = xCourseEntry.GetPlanningEntryName().split(",");
			v.add(xString[1]);
			v.add(xCourseEntry.getResourse().getName());
			switch (xCourseEntry.getState()) {
			case WAITING:
				v.add("δ������Դ");
				break;
			case ALLOCATED:
				v.add("δ����(�ѷ�����Դ)");
				break;
			case CANCELLED:
				v.add("��ȡ��");
				break;
			case ENDED:
				v.add("�ѽ���");
				break;
			case RUNNING:
				v.add("��������");
				break;
			default:
				break;
			}
			vData.add(v);
		}

		// ����һ�����ָ�� ���������� �� ��ͷ
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
		jScrollPane.add(table.getTableHeader(), BorderLayout.NORTH);
		// �� ������� ��ӵ���������
		jScrollPane.add(table, BorderLayout.CENTER);
		return jScrollPane;
	}
}