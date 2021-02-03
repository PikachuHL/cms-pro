package cn.hellopika.dao.enums;

import lombok.Getter;

/**
 * 表示用户状态的 Enum
 */

@Getter
public enum UserStatusEnum {

    Normal(1, "正常"),
    DISABLED(2, "禁用"),
    LOCKED(3, "锁定");

    private int grade;
    private String label;

    UserStatusEnum(int grade, String label) {
        this.grade = grade;
        this.label = label;
    }
}
