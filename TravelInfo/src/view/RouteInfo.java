package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import dao.CustomersDao;
import dao.ResultSetTableModel;
import dao.RouteDao;

public class RouteInfo extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 创建一个上下方向分割的JSplitPane对象
	private JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JPanel jp = new JPanel();
	private JScrollPane scrollPane;
	private ResultSetTableModel model;
	private JComboBox<String> customerName = new JComboBox<>();
	private JLabel jlb = new JLabel("请选择您要查询的用户姓名：");
	private JButton btn = new JButton("查询线路");
	private JTable table;
	private RouteDao routeDAO = new RouteDao();
	private CustomersDao customerDAO = new CustomersDao();
	private ResultSet rs;

	public RouteInfo() {
		initNameComboBox();
		this.setLayout(new GridLayout(1, 1));// 声明本界面为网格布局
		jp.setLayout(null);// 上班部分为空白布局
		jp.setBackground(new Color(240, 248,255));
		jsp.setDividerLocation(200);// 设置jspt中分割条的初始位置
		jsp.setDividerSize(4);// 设置分隔条的宽度
		// 为table填充数据
		String name = customerName.getSelectedItem().toString();
		rs = routeDAO.query(name);
		model = new ResultSetTableModel(rs);
		table = new JTable(model);
		table.setRowHeight(35);
		table.setEnabled(false);
		// 设置单元格内容居中
		SetTableBg.setColumnColor(table);
		scrollPane = new JScrollPane(table);
		// 设置上下分割
		jsp.setTopComponent(jp);
		jsp.setBottomComponent(scrollPane);
		// 上面板内容设置
		jlb.setBounds(100, 50, 200, 30);
		customerName.setSelectedIndex(0);
		customerName.setBounds(310, 50, 100, 30);
		btn.setBounds(430, 50, 90, 30);
		jp.add(jlb);
		jp.add(customerName);
		jp.add(btn);
		btn.addActionListener(this);
		this.add(jsp);
		this.setVisible(true);
	}

/*	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.add(new RouteInfo());
		jf.setBounds(200, 200, 900, 700); // jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}*/

	private void initNameComboBox() {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			String name = customerName.getSelectedItem().toString();
			rs = routeDAO.query(name);
			model = new ResultSetTableModel(rs);
			table.setModel(model);
			SetTableBg.setColumnColor(table);
		}

	}

	// 刷新视图显示
	public void updateModel() {
		initNameComboBox();
		this.repaint();
		this.updateUI();
	}

}
