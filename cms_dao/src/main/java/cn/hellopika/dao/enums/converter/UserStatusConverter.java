package cn.hellopika.dao.enums.converter;

import cn.hellopika.dao.enums.UserStatusEnum;

import java.util.Objects;

/**
 * 把user的status在 Interger 和 UserStatusEnum 之间转换
 */
public class UserStatusConverter {

    public static UserStatusEnum toUserStatusEnum(Integer status) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (Objects.equals(userStatusEnum.getGrade(), status)) {
                return userStatusEnum;
            }
        }
        return null;
    }
}
