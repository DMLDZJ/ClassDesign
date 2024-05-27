package classDesign0.view;

import classDesign0.entity.TeacherDO;
import classDesign0.handler.UpdateTeacherViewHandler;
import classDesign0.service.TeacherService;
import classDesign0.service.impl.TeacherServiceImpl;

import javax.swing.*;
import java.awt.*;

public class UpdateTeacherView extends JFrame {
    ImageIcon icon = new ImageIcon("D:\\Codes\\Java\\ClassDesign\\img\\CCSU.jpg");
    JLabel NameLabel = new JLabel("修改",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPane = new JPanel(springLayout);
    JLabel teacherIdLabel = new JLabel("教师编号:");
    JTextField teacherIdTxt = new JTextField(15);
    JLabel teacherNameLabel = new JLabel("教师姓名:");
    JTextField teacherNameTxt = new JTextField(15);
    JLabel teacherGenderLabel = new JLabel("性别:");
    JTextField teacherGenderTxt = new JTextField(15);
    JLabel teacherAgeLabel = new JLabel("年龄:");
    JTextField teacherAgeTxt = new JTextField(15);
    JLabel teacherSectionLabel = new JLabel("所在部门:");
    JTextField teacherSectionTxt = new JTextField(15);
    JLabel teacherMajorLabel = new JLabel("所学专业:");
    JTextField teacherMajorTxt = new JTextField(15);
    JLabel HoursLabel = new JLabel("课时");
    JTextField HoursTxt = new JTextField(15);
    JButton updateBtn = new JButton("修改");
    UpdateTeacherViewHandler updateTeacherViewHandler;
    public UpdateTeacherView(MainView mainView, int selectedTeacherId) {
        super("修改教师信息");
        updateTeacherViewHandler = new UpdateTeacherViewHandler(this,mainView);
        TeacherService teacherService = new TeacherServiceImpl();
        TeacherDO selectedTea= teacherService.getById(selectedTeacherId);

        Container contentPane = getContentPane();

        // 标题
        NameLabel.setForeground(Color.BLACK);
        NameLabel.setFont(new Font("华文行楷",Font.PLAIN,30));

        centerPane.add(teacherIdLabel);
        centerPane.add(teacherIdTxt);
        centerPane.add(teacherNameLabel);
        centerPane.add(teacherNameTxt);
        centerPane.add(teacherGenderLabel);
        centerPane.add(teacherGenderTxt);
        centerPane.add(teacherAgeLabel);
        centerPane.add(teacherAgeTxt);
        centerPane.add(teacherSectionLabel);
        centerPane.add(teacherSectionTxt);
        centerPane.add(teacherMajorLabel);
        centerPane.add(teacherMajorTxt);
        centerPane.add(HoursLabel);
        centerPane.add(HoursTxt);
        updateBtn.addActionListener(updateTeacherViewHandler);
        centerPane.add(updateBtn);


        // teacherIdLabel
        springLayout.putConstraint(SpringLayout.NORTH,teacherIdLabel,5,SpringLayout.NORTH,centerPane);
        springLayout.putConstraint(SpringLayout.WEST,teacherIdLabel,20,SpringLayout.WEST,centerPane);
        // teacherIdTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherIdTxt,0,SpringLayout.NORTH,teacherIdLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherIdTxt,20,SpringLayout.EAST,teacherIdLabel);
        teacherIdTxt.setText(String.valueOf(selectedTea.getId()));
        teacherIdTxt.setEnabled(false);
        // teacherNameLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherNameLabel,0,SpringLayout.EAST,teacherIdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherNameLabel,20,SpringLayout.SOUTH,teacherIdLabel);
        // teacherNameTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherNameTxt,0,SpringLayout.NORTH,teacherNameLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherNameTxt,20,SpringLayout.EAST,teacherNameLabel);
        teacherNameTxt.setText(selectedTea.getTeacherName());
        //teacherNameTxt.setEnabled(false);
        // teacherGenderLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherGenderLabel,0,SpringLayout.EAST,teacherNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherGenderLabel,20,SpringLayout.SOUTH,teacherNameLabel);
        // teacherGenderTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherGenderTxt,0,SpringLayout.NORTH,teacherGenderLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherGenderTxt,20,SpringLayout.EAST,teacherGenderLabel);
        teacherGenderTxt.setText(selectedTea.getTeacherGender());
        //teacherGenderTxt.setEnabled(false);
        // teacherAgeLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherAgeLabel,0,SpringLayout.EAST,teacherGenderLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherAgeLabel,20,SpringLayout.SOUTH,teacherGenderLabel);
        // teacherAgeTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherAgeTxt,0,SpringLayout.NORTH,teacherAgeLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherAgeTxt,20,SpringLayout.EAST,teacherAgeLabel);
        teacherAgeTxt.setText(String.valueOf(selectedTea.getTeacherAge()));
        //teacherAgeTxt.setEnabled(false);
        // teacherSectionLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherSectionLabel,0,SpringLayout.EAST,teacherAgeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherSectionLabel,20,SpringLayout.SOUTH,teacherAgeLabel);
        // teacherSectionTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherSectionTxt,0,SpringLayout.NORTH,teacherSectionLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherSectionTxt,20,SpringLayout.EAST,teacherSectionLabel);
        teacherSectionTxt.setText(selectedTea.getSection());
        //teacherSectionTxt.setEnabled(false);
        // teacherMajorLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherMajorLabel,0,SpringLayout.EAST,teacherSectionLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherMajorLabel,20,SpringLayout.SOUTH,teacherSectionLabel);
        // teacherMajorTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherMajorTxt,0,SpringLayout.NORTH,teacherMajorLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherMajorTxt,20,SpringLayout.EAST,teacherMajorLabel);
        teacherMajorTxt.setText(selectedTea.getMajor());
        //teacherMajorTxt.setEnabled(false);
        // HoursLabel
        springLayout.putConstraint(SpringLayout.EAST,HoursLabel,0,SpringLayout.EAST,teacherMajorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,HoursLabel,20,SpringLayout.SOUTH,teacherMajorLabel);
        // HoursTxt
        springLayout.putConstraint(SpringLayout.NORTH,HoursTxt,0,SpringLayout.NORTH,HoursLabel);
        springLayout.putConstraint(SpringLayout.WEST,HoursTxt,20,SpringLayout.EAST,HoursLabel);
        HoursTxt.setText(String.valueOf(selectedTea.getLessonNum()));
        // updateBtn
        springLayout.putConstraint(SpringLayout.EAST,updateBtn,80,SpringLayout.EAST,HoursLabel);
        springLayout.putConstraint(SpringLayout.NORTH,updateBtn,20,SpringLayout.SOUTH,HoursLabel);

        contentPane.add(centerPane,BorderLayout.CENTER);
        contentPane.add(NameLabel,BorderLayout.NORTH);
        setIconImage(icon.getImage());
        setSize(270,370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    // 修改后的教师对象
    public TeacherDO buildUpdateTeacherDO() {
            TeacherDO teacherDO = new TeacherDO();
            teacherDO.setId(Integer.parseInt(teacherIdTxt.getText()));
            teacherDO.setTeacherName(teacherNameTxt.getText());
            teacherDO.setTeacherGender(teacherGenderTxt.getText());
            teacherDO.setTeacherAge(Integer.parseInt(teacherAgeTxt.getText()));
            teacherDO.setSection(teacherSectionTxt.getText());
            teacherDO.setMajor(teacherMajorTxt.getText());
            teacherDO.setLessonNum(Integer.parseInt(HoursTxt.getText()));
            return teacherDO;
    }
}
