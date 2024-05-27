package classDesign0.handler;

import classDesign0.entity.AdminDO;
import classDesign0.entity.TeacherDO;
import classDesign0.entity.UserDO;
import classDesign0.service.AdminService;
import classDesign0.service.TeacherService;
import classDesign0.service.UserService;
import classDesign0.service.impl.AdminServiceImpl;
import classDesign0.service.impl.TeacherServiceImpl;
import classDesign0.service.impl.UserServiceImpl;
import classDesign0.view.LoginView;
import classDesign0.view.MainView;
import classDesign0.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private final LoginView loginView;
    private boolean AdmOrTea;
    public LoginHandler(LoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean a = loginView.c1.isSelected();
        AdmOrTea = a;
        System.out.println("c1 = "+a);
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if(text.equals("登录")){
            login();
        }else if(text.equals("注册")) {
            String user = loginView.getUserTxt().getText();
            char[] chars = loginView.getPwdTxt().getPassword();
            if(user == null || user.trim().isEmpty() || chars == null) {
                JOptionPane.showMessageDialog(loginView,"请输入用户名或密码");
                return ;
            }
            String pwd = new String(chars);
            boolean flag;
            UserService userService = new UserServiceImpl();
            UserDO userDO = new UserDO();
            userDO.setUserName(user);
            userDO.setPwd(pwd);
            flag = userService.validateUser(userDO);

            if(!flag) {
                JOptionPane.showMessageDialog(loginView,"该用户已存在");
            }else {
                UserDO userDO1 = new UserDO();
                userDO1.setUserName(user);
                userDO1.setPwd(pwd);
                boolean addResult = userService.addUser(userDO1);
                if(addResult) {
                    JOptionPane.showMessageDialog(loginView,"注册成功");
                }else {
                    JOptionPane.showMessageDialog(loginView,"注册失败,请重试");
                }
            }
            System.out.println("用户名:" + user );
            System.out.println("密码:" + pwd );

            loginView.getUserTxt().setText("");
            loginView.getPwdTxt().setText("");
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        char[] chars = loginView.getPwdTxt().getPassword();
        if(user == null || "".equals(user.trim())|| chars == null) {
            JOptionPane.showMessageDialog(loginView,"请输入用户名或密码");
            return ;
        }

        String pwd = new String(chars);
        System.out.println("用户名:" + user );
        System.out.println("密码:" + pwd );
        loginView.getUserTxt().setText("");
        loginView.getPwdTxt().setText("");

        boolean flag;
        int a = 0;
        // 查询
        if(AdmOrTea) {
            AdminService adminService = new AdminServiceImpl();
            AdminDO adminDO = new AdminDO();
            adminDO.setUserName(user);
            adminDO.setPwd(pwd);
            flag = adminService.validateAdmin(adminDO);
            a = 1;
        }else {
            AdminService adminService = new AdminServiceImpl();
            UserDO userDO = new UserDO();
            userDO.setUserName(user);
            userDO.setPwd(pwd);
            flag = adminService.validateUser(userDO);
        }

        if(flag) {
            // 跳转主界面
            if(a == 1){
                JOptionPane.showMessageDialog(loginView,"登录成功!");
                new MainView();
                loginView.dispose();
            }else {
                JOptionPane.showMessageDialog(loginView,"登录成功!");
                new UserView();
                loginView.dispose();
            }

        }else {
            JOptionPane.showMessageDialog(loginView,"用户名或密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()) {
            login();
        }
    }
}
