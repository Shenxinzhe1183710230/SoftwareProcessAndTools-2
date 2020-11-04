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
	private Date date;// ��ǰʱ��
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
		Timer timer = new Timer();// ʱ��ˢ��
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

		JLabel lblNewLabel_1 = new JLabel("����");
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

		// ���ô�������
		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel setLeaveTable() {
		// ����������壬ʹ�ñ߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ��ͷ��������
		Vector<String> vName = new Vector<String>();
		vName.add("�Ͽ�ʱ��");
		vName.add("�γ�����");
		vName.add("�Ͽν�ʦ");
		vName.add("״̬");

		// �������������
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
					if (xCourseEntry.getTimeslot().getStartDate().getTime() >= date.getTime()) {
						v.add("�����Ͽ�");
					} else if (xCourseEntry.getTimeslot().getStartDate().getTime() < date.getTime()
							&& date.getTime() < xCourseEntry.getTimeslot().getEndDate().getTime()) {
						v.add("�����Ͽ�");
					} else if (xCourseEntry.getTimeslot().getStartDate().getTime() < date.getTime()) {
						v.add("������(�������У�δ����)");
					}
					break;
				default:
					break;
				}
				vData.add(v);
			}
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