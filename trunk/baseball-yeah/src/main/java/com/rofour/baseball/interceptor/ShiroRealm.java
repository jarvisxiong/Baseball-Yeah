package com.rofour.baseball.interceptor;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.rofour.baseball.controller.model.Permission;
import com.rofour.baseball.dao.manager.bean.RoleBean;
import com.rofour.baseball.dao.manager.bean.RolePermissionBean;
import com.rofour.baseball.dao.manager.mapper.MenuMapper;
import com.rofour.baseball.dao.manager.mapper.RoleMapper;
import com.rofour.baseball.service.manager.RolePermissionService;

/**
 * @ClassName: ShiroRealm
 * @Description: 权限管理
 * @author ZhangLei
 * @date 2016年5月5日 下午2:19:02
 *
 */

public class ShiroRealm extends AuthorizingRealm {

	private static final Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Autowired
	@Qualifier("rolePermissionService")
	private RolePermissionService rolePermissionService;

	@Autowired
	@Qualifier("roleMapper")
	private RoleMapper roleMapper;
	
	@Autowired
	@Qualifier("tbMenuMapper")
	private MenuMapper MenuMapper;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, SimpleAuthorizationInfo> redisTemplate;

	private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	/*
	 * 登录信息和用户验证信息验证(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org
	 * .apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到密码

		if (null != username && null != password) {
			return new SimpleAuthenticationInfo(username, password, getName());
		} else {
			return null;
		}
	}

	/*
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用,负责在应用程序中决定用户的访问控制的方法(non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		String userName = (String) pc.fromRealm(getName()).iterator().next();
//		logger.info(((JedisConnectionFactory)redisTemplate.getConnectionFactory()).getHostName());
		SimpleAuthorizationInfo info = (SimpleAuthorizationInfo) redisTemplate.opsForHash().get("roleAndPermission", userName);

		if (info==null) {
			if (userName != null) {
				info=new SimpleAuthorizationInfo();
				List<RolePermissionBean> pers = null;
				List<RoleBean> roles = roleMapper.selectAllRoleByUserName(userName);
				boolean ifSys=false;
				if (roles != null && !roles.isEmpty()) {
					for (RoleBean role : roles) {
						// 将权限资源添加到用户信息中
						info.addRole(role.getRoleName());
						/*info.addStringPermission(role.getBeSysPrivilege()==1?"系统权限":"");*/
						if (role.getBeSysPrivilege()==1) {
							ifSys=true;
						}
					}
				}
				if (ifSys) {
					List<Long> menuIds=MenuMapper.selectAllMenuId();
					if (menuIds != null && menuIds.size()>0) {
						for (Long each : menuIds) {
							// 将权限资源添加到用户信息中
							List<String> pe=Permission.getAllDbName();
							for (int i = 0; i < pe.size(); i++) {
								info.addStringPermission(each+"_"+pe.get(i));
							}
						}
					}
				}else {
					pers = rolePermissionService.selectRolePermissionByUserName(userName);
					if (pers != null && !pers.isEmpty()) {
						for (RolePermissionBean each : pers) {
							// 将权限资源添加到用户信息中
							String[] s = each.getAction().split(",");
							for (int i = 0; i < s.length; i++) {
								info.addStringPermission(each.getMenuId() + "_" + s[i]);
							}
							info.addStringPermission(each.getMenuId()+"");
						}
					}

				}
				redisTemplate.opsForHash().put("roleAndPermission", userName, info);
				return info;
			}
			return null;
		}else {
			return info;
		}
	}

}
