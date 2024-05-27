package classDesign0.handler;

import classDesign0.service.TeacherService;
import classDesign0.service.impl.TeacherServiceImpl;
import classDesign0.view.*;
import classDesign0.view.UpdateTeacherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {
    private MainView mainView;
    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加教师".equals(text)) {
            new AddTeacherView(mainView);
        }else if("查看课程".equals(text)) {
            new LessonView();
            mainView.dispose();
        }else if("修改".equals(text)) {
            int[] selectedTeacherIds = mainView.getSelectedTeacherIds();
            if(selectedTeacherIds.length != 1) {
                JOptionPane.showMessageDialog(mainView,"请选择一行进行修改");
                return ;
            }
            new UpdateTeacherView(mainView,selectedTeacherIds[0]);
        }else if("删除".equals(text)) {
            int[] selectedTeacherIds = mainView.getSelectedTeacherIds();
            if(selectedTeacherIds.length != 1) {
                JOptionPane.showMessageDialog(mainView,"请选择一行删除");
                return ;
            }
            int option = JOptionPane.showConfirmDialog(mainView,"你确认要删除吗？","请确认是否删除",JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                TeacherService teacherService = new TeacherServiceImpl();
                boolean delResult = teacherService.del(selectedTeacherIds[0]);
                if(delResult) {
                    JOptionPane.showMessageDialog(mainView,"已删除");
                    mainView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }

        }else if("查询".equals(text)) {
            mainView.setPageNow(1);
            mainView.reloadTable();
        }else if("按课时排序".equals(text)){
            mainView.sortHours();
        }else if("上一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() - 1);
            mainView.reloadTable();
        }else if("下一页".equals(text)) {
            mainView.setPageNow(mainView.getPageNow() + 1);
            mainView.reloadTable();
        }
    }
}
