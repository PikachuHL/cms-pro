package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.core.annotation.DoLog;
import cn.hellopika.core.annotation.DoValid;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.api.CmsRolePermissionService;
import cn.hellopika.service.api.CmsRoleService;
import cn.hellopika.service.dto.CmsPermissionDto;
import cn.hellopika.service.dto.CmsRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("role")
public class CmsRoleController {

    @Autowired
    private CmsRoleService cmsRoleService;

    @Autowired
    private CmsPermissionService cmsPermissionService;

    @Autowired
    private CmsRolePermissionService cmsRolePermissionService;

    @GetMapping("index.do")
    public String toIndex() {
        return "admin/role/index";
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsRoleDto dto) {
        return Result.success(cmsRoleService.getPage(dto));
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermissionList(Integer roleId) {
        ArrayList top = (ArrayList) cmsPermissionService.treeBuilder(null);

        // 如果前端传来了 roleId（在角色修改时），
        // 就去 角色-权限 中间表里面查出 roleId 对应的 permissionId
        List<Integer> permissionIds = null;
        if(Objects.nonNull(roleId)){
            permissionIds = cmsRolePermissionService.selectPermissionIdsByRoleId(roleId);
        }
        addCheckArr(top, permissionIds);
        return Result.success(top);
    }


    // 给 top 中的每一个元素添加checkArr属性, 用于dtree的复选框
    public void addCheckArr(List<CmsPermissionDto> top, List<Integer> permissionIds) {
        top.forEach(x -> {

            List<Map<String, String>> checked = Collections.singletonList(Collections.singletonMap("checked", "0"));

            if(Objects.nonNull(permissionIds)){
                if(permissionIds.contains(x.getId())){
                    checked = Collections.singletonList(Collections.singletonMap("checked", "1"));
                }
            }

            x.setCheckArr(checked);

            if (!Objects.isNull(x.getChildren())) {
                // 使用递归
                addCheckArr(x.getChildren(), permissionIds);
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
    @DoValid
    public Result doAdd(@Valid CmsRoleDto cmsRoleDto, BindingResult result) {
        cmsRoleService.save(cmsRoleDto);
        return Result.success("添加角色成功");
    }

    @GetMapping("edit.do")
    public String toEdit(Integer id, Model model){
        CmsRoleDto cmsRoleDto = cmsRoleService.selectById(id);
        model.addAttribute("data", cmsRoleDto);
        return "admin/role/edit";
    }

    @PostMapping("edit.do")
    @ResponseBody
    @DoLog(content = "修改角色")
    @DoValid
    public Result doEdit(@Valid CmsRoleDto cmsRoleDto, BindingResult result){
        cmsRoleService.update(cmsRoleDto);
        return Result.success("修改角色成功");
    }

    @PostMapping("delete.do")
    @ResponseBody
    @DoLog(content = "删除角色")
    public Result doDelete(Integer id){
        cmsRoleService.deleteById(id);
        return Result.success("删除角色成功");
    }
}
