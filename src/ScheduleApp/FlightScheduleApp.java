package ScheduleApp;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import APIs.facadePlanningEntry;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;

public class FlightScheduleApp extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private String nameString;
	private JTextField textField_6;
	private JTextField textField_7;

	/**
	 * Create the frame.
	 */
	public FlightScheduleApp() {

		setBounds(100, 100, 718, 558);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setEnabled(false);
		contentPane.add(verticalBox, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		verticalBox.add(panel);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setEnabled(false);

		JLabel lblNewLabel_2 = new JLabel(
				"\u5982\u679C\u60F3\u8981\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u8F93\u5165\u65F6\u95F4,\u8BF7\u8F93\u5165\u60F3\u8981\u8BBE\u7F6E\u7684\u65F6\u95F4,\u5730\u70B9,\u4E2D\u95F4\u7528\u7A7A\u683C\u95F4\u9694,\u5982\u679C\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u7CFB\u7EDF\u65F6\u95F4,\u53EA\u9700\u8F93\u5165\u5730\u70B9\u5373\u53EF");
		lblNewLabel_2.setForeground(Color.RED);
		verticalBox_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(
				"\u65F6\u95F4\u6309\u7167yyyy-MM-dd HH:mm\u683C\u5F0F\uFF0C\u5206\u522B\u8F93\u5165\u5E74\u6708\u65E5\uFF0C\u5C0F\u65F6\uFF0C\u5206\u949F");
		verticalBox_1.add(lblNewLabel_3);

		textField_3 = new JTextField();
		verticalBox_1.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(panel_2);

		JButton btnNewButton_2 = new JButton("\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u8F93\u5165\u65F6\u95F4");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 设置时间为输入时间
				String[] xString = textField_3.getText().split(" ");
				if (xString.length == 3) {
					facadePlanningEntry.BoardSetInputTime("flight", xString[2], xString[0] + " " + xString[1]);
				}
			}
		});
		panel_2.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("\u8BBE\u7F6E\u65F6\u95F4\u4E3A\u7CFB\u7EDF\u65F6\u95F4");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facadePlanningEntry.BoardSetSystemTime("flight", textField_3.getText());
			}
		});
		panel_2.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel(
				"\u8BF7\u8F93\u5165\u8D77\u59CB\u5730\u70B9\uFF0C\u7ED3\u675F\u5730\u70B9\uFF0C\u8BA1\u5212\u9879\u7684\u540D\u79F0\uFF0C\u8D77\u59CB\u65F6\u95F4\uFF0C\u7ED3\u675F\u65F6\u95F4\uFF0C\u4E2D\u95F4\u7528\u7A7A\u683C\u9694\u5F00");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel);

		JLabel lblyyyymmddHhmm = new JLabel(
				"\u8BA1\u5212\u9879\u7684\u540D\u79F0\u662F\u201C\u65E5\u671F,\u822A\u73ED\u53F7\u201D\u7684\u683C\u5F0F,\u65F6\u95F4\u6309\u7167yyyy-MM-dd HH:mm\u683C\u5F0F\uFF0C\u65E5\u671F\u6309\u7167yyyy-MM-dd\u683C\u5F0F");
		lblyyyymmddHhmm.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblyyyymmddHhmm);

		textField = new JTextField();
		verticalBox_1.add(textField);
		textField.setColumns(10);

		// 创建新的计划项
		JButton btnNewButton = new JButton("\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684FLight\u8BA1\u5212\u9879");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] xString = textField.getText().split(" ");
				if (xString.length == 7) {
					nameString = xString[2];
					if (!facadePlanningEntry.checkPlanningEntryName(nameString, "fligth")) {
						facadePlanningEntry.CreateNewFlightEntry(xString[0], xString[1], nameString,
								xString[3] + " " + xString[4], xString[5] + " " + xString[6]);
					} else {
						textField_5.setText("计划项(航班号)名字重复");
					}
				} else {
					textField_5.setText("创建新的计划项输入错误");
				}
			}
		});
		verticalBox_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(
				"\u98DE\u673A\u7F16\u53F7\uFF0C\u673A\u578B\u53F7\uFF0C\u5EA7\u4F4D\u6570\uFF0C\u673A\u9F84");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel_1);

		textField_1 = new JTextField();
		verticalBox_1.add(textField_1);
		textField_1.setColumns(10);
		// 分配资源
		JButton btnNewButton_1 = new JButton("\u5206\u914D\u8D44\u6E90");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] xString = textField_1.getText().split(" ");
				if (xString.length == 4) {
					if (!facadePlanningEntry.flightAssignResourse(nameString, xString[0], xString[1], xString[2],
							xString[3])) {
						textField_5.setText("资源冲突");
					}
				} else {
					textField_5.setText("分配资源格式输入错误");
				}
			}
		});
		verticalBox_1.add(btnNewButton_1);

		JLabel lblNewLabel_1_1 = new JLabel(
				"\u8BF7\u8F93\u5165\u8981\u542F\u52A8\u6216\u53D6\u6D88\u7684\u6216\u60F3\u8981\u67E5\u8BE2\u7684\u8BA1\u5212\u9879\u7684\u540D\u5B57");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		verticalBox_1.add(lblNewLabel_1_1);

		textField_2 = new JTextField();
		verticalBox_1.add(textField_2);
		textField_2.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(panel_1);

		JButton btnNewButton_1_1 = new JButton("\u542F\u52A8\u8BA1\u5212\u9879");
		btnNewButton_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// 启动计划项
				String name = textField_2.getText();
				facadePlanningEntry.begin("flight", name);
			}
		});
		panel_1.add(btnNewButton_1_1);

		JButton btnNewButton_1_2 = new JButton("\u53D6\u6D88\u8BA1\u5212\u9879");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 取消计划项
				String name = textField_2.getText();
				facadePlanningEntry.cancel("flight", name);
			}
		});
		panel_1.add(btnNewButton_1_2);

		JButton btnNewButton_4 = new JButton("\u67E5\u8BE2\u8BA1\u5212\u9879\u72B6\u6001");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 查询某个计划项状态
				String name = textField_2.getText();
				textField_4.setText(facadePlanningEntry.getState("flight", name));
			}
		});

		JButton btnNewButton_5 = new JButton("\u7ED3\u675F\u8BA1\u5212\u9879");
		btnNewButton_5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {// 结束计划项
				String name = textField_2.getText();
				facadePlanningEntry.end("flight", name);
			}
		});
		panel_1.add(btnNewButton_5);
		panel_1.add(btnNewButton_4);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(verticalBox_1);

		JLabel lblNewLabel_4 = new JLabel(
				"\u8BF7\u8F93\u5165\u98DE\u673A\u7F16\u53F7\uFF0C\u8BA1\u5212\u9879\u7684\u540D\u79F0\uFF0C\u4E2D\u95F4\u7528\u7A7A\u683C\u95F4\u9694\u5F00   \u5982\u679C\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u5168\u90E8\u8BA1\u5212\u9879\uFF0C\u53EA\u9700\u8F93\u5165\u98DE\u673A\u7F16\u53F7\u5373\u53EF");
		lblNewLabel_4.setForeground(Color.RED);
		verticalBox_1.add(lblNewLabel_4);

		textField_6 = new JTextField();
		textField_6.setColumns(40);
		verticalBox_1.add(textField_6);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setAlignmentY(0.0f);
		panel_2_1.setAlignmentX(0.0f);
		verticalBox_1.add(panel_2_1);

		JButton btnNewButton_2_1 = new JButton(
				"\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u7684\u524D\u5E8F\u8BA1\u5212\u9879");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 查询特定资源前序计划项
				String[] xString = textField_6.getText().split(" ");
				textField_7.setText(facadePlanningEntry.findPreEntryPerResource(xString[0], xString[1], "flight"));
			}
		});
		panel_2_1.add(btnNewButton_2_1);

		JButton btnNewButton_6 = new JButton(
				"\u67E5\u8BE2\u7279\u5B9A\u8D44\u6E90\u7684\u5168\u90E8\u8BA1\u5212\u9879");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 根据资源查询计划项
				JFrame jFrame = new JFrame();
				JScrollPane scrollpane = new JScrollPane(facadePlanningEntry.flightJPanel(textField_6.getText()));
				jFrame.setContentPane(scrollpane);
				jFrame.setVisible(true);
				jFrame.setPreferredSize(new Dimension(400, 400));
				jFrame.pack();
				jFrame.setLocationRelativeTo(null);
			}
		});
		panel_2_1.add(btnNewButton_6);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		verticalBox_1.add(textField_7);

		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setAlignmentY(0.0f);
		panel_2_1_1.setAlignmentX(0.0f);
		verticalBox_1.add(panel_2_1_1);

		JButton btnNewButton_2_1_1 = new JButton("\u67E5\u8BE2\u8BE5\u8BA1\u5212\u9879");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_7.setText(facadePlanningEntry.seek("flight", textField_7.getText()));
			}
		});
		panel_2_1_1.add(btnNewButton_2_1_1);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		verticalBox_1.add(textField_5);
		textField_5.setColumns(10);
	}
}
