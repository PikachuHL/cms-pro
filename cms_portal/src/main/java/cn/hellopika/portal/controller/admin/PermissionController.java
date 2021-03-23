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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Controller
@Validated
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex() {
        return "/admin/permission/index";
    }

    @GetMapping("selectAll.do")
    @ResponseBody
    public Result doSelectAll() {
        return Result.success((ArrayList) cmsPermissionService.selectAll());
    }

    @GetMapping("add.do")
    public String toAdd(@NotNull(message = "请传入id(add.do)") Integer parentId, Model model) {
        model.addAttribute("parentId", parentId);
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
        return Result.success("添加权限成功");
    }

    @GetMapping("edit.do")
    public String toEdit(@NotNull(message = "请传入id(edit.do)") Integer id, Model model) {
        CmsPermissionDto cmsPermissionDto = cmsPermissionService.selectById(id);
        model.addAttribute("data", cmsPermissionDto);
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return "admin/permission/edit";
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "修改权限")
    public Result<String> doEdit(@Valid CmsPermissionDto dto, BindingResult result) {
        cmsPermissionService.update(dto);
        return Result.success("修改权限成功");
    }

    @PostMapping("delete.do")
    @ResponseBody
    @DoLog(content = "删除权限")
    public Result doDelete(@NotNull(message = "请传入id(delete.do)") Integer id) {
        cmsPermissionService.deleteById(id);
        return Result.success("删除成功");
    }


    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer excludeId) {
        ArrayList top = (ArrayList) cmsPermissionService.treeBuilder(excludeId);

        return Result.success(top);
    }
}
