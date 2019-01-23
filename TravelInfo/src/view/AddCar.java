package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CarDao;

public class AddCar extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 声明标签数组
	private JLabel[] jlArray = new JLabel[] { new JLabel("租车型号："), new JLabel("租车位置："), new JLabel("租车价格："),
			new JLabel("车辆总数："), new JLabel("剩余数量：") };

	// 声明文本框数组
	private JTextField[] jtxtArray = new JTextField[] { new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField() };

	private JButton btn = new JButton("添加信息");
	private JButton btn2 = new JButton("重置信息");
	CarDao carDAO = new CarDao();
	public AddCar() {
		// 将文本框添加进上部面板
		for (int i = 0; i < jlArray.length; i++) {
			if (i == 1 || i == 0) {
				jtxtArray[i].setBounds(175, 100 + i * 60, 290, 30);
				jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
			}
			if (i == 2 || i == 3 || i == 4) {
				jtxtArray[i].setBounds(175, 100 + i * 60, 160, 30);
				jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
				JLabel tip = new JLabel("* 值必须为整数类型！");
				tip.setForeground(new Color(255, 0, 0));
				tip.setBounds(340, 100 + i * 60, 160, 30);
				this.add(tip);
			}
			this.add(jlArray[i]);
			this.add(jtxtArray[i]);
		}
		btn.setBounds(205, 385, 90, 30);
		btn2.setBounds(105, 385, 90, 30);
		this.add(btn);
		this.add(btn2);
		btn.addActionListener(this);
		btn2.addActionListener(this);
		// 设置成绝对定位
		this.setLayout(null);
		this.setBounds(5, 5, 900, 700);
		this.setVisible(true);
		this.setBackground(new Color(240, 248,255));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			String[] str = new String[jtxtArray.length];
			for (int i = 0; i < jtxtArray.length; i++) {
				if (jtxtArray[i].getText().trim().equals("")) {
					// 当各文本框为空提示
					JOptionPane.showMessageDialog(this, "租车信息不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
					return;
				}
				str[i] = jtxtArray[i].getText().trim();
			}
			if (!validateNum(str[4], str[2], str[3])) {
				JOptionPane.showMessageDialog(this, "租车信息添加失败！\n请检查所填信息类型是否正确！", "提示消息", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			boolean flag = carDAO.insert(str[0], str[1], str[2], str[3], str[4]);
			if (flag) {
				for (int i = 0; i < jtxtArray.length; i++) {
					jtxtArray[i].setText("");
				}
				JOptionPane.showMessageDialog(this, "租车信息添加成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if (e.getSource() == btn2) {
			for (int i = 0; i < jtxtArray.length; i++) {
				jtxtArray[i].setText("");
			}
		}

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
