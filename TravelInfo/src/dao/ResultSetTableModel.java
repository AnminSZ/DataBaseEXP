package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

//将一个resultset 包装成tablemodel
public class ResultSetTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd;

	// 构造器 初始化rs和rsmd两个属性
	public ResultSetTableModel(ResultSet s) {
		rs = s;
		try {
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getRowCount() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public int getColumnCount() {
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	// 用于设置该tablemodel 指定单元格的值
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			rs.absolute(rowIndex + 1);
			return rs.getObject(columnIndex + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 用于为该tablemodel设置列名
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		try {
			return rsmd.getColumnName(column + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// 让每个单元格可编辑
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// 指定主键列不可更新
		if (columnIndex + 1 == 1)
			return false;
		return true;
	}

	// 用户编辑单元格时 会触发该方法
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		try {
			// 结果集定位到对应的行数
			rs.absolute(rowIndex + 1);
			// 修改单元格对应的值
			rs.updateObject(columnIndex + 1, aValue);
			// 出现乱码的原因是数据库连接后面要加上字符编码规则
			// 提交修改
			rs.updateRow();
			// 触发单元格的修改事件
			fireTableCellUpdated(rowIndex, columnIndex);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
