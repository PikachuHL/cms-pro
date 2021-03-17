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
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.*;

@Controller
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
    public Result doSelectAll(){
        return Result.success((ArrayList)cmsPermissionService.selectAll());
    }

    @GetMapping("add.do")
    public String toAdd(Integer parentId, Model model) {
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
    public String toEdit(Integer id, Model model){
        CmsPermissionDto cmsPermissionDto = cmsPermissionService.selectById(id);
        model.addAttribute("data", cmsPermissionDto);
        model.addAttribute("permissionType", PermissionTypeEnum.values());
        return "admin/permission/edit";
    }

    @PostMapping("delete.do")
    @ResponseBody
    public Result doDelete(Integer id){
        return Result.success();
    }


    @PostMapping("selectTree.do")
    @ResponseBody
    public Result doSelectTree(Integer excludeId) {
        List<CmsPermissionDto> permissionList = createData();

        // 新建map用于存放 id 和 id所对应的dto
        Map<Integer, CmsPermissionDto> permissionMap = new HashMap<>();

        // 仅用于存放 顶层菜单
        List<CmsPermissionDto> top = new ArrayList<>();

        permissionList.forEach(x -> {
            Integer id = x.getId();
            // 如果在修改页面, 需要把 小于等于自己级别的元素 不在前端显示, 就要从当前元素把链断开
            if(Objects.nonNull(excludeId) && id.compareTo(excludeId)==0){
                return; // 这里的return会跳出当前循环
            }
            permissionMap.put(id, x);

            Integer parentId = x.getParentId();

            if (parentId == 0) {
                top.add(x);
            } else {
                CmsPermissionDto parentDto = permissionMap.get(parentId);
                // 如果排除了相关元素, parentDto可能为空, 需要判断一下
                if(Objects.isNull(parentDto) && Objects.nonNull(excludeId) && parentId.compareTo(excludeId)==0){
                    return;
                }
                List<CmsPermissionDto> children = parentDto.getChildren();

                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                children.add(x);

                parentDto.setChildren(children);
            }
        });

        return Result.success((ArrayList)top);
    }


    // 自己创建一些数据, 用于测试
    public List<CmsPermissionDto> createData() {
        List<CmsPermissionDto> cmsPermissionDtoList = new ArrayList<>();

        CmsPermissionDto cmsPermissionDto1 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto2 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto3 = new CmsPermissionDto();
        CmsPermissionDto cmsPermissionDto4 = new CmsPermissionDto();

        cmsPermissionDto1.setName("测试1");
        cmsPermissionDto2.setName("测试2");
        cmsPermissionDto3.setName("测试3");
        cmsPermissionDto4.setName("测试4");

        cmsPermissionDto1.setId(1);
        cmsPermissionDto2.setId(2);
        cmsPermissionDto3.setId(3);
        cmsPermissionDto4.setId(4);

        cmsPermissionDto1.setParentId(0);
        cmsPermissionDto2.setParentId(1);
        cmsPermissionDto3.setParentId(2);
        cmsPermissionDto4.setParentId(3);

        cmsPermissionDtoList.add(cmsPermissionDto1);
        cmsPermissionDtoList.add(cmsPermissionDto2);
        cmsPermissionDtoList.add(cmsPermissionDto3);
        cmsPermissionDtoList.add(cmsPermissionDto4);

        return cmsPermissionDtoList;
    }
}
