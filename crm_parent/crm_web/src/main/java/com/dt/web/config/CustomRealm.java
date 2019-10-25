package com.dt.web.config;

import com.dt.common.utils.CommonUtils;
import com.dt.pojo.CrmUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        CrmUserVo user = (CrmUserVo) subject.getPrincipal();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        List<String> list = new ArrayList<String>();
        if (user.getUserName().equals("admin@cnlive.com")) {
            simpleAuthorInfo.addStringPermissions(list);
            return simpleAuthorInfo;
        }
        return null;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        if (CommonUtils.isNotEmpty(username)) {
            CrmUserVo user = new CrmUserVo();
            user.setUserName("111");
            user.setPassword("111");
            if (user != null) {
                return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
            }
        }
        return null;
    }

}
