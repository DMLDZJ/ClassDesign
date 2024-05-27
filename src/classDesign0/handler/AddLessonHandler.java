package classDesign0.handler;

import classDesign0.entity.LessonDO;
import classDesign0.service.LessonService;
import classDesign0.service.impl.LessonServiceImpl;
import classDesign0.view.AddLessonView;
import classDesign0.view.LessonView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLessonHandler implements ActionListener {
    private AddLessonView addLessonView;
    private LessonView lessonView;
    public AddLessonHandler(AddLessonView addLessonView, LessonView lessonView) {
        this.addLessonView = addLessonView;
        this.lessonView = lessonView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)) {
            LessonService lessonService = new LessonServiceImpl();
            LessonDO LessonDO = addLessonView.buildLessonDO();
            boolean addResult = lessonService.addLesson(LessonDO);
            if(addResult) {
                JOptionPane.showMessageDialog(addLessonView,"添加完成");
                // 重新加载表格
                lessonView.reloadTable();
                addLessonView.dispose();
            }else {
                JOptionPane.showMessageDialog(addLessonView,"添加失败");

            }
        }
    }
}
