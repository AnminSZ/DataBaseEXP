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

import dao.CustomersDao;

public class AddCustomer extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 声明标签数组
	private JLabel[] jlArray = new JLabel[] { new JLabel("顾客姓名：") };

	// 声明文本框数组
	private JTextField[] jtxtArray = new JTextField[] { new JTextField() };

	private JButton btn = new JButton("添加信息");
	private JButton btn2 = new JButton("重置信息");
	CustomersDao customerDAO= new CustomersDao();

	public AddCustomer() {
		// 将文本框添加进上部面板
		for (int i = 0; i < jlArray.length; i++) {
			jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
			jtxtArray[i].setBounds(175, 100 + i * 60, 120, 30);
			this.add(jlArray[i]);
			this.add(jtxtArray[i]);

		}
		btn.setBounds(205, 150, 90, 30);
		btn2.setBounds(100, 150, 90, 30);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			
			String[] str = new String[jtxtArray.length];
			for(int i=0;i<jtxtArray.length;i++) {
				if(jtxtArray[i].getText().trim().equals("")) {
					//当各文本框为空提示
					JOptionPane.showMessageDialog(
				  			this,"顾客信息不能为空！","提示消息",JOptionPane.WARNING_MESSAGE);
					return;	  	
				}	
				str[i] = jtxtArray[i].getText().trim();
			}			
			boolean flag = customerDAO.insert(str[0]);
			if(flag) {
				for(int i=0;i<jtxtArray.length;i++) {
					jtxtArray[i].setText("");		
				}
				JOptionPane.showMessageDialog(
			  			this,"顾客信息添加成功！","提示消息",JOptionPane.INFORMATION_MESSAGE);
				return;	
			}
		}else if(e.getSource()==btn2) {
			for(int i=0;i<jtxtArray.length;i++) {
				jtxtArray[i].setText("");		
			}
		}
		
	}
	
	//正则表达式验证字符串格式
	public boolean validateNum(String...str) {
		for(int i=0;i<str.length;i++) {
			Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$"); 
			if(!pattern.matcher(str[i]).matches()) {
				return false;
			} 
		}
		return true;
		
	}

}
