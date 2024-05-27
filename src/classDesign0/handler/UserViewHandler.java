package classDesign0.handler;

import classDesign0.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserViewHandler implements ActionListener {
    private UserView userView;
    public UserViewHandler(UserView userView) {
        this.userView = userView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("选课".equals(text)) {
            int[] selectedLessonIds = userView.getSelectedLessonIds();
            if(selectedLessonIds.length != 1) {
                JOptionPane.showMessageDialog(userView,"请选择一行进行修改");
                return ;
            }
            new SelectLessonView(userView,selectedLessonIds[0]);
        }else if("查询".equals(text)) {
            userView.setPageNow(1);
            userView.reloadTable();
        }

    }
}
