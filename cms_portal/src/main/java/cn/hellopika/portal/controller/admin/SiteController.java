package cn.hellopika.portal.controller.admin;

import cn.hellopika.context.foundation.Result;
import cn.hellopika.core.annotation.DoLog;
import cn.hellopika.core.annotation.DoValid;
import cn.hellopika.service.api.CmsSiteService;
import cn.hellopika.service.dto.CmsSiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 站点配置 的控制器
 */

@Controller
@RequestMapping("site")
public class SiteController {

    @Autowired
    private CmsSiteService cmsSiteService;

    @GetMapping("index.do")
    public String toIndex(Model model) {
        model.addAttribute("data", cmsSiteService.selectById(1));
        return "admin/site/index";
    }

    @PostMapping("update.do")
    @ResponseBody
    @DoValid
    @DoLog(content = "修改站点配置")
    public Result doUpdate(@Valid CmsSiteDto cmsSiteDto, BindingResult result){
        cmsSiteService.update(cmsSiteDto);
        return Result.success();
    }
}
