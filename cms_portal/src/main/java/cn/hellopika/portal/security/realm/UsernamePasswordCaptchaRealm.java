package cn.hellopika.portal.security.realm;

import cn.hellopika.dao.enums.UserStatusEnum;
import cn.hellopika.service.api.CmsUserService;
import cn.hellopika.service.dto.CmsUserDto;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UsernamePasswordCaptchaRealm extends AuthorizingRealm {

    @Autowired
    private CmsUserService cmsUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        // 获取用户名
        String username = (String) Token.getPrincipal();
        // 在 用户附表 中通过用户名查找用户
        CmsUserDto cmsUserDto = cmsUserService.findByUsername(username);
        // 判断用户是否存在
        if(Objects.isNull(cmsUserDto)){
            throw new UnknownAccountException();
        }

        // 如果用户存在, 判断用户状态
        if (!cmsUserDto.getStatus()) {
            throw new DisabledAccountException("用户被禁用, 请联系管理员");
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(cmsUserDto, cmsUserDto.getPassword(),
                ByteSource.Util.bytes(cmsUserDto.getSalt()), getName());

//        super.clearCache(simpleAuthenticationInfo.getPrincipals());

        return simpleAuthenticationInfo;
    }
}
