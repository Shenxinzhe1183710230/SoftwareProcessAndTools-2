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
import Entry.TrainEntry;
import LocationAndTimeslot.Location;
import PlanningEntryCollection.TrainPlanningEntryCollection;
import Resourse.TrainCarriage;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TrainBoard extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private static final long serialVersionUID = 1L;
	private Location location;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private Date date;// ��ǰʱ��
	private TrainPlanningEntryCollection trainPlanningEntryCollection;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TrainBoard(TrainPlanningEntryCollection trainPlanningEntryCollection, Location location,
			String nowDataString) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		try {
			setDate(nowDataString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.location = location;
		this.trainPlanningEntryCollection = trainPlanningEntryCollection;
		getContentPane().setLayout(null);
		TimeUpdate();
		setVisible(true);

	}

	public void Update(TrainPlanningEntryCollection trainPlanningEntryCollection) {
		this.trainPlanningEntryCollection = trainPlanningEntryCollection;
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

		JLabel lblNewLabel_1 = new JLabel("\u8F66\u7AD9");
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
		vName.add("��վʱ��");
		vName.add("����ʱ��");
		vName.add("����");
		vName.add("ʼ�����յ�վ");
		vName.add("״̬");

		// �������������
		Vector<Vector<Object>> vData = new Vector<Vector<Object>>();

		for (TrainEntry<TrainCarriage> xTrainEntry : trainPlanningEntryCollection.OnehourPlanningEntries(date,
				location)) {
			Vector<Object> v = new Vector<Object>();
			if (xTrainEntry.isMiddleLocation(location)) {
				v.add(format.format(xTrainEntry.getLocationAndTime().get(location).getStartDate()));
				v.add(format.format(xTrainEntry.getLocationAndTime().get(location).getEndDate()));
			} else if (xTrainEntry.getBeginLocation().getName().equals(location.getName())) {
				v.add(format.format(xTrainEntry.getBeginAndEndTimeslot().getStartDate()));
				v.add("");
			} else if (xTrainEntry.getBeginLocation().getName().equals(location.getName())) {
				v.add("");
				v.add(format.format(xTrainEntry.getBeginAndEndTimeslot().getEndDate()));
			}
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
				v.add("�ѵ��ﱾվ(����)");
				break;
			case RUNNING:
				if (xTrainEntry.isMiddleLocation(location)) {
					if (xTrainEntry.getLocationAndTime().get(location).getStartDate().getTime() >= date.getTime()) {
						v.add("��������");
					} else if (xTrainEntry.getLocationAndTime().get(location).getStartDate().getTime() < date
							.getTime()) {
						v.add("�ѵִ�");
					}
				} else if (xTrainEntry.getBeginLocation().getName().equals(location.getName())) {
					if (xTrainEntry.getBeginAndEndTimeslot().getStartDate().getTime() >= date.getTime()) {
						v.add("��������");
					} else if (xTrainEntry.getBeginAndEndTimeslot().getStartDate().getTime() < date.getTime()) {
						v.add("�ѷ���");
					}
				} else if (xTrainEntry.getEndLocation().getName().equals(location.getName())) {
					if (xTrainEntry.getBeginAndEndTimeslot().getEndDate().getTime() >= date.getTime()) {
						v.add("�����ִ�");
					} else if (xTrainEntry.getBeginAndEndTimeslot().getEndDate().getTime() < date.getTime()) {
						v.add("�ѵ���");
					}
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
