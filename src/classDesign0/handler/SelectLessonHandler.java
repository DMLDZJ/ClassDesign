package classDesign0.handler;

import classDesign0.entity.LessonDO;
import classDesign0.service.LessonService;
import classDesign0.service.impl.LessonServiceImpl;
import classDesign0.view.SelectLessonView;
import classDesign0.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectLessonHandler implements ActionListener {
    private SelectLessonView selectLessonView;
    private UserView userView;

    public SelectLessonHandler(SelectLessonView selectLessonView, UserView userView) {
        this.selectLessonView = selectLessonView;
        this.userView = userView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("确定".equals(text)) {
            LessonService lessonService = new LessonServiceImpl();
            LessonDO lessonDO = selectLessonView.buildUpdateLessonDO();
            boolean updateResult = lessonService.update(lessonDO);
            if(updateResult) {
                JOptionPane.showMessageDialog(selectLessonView,"选课成功");
                userView.reloadTable();
                selectLessonView.dispose();
            }else {
                JOptionPane.showMessageDialog(selectLessonView,"选课失败");
            }
        }
    }
}
