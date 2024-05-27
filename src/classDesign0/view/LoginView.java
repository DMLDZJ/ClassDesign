package classDesign0.view;

import classDesign0.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginView extends JFrame {
    ImageIcon icon = new ImageIcon(".\\img\\CCSU.jpg");
    JLabel NameLabel = new JLabel("欢迎使用教师选课管理系统",JLabel.CENTER);
    Date date = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //组件
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);//中心添加组件的Panel
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码:");
    JPasswordField pwdTxt = new JPasswordField();
    public JRadioButton c1 = new JRadioButton("管理员请勾选");
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("注册");

    LoginHandler loginHandler;
    public LoginView() {
        super("登录-教师选课管理系统");
        System.out.println("此次系统登录时间: " + ft.format(date));

        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();

        NameLabel.setForeground(Color.BLACK);
        NameLabel.setFont(new Font("华文行楷",Font.PLAIN,30));
        NameLabel.setPreferredSize(new Dimension(0,80));

        Font centerFont = new Font("楷体",Font.PLAIN,20);
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);

        //组件都放到centerPanel里
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
//        c1.addActionListener(loginHandler);

        centerPanel.add(c1);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdTxt);

        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);

        // 弹簧布局
        // userNameLabel
        Spring spaceWidth = Spring.constant(20);
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),spaceWidth);
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        // userTxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        // pwdLabel
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        // pwdTxt
        springLayout.putConstraint(SpringLayout.WEST,pwdTxt,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdTxt,0,SpringLayout.NORTH,pwdLabel);
        // c1
        springLayout.putConstraint(SpringLayout.WEST,c1,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,c1,10,SpringLayout.SOUTH,pwdLabel);

        // loginBtn
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,50,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.EAST,loginBtn,70,SpringLayout.EAST,pwdLabel);
        // resetBtn
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,40,SpringLayout.EAST,loginBtn);


        contentPane.add(NameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        // 设置loginBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);
        setIconImage(icon.getImage());
        setSize(400,280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPwdTxt() {
        return pwdTxt;
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
