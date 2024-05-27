package classDesign0.handler;

import classDesign0.entity.TeacherDO;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.TeacherServiceImpl;
import classDesign0.view.AddTeacherView;
import classDesign0.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddTeacherHandler implements ActionListener {
    private AddTeacherView addTeacherView;
    private MainView mainView;
    public AddTeacherHandler(AddTeacherView addTeacherView,MainView mainView) {
        this.addTeacherView = addTeacherView;
        this.mainView = mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("添加".equals(text)) {
            TeacherService teacherService = new TeacherServiceImpl();
            TeacherDO teacherDO = addTeacherView.buildTeacherDO();
            boolean addResult = teacherService.addTeacher(teacherDO);
            if(addResult) {
                JOptionPane.showMessageDialog(addTeacherView,"添加完成");

                // 重新加载表格
                mainView.reloadTable();
                addTeacherView.dispose();
            }else {
                JOptionPane.showMessageDialog(addTeacherView,"添加失败");

            }
        }

    }
}
