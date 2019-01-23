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
	// ������ǩ����
	private JLabel[] jlArray = new JLabel[] { new JLabel("�˿�������") };

	// �����ı�������
	private JTextField[] jtxtArray = new JTextField[] { new JTextField() };

	private JButton btn = new JButton("�����Ϣ");
	private JButton btn2 = new JButton("������Ϣ");
	CustomersDao customerDAO= new CustomersDao();

	public AddCustomer() {
		// ���ı�����ӽ��ϲ����
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
		// ���óɾ��Զ�λ
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
					//�����ı���Ϊ����ʾ
					JOptionPane.showMessageDialog(
				  			this,"�˿���Ϣ����Ϊ�գ�","��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
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
			  			this,"�˿���Ϣ��ӳɹ���","��ʾ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
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
