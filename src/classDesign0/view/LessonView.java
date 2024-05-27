package classDesign0.view;

import classDesign0.handler.LessonViewHandler;
import classDesign0.request.LessonRequest;
import classDesign0.res.TableDTO;
import classDesign0.service.LessonService;
import classDesign0.service.impl.LessonServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LessonView extends JFrame {
    ImageIcon icon = new ImageIcon("D:\\Codes\\Java\\ClassDesign\\img\\CCSU.jpg");
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton lookTeacher = new JButton("查看教师");
    JButton addLessonBtn = new JButton("添加课程");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JLabel lessonMessage = new JLabel("课程信息界面");
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    ViewTable viewTable = new ViewTable();
    private  int pageNow = 1; // 当前页码
    private  int pageSize = 10; // 一页几条
    LessonViewHandler lessonViewHandler;
    public LessonView() {
        super("课程-教师选课管理系统");

        Container contentPane = getContentPane();
        lessonViewHandler = new LessonViewHandler(this);
        // North的组件
        NorthLayout(contentPane);
        // Center的组件
        CenterLayout(contentPane);
        // South的组件
        SouthLayout(contentPane);
        setIconImage(icon.getImage());
        setSize(800,500);
        // 根据屏幕大小设置主界面
        //setBounds(DimensionUtil.getBounds());
        // 设置窗体完全充满整个屏幕的可见大小
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    private void CenterLayout(Container contentPane) {
        Vector<Vector<Object>> data = getVectors();
        TableModel lessonTable = TableModel.assembleModel(data,2);
        assert lessonTable != null;
        viewTable.setModel(lessonTable);
        JScrollPane jScrollPane = new JScrollPane(viewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
    }

    private void SouthLayout(Container contentPane) {
        preBtn.addActionListener(lessonViewHandler);
        nextBtn.addActionListener(lessonViewHandler);
        southPanel.add(lessonMessage);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void NorthLayout(Container contentPane) {
        // 添加事件
        lookTeacher.addActionListener(lessonViewHandler);
        addLessonBtn.addActionListener(lessonViewHandler);
        searchBtn.addActionListener(lessonViewHandler);
        updateBtn.addActionListener(lessonViewHandler);
        delBtn.addActionListener(lessonViewHandler);
        northPanel.add(lookTeacher);
        northPanel.add(addLessonBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    private Vector<Vector<Object>> getVectors() {
        LessonService lessonService = new LessonServiceImpl();
        LessonRequest request = new LessonRequest();
        request.setPageNow(pageNow);
        request.setPageSize(pageSize);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = lessonService.retrieveLesson(request);
        return tableDTO.getData();
    }
    public void reloadTable() {
        Vector<Vector<Object>> data = getVectors();
        TableModel.updateModel(data,2);
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public  int[] getSelectedLessonIds() {
        int[] selectRows = viewTable.getSelectedRows();
        int[] ids = new int[selectRows.length];
        for(int i = 0; i < selectRows.length; i++) {
            int rowIndex = selectRows[i];
            Object idObj = viewTable.getValueAt(rowIndex,0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }


}
