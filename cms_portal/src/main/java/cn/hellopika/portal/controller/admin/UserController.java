package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.dto.CmsUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private CmsUserService cmsUserService;

    @GetMapping("index.do")
    public String toIndex(){
        return "admin/user/index";
    }

    @PostMapping("page.do")
    @ResponseBody
    public Result doPage(CmsUserDto dto){

        return Result.success(cmsUserService.getPage(dto));
    }
}
