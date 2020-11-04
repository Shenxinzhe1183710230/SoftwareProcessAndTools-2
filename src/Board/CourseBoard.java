package Board;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Entry.CourseEntry;
import LocationAndTimeslot.Location;
import PlanningEntryCollection.CoursePlanningEntryCollection;
import Resourse.Teacher;

public class CourseBoard extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private static final long serialVersionUID = 1L;
	private Location location;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Date date;// 当前时间
	private CoursePlanningEntryCollection coursePlanningEntryCollection;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CourseBoard(CoursePlanningEntryCollection coursePlanningEntryCollection, Location location,
			String nowDataString) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		try {
			setDate(nowDataString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.location = location;
		this.coursePlanningEntryCollection = coursePlanningEntryCollection;
		getContentPane().setLayout(null);
		TimeUpdate();
		setVisible(true);

	}

	public void Update(CoursePlanningEntryCollection coursePlanningEntryCollection) {
		this.coursePlanningEntryCollection = coursePlanningEntryCollection;
		setTable();
	}

	private void TimeUpdate() {
		Timer timer = new Timer();// 时间刷新
		timer.schedule(new RemindTask(), 60000);
	}

	public void setTable() {
		setPreferredSize(new Dimension(750, 600));
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(29, 0, 675, 37);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u65F6\u95F4");
		lblNewLabel.setBounds(128, 15, 54, 15);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(192, 10, 137, 21);
		textField.setText(getTime());
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("教室");
		lblNewLabel_1.setBounds(352, 15, 33, 15);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(395, 10, 91, 21);
		textField_1.setText(location.getName());
		panel.add(textField_1);
		textField_1.setColumns(10);

		JScrollPane scrollPane = new JScrollPane(setLeaveTable());
		scrollPane.setBounds(22, 65, 702, 475);
		contentPane.add(scrollPane);

		// 设置窗口属性
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel setLeaveTable() {
		// 创建内容面板，使用边界布局
		JPanel panel = new JPanel(new BorderLayout());

		// 表头（列名）
		Vector<String> vName = new Vector<String>();
		vName.add("上课时间");
		vName.add("课程名称");
		vName.add("上课教师");
		vName.add("状态");

		// 表格所有行数据
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (CourseEntry<Teacher> xCourseEntry : coursePlanningEntryCollection.OnehourPlanningEntries(date, location)) {
			if (xCourseEntry.onehourDatas(date, location)) {
				Vector<Object> v = new Vector<Object>();
				v.add(format.format(xCourseEntry.getTimeslot().getStartDate()));
				String xString[] = xCourseEntry.GetPlanningEntryName().split(",");
				v.add(xString[1]);
				v.add(xCourseEntry.getResourse().getName());
				switch (xCourseEntry.getState()) {
				case WAITING:
					v.add("未分配资源");
					break;
				case ALLOCATED:
					v.add("未运行(已分配资源)");
					break;
				case CANCELLED:
					v.add("已取消");
					break;
				case ENDED:
					v.add("已结束");
					break;
				case RUNNING:
					if (xCourseEntry.getTimeslot().getStartDate().getTime() >= date.getTime()) {
						v.add("即将上课");
					} else if (xCourseEntry.getTimeslot().getStartDate().getTime() < date.getTime()
							&& date.getTime() < xCourseEntry.getTimeslot().getEndDate().getTime()) {
						v.add("正在上课");
					} else if (xCourseEntry.getTimeslot().getStartDate().getTime() < date.getTime()) {
						v.add("已上完(正在运行，未结束)");
					}
					break;
				default:
					break;
				}
				vData.add(v);
			}
		}

		// 创建一个表格，指定 所有行数据 和 表头
		DefaultTableModel model = new DefaultTableModel(vData, vName);
		JTable table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// 把 表头 添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		// 把 表格内容 添加到容器中心
		panel.add(table, BorderLayout.CENTER);
		return panel;
	}

	private void setDate(String nowDataString) throws ParseException {
		date = format.parse(nowDataString);
	}

	public String getTime() {
		return format.format(date);
	}

	private class RemindTask extends TimerTask {
		public void run() {
			date.setTime(date.getTime() + 60000);
			textField.setText(getTime());
			setTable();
			TimeUpdate();
		}
	}
}