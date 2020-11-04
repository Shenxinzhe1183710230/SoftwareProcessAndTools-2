package ScheduleApp;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import APIs.facadePlanningEntry;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class CourseScheduleApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_9;
	private JTextField textField_5;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_12;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private String name;
	private String classroomString;
	private String beginTime;
	private String endTime;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private List<JCheckBox> checkBoxlList = new ArrayList<JCheckBox>();
	private JTextField textField_1;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseScheduleApp frame = new CourseScheduleApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourseScheduleApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 545, 365);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("\u4FE1\u606F\u7248\u8BBE\u7F6E", null, panel, null);
		tabbedPane.setEnabledAt(0, true);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u8F93\u5165\u65F6\u95F4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置时间为输入时间
				String[] xString = textField.getText().split(" ");
				if (xString.length == 2) {
					facadePlanningEntry.BoardSetInputTime("course", textField_2.getText(), xString[0] + " " + xString[1]);
				}
			}
		});
		btnNewButton.setBounds(329, 48, 181, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u7CFB\u7EDF\u65F6\u95F4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置时间为系统时间
				facadePlanningEntry.BoardSetSystemTime("course", textField_2.getText());
			}
		});
		btnNewButton_1.setBounds(329, 104, 181, 23);
		panel.add(btnNewButton_1);

		textField = new JTextField();
		textField.setBounds(45, 105, 181, 21);
		panel.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(45, 48, 181, 21);
		panel.add(textField_2);

		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u5F53\u524D\u6559\u5BA4");
		lblNewLabel.setBounds(45, 34, 181, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"\u8F93\u5165\u60F3\u8981\u8BBE\u7F6E\u7684\u5F53\u524D\u65F6\u95F4\u5982\u679C\u8BBE\u7F6E\u4E3A\u7CFB\u7EDF\u65F6\u95F4\u4E0D\u7528\u8F93\u5165");
		lblNewLabel_1.setBounds(45, 90, 331, 15);
		panel.add(lblNewLabel_1);

		JButton btnNewButton_3 = new JButton("\u4E0B\u4E00\u9875\u25B6");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnNewButton_3.setBounds(431, 293, 99, 23);
		panel.add(btnNewButton_3);

		JLabel lblNewLabel_10 = new JLabel();
		ImageIcon image1 = new ImageIcon("src/picture/board.jpg");
		Image img1 = image1.getImage();
		img1 = img1.getScaledInstance(372, 146, Image.SCALE_DEFAULT);
		image1.setImage(img1);
		lblNewLabel_10.setIcon(image1);

		lblNewLabel_10.setBounds(45, 161, 372, 140);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_5_3_1 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5_3_1.setBounds(45, 125, 126, 15);
		panel.add(lblNewLabel_5_3_1);

		ImageIcon image = new ImageIcon("src/picture/train.jpg");
		Image img = image.getImage();
		img = img.getScaledInstance(320, 199, Image.SCALE_DEFAULT);
		image.setImage(img);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u521B\u5EFA\u8BA1\u5212\u9879", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\u8BA1\u5212\u9879\u540D\u79F0");
		lblNewLabel_2.setBounds(10, 9, 66, 15);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("\u4E0A\u8BFE\u65F6\u95F4");
		lblNewLabel_4.setBounds(10, 40, 54, 15);
		panel_1.add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(10, 52, 147, 21);
		panel_1.add(textField_4);

		JLabel lblNewLabel_5 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5.setBounds(10, 73, 142, 15);
		panel_1.add(lblNewLabel_5);

		JButton btnNewButton_3_1 = new JButton("\u5206\u914D\u8D44\u6E90\u25B6");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 下一页分配资源
				textField_10.setText(name);
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnNewButton_3_1.setBounds(429, 293, 101, 23);
		panel_1.add(btnNewButton_3_1);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(86, 6, 228, 21);
		panel_1.add(textField_9);

		JButton btnNewButton_4_2 = new JButton("\u8BBE\u7F6E\u8BA1\u5212\u9879\u540D\u79F0");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置计划项名称
				if (facadePlanningEntry.checkPlanningEntryName(textField_9.getText(), "course")) {
					textField_18.setText("名字重复");
				} else {
					name = new String(textField_9.getText());
				}
			}
		});
		btnNewButton_4_2.setBounds(324, 6, 147, 21);
		panel_1.add(btnNewButton_4_2);

		JLabel lblNewLabel_4_1 = new JLabel("\u62B5\u8FBE\u65F6\u95F4");
		lblNewLabel_4_1.setBounds(167, 40, 54, 15);
		panel_1.add(lblNewLabel_4_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(167, 52, 147, 21);
		panel_1.add(textField_5);

		JLabel lblNewLabel_5_1 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5_1.setBounds(167, 73, 142, 15);
		panel_1.add(lblNewLabel_5_1);

		JButton btnNewButton_4_3 = new JButton("\u8BBE\u7F6E\u4E0A\u8BFE\u4E0B\u8BFE\u65F6\u95F4");
		btnNewButton_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置上下课时间
				beginTime = textField_4.getText();
				endTime = textField_5.getText();
			}
		});
		btnNewButton_4_3.setBounds(324, 52, 147, 21);
		panel_1.add(btnNewButton_4_3);

		JButton btnNewButton_5 = new JButton("\u521B\u5EFA\u65B0\u7684\u8BA1\u5212\u9879");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 创建新的计划项
				if (!facadePlanningEntry.checkPlanningEntryName(name, "course")) {
					facadePlanningEntry.CreateNewCrouseEntry(name, classroomString, beginTime, endTime);
				} else {
					textField_18.setText("创建计划项格式错误");
				}
				classroomString = "";
				beginTime = "";
				endTime = "";
				textField_1.setText("");
				textField_4.setText("");
				textField_9.setText("");
				textField_5.setText("");
			}
		});
		btnNewButton_5.setBounds(324, 154, 147, 23);
		panel_1.add(btnNewButton_5);

		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setBounds(10, 155, 304, 21);
		panel_1.add(textField_18);
		textField_18.setColumns(10);

		JLabel lblNewLabel_5_2 = new JLabel("\u683C\u5F0F yyyy-MM-dd,\u8BFE\u7A0B\u540D\u79F0");
		lblNewLabel_5_2.setBounds(86, 27, 165, 15);
		panel_1.add(lblNewLabel_5_2);

		JLabel lblNewLabel_2_1 = new JLabel("\u4E0A\u8BFE\u5730\u70B9");
		lblNewLabel_2_1.setBounds(10, 109, 66, 15);
		panel_1.add(lblNewLabel_2_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(86, 106, 228, 21);
		panel_1.add(textField_1);

		JButton btnNewButton_4_2_1 = new JButton("\u8BBE\u7F6E\u4E0A\u8BFE\u5730\u70B9");
		btnNewButton_4_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				classroomString = textField_1.getText();
			}
		});
		btnNewButton_4_2_1.setBounds(324, 105, 147, 21);
		panel_1.add(btnNewButton_4_2_1);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u5206\u914D\u8D44\u6E90", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("\u8BA1\u5212\u9879\u540D\u79F0");
		lblNewLabel_7.setBounds(28, 20, 68, 15);
		panel_2.add(lblNewLabel_7);

		textField_10 = new JTextField();
		textField_10.setBounds(30, 35, 126, 21);
		panel_2.add(textField_10);
		textField_10.setColumns(10);

		JButton btnNewButton_6 = new JButton("\u786E\u8BA4");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = textField_10.getText();
			}
		});
		btnNewButton_6.setBounds(166, 36, 93, 23);
		panel_2.add(btnNewButton_6);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(30, 85, 126, 21);
		panel_2.add(textField_11);

		JLabel lblNewLabel_7_1 = new JLabel("\u6559\u5E08\u59D3\u540D");
		lblNewLabel_7_1.setBounds(28, 70, 116, 15);
		panel_2.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_2 = new JLabel("\u6027\u522B");
		lblNewLabel_7_2.setBounds(28, 120, 68, 15);
		panel_2.add(lblNewLabel_7_2);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(30, 185, 126, 21);
		panel_2.add(textField_13);

		JLabel lblNewLabel_7_3 = new JLabel("\u6559\u5E08\u8EAB\u4EFD\u8BC1\u53F7");
		lblNewLabel_7_3.setBounds(28, 170, 114, 15);
		panel_2.add(lblNewLabel_7_3);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(30, 235, 126, 21);
		panel_2.add(textField_14);

		JLabel lblNewLabel_7_4 = new JLabel("\u6559\u5E08\u804C\u79F0");
		lblNewLabel_7_4.setBounds(28, 220, 68, 15);
		panel_2.add(lblNewLabel_7_4);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u7537", "\u5973" }));
		comboBox.setBounds(30, 135, 126, 21);
		panel_2.add(comboBox);

		JButton btnNewButton_7 = new JButton("\u5206\u914D\u8D44\u6E90");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 添加新车厢
				if (!facadePlanningEntry.courseAssignResourse(name, comboBox.getSelectedItem().toString(),
						textField_11.getText(), textField_13.getText(), textField_14.getText())) {
					textField_19.setText("资源冲突");
				}
			}
		});
		btnNewButton_7.setBounds(30, 265, 126, 21);
		panel_2.add(btnNewButton_7);
		JLabel lblNewLabel_9 = new JLabel(image);
		lblNewLabel_9.setIcon(image);
		lblNewLabel_9.setBounds(195, 87, 319, 199);
		panel_2.add(lblNewLabel_9);

		JButton btnNewButton_3_1_1 = new JButton("\u6539\u53D8\u8BA1\u5212\u9879\u72B6\u6001\u25B6");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 改变计划项状态下一页
				tabbedPane.setSelectedIndex(3);
				textField_12.setText(textField_10.getText());
				textField_10.setText("");
				textField_11.setText("");
				textField_13.setText("");
				textField_14.setText("");
			}
		});
		btnNewButton_3_1_1.setBounds(398, 295, 132, 21);
		panel_2.add(btnNewButton_3_1_1);

		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setBounds(30, 295, 126, 21);
		panel_2.add(textField_19);
		textField_19.setColumns(10);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("\u6539\u53D8\u8BA1\u5212\u9879\u72B6\u6001", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_7_5 = new JLabel("\u8BA1\u5212\u9879\u540D\u79F0");
		lblNewLabel_7_5.setBounds(27, 25, 68, 15);
		panel_3.add(lblNewLabel_7_5);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(27, 41, 114, 21);
		panel_3.add(textField_12);

		JButton btnNewButton_6_1 = new JButton("\u67E5\u8BE2");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 查询计划项
				State(facadePlanningEntry.getState("course", textField_12.getText()));
				textField_16.setText(facadePlanningEntry.getState("course", textField_12.getText()));
				textField_15.setText(facadePlanningEntry.seek("course", textField_12.getText()));
			}
		});
		btnNewButton_6_1.setBounds(165, 40, 93, 23);
		panel_3.add(btnNewButton_6_1);

		textField_15 = new JTextField();
		textField_15.setBounds(27, 72, 447, 23);
		panel_3.add(textField_15);
		textField_15.setColumns(10);

		ImageIcon image2 = new ImageIcon("src/picture/state.jpg");
		JLabel lblNewLabel_11 = new JLabel();
		Image img2 = image2.getImage();
		img2 = img2.getScaledInstance(372, 145, Image.SCALE_DEFAULT);
		image2.setImage(img2);
		lblNewLabel_11.setIcon(image2);

		lblNewLabel_11.setBounds(27, 154, 372, 145);
		panel_3.add(lblNewLabel_11);

		JButton btnNewButton_3_1_1_1 = new JButton("\u529F\u80FD\u9875\u9762\u25B6");
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnNewButton_3_1_1_1.setBounds(398, 309, 132, 21);
		panel_3.add(btnNewButton_3_1_1_1);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(360, 41, 114, 21);
		panel_3.add(textField_16);

		JLabel lblNewLabel_7_5_1 = new JLabel("\u8BA1\u5212\u9879\u72B6\u6001");
		lblNewLabel_7_5_1.setBounds(360, 25, 68, 15);
		panel_3.add(lblNewLabel_7_5_1);

		JCheckBox chckbxNewCheckBox = new JCheckBox("RUNNING");
		checkBoxlList.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.begin("course", textField_12.getText());
				State(facadePlanningEntry.getState("course", textField_12.getText()));
			}
		});
		chckbxNewCheckBox.setBounds(27, 101, 87, 23);
		panel_3.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("CANCEL");
		checkBoxlList.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.cancel("course", textField_12.getText());
				State(facadePlanningEntry.getState("course", textField_12.getText()));
			}
		});
		chckbxNewCheckBox_1.setBounds(116, 101, 98, 23);
		panel_3.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("ENDED");
		checkBoxlList.add(chckbxNewCheckBox_3);
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.end("course", textField_12.getText());
				State(facadePlanningEntry.getState("course", textField_12.getText()));
			}
		});
		chckbxNewCheckBox_3.setBounds(212, 101, 98, 23);
		panel_3.add(chckbxNewCheckBox_3);

		JButton btnNewButton_2 = new JButton("\u66F4\u6539\u4E0A\u8BFE\u5730\u70B9");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.courseChangeLocation(textField_12.getText(), textField_3.getText());
			}
		});
		btnNewButton_2.setBounds(165, 130, 114, 23);
		panel_3.add(btnNewButton_2);

		textField_3 = new JTextField();
		textField_3.setBounds(27, 130, 114, 21);
		panel_3.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\u529F\u80FD", null, panel_4, null);
		panel_4.setLayout(null);

		textField_17 = new JTextField();
		textField_17.setBounds(10, 28, 135, 21);
		panel_4.add(textField_17);
		textField_17.setColumns(10);

		JButton btnNewButton_9 = new JButton("\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u524D\u5E8F\u8BA1\u5212\u9879");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] xString = textField_17.getText().split(" ");
				textField_20.setText(facadePlanningEntry.findPreEntryPerResource(xString[0], xString[1], "course"));
			}
		});
		btnNewButton_9.setBounds(365, 27, 165, 23);
		panel_4.add(btnNewButton_9);

		JLabel lblNewLabel_12 = new JLabel("\u8F93\u5165\u8BFE\u7A0B\u540D\u79F0");
		lblNewLabel_12.setBounds(176, 10, 102, 15);
		panel_4.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel(
				"\u8F93\u5165\u6559\u5E08\u540D\u79F0\uFF0C\u8BA1\u5212\u9879\u7684\u540D\u79F0");
		lblNewLabel_13.setBounds(365, 10, 165, 15);
		panel_4.add(lblNewLabel_13);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 108, 520, 203);
		panel_4.add(panel_5);

		JButton btnNewButton_8 = new JButton("\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u5168\u90E8\u8BA1\u5212\u9879");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(facadePlanningEntry.courseJPanel(textField_17.getText()));
				panel_5.updateUI();
			}
		});

		btnNewButton_8.setBounds(176, 27, 165, 23);
		panel_4.add(btnNewButton_8);

		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setBounds(10, 59, 520, 21);
		panel_4.add(textField_20);
		textField_20.setColumns(10);

	}

	private void State(String state) {
		for (int i = 0; i < 4; i++) {
			checkBoxlList.get(i).setSelected(false);
			checkBoxlList.get(i).setEnabled(false);
			if (checkBoxlList.get(i).getText().equals(state)) {
				checkBoxlList.get(i).setSelected(true);
			}
		}
		switch (state) {
		case "WAITING":
			checkBoxlList.get(1).setEnabled(true);
			break;
		case "ALLOCATED":
			checkBoxlList.get(0).setEnabled(true);
			checkBoxlList.get(1).setEnabled(true);
			break;
		case "RUNNING":
			checkBoxlList.get(2).setEnabled(true);
			break;
		default:
			break;
		}
	}
}
