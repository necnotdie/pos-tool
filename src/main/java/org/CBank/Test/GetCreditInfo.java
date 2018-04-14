package org.CBank.Test;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class GetCreditInfo {
	private String mdbPath;

	public String getMdbPath() {
		return mdbPath;
	}

	public void setMdbPath(String mdbPath) {
		this.mdbPath = mdbPath;
	}

	public void execute() {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultTableModel model = new DefaultTableModel();
		JTable jTable = new JTable(model);
		JScrollPane jScrollPane = new JScrollPane(jTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		File file = new File(mdbPath);
		System.out.println(file.getAbsolutePath());
		String dbUr1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
				+ file.getAbsolutePath();
		String user = "";
		String password = "";
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection connection = DriverManager.getConnection(dbUr1, user,
					password);
			System.out
					.println(file.getName().replaceAll("\\.[Mm][Dd][Bb]", ""));
			PreparedStatement statement = connection
					.prepareStatement("select * from "
							+ file.getName().replaceAll("\\.[Mm][Dd][Bb]", ""));
			ResultSet resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			Vector<String> vector = new Vector<String>();
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				vector.add(metaData.getColumnName(i + 1));
			}
			model.setColumnIdentifiers(vector);
			while (resultSet.next()) {
				Vector<Object> rows = new Vector<Object>();
				for (String columnName : vector) {
					rows.add(resultSet.getObject(columnName));
				}
				model.addRow(rows);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		FitTableColumns(jTable);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frame.add(jScrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public void FitTableColumns(JTable myTable) { // 設置table的列寬隨內容調整

		JTableHeader header = myTable.getTableHeader();

		int rowCount = myTable.getRowCount();

		Enumeration<TableColumn> columns = myTable.getColumnModel().getColumns();

		while (columns.hasMoreElements()) {

			TableColumn column = (TableColumn) columns.nextElement();

			int col = header.getColumnModel().getColumnIndex(

			column.getIdentifier());

			int width = (int) myTable.getTableHeader().getDefaultRenderer()

			.getTableCellRendererComponent(myTable,

			column.getIdentifier(), false, false, -1, col)

			.getPreferredSize().getWidth();

			for (int row = 0; row < rowCount; row++) {

				int preferedWidth = (int) myTable.getCellRenderer(row, col)

				.getTableCellRendererComponent(myTable,

				myTable.getValueAt(row, col), false, false,

				row, col).getPreferredSize().getWidth();

				width = Math.max(width, preferedWidth);

			}

			header.setResizingColumn(column);
			column.setWidth(width + myTable.getIntercellSpacing().width);
		}

	}

	public static void main(String[] args) {
		GetCreditInfo creditInfo = new GetCreditInfo();
		creditInfo.setMdbPath("D:\\央行费用分摊\\VSNC2017011903126224.mdb");
		creditInfo.execute();
	}
}
