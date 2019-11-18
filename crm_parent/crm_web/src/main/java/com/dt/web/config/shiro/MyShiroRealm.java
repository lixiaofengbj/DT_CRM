package com.dt.web.config.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dt.common.bean.ResponseBean;
import com.dt.common.constant.ErrorEnum;
import com.dt.common.utils.CommonUtils;
import com.dt.pojo.CrmMenuVo;
import com.dt.pojo.CrmUser;
import com.dt.pojo.CrmUserVo;
import com.dt.service.MenuService;
import com.dt.service.RoleService;
import com.dt.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * @Author: lixiaofeng
 * @Date: 2019/11/1 16:20
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 权限信息，包括角色以及权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        CrmUser user = (CrmUser) principals.getPrimaryPrincipal();
        ResponseBean roleIdsBean = this.userService.findRoleIdByUserId(user.getId());
        if (ErrorEnum.SUCCESS.getErrorCode().equals(roleIdsBean.getErrorCode())) {
            JSONObject roleIds = JSON.parseObject(JSONObject.toJSONString(roleIdsBean.getData()));
            if (CommonUtils.isNotNull(roleIds)) {
                ArrayList<Integer> roleIdArr = roleIds.toJavaObject(ArrayList.class);
                for (Integer roleId : roleIdArr) {
                    ResponseBean menuIdsBean = this.roleService.findMenuIdByRoleId(roleId);
                    if (ErrorEnum.SUCCESS.getErrorCode().equals(menuIdsBean.getErrorCode())) {
                        JSONObject menuIds = JSON.parseObject(JSONObject.toJSONString(menuIdsBean.getData()));
                        if (CommonUtils.isNotNull(menuIds)) {
                            ArrayList<Integer> menuIdArr = menuIds.toJavaObject(ArrayList.class);
                            for (Integer menuId : menuIdArr) {
                                ResponseBean meneBean = this.menuService.findById(menuId);
                                if (ErrorEnum.SUCCESS.getErrorCode().equals(meneBean.getErrorCode())) {
                                    JSONObject menuJson = JSON.parseObject(JSONObject.toJSONString(meneBean.getData()));
                                    if (CommonUtils.isNotNull(menuJson)) {
                                        CrmMenuVo crmMenuVo = menuIds.toJavaObject(CrmMenuVo.class);
                                        authorizationInfo.addStringPermission(crmMenuVo.getHref());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        ResponseBean responseBean = userService.findByUserName(userName);
        if (ErrorEnum.SUCCESS.getErrorCode().equals(responseBean.getErrorCode())) {
            JSONObject json = JSON.parseObject(JSONObject.toJSONString(responseBean.getData()));
            if (CommonUtils.isNotNull(json)) {
                try {
                    CrmUserVo user = json.toJavaObject(CrmUserVo.class);
                    return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
