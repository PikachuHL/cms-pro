package cn.hellopika.portal.controller.admin;


import cn.hellopika.context.foundation.Result;
import cn.hellopika.core.annotation.DoLog;
import cn.hellopika.core.annotation.DoValid;
import cn.hellopika.dao.enums.PermissionTypeEnum;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private CmsPermissionService cmsPermissionService;

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
    @ResponseBody
    @DoValid
    @DoLog(content = "添加权限")
    public Result<String> doAdd(@Valid CmsPermissionDto dto, BindingResult result) {
        cmsPermissionService.save(dto);
        return Result.success();
    }
}
