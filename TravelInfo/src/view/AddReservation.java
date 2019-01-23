package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CustomersDao;
import dao.ReservationDao;

public class AddReservation extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 声明标签数组
	private JLabel[] jlArray = new JLabel[] { new JLabel("顾客姓名："), new JLabel("预定类型："), new JLabel("预定编号：") };
	private static JComboBox<String> customerName = new JComboBox<>();
	private JComboBox<String> typeName = new JComboBox<>();
	// 声明文本框数组
	private JTextField jtxtFiled = new JTextField();

	private JButton btn = new JButton("添加信息");
	private JButton btn2 = new JButton("重置信息");

	private ReservationDao reservationDao = new ReservationDao();
	private static CustomersDao customerDAO = new CustomersDao();

	public AddReservation() {
		initNameComboBox();
		typeName.addItem("预定航班");
		typeName.addItem("预定租车");
		typeName.addItem("预定酒店");
		// 将文本框添加进上部面板
		for (int i = 0; i < jlArray.length; i++) {
			jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
			if (i == 0) {
				customerName.setBounds(175, 100 + i * 60, 200, 30);
				customerName.setMaximumRowCount(6);
				customerName.setSelectedIndex(0);
				this.add(customerName);
			} else if (i == 1) {
				typeName.setBounds(175, 100 + i * 60, 200, 30);
				typeName.setSelectedItem(0);
				this.add(typeName);
			} else if (i == 2) {
				jtxtFiled.setBounds(175, 100 + i * 60, 200, 30);
				this.add(jtxtFiled);
				JLabel tip = new JLabel("* 输入所选预定类型的整型ID号即可！");
				tip.setForeground(new Color(255, 0, 0));
				tip.setBounds(380, 100 + i * 60, 250, 30);
				this.add(tip);
			}
			this.add(jlArray[i]);
		}
		btn.setBounds(205, 270, 90, 30);
		btn2.setBounds(100, 270, 90, 30);
		this.add(btn);
		this.add(btn2);
		btn.addActionListener(this);
		btn2.addActionListener(this);
		this.setLayout(null);
		this.setBounds(5, 5, 900, 700);
		this.setVisible(true);
		this.setBackground(new Color(240, 248,255));
	}

	private static void initNameComboBox() {
		customerName.removeAllItems();
		ResultSet rs = customerDAO.query();
		try {
			while (rs.next()) {
				String name = rs.getString("custName");
				customerName.addItem(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/*
	 * public static void main(String[] args) { JFrame jf = new JFrame(); jf.add(new
	 * AddReservation()); jf.setBounds(200, 200, 900, 700); // jf.pack();
	 * jf.setVisible(true); jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			String selectName = (String) customerName.getSelectedItem();
			int selectType = typeName.getSelectedIndex() + 1;
			String resvKey = jtxtFiled.getText().trim();
			if (resvKey.equals("")) {
				JOptionPane.showMessageDialog(this, "输入信息不能有空！", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!validateNum(resvKey)) {
				JOptionPane.showMessageDialog(this, "请输入整数并已存在的ID号！", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			boolean flag = reservationDao.insert(selectName, selectType, Integer.valueOf(resvKey));
			if (flag) {
				customerName.setSelectedIndex(0);
				typeName.setSelectedIndex(0);
				jtxtFiled.setText("");
				JOptionPane.showMessageDialog(this, "预定信息添加成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if (e.getSource() == btn2) {
			customerName.setSelectedIndex(0);
			typeName.setSelectedIndex(0);
			jtxtFiled.setText("");
		}
	}

	// 刷新视图显示
	public void updateModel() {
		initNameComboBox();
		this.repaint();
		this.updateUI();
	}

	// 正则表达式验证字符串格式
	public boolean validateNum(String... str) {
		for (int i = 0; i < str.length; i++) {
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
			if (!pattern.matcher(str[i]).matches()) {
				return false;
			}
		}
		return true;

	}
}
