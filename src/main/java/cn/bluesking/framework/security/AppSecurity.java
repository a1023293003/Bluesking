package cn.bluesking.framework.security;

import java.util.Set;

import cn.bluesking.framework.helper.DatabaseHelper;

/**
 * 应用安全控制
 * 
 * @author 随心
 *
 */
public class AppSecurity implements BlueskingSecurity {

	@Override
	public String getPassword(String userName) {
		String sql = "select password from user where userName = ?";
		return DatabaseHelper.query(sql, userName);
	}

	@Override
	public Set<String> getRoleNameSet(String userName) {
		String sql = "select r.role_name from user u, user_role ur, role r " + 
					 "where u.id = ur.user_id and r.id = ur.role_id and u.userName = ?";
		return DatabaseHelper.querySet(sql, userName);
	}

	@Override
	public Set<String> getPermissionNameSet(String roleName) {
		String sql = "select permission_name from role r, role_permission rp, permission p " + 
					 "where r.id = rp.role_id and p.id = rp.permission_id and r.role_name = ?";
		return DatabaseHelper.querySet(sql, roleName);
	}

}
