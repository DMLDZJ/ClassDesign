package classDesign0.handler;

import classDesign0.service.LessonService;
import classDesign0.service.impl.LessonServiceImpl;
import classDesign0.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonViewHandler implements ActionListener {
    private LessonView lessonView;
    public LessonViewHandler(LessonView lessonView) {
        this.lessonView = lessonView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("查看教师".equals(text)){
            new MainView();
            lessonView.dispose();
        } else if ("添加课程".equals(text)) {
            new AddLessonView(lessonView);
        }else if("查询".equals(text)) {
            lessonView.setPageNow(1);
            lessonView.reloadTable();
        }else if("修改".equals(text)){
            int[] selectedLessonIds = lessonView.getSelectedLessonIds();
            if(selectedLessonIds.length != 1) {
                JOptionPane.showMessageDialog(lessonView,"请选择一行修改");
                return ;
            }
            new UpdateLessonView(lessonView,selectedLessonIds[0]);
        }else if("删除".equals(text)){
            int[] selectedTeacherIds = lessonView.getSelectedLessonIds();
            if(selectedTeacherIds.length != 1) {
                JOptionPane.showMessageDialog(lessonView,"请选择一行删除");
                return ;
            }
            int option = JOptionPane.showConfirmDialog(lessonView,"你确认要删除吗？","请确认是否删除",JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION){
                LessonService lessonService = new LessonServiceImpl();
                boolean delResult = lessonService.del(selectedTeacherIds[0]);
                if(delResult) {
                    JOptionPane.showMessageDialog(lessonView,"已删除");
                    lessonView.reloadTable();
                }else {
                    JOptionPane.showMessageDialog(lessonView,"删除失败");
                }
            }
        }else if("上一页".equals(text)) {
            lessonView.setPageNow(lessonView.getPageNow() - 1);
            lessonView.reloadTable();
        }else if("下一页".equals(text)) {
            lessonView.setPageNow(lessonView.getPageNow() + 1);
            lessonView.reloadTable();
        }
    }
}
