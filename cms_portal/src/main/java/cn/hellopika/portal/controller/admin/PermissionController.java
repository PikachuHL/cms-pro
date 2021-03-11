package cn.hellopika.portal.controller.admin;


import cn.hellopika.context.foundation.Result;
import cn.hellopika.dao.enums.PermissionTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @GetMapping("index.do")
    public String toIndex() {
        return "/admin/permission/index";
    }

    @GetMapping("add.do")
    public String toAdd(Model model) {
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return "/admin/permission/add";
    }

    /**
     * 和上面采用的是同一个路径, 根据访问方式的不同区分不同的方法
     *
     * @return
     */
    @PostMapping("add.do")
    public Result doAdd() {

        return Result.success();
    }
}
