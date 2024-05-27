package classDesign0.view;

import classDesign0.handler.SelectLessonHandler;
import classDesign0.handler.UpdateLessonViewHandler;
import classDesign0.handler.UserViewHandler;
import classDesign0.request.LessonRequest;
import classDesign0.request.TeacherRequest;
import classDesign0.res.TableDTO;
import classDesign0.service.LessonService;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.LessonServiceImpl;
import classDesign0.service.impl.TeacherServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class UserView extends JFrame {
    ImageIcon icon = new ImageIcon(".\\img\\CCSU.jpg");
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton selectLessonBtn = new JButton("选课");
//    JButton lookLessonBtn = new JButton("查看课程");
    JTextField searchTxt = new JTextField(15);
    JButton searchBtn = new JButton("查询");
    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    ViewTable viewTable = new ViewTable();
    private  int pageNow = 1; // 当前页码
    UserViewHandler userViewHandler;
    SelectLessonHandler selectLessonHandler;
    public UserView() {
        super("欢迎使用教师选课系统");
        Container contentPane = getContentPane();
        userViewHandler = new UserViewHandler(this);
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
    private Vector<Vector<Object>> getVectors() {
        LessonService lessonService = new LessonServiceImpl();
        LessonRequest request = new LessonRequest();
        request.setPageNow(pageNow);
        request.setPageSize(10);
        request.setSearchKey(searchTxt.getText().trim());
        TableDTO tableDTO = lessonService.retrieveLesson1(request);
        return tableDTO.getData();
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
        southPanel.add(new JLabel("教师选课界面"));
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }

    private void NorthLayout(Container contentPane) {

//        northPanel.add(lookLessonBtn);
        selectLessonBtn.addActionListener(userViewHandler);
        searchBtn.addActionListener(userViewHandler);
        northPanel.add(selectLessonBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public void reloadTable() {
        Vector<Vector<Object>> data = getVectors();
        TableModel.updateModel(data,2);
    }

    public int[] getSelectedLessonIds() {
        int[] selectRows = viewTable.getSelectedRows();
        int[] ids = new int[selectRows.length];
        for(int i = 0; i < selectRows.length; i++) {
            int rowIndex = selectRows[i];
            Object idObj = viewTable.getValueAt(rowIndex,0);
            ids[i] = Integer.valueOf(idObj.toString());
        }
        return ids;
    }

    public void setPageNow(int i) {
        this.pageNow = i;
    }
}
