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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Entry.FlightEntry;
import LocationAndTimeslot.Location;
import PlanningEntryCollection.FlightPlanningEntryCollection;
import Resourse.Plane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FlightBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private Location location;
	// private JPanel contentPane = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Date date;// ��ǰʱ��
	private FlightPlanningEntryCollection xFlightPlanningEntryCollection;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FlightBoard(FlightPlanningEntryCollection FlightPlanningEntryCollection, Location location,
			String nowDataString) {
		try {
			setDate(nowDataString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.location = location;
		this.xFlightPlanningEntryCollection = FlightPlanningEntryCollection;
		TimeUpdate();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 750);
	}

	public void Update(FlightPlanningEntryCollection FlightPlanningEntryCollection) {
		this.xFlightPlanningEntryCollection = FlightPlanningEntryCollection;
		setTable();
	}

	private void TimeUpdate() {
		Timer timer = new Timer();// ʱ��ˢ��
		timer.schedule(new RemindTask(), 60000);
	}

	public void setTable() {
		setPreferredSize(new Dimension(750, 750));
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(29, 10, 675, 37);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u5F53\u524D\u65F6\u95F4");
		lblNewLabel.setBounds(80, 10, 58, 17);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(140, 8, 181, 21);
		textField.setEditable(false);
		textField.setText(getTime());
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(373, 8, 134, 21);
		textField_1.setText(location.getName());
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u673A\u573A:");
		lblNewLabel_1.setBounds(331, 10, 38, 17);
		panel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane(setLeaveTable());
		scrollPane.setBounds(29, 75, 675, 300);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane(setArriveTable());
		scrollPane_1.setBounds(29, 401, 675, 300);
		contentPane.add(scrollPane_1);

		JLabel lblNewLabel_2 = new JLabel("\u51FA\u53D1\u822A\u73ED");
		lblNewLabel_2.setBounds(347, 57, 54, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\u62B5\u8FBE\u822A\u73ED");
		lblNewLabel_3.setBounds(347, 380, 54, 15);
		contentPane.add(lblNewLabel_3);

		setContentPane(contentPane);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel setLeaveTable() {
		/* �������� */
		// ����������壬ʹ�ñ߽粼��
		JPanel panel = new JPanel(new BorderLayout());

		// ��ͷ��������
		Vector<String> vName = new Vector<String>();
		vName.add("����ʱ��");
		vName.add("�����");
		vName.add("����������ص�");
		vName.add("״̬");

		// �������������
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (FlightEntry<Plane> xFlightEntry : xFlightPlanningEntryCollection.beforeBeginTimePlanningEntries(date,
				location)) {
			if (xFlightEntry.beforeBeginDatas(date)) {
				Vector<Object> v = new Vector<Object>();
				v.add(format.format(xFlightEntry.getTimeslot().getStartDate()));
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
					if (xFlightEntry.getTimeslot().getStartDate().getTime() >= date.getTime()) {
						v.add("�������");
					} else if (xFlightEntry.getTimeslot().getStartDate().getTime() < date.getTime()) {
						v.add("�����");
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

	private JPanel setArriveTable() {// ���õִ�ı��
		JPanel panel1 = new JPanel(new BorderLayout());

		// ��ͷ��������
		Vector<String> vName1 = new Vector<String>();
		vName1.add("�ִ�ʱ��");
		vName1.add("�����");
		vName1.add("����������ص�");
		vName1.add("״̬");

		// �������������
		Vector<Vector<Object>> vData1 = new Vector<Vector<Object>>();

		for (FlightEntry<Plane> xFlightEntry : xFlightPlanningEntryCollection.afterEndTimePlanningEntries(date,
				location)) {
			if (xFlightEntry.afterEndDatas(date)) {
				Vector<Object> v = new Vector<Object>();
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
					if (xFlightEntry.getTimeslot().getEndDate().getTime() >= date.getTime()) {
						v.add("��������");
					} else if (xFlightEntry.getTimeslot().getStartDate().getTime() < date.getTime()) {
						v.add("�ѽ���");
					}
					break;
				default:
					break;
				}
				vData1.add(v);
			}
		}

		// ����һ�����ָ�� ���������� �� ��ͷ
		DefaultTableModel model1 = new DefaultTableModel(vData1, vName1);
		JTable table1 = new JTable();
		table1.setModel(model1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// �� ��ͷ ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�
		panel1.add(table1.getTableHeader(), BorderLayout.NORTH);
		// �� ������� ��ӵ���������
		panel1.add(table1, BorderLayout.CENTER);
		return panel1;
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
