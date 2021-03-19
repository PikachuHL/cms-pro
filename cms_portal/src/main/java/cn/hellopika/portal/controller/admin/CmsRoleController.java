package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.service.api.CmsPermissionService;
import cn.hellopika.service.dto.CmsPermissionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("role")
public class CmsRoleController {

    @Autowired
    CmsPermissionService cmsPermissionService;

    @GetMapping("index.do")
    public String toIndex(){
        return "admin/role/index";
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result toPage(){
        return Result.success();
    }

    @PostMapping("permission.do")
    @ResponseBody
    public Result doPermissionList(){
        List<CmsPermissionDto> permissionList = cmsPermissionService.selectAll();
        // 新建map用于存放 id 和 id所对应的dto
        Map<Integer, CmsPermissionDto> permissionMap = new HashMap<>();

        // 仅用于存放 顶层菜单
        List<CmsPermissionDto> top = new ArrayList<>();

        permissionList.forEach(x -> {
            Integer id = x.getId();

            permissionMap.put(id, x);

            Integer parentId = x.getParentId();

            if (parentId == 0) {
                top.add(x);
            } else {
                CmsPermissionDto parentDto = permissionMap.get(parentId);

                List<CmsPermissionDto> children = parentDto.getChildren();

                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                children.add(x);

                parentDto.setChildren(children);
            }
        });
        addCheckArr(top);
        return Result.success((ArrayList)top);
    }


    // 给 top 中的每一个元素添加checkArr属性, 用于dtree的复选框
    public void addCheckArr(List<CmsPermissionDto> top){
        top.forEach(x->{
            x.setCheckArr(Collections.singletonList(Collections.singletonMap("checked", "0")));
            if(!Objects.isNull(x.getChildren())){
                // 使用递归
                addCheckArr(x.getChildren());
            }
        });

    }

    /**
     * 跳转添加页面
     * @return  添加页面路径
     */
    @GetMapping("add.do")
    public String toAdd(){
        return "admin/role/add";
    }

    /**
     * 执行添加操作
     * @return  Result
     */
    @PostMapping("add.do")
    @ResponseBody
    public Result doAdd(){
        return Result.success();
    }
}
