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

import dao.FlightDao;

public class AddFlight extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//声明标签数组
	private JLabel[] jlArray = new JLabel[] {
		new JLabel("航班号："),
		new JLabel("票价："),
		new JLabel("座位数："),
		new JLabel("剩余座位："),
		new JLabel("始发城市："),
		new JLabel("目的城市：")
	};
	
	//声明文本框数组
	private JTextField[] jtxtArray=new JTextField[]{
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField()
	};
	
	private JButton btn = new JButton("添加信息");
	private JButton btn2 = new JButton("重置信息");
	FlightDao flightDAO = new FlightDao();
	public AddFlight() {
		
		//将文本框添加进上部面板
		for(int i=0;i<6;i++){
			jlArray[i].setBounds(100, 100+i*60, 70, 30);
			jtxtArray[i].setBounds(175, 100+i*60, 120,30);
			if(i==1 || i==2 ||i==3) {
				JLabel tip = new JLabel("* 值必须为整数类型！");
				tip.setForeground(new Color(255, 0, 0));
				tip.setBounds(300, 100+i*60, 140, 30);
				this.add(tip);
			}
			this.add(jlArray[i]);
			this.add(jtxtArray[i]);
			
		}
		btn.setBounds(205, 450, 90, 30);
		btn2.setBounds(100, 450, 90, 30);
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
	
/*	public  static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new AddFlight());
		jf.setBounds(200, 200, 900, 700);
//		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			System.out.println("12");
			String[] str = new String[6];
			for(int i=0;i<jtxtArray.length;i++) {
				if(jtxtArray[i].getText().trim().equals("")) {
					//当各文本框为空提示
					JOptionPane.showMessageDialog(
				  			this,"航班信息不能为空！","提示消息",JOptionPane.WARNING_MESSAGE);
					return;	  	
				}	
				str[i] = jtxtArray[i].getText().trim();			
			}
			if(!validateNum(str[1],str[2],str[3])) {
				JOptionPane.showMessageDialog(
			  			this,"航班信息添加失败！\n请检查所填信息类型是否正确！","提示消息",JOptionPane.WARNING_MESSAGE);
				return;	
			}
			
			boolean flag = flightDAO.insert(str[0], str[1],str[2],str[3],str[4],str[5]);
			if(flag) {
				for(int i=0;i<jtxtArray.length;i++) {
					jtxtArray[i].setText("");		
				}
				JOptionPane.showMessageDialog(
			  			this,"航班信息添加成功！","提示消息",JOptionPane.INFORMATION_MESSAGE);
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
