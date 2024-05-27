package classDesign0.service;

import classDesign0.entity.LessonDO;
import classDesign0.entity.TeacherDO;
import classDesign0.request.LessonRequest;
import classDesign0.res.TableDTO;

public interface LessonService {
   boolean del(int selectedTeacherId);

    TableDTO retrieveLesson(LessonRequest Request);
    TableDTO retrieveLesson1(LessonRequest request);
    boolean addLesson(LessonDO lessonDO);
    LessonDO getById(int selectLessonId);
    boolean update(LessonDO lessonDO);

}
