package classDesign0.entity;

public class LessonDO {
    private int id;
    private String lessonName;
    private int hour;
    private int classNum;
    private String lessonTeacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public String getLessonTeacher() {
        return lessonTeacher;
    }

    public void setLessonTeacher(String lessonTeacher) {
        this.lessonTeacher = lessonTeacher;
    }
}
