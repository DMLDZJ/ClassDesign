package classDesign0.view;

import classDesign0.handler.MainViewHandler;
import classDesign0.request.TeacherRequest;
import classDesign0.res.TableDTO;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.TeacherServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MainView extends JFrame {
    ImageIcon icon = new ImageIcon(".\\img\\CCSU.jpg");
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addTeacherBtn = new JButton("添加教师");
    JButton lookLessonBtn = new JButton("查看课程");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JButton sortHours = new JButton("按课时排序");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    ViewTable viewTable = new ViewTable();
    private  int pageNow = 1; // 当前页码
    MainViewHandler mainViewHandler;
    public MainView() {
        super("管理员-教师选课管理系统");

        Container contentPane = getContentPane();
        mainViewHandler = new MainViewHandler(this);

        // North的组件
        NorthLayout(contentPane);
        // Center的组件
        CenterLayout(contentPane);
        // South的组件
        SouthLayout(contentPane);
        setIconImage(icon.getImage());
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void CenterLayout(Container contentPane) {
        Vector<Vector<Object>> data = getVectors();
        TableModel teacherTable = TableModel.assembleModel(data,1);
        assert teacherTable != null;
        viewTable.setModel(teacherTable);
        JScrollPane jScrollPane = new JScrollPane(viewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    private void SouthLayout(Container contentPane) {
        southPanel.add(new JLabel("教师信息界面"));
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void NorthLayout(Container contentPane) {
        // 添加事件
        addTeacherBtn.addActionListener(mainViewHandler);
        lookLessonBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        sortHours.addActionListener(mainViewHandler);

        northPanel.add(lookLessonBtn);
        northPanel.add(addTeacherBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        northPanel.add(sortHours);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }

    private void showPreNext() {
        if(pageNow == 1) {
            preBtn.setVisible(false);
        }else {
            preBtn.setVisible(true);
        }
    }
    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }
    public void sortHours() {
        Vector<Vector<Object>> data = getVectors(1);
        TableModel.updateModel(data,1);
    }
    public void reloadTable() {
        Vector<Vector<Object>> data = getVectors();
        TableModel.updateModel(data,1);
    }
    
    public  int[] getSelectedTeacherIds() {
        int[] selectRows = viewTable.getSelectedRows();
        int[] ids = new int[selectRows.length];
        for(int i = 0; i < selectRows.length; i++) {
            int rowIndex = selectRows[i];
            Object idObj = viewTable.getValueAt(rowIndex,0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }
    private Vector<Vector<Object>> getVectors() {
        TeacherService teacherService = new TeacherServiceImpl();
        TeacherRequest request = new TeacherRequest();
        request.setPageNow(pageNow);
        // 一页几条
        int pageSize = 10;
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = teacherService.retrieveTeachers(request);
        return tableDTO.getData();
    }
    private Vector<Vector<Object>> getVectors(int a) {
        TeacherService teacherService = new TeacherServiceImpl();
        TeacherRequest request = new TeacherRequest();
        request.setPageNow(pageNow);
        // 一页几条
        int pageSize = 10;
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = teacherService.sortHours();
        return tableDTO.getData();
    }

}