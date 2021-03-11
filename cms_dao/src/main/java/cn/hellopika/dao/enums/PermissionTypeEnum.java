package cn.hellopika.dao.enums;

import lombok.Getter;

@Getter
public enum PermissionTypeEnum {

    BUTTON(1, "按钮"),
    MENU(0, "菜单");

    private int ordinal;
    private String label;

    PermissionTypeEnum(int ordinal, String label){
        this.ordinal = ordinal;
        this.label = label;
    }
}
