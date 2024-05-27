package classDesign0.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class TableModel extends DefaultTableModel {

    static Vector<String> teacher = new Vector<>();
    static {
//        teacher.addElement("序号");
        teacher.addElement("教师编号");
        teacher.addElement("教师姓名");
        teacher.addElement("性别");
        teacher.addElement("年龄");
        teacher.addElement("所在部门");
        teacher.addElement("所学专业");
        teacher.addElement("课时");
    }
    static Vector<String> lesson = new Vector<>();
    static {
//        lesson.addElement("序号");
        lesson.addElement("课程编号");
        lesson.addElement("课程名称");
        lesson.addElement("课时数");
        lesson.addElement("授课班级数");
        lesson.addElement("授课老师");
    }
    private TableModel() {
        super(null,teacher);
    }
    private static TableModel teacherTableModel = new TableModel();
    private static TableModel lessonTableModel = new TableModel();
    public static TableModel assembleModel(Vector<Vector<Object>> data, int a) {
        if(a == 1){
            teacherTableModel.setDataVector(data,teacher);
            return teacherTableModel;
        }
        else if(a == 2) {
            lessonTableModel.setDataVector(data,lesson);
            return lessonTableModel;
        }
        return null;
    }

    public static void updateModel(Vector<Vector<Object>> data,int a) {
        if (a == 1) {
            teacherTableModel.setDataVector(data, teacher);
        } else if (a == 2) {
            lessonTableModel.setDataVector(data, lesson);
        }

    }
}