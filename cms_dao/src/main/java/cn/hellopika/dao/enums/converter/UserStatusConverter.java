package cn.hellopika.dao.enums.converter;

import cn.hellopika.dao.enums.UserStatusEnum;

import java.util.Objects;

/**
 * 把user的status在 Interger 和 UserStatusEnum 之间转换
 */
public class UserStatusConverter {

    // 把 Integer 转换成 UserStatusEnum
    public static UserStatusEnum toUserStatusEnum(Integer status) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (Objects.equals(userStatusEnum.getGrade(), status)) {
                return userStatusEnum;
            }
        }
        return null;
    }

    // 把 UserStatusEnum 转换成 Integer
    public static Integer toInteger(UserStatusEnum userStatusEnum) {
        return (userStatusEnum != null) ? userStatusEnum.getGrade() : null;
    }
}
