package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

//��һ��resultset ��װ��tablemodel
public class ResultSetTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData rsmd;

	// ������ ��ʼ��rs��rsmd��������
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

	// �������ø�tablemodel ָ����Ԫ���ֵ
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

	// ����Ϊ��tablemodel��������
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

	// ��ÿ����Ԫ��ɱ༭
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// ָ�������в��ɸ���
		if (columnIndex + 1 == 1)
			return false;
		return true;
	}

	// �û��༭��Ԫ��ʱ �ᴥ���÷���
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		try {
			// �������λ����Ӧ������
			rs.absolute(rowIndex + 1);
			// �޸ĵ�Ԫ���Ӧ��ֵ
			rs.updateObject(columnIndex + 1, aValue);
			// ���������ԭ�������ݿ����Ӻ���Ҫ�����ַ��������
			// �ύ�޸�
			rs.updateRow();
			// ������Ԫ����޸��¼�
			fireTableCellUpdated(rowIndex, columnIndex);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
