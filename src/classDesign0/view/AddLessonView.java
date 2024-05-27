package classDesign0.view;

import classDesign0.entity.LessonDO;
import classDesign0.handler.AddLessonHandler;

import javax.swing.*;
import java.awt.*;

public class AddLessonView extends JFrame {
    ImageIcon icon = new ImageIcon("D:\\Codes\\Java\\ClassDesign\\img\\CCSU.jpg");
    JLabel NameLabel = new JLabel("添加-课程",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPane = new JPanel(springLayout);
    JLabel lessonIdLabel = new JLabel("课程编号:");
    JTextField lessonIdTxt = new JTextField(15);
    JLabel lessonNameLabel = new JLabel("课程名称:");
    JTextField lessonNameTxt = new JTextField(15);
    JLabel lessonHoursLabel = new JLabel("课时:");
    JTextField lessonHoursTxt = new JTextField(15);
    JLabel lessonClassNumLabel = new JLabel("课程班级数:");
    JTextField lessonClassNumTxt = new JTextField(15);
    JLabel lessonTeacherLabel = new JLabel("授课老师");
    JTextField lessonTeacherTxt = new JTextField(15);
    JButton addBtn = new JButton("添加");
    LessonView lessonView;
    AddLessonHandler addLessonHandler;
    public AddLessonView(LessonView lessonView) {
        super("添加课程");

        Container contentPane = getContentPane();
        this.lessonView = lessonView;
        addLessonHandler = new AddLessonHandler(this,this.lessonView);
        // 标题
        NameLabel.setForeground(Color.BLACK);
        NameLabel.setFont(new Font("华文行楷",Font.PLAIN,30));

        centerPane.add(lessonIdLabel);
        centerPane.add(lessonIdTxt);
        centerPane.add(lessonNameLabel);
        centerPane.add(lessonNameTxt);
        centerPane.add(lessonHoursLabel);
        centerPane.add(lessonHoursTxt);
        centerPane.add(lessonClassNumLabel);
        centerPane.add(lessonClassNumTxt);
        centerPane.add(lessonTeacherLabel);
        centerPane.add(lessonTeacherTxt);
        addBtn.addActionListener(addLessonHandler);
        centerPane.add(addBtn);
        // 布局

        // teacherLabel
        springLayout.putConstraint(SpringLayout.WEST,lessonIdLabel,20,SpringLayout.WEST,centerPane);
        springLayout.putConstraint(SpringLayout.NORTH,lessonIdLabel,0,SpringLayout.NORTH,centerPane);
        // teacherTxt
        springLayout.putConstraint(SpringLayout.WEST,lessonIdTxt,20,SpringLayout.EAST,lessonIdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,lessonIdTxt,0,SpringLayout.NORTH,lessonIdLabel);
        // lessonNameLabel
        springLayout.putConstraint(SpringLayout.NORTH,lessonNameLabel,20,SpringLayout.SOUTH,lessonIdLabel);
        springLayout.putConstraint(SpringLayout.WEST,lessonNameLabel,0,SpringLayout.WEST,lessonIdLabel);
        // lessonNameTxt
        springLayout.putConstraint(SpringLayout.WEST,lessonNameTxt,20,SpringLayout.EAST,lessonNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,lessonNameTxt,0,SpringLayout.NORTH,lessonNameLabel);
        // lessonHoursLabel
        springLayout.putConstraint(SpringLayout.EAST,lessonHoursLabel,0,SpringLayout.EAST,lessonNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,lessonHoursLabel,20,SpringLayout.SOUTH,lessonNameLabel);
        // lessonHoursTxt
        springLayout.putConstraint(SpringLayout.NORTH,lessonHoursTxt,0,SpringLayout.NORTH,lessonHoursLabel);
        springLayout.putConstraint(SpringLayout.WEST,lessonHoursTxt,20,SpringLayout.EAST,lessonHoursLabel);
        // lessonClassNumLabel
        springLayout.putConstraint(SpringLayout.EAST,lessonClassNumLabel,0,SpringLayout.EAST,lessonHoursLabel);
        springLayout.putConstraint(SpringLayout.NORTH,lessonClassNumLabel,20,SpringLayout.SOUTH,lessonHoursLabel);
        // lessonCLassNumTxt
        springLayout.putConstraint(SpringLayout.NORTH,lessonClassNumTxt,0,SpringLayout.NORTH,lessonClassNumLabel);
        springLayout.putConstraint(SpringLayout.WEST,lessonClassNumTxt,20,SpringLayout.EAST,lessonClassNumLabel);
        // lessonTeacherLabel
        springLayout.putConstraint(SpringLayout.EAST,lessonTeacherLabel,0,SpringLayout.EAST,lessonClassNumLabel);
        springLayout.putConstraint(SpringLayout.NORTH,lessonTeacherLabel,20,SpringLayout.SOUTH,lessonClassNumLabel);
        // lessonTeacherTxt
        springLayout.putConstraint(SpringLayout.NORTH,lessonTeacherTxt,0,SpringLayout.NORTH,lessonTeacherLabel);
        springLayout.putConstraint(SpringLayout.WEST,lessonTeacherTxt,20,SpringLayout.EAST,lessonTeacherLabel);
        // addBtn
        springLayout.putConstraint(SpringLayout.WEST,addBtn,0,SpringLayout.WEST,lessonTeacherTxt);
        springLayout.putConstraint(SpringLayout.NORTH,addBtn,20,SpringLayout.SOUTH,lessonTeacherTxt);

        contentPane.add(NameLabel,BorderLayout.NORTH);
        contentPane.add(centerPane,BorderLayout.CENTER);
        setIconImage(icon.getImage());
        setSize(270,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public LessonDO buildLessonDO() {
        LessonDO lessonDO = new LessonDO();
        lessonDO.setId(Integer.parseInt(lessonIdTxt.getText()));
        lessonDO.setLessonName(lessonNameTxt.getText());
        lessonDO.setHour(Integer.parseInt(lessonHoursTxt.getText()));
        lessonDO.setClassNum(Integer.parseInt(lessonClassNumTxt.getText()));
        lessonDO.setLessonTeacher(lessonTeacherTxt.getText());
        return lessonDO;
    }
}
