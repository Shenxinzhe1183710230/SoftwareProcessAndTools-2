package ScheduleApp;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import APIs.facadePlanningEntry;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.management.loading.PrivateClassLoader;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

public class TrainScheduleApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_3;
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
	private List<String> locationlList = new ArrayList<String>();
	private List<String> beginTimeList = new ArrayList<String>();
	private List<String> endTimeList = new ArrayList<String>();
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private List<JCheckBox> checkBoxlList = new ArrayList<JCheckBox>();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TrainScheduleApp() {
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
					facadePlanningEntry.BoardSetInputTime("train", textField_2.getText(), xString[0] + " " + xString[1]);
				}
			}
		});
		btnNewButton.setBounds(329, 48, 181, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u7CFB\u7EDF\u65F6\u95F4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置时间为系统时间
				facadePlanningEntry.BoardSetSystemTime("train", textField_2.getText());
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

		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u5F53\u524D\u8F66\u7AD9");
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

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u521B\u5EFA\u8BA1\u5212\u9879", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("\u8BA1\u5212\u9879\u540D\u79F0");
		lblNewLabel_2.setBounds(10, 9, 66, 15);
		panel_1.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 48, 66, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u8D77\u59CB\u8F66\u7AD9");
		lblNewLabel_3.setBounds(10, 34, 54, 15);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u51FA\u53D1\u65F6\u95F4");
		lblNewLabel_4.setBounds(96, 34, 54, 15);
		panel_1.add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(96, 48, 147, 21);
		panel_1.add(textField_4);

		JLabel lblNewLabel_5 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5.setBounds(96, 71, 142, 15);
		panel_1.add(lblNewLabel_5);

		textField_6 = new JTextField();
		textField_6.setBounds(10, 113, 66, 21);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(96, 113, 147, 21);
		panel_1.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(254, 113, 147, 21);
		panel_1.add(textField_8);

		JLabel lblNewLabel_6 = new JLabel("\u4E2D\u95F4\u8F66\u7AD9\u540D\u79F0");
		lblNewLabel_6.setBounds(10, 96, 72, 15);
		panel_1.add(lblNewLabel_6);

		JLabel lblNewLabel_5_2 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5_2.setBounds(96, 132, 126, 15);
		panel_1.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_3 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5_3.setBounds(254, 132, 126, 15);
		panel_1.add(lblNewLabel_5_3);

		JLabel lblNewLabel_5_2_1 = new JLabel("\u62B5\u8FBE\u8BE5\u8F66\u7AD9\u65F6\u95F4");
		lblNewLabel_5_2_1.setBounds(96, 96, 101, 15);
		panel_1.add(lblNewLabel_5_2_1);

		JLabel lblNewLabel_5_2_1_1 = new JLabel("\u79BB\u5F00\u8BE5\u8F66\u7AD9\u65F6\u95F4");
		lblNewLabel_5_2_1_1.setBounds(252, 96, 126, 15);
		panel_1.add(lblNewLabel_5_2_1_1);

		JButton btnNewButton_4_1 = new JButton("\u6DFB\u52A0\u4E00\u4E2A\u4E2D\u95F4\u8F66\u7AD9");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 添加中间车站
				locationlList.add(textField_6.getText());
				beginTimeList.add(textField_7.getText());
				endTimeList.add(textField_8.getText());
			}
		});
		btnNewButton_4_1.setBounds(411, 112, 129, 23);
		panel_1.add(btnNewButton_4_1);

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
		textField_9.setBounds(96, 6, 147, 21);
		panel_1.add(textField_9);

		JButton btnNewButton_4_2 = new JButton("\u8BBE\u7F6E\u8BA1\u5212\u9879\u540D\u79F0");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置计划项名称
				if (facadePlanningEntry.checkPlanningEntryName(textField_9.getText(), "train")) {
					textField_18.setText("名字重复");
				} else {
					name = new String(textField_9.getText());
				}
			}
		});
		btnNewButton_4_2.setBounds(254, 6, 126, 21);
		panel_1.add(btnNewButton_4_2);

		JButton btnNewButton_4 = new JButton("\u8BBE\u7F6E\u7EC8\u70B9\u7AD9");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置终点站
				locationlList.add(textField_3.getText());
				endTimeList.add(textField_5.getText());
			}
		});
		btnNewButton_4.setBounds(254, 168, 126, 23);
		panel_1.add(btnNewButton_4);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(10, 169, 66, 21);
		panel_1.add(textField_3);

		JLabel lblNewLabel_3_1 = new JLabel("\u7EC8\u70B9\u8F66\u7AD9");
		lblNewLabel_3_1.setBounds(10, 155, 54, 15);
		panel_1.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4_1 = new JLabel("\u62B5\u8FBE\u65F6\u95F4");
		lblNewLabel_4_1.setBounds(96, 157, 54, 15);
		panel_1.add(lblNewLabel_4_1);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(96, 169, 147, 21);
		panel_1.add(textField_5);

		JLabel lblNewLabel_5_1 = new JLabel("\u683C\u5F0Fyyyy-MM-dd HH:mm");
		lblNewLabel_5_1.setBounds(96, 190, 142, 15);
		panel_1.add(lblNewLabel_5_1);

		JButton btnNewButton_4_3 = new JButton("\u8BBE\u7F6E\u8D77\u59CB\u7AD9");
		btnNewButton_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置起始站
				locationlList.add(textField_1.getText());
				beginTimeList.add(textField_4.getText());
			}
		});
		btnNewButton_4_3.setBounds(253, 47, 126, 23);
		panel_1.add(btnNewButton_4_3);

		JButton btnNewButton_5 = new JButton("\u521B\u5EFA\u65B0\u7684\u8BA1\u5212\u9879");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 创建新的计划项
				if (!facadePlanningEntry.checkPlanningEntryName(name, "train")) {
					facadePlanningEntry.CreateNewTrainEntry(name, locationlList, beginTimeList, endTimeList);
				} else {
					textField_18.setText("创建计划项格式错误");
				}
				locationlList.clear();
				beginTimeList.clear();
				endTimeList.clear();
				textField_1.setText("");
				textField_4.setText("");
				textField_9.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				textField_3.setText("");
				textField_5.setText("");
			}
		});
		btnNewButton_5.setBounds(100, 234, 228, 23);
		panel_1.add(btnNewButton_5);

		textField_18 = new JTextField();
		textField_18.setEditable(false);
		textField_18.setBounds(96, 267, 330, 21);
		panel_1.add(textField_18);
		textField_18.setColumns(10);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u5206\u914D\u8D44\u6E90", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("\u8BA1\u5212\u9879\u540D\u79F0");
		lblNewLabel_7.setBounds(28, 21, 68, 15);
		panel_2.add(lblNewLabel_7);

		textField_10 = new JTextField();
		textField_10.setBounds(30, 37, 114, 21);
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
		textField_11.setBounds(30, 93, 114, 21);
		panel_2.add(textField_11);

		JLabel lblNewLabel_7_1 = new JLabel("\u8F66\u53A2\u7F16\u53F7");
		lblNewLabel_7_1.setBounds(28, 77, 116, 15);
		panel_2.add(lblNewLabel_7_1);

		JLabel lblNewLabel_7_2 = new JLabel("\u8F66\u53A2\u7C7B\u578B");
		lblNewLabel_7_2.setBounds(28, 118, 68, 15);
		panel_2.add(lblNewLabel_7_2);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(30, 174, 114, 21);
		panel_2.add(textField_13);

		JLabel lblNewLabel_7_3 = new JLabel("\u5B9A\u5458\u6570");
		lblNewLabel_7_3.setBounds(28, 158, 68, 15);
		panel_2.add(lblNewLabel_7_3);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(30, 219, 114, 21);
		panel_2.add(textField_14);

		JLabel lblNewLabel_7_4 = new JLabel("\u51FA\u573A\u5E74\u4EFD");
		lblNewLabel_7_4.setBounds(28, 203, 68, 15);
		panel_2.add(lblNewLabel_7_4);

		JLabel lblNewLabel_5_4 = new JLabel("\u683C\u5F0Fyyyy-MM-dd");
		lblNewLabel_5_4.setBounds(28, 243, 126, 15);
		panel_2.add(lblNewLabel_5_4);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\u5546\u52A1", "\u4E00 \u7B49", "\u4E8C\u7B49",
				"\u8F6F\u5367", "\u786C\u5367", "\u786C\u5EA7", "\u884C\u674E\u8F66", "\u9910\u8F66" }));
		comboBox.setBounds(28, 135, 116, 21);
		panel_2.add(comboBox);

		JButton btnNewButton_7 = new JButton("\u6DFB\u52A0\u65B0\u8F66\u53A2");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 添加新车厢
				if (!facadePlanningEntry.trainAddResourse(name, textField_11.getText(),
						comboBox.getSelectedItem().toString(), textField_13.getText(), textField_14.getText())) {
					textField_19.setText("资源冲突");
				}
			}
		});
		btnNewButton_7.setBounds(28, 268, 93, 23);
		panel_2.add(btnNewButton_7);

		JButton btnNewButton_7_1 = new JButton("\u79FB\u9664\u8F66\u53A2");
		btnNewButton_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.trainRemoveResourse(textField_10.getText(), textField_11.getText());
			}
		});
		btnNewButton_7_1.setBounds(131, 268, 93, 23);
		panel_2.add(btnNewButton_7_1);

		JLabel lblNewLabel_8 = new JLabel("\u8F93\u5165\u8F66\u53A2\u7F16\u53F7\u5373\u53EF");
		lblNewLabel_8.setBounds(131, 289, 105, 15);
		panel_2.add(lblNewLabel_8);

		ImageIcon image = new ImageIcon("src/picture/train.jpg");
		JLabel lblNewLabel_9 = new JLabel(image);
		Image img = image.getImage();
		img = img.getScaledInstance(320, 199, Image.SCALE_DEFAULT);
		image.setImage(img);
		lblNewLabel_9.setIcon(image);
		lblNewLabel_9.setBounds(196, 59, 319, 199);
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
		textField_19.setBounds(28, 301, 93, 21);
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
				State(facadePlanningEntry.getState("train", textField_12.getText()));
				textField_16.setText(facadePlanningEntry.getState("train", textField_12.getText()));
				textField_15.setText(facadePlanningEntry.seek("train", textField_12.getText()));
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
				facadePlanningEntry.begin("train", textField_12.getText());
				State(facadePlanningEntry.getState("train", textField_12.getText()));
			}
		});
		chckbxNewCheckBox.setBounds(27, 101, 87, 23);
		panel_3.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("CANCEL");
		checkBoxlList.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.cancel("train", textField_12.getText());
				State(facadePlanningEntry.getState("train", textField_12.getText()));
			}
		});
		chckbxNewCheckBox_1.setBounds(116, 101, 98, 23);
		panel_3.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("BLOCKED");
		checkBoxlList.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.blocked(textField_12.getText());
				State(facadePlanningEntry.getState("train", textField_12.getText()));
			}
		});
		chckbxNewCheckBox_2.setBounds(217, 101, 93, 23);
		panel_3.add(chckbxNewCheckBox_2);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("ENDED");
		checkBoxlList.add(chckbxNewCheckBox_3);
		chckbxNewCheckBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.end("train", textField_12.getText());
				State(facadePlanningEntry.getState("train", textField_12.getText()));
			}
		});
		chckbxNewCheckBox_3.setBounds(312, 101, 98, 23);
		panel_3.add(chckbxNewCheckBox_3);

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
				textField_20.setText(facadePlanningEntry.findPreEntryPerResource(xString[0], xString[1], "train"));
			}
		});
		btnNewButton_9.setBounds(365, 27, 165, 23);
		panel_4.add(btnNewButton_9);

		JLabel lblNewLabel_12 = new JLabel("\u8F93\u5165\u8F66\u53A2\u7F16\u53F7");
		lblNewLabel_12.setBounds(176, 10, 102, 15);
		panel_4.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel(
				"\u8F93\u5165\u8F66\u53A2\u7F16\u53F7\uFF0C\u8BA1\u5212\u9879\u7684\u540D\u79F0");
		lblNewLabel_13.setBounds(365, 10, 165, 15);
		panel_4.add(lblNewLabel_13);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 108, 520, 203);
		panel_4.add(panel_5);

		JButton btnNewButton_8 = new JButton("\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u5168\u90E8\u8BA1\u5212\u9879");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_5.removeAll();
				panel_5.add(facadePlanningEntry.trainJPanel(textField_17.getText()));
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
			checkBoxlList.get(3).setEnabled(true);
			break;
		case "BLOCKED":
			checkBoxlList.get(1).setEnabled(true);
			checkBoxlList.get(0).setEnabled(true);
			break;
		default:
			break;
		}
	}
}
