package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.context.utils.UtilsShiro;
import cn.hellopika.service.api.CmsRoleService;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.dto.CmsRoleDto;
import cn.hellopika.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private CmsUserService cmsUserService;

    @Autowired
    private CmsRoleService cmsRoleService;

    @GetMapping("index.do")
    public String toIndex(){
        return "admin/user/index";
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto dto){

        return Result.success(cmsUserService.getPage(dto));
    }

    @GetMapping("add.do")
    public String toAdd(Model model){
        List<CmsRoleDto> cmsRoleDtos = cmsRoleService.selectAll();
        System.out.println(cmsRoleDtos);
        model.addAttribute("roles", cmsRoleDtos);
        return "admin/user/add";
    }

    @PostMapping("add.do")
    @ResponseBody
    public Result doAdd(CmsUserDto cmsUserDto){

        // 添加用户之前，先根据 用户名 或者 邮箱 判断用户是否存在
        if(Objects.nonNull(cmsUserService.findByUsername(cmsUserDto.getUsername()))){
            return Result.failed("用户名已存在，请重新输入");
        }
        if(Objects.nonNull(cmsUserService.findByEmail(cmsUserDto.getEmail()))){
            return Result.failed("邮箱已注册，请重新输入");
        }

        cmsUserService.save(cmsUserDto);

        return Result.success("添加用户成功");
    }
}
