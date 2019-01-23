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
	//������ǩ����
	private JLabel[] jlArray = new JLabel[] {
		new JLabel("����ţ�"),
		new JLabel("Ʊ�ۣ�"),
		new JLabel("��λ����"),
		new JLabel("ʣ����λ��"),
		new JLabel("ʼ�����У�"),
		new JLabel("Ŀ�ĳ��У�")
	};
	
	//�����ı�������
	private JTextField[] jtxtArray=new JTextField[]{
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField(),
		new JTextField(),new JTextField()
	};
	
	private JButton btn = new JButton("�����Ϣ");
	private JButton btn2 = new JButton("������Ϣ");
	FlightDao flightDAO = new FlightDao();
	public AddFlight() {
		
		//���ı�����ӽ��ϲ����
		for(int i=0;i<6;i++){
			jlArray[i].setBounds(100, 100+i*60, 70, 30);
			jtxtArray[i].setBounds(175, 100+i*60, 120,30);
			if(i==1 || i==2 ||i==3) {
				JLabel tip = new JLabel("* ֵ����Ϊ�������ͣ�");
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
		// ���óɾ��Զ�λ
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
					//�����ı���Ϊ����ʾ
					JOptionPane.showMessageDialog(
				  			this,"������Ϣ����Ϊ�գ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
					return;	  	
				}	
				str[i] = jtxtArray[i].getText().trim();			
			}
			if(!validateNum(str[1],str[2],str[3])) {
				JOptionPane.showMessageDialog(
			  			this,"������Ϣ���ʧ�ܣ�\n����������Ϣ�����Ƿ���ȷ��","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
				return;	
			}
			
			boolean flag = flightDAO.insert(str[0], str[1],str[2],str[3],str[4],str[5]);
			if(flag) {
				for(int i=0;i<jtxtArray.length;i++) {
					jtxtArray[i].setText("");		
				}
				JOptionPane.showMessageDialog(
			  			this,"������Ϣ��ӳɹ���","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
				return;	
			}
		}else if(e.getSource()==btn2) {
			for(int i=0;i<jtxtArray.length;i++) {
				jtxtArray[i].setText("");		
			}
		}
		
	}
	
	//������ʽ��֤�ַ�����ʽ
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
