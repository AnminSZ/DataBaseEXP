package view;

import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dao.CarDao;
import dao.ResultSetTableModel;

public class CarInfo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private static ResultSetTableModel model;
	private static ResultSet rs;
	private static CarDao carDAO = new CarDao();
	private static JTable table;

	public CarInfo() {
		rs = carDAO.query();
		// ʹ�ò�ѯ����resultset����tablemodel����
		model = new ResultSetTableModel(rs);

		table = new JTable(model);
		table.setRowHeight(35);

		scrollPane = new JScrollPane(table);
		this.setLayout(new GridLayout(1, 1));// ����������Ϊ���񲼾�
		this.add(scrollPane);
		// ���ô���Ĵ�Сλ�ü��ɼ���
		this.setBounds(5, 5, 900, 700);
		this.setVisible(true);
	}

	// ˢ����ͼ��ʾ
	public static void updateModel() {
		rs = carDAO.query();
		model = new ResultSetTableModel(rs);
		table.setModel(model);
		SetTableBg.setColumnColor(table);
	}
}
