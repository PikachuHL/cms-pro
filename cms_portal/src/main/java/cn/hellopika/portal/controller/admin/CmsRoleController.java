package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.core.annotation.DoLog;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.api.CmsRoleService;
import cn.hellopika.service.dto.CmsPermissionDto;
import cn.hellopika.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("role")
public class CmsRoleController {

    @Autowired
    private CmsRoleService cmsRoleService;

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex() {
        return "admin/role/index";
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result toPage() {
        return Result.success();
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermissionList() {
        ArrayList top = (ArrayList) cmsPermissionService.treeBuilder(null);
        addCheckArr(top);
        return Result.success(top);
    }


    // 给 top 中的每一个元素添加checkArr属性, 用于dtree的复选框
    public void addCheckArr(List<CmsPermissionDto> top) {
        top.forEach(x -> {
            x.setCheckArr(Collections.singletonList(Collections.singletonMap("checked", "0")));
            if (!Objects.isNull(x.getChildren())) {
                // 使用递归
                addCheckArr(x.getChildren());
            }
        });

    }

    /**
     * 跳转添加页面
     *
     * @return 添加页面路径
     */
    @GetMapping("add.do")
    public String toAdd() {
        return "admin/role/add";
    }

    /**
     * 执行添加操作
     *
     * @return Result
     */
    @PostMapping("add.do")
    @ResponseBody
    @DoLog(content = "添加角色")
    public Result doAdd(CmsRoleDto cmsRoleDto) {
        cmsRoleService.save(cmsRoleDto);
        return Result.success("添加角色成功");
    }
}
