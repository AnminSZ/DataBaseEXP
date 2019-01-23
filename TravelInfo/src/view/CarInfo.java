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
		// 使用查询到的resultset创建tablemodel对象
		model = new ResultSetTableModel(rs);

		table = new JTable(model);
		table.setRowHeight(35);

		scrollPane = new JScrollPane(table);
		this.setLayout(new GridLayout(1, 1));// 声明本界面为网格布局
		this.add(scrollPane);
		// 设置窗体的大小位置及可见性
		this.setBounds(5, 5, 900, 700);
		this.setVisible(true);
	}

	// 刷新视图显示
	public static void updateModel() {
		rs = carDAO.query();
		model = new ResultSetTableModel(rs);
		table.setModel(model);
		SetTableBg.setColumnColor(table);
	}
}
