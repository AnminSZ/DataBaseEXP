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

import dao.HotelDao;

public class AddHotel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// ������ǩ����
	private JLabel[] jlArray = new JLabel[] { new JLabel("�Ƶ����ƣ�"), new JLabel("�Ƶ�λ�ã�"), new JLabel("�Ƶ�۸�"),
			new JLabel("����������"), new JLabel("ʣ��������") };

	// �����ı�������
	private JTextField[] jtxtArray = new JTextField[] { new JTextField(), new JTextField(), new JTextField(),
			new JTextField(), new JTextField() };

	private JButton btn = new JButton("�����Ϣ");
	private JButton btn2 = new JButton("������Ϣ");
	HotelDao hotelDAO = new HotelDao();
	public AddHotel() {
		// ���ı�����ӽ��ϲ����
		for (int i = 0; i < jlArray.length; i++) {
			if (i == 1 || i == 0) {
				jtxtArray[i].setBounds(175, 100 + i * 60, 290, 30);
				jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
			}
			if (i == 2 || i == 3 || i == 4) {
				jtxtArray[i].setBounds(175, 100 + i * 60, 160, 30);
				jlArray[i].setBounds(100, 100 + i * 60, 70, 30);
				JLabel tip = new JLabel("* ֵ����Ϊ�������ͣ�");
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
		// ���óɾ��Զ�λ
		this.setLayout(null);
		this.setBounds(5, 5, 900, 700);
		this.setVisible(true);
		this.setBackground(new Color(240, 248,255));
	}

	/*
	 * public static void main(String[] args) { for(int i=0; i<25 ;i++) { try {
	 * Thread.sleep(50); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } System.out.println(new Date().getTime());
	 * }}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			String[] str = new String[jtxtArray.length];
			for (int i = 0; i < jtxtArray.length; i++) {
				if (jtxtArray[i].getText().trim().equals("")) {
					// �����ı���Ϊ����ʾ
					JOptionPane.showMessageDialog(this, "�Ƶ���Ϣ����Ϊ�գ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
					return;
				}
				str[i] = jtxtArray[i].getText().trim();
			}
			if (!validateNum(str[4], str[2], str[3])) {
				JOptionPane.showMessageDialog(this, "�Ƶ���Ϣ���ʧ�ܣ�\n����������Ϣ�����Ƿ���ȷ��", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			boolean flag = hotelDAO.insert(str[0], str[1], str[2], str[3], str[4]);
			if (flag) {
				for (int i = 0; i < jtxtArray.length; i++) {
					jtxtArray[i].setText("");
				}
				JOptionPane.showMessageDialog(this, "�Ƶ���Ϣ��ӳɹ���", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if (e.getSource() == btn2) {
			for (int i = 0; i < jtxtArray.length; i++) {
				jtxtArray[i].setText("");
			}
		}

	}

	// ������ʽ��֤�ַ�����ʽ
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
