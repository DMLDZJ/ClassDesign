package classDesign0.service;

import classDesign0.entity.TeacherDO;
import classDesign0.entity.UserDO;

public interface UserService {
    boolean validateUser(UserDO userDO);
    boolean addUser(UserDO userDO);
}
