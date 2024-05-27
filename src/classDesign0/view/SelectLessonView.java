package classDesign0.view;

import classDesign0.entity.LessonDO;
import classDesign0.handler.SelectLessonHandler;
import classDesign0.handler.UpdateLessonViewHandler;
import classDesign0.service.LessonService;
import classDesign0.service.impl.LessonServiceImpl;

import javax.swing.*;
import java.awt.*;

public class SelectLessonView extends JFrame{

        ImageIcon icon = new ImageIcon("D:\\Codes\\Java\\ClassDesign\\img\\CCSU.jpg");
        JLabel NameLabel = new JLabel("选择",JLabel.CENTER);
        SpringLayout springLayout = new SpringLayout();
        JPanel centerPane = new JPanel(springLayout);
        JLabel lessonIdLabel = new JLabel("课程编号:");
        JTextField lessonIdTxt = new JTextField(15);
        JLabel lessonNameLabel = new JLabel("课程名称:");
        JTextField lessonNameTxt = new JTextField(15);
        JLabel lessonHoursLabel = new JLabel("课时:");
        JTextField lessonHoursTxt = new JTextField(15);
        JLabel lessonNumClassLabel = new JLabel("授课班级数:");
        JTextField lessonNumClassTxt = new JTextField(15);
        JLabel lessonTeacherLabel = new JLabel("授课教师:");
        JTextField lessonTeacherTxt = new JTextField(15);

        JButton updateBtn = new JButton("确定");
        SelectLessonHandler selectLessonHandler;
        UserView userView;
        public SelectLessonView(UserView userView, int selectedLessonId) {
            super("选课");
            LessonService lessonService = new LessonServiceImpl();
            LessonDO selectedLes= lessonService.getById(selectedLessonId);
            selectLessonHandler = new SelectLessonHandler(this,userView);
            Container contentPane = getContentPane();

            // 标题
            NameLabel.setForeground(Color.BLACK);
            NameLabel.setFont(new Font("华文行楷",Font.PLAIN,30));

            centerPane.add(lessonIdLabel);
            centerPane.add(lessonIdTxt);
            centerPane.add(lessonNameLabel);
            centerPane.add(lessonNameTxt);
            centerPane.add(lessonHoursLabel);
            centerPane.add(lessonHoursTxt);
            centerPane.add(lessonNumClassLabel);
            centerPane.add(lessonNumClassTxt);
            centerPane.add(lessonTeacherLabel);
            centerPane.add(lessonTeacherTxt);
            updateBtn.addActionListener(selectLessonHandler);
            centerPane.add(updateBtn);


            // lessonIdLabel
            springLayout.putConstraint(SpringLayout.NORTH,lessonIdLabel,5,SpringLayout.NORTH,centerPane);
            springLayout.putConstraint(SpringLayout.WEST,lessonIdLabel,20,SpringLayout.WEST,centerPane);
            // lessonIdTxt
            springLayout.putConstraint(SpringLayout.NORTH,lessonIdTxt,0,SpringLayout.NORTH,lessonIdLabel);
            springLayout.putConstraint(SpringLayout.WEST,lessonIdTxt,20,SpringLayout.EAST,lessonIdLabel);
            lessonIdTxt.setText(String.valueOf(selectedLes.getId()));
            lessonIdTxt.setEnabled(false);
            // lessonNameLabel
            springLayout.putConstraint(SpringLayout.EAST,lessonNameLabel,0,SpringLayout.EAST,lessonIdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,lessonNameLabel,20,SpringLayout.SOUTH,lessonIdLabel);
            // lessonNameTxt
            springLayout.putConstraint(SpringLayout.NORTH,lessonNameTxt,0,SpringLayout.NORTH,lessonNameLabel);
            springLayout.putConstraint(SpringLayout.WEST,lessonNameTxt,20,SpringLayout.EAST,lessonNameLabel);
            lessonNameTxt.setText(selectedLes.getLessonName());
            lessonNameTxt.setEnabled(false);
            // lessonHoursLabel
            springLayout.putConstraint(SpringLayout.EAST,lessonHoursLabel,0,SpringLayout.EAST,lessonNameLabel);
            springLayout.putConstraint(SpringLayout.NORTH,lessonHoursLabel,20,SpringLayout.SOUTH,lessonNameLabel);
            // lessonHoursTxt
            springLayout.putConstraint(SpringLayout.NORTH,lessonHoursTxt,0,SpringLayout.NORTH,lessonHoursLabel);
            springLayout.putConstraint(SpringLayout.WEST,lessonHoursTxt,20,SpringLayout.EAST,lessonHoursLabel);
            lessonHoursTxt.setText(String.valueOf(selectedLes.getHour()));
            lessonHoursTxt.setEnabled(false);
            // lessonNumClassLabel
            springLayout.putConstraint(SpringLayout.EAST,lessonNumClassLabel,0,SpringLayout.EAST,lessonHoursLabel);
            springLayout.putConstraint(SpringLayout.NORTH,lessonNumClassLabel,20,SpringLayout.SOUTH,lessonHoursLabel);
            // lessonNumClassTxt
            springLayout.putConstraint(SpringLayout.NORTH,lessonNumClassTxt,0,SpringLayout.NORTH,lessonNumClassLabel);
            springLayout.putConstraint(SpringLayout.WEST,lessonNumClassTxt,20,SpringLayout.EAST,lessonNumClassLabel);
            lessonNumClassTxt.setText(String.valueOf(selectedLes.getClassNum()));
            lessonNumClassTxt.setEnabled(false);
            // lessonTeacherLabel
            springLayout.putConstraint(SpringLayout.EAST,lessonTeacherLabel,0,SpringLayout.EAST,lessonNumClassLabel);
            springLayout.putConstraint(SpringLayout.NORTH,lessonTeacherLabel,20,SpringLayout.SOUTH,lessonNumClassLabel);
            // lessonTeacherTxt
            springLayout.putConstraint(SpringLayout.NORTH,lessonTeacherTxt,0,SpringLayout.NORTH,lessonTeacherLabel);
            springLayout.putConstraint(SpringLayout.WEST,lessonTeacherTxt,20,SpringLayout.EAST,lessonTeacherLabel);
            lessonTeacherTxt.setText(selectedLes.getLessonTeacher());
            // updateBtn
            springLayout.putConstraint(SpringLayout.EAST,updateBtn,80,SpringLayout.EAST,lessonTeacherLabel);
            springLayout.putConstraint(SpringLayout.NORTH,updateBtn,20,SpringLayout.SOUTH,lessonTeacherLabel);

            contentPane.add(centerPane,BorderLayout.CENTER);
            contentPane.add(NameLabel,BorderLayout.NORTH);
            setIconImage(icon.getImage());
            setSize(270,350);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        }
        // 修改后的课程对象
        public LessonDO buildUpdateLessonDO() {
            LessonDO lessonDO = new LessonDO();
            lessonDO.setId(Integer.parseInt(lessonIdTxt.getText()));
            lessonDO.setLessonName(lessonNameTxt.getText());
            lessonDO.setHour(Integer.parseInt(lessonHoursTxt.getText()));
            lessonDO.setClassNum(Integer.parseInt(lessonNumClassTxt.getText()));
            lessonDO.setLessonTeacher(lessonTeacherTxt.getText());
            return lessonDO;
        }
}
