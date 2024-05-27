package classDesign0.view;

import classDesign0.entity.TeacherDO;
import classDesign0.handler.AddTeacherHandler;

import javax.swing.*;
import java.awt.*;

public class AddTeacherView extends JFrame {
    ImageIcon icon = new ImageIcon("D:\\Codes\\Java\\ClassDesign\\img\\CCSU.jpg");
    JLabel NameLabel = new JLabel("添加教师",JLabel.CENTER);
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
    JButton addBtn = new JButton("添加");
    AddTeacherHandler addTeacherHandler;
    public AddTeacherView(MainView mainView) {
        super("添加-教师");
        Container contentPane = getContentPane();

        addTeacherHandler = new AddTeacherHandler(this,mainView);
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
        addBtn.addActionListener(addTeacherHandler);
        centerPane.add(addBtn);
        // 布局

        // teacherLabel
        springLayout.putConstraint(SpringLayout.WEST,teacherIdLabel,20,SpringLayout.WEST,centerPane);
        springLayout.putConstraint(SpringLayout.NORTH,teacherIdLabel,0,SpringLayout.NORTH,centerPane);
        // teacherTxt
        springLayout.putConstraint(SpringLayout.WEST,teacherIdTxt,20,SpringLayout.EAST,teacherIdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherIdTxt,0,SpringLayout.NORTH,teacherIdLabel);
        // teacherNameLabel
        springLayout.putConstraint(SpringLayout.NORTH,teacherNameLabel,20,SpringLayout.SOUTH,teacherIdLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherNameLabel,0,SpringLayout.WEST,teacherIdLabel);
        // teacherNameTxt
        springLayout.putConstraint(SpringLayout.WEST,teacherNameTxt,20,SpringLayout.EAST,teacherNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherNameTxt,0,SpringLayout.NORTH,teacherNameLabel);
        // teacherGenderLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherGenderLabel,0,SpringLayout.EAST,teacherNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherGenderLabel,20,SpringLayout.SOUTH,teacherNameLabel);
        // teacherGenderTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherGenderTxt,0,SpringLayout.NORTH,teacherGenderLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherGenderTxt,20,SpringLayout.EAST,teacherGenderLabel);
        // teacherAgeLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherAgeLabel,0,SpringLayout.EAST,teacherGenderLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherAgeLabel,20,SpringLayout.SOUTH,teacherGenderLabel);
        // teacherAgeTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherAgeTxt,0,SpringLayout.NORTH,teacherAgeLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherAgeTxt,20,SpringLayout.EAST,teacherAgeLabel);
        // teacherSectionLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherSectionLabel,0,SpringLayout.EAST,teacherAgeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherSectionLabel,20,SpringLayout.SOUTH,teacherAgeLabel);
        // teacherSectionTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherSectionTxt,0,SpringLayout.NORTH,teacherSectionLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherSectionTxt,20,SpringLayout.EAST,teacherSectionLabel);
        // teacherMajorLabel
        springLayout.putConstraint(SpringLayout.EAST,teacherMajorLabel,0,SpringLayout.EAST,teacherSectionLabel);
        springLayout.putConstraint(SpringLayout.NORTH,teacherMajorLabel,20,SpringLayout.SOUTH,teacherSectionLabel);
        // teacherMajorTxt
        springLayout.putConstraint(SpringLayout.NORTH,teacherMajorTxt,0,SpringLayout.NORTH,teacherMajorLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherMajorTxt,20,SpringLayout.EAST,teacherMajorLabel);
        // addBtn
        springLayout.putConstraint(SpringLayout.NORTH,addBtn,20,SpringLayout.SOUTH,teacherMajorLabel);
        springLayout.putConstraint(SpringLayout.WEST,addBtn,0,SpringLayout.WEST,teacherMajorTxt);

        contentPane.add(NameLabel,BorderLayout.NORTH);
        contentPane.add(centerPane,BorderLayout.CENTER);
        setIconImage(icon.getImage());
        setSize(270,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public TeacherDO buildTeacherDO() {
        TeacherDO teacherDO = new TeacherDO();
        teacherDO.setId(Integer.parseInt(teacherIdTxt.getText()));
        teacherDO.setTeacherName(teacherNameTxt.getText());
        teacherDO.setTeacherGender(teacherGenderTxt.getText());
        teacherDO.setTeacherAge(Integer.parseInt(teacherAgeTxt.getText()));
        teacherDO.setSection(teacherSectionTxt.getText());
        teacherDO.setMajor(teacherMajorTxt.getText());
        teacherDO.setLessonNum(0);
        return teacherDO;
    }
}
