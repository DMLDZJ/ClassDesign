package classDesign0.view;


import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ViewTable extends JTable {
    public ViewTable() {
        // 设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.BLACK);
        // 设置表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.BLACK);
        // 设置表格线
        setGridColor(Color.BLACK);
        // 设置行高
        setRowHeight(30);
        // 设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }
    public static void main(String[] args) {
        new ViewTable();
    }
}
