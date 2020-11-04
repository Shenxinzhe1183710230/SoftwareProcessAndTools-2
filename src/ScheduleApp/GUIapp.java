package ScheduleApp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.ComboBoxModel;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;

public class GUIapp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtplanningentry;

	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		GUIapp flightScheduleApp = new GUIapp();
		flightScheduleApp.setVisible(true);
	}

	public GUIapp() {
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 93);
		contentPane = new JPanel();
		contentPane.setToolTipText("\u9009\u62E9\u8981\u5B9E\u73B0\u7684\u529F\u80FD");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		verticalBox.add(panel);

		txtplanningentry = new JTextField();
		txtplanningentry.setEnabled(false);
		txtplanningentry.setEditable(false);
		txtplanningentry.setText("\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684PlanningEntry");
		panel.add(txtplanningentry);
		txtplanningentry.setColumns(26);

		JComboBox<?> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Flight", "Train", "Course" }));
		panel.add(comboBox);

		JButton btnNewButton_1 = new JButton("\u786E\u5B9A");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Flight")) {
					FlightScheduleApp flightScheduleApp = new FlightScheduleApp();
					setVisible(false);
					flightScheduleApp.setVisible(true);
				} else if (comboBox.getSelectedItem().toString().equals("Train")) {
					TrainScheduleApp trainScheduleApp = new TrainScheduleApp();
					setVisible(false);
					trainScheduleApp.setVisible(true);
				} else if (comboBox.getSelectedItem().toString().equals("Course")) {
					CourseScheduleApp courseScheduleApp = new CourseScheduleApp();
					setVisible(false);
					courseScheduleApp.setVisible(true);
				}
			}
		});
		panel.add(btnNewButton_1);

		Panel panel_1 = new Panel();
		verticalBox.add(panel_1);
	}
}

class PopupFlight extends JFrame {

	private static final long serialVersionUID = 1L;// µ¯´°
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupFlight frame = new PopupFlight();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PopupFlight() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Box verticalBox = Box.createVerticalBox();
		contentPane.add(verticalBox, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		verticalBox.add(panel);

		Box verticalBox_1 = Box.createVerticalBox();
		panel.add(verticalBox_1);

		JLabel lblNewLabel = new JLabel(
				"\u8BF7\u8F93\u5165\u8D77\u59CB\u5730\u70B9\uFF0C\u7ED3\u675F\u5730\u70B9\uFF0C\u8BA1\u5212\u9879\u7684\u540D\u79F0\uFF0C\u8D77\u59CB\u65F6\u95F4\uFF0C\u7ED3\u675F\u65F6\u95F4\uFF0C\u4E2D\u95F4\u7528\u7A7A\u683C\u9694\u5F00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel);

		JTextArea textArea_1 = new JTextArea();
		verticalBox_1.add(textArea_1);

		JButton btnNewButton = new JButton("\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684FLight\u8BA1\u5212\u9879");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		verticalBox_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(
				"\u8BF7\u8F93\u5165\u98DE\u673A\u7F16\u53F7\uFF0C\u673A\u578B\u53F7\uFF0C\u5EA7\u4F4D\u6570\uFF0C\u673A\u9F84");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel_1);

		JTextArea textArea_1_1 = new JTextArea();
		verticalBox_1.add(textArea_1_1);

		JButton btnNewButton_1 = new JButton("\u5206\u914D\u8D44\u6E90");
		verticalBox_1.add(btnNewButton_1);

		JLabel lblNewLabel_1_1 = new JLabel(
				"\u8BF7\u8F93\u5165\u8981\u542F\u52A8\u6216\u53D6\u6D88\u7684\u8BA1\u5212\u9879\u7684\u822A\u73ED\u53F7");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel_1_1);

		JTextArea textArea_1_1_1 = new JTextArea();
		verticalBox_1.add(textArea_1_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(panel_1);

		JButton btnNewButton_1_1 = new JButton("\u542F\u52A8\u8BA1\u5212\u9879");
		panel_1.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("\u53D6\u6D88\u8BA1\u5212\u9879");
		panel_1.add(btnNewButton_1_2);
	}

}
