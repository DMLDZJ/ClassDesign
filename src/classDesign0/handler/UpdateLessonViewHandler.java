package classDesign0.handler;

import classDesign0.entity.LessonDO;
import classDesign0.entity.TeacherDO;
import classDesign0.service.LessonService;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.LessonServiceImpl;
import classDesign0.service.impl.TeacherServiceImpl;
import classDesign0.view.LessonView;
import classDesign0.view.MainView;
import classDesign0.view.UpdateLessonView;
import classDesign0.view.UpdateTeacherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateLessonViewHandler implements ActionListener {
    private final UpdateLessonView updateLessonView;
    private final LessonView lessonView;
    public UpdateLessonViewHandler(UpdateLessonView updateLessonView, LessonView lessonView) {
        this.updateLessonView = updateLessonView;
        this.lessonView = lessonView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)) {
            LessonService lessonService = new LessonServiceImpl();
            LessonDO lessonDO = updateLessonView.buildUpdateLessonDO();
            boolean updateResult = lessonService.update(lessonDO);
            if(updateResult) {
                JOptionPane.showMessageDialog(updateLessonView,"修改成功");
                lessonView.reloadTable();;
                updateLessonView.dispose();
            }else {
                JOptionPane.showMessageDialog(updateLessonView,"修改失败");
            }
        }
    }
}
