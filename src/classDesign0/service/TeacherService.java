package classDesign0.service;

import classDesign0.entity.TeacherDO;
import classDesign0.request.TeacherRequest;
import classDesign0.res.TableDTO;

public interface TeacherService {
    //查询
    TableDTO retrieveTeachers(TeacherRequest request);

    boolean addTeacher(TeacherDO teacherDO);
    TableDTO sortHours();

    TeacherDO getById(int selectedTeacherId);

    boolean update(TeacherDO teacherDO);

    boolean del(int selectedTeacherIds);
}
