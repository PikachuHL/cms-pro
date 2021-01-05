package cn.hellopika.portal.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.web.filter.authc.UserFilter;

@Setter
@Getter
public class CmsUserFilter extends UserFilter {

    private String adminLoginUrl;
    private String adminPrefix;


}
