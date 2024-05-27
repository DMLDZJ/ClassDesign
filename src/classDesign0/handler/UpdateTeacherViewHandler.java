package classDesign0.handler;

import classDesign0.entity.TeacherDO;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.TeacherServiceImpl;
import classDesign0.view.MainView;
import classDesign0.view.UpdateTeacherView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTeacherViewHandler implements ActionListener {
    private final UpdateTeacherView updateTeacherView;
    private final MainView mainView;
    public UpdateTeacherViewHandler(UpdateTeacherView updateTeacherView, MainView mainView) {
        this.updateTeacherView = updateTeacherView;
        this.mainView = mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)) {
            TeacherService teacherService = new TeacherServiceImpl();
            TeacherDO teacherDO = updateTeacherView.buildUpdateTeacherDO();
            boolean updateResult = teacherService.update(teacherDO);
            if(updateResult) {
                JOptionPane.showMessageDialog(updateTeacherView,"修改成功");
                mainView.reloadTable();;
                updateTeacherView.dispose();
            }else {
                JOptionPane.showMessageDialog(updateTeacherView,"修改失败");
            }
        }
    }
}
