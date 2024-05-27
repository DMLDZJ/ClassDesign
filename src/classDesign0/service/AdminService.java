package classDesign0.service;

import classDesign0.entity.AdminDO;
import classDesign0.entity.UserDO;

public interface AdminService {
    boolean validateAdmin(AdminDO adminDO);
    boolean validateUser(UserDO userDO);
}
