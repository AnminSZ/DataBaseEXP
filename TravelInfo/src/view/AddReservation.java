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

	// ������ǩ����
	private JLabel[] jlArray = new JLabel[] { new JLabel("�˿�������"), new JLabel("Ԥ�����ͣ�"), new JLabel("Ԥ����ţ�") };
	private static JComboBox<String> customerName = new JComboBox<>();
	private JComboBox<String> typeName = new JComboBox<>();
	// �����ı�������
	private JTextField jtxtFiled = new JTextField();

	private JButton btn = new JButton("�����Ϣ");
	private JButton btn2 = new JButton("������Ϣ");

	private ReservationDao reservationDao = new ReservationDao();
	private static CustomersDao customerDAO = new CustomersDao();

	public AddReservation() {
		initNameComboBox();
		typeName.addItem("Ԥ������");
		typeName.addItem("Ԥ���⳵");
		typeName.addItem("Ԥ���Ƶ�");
		// ���ı�����ӽ��ϲ����
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
				JLabel tip = new JLabel("* ������ѡԤ�����͵�����ID�ż��ɣ�");
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
				JOptionPane.showMessageDialog(this, "������Ϣ�����пգ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!validateNum(resvKey)) {
				JOptionPane.showMessageDialog(this, "�������������Ѵ��ڵ�ID�ţ�", "��ʾ��Ϣ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			boolean flag = reservationDao.insert(selectName, selectType, Integer.valueOf(resvKey));
			if (flag) {
				customerName.setSelectedIndex(0);
				typeName.setSelectedIndex(0);
				jtxtFiled.setText("");
				JOptionPane.showMessageDialog(this, "Ԥ����Ϣ��ӳɹ���", "��ʾ��Ϣ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} else if (e.getSource() == btn2) {
			customerName.setSelectedIndex(0);
			typeName.setSelectedIndex(0);
			jtxtFiled.setText("");
		}
	}

	// ˢ����ͼ��ʾ
	public void updateModel() {
		initNameComboBox();
		this.repaint();
		this.updateUI();
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
