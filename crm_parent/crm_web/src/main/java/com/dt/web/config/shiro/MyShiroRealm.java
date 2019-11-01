package com.dt.web.config.shiro;

import com.dt.pojo.CrmUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/1 16:20
 */
public class MyShiroRealm extends AuthorizingRealm {

    /**
     * 权限信息，包括角色以及权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        CrmUser user = (CrmUser) principals.getPrimaryPrincipal();
        /*for (SysRole role : user.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }*/
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        return new SimpleAuthenticationInfo("", "", this.getName());
    }
}
