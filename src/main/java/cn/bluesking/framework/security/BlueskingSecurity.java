package cn.bluesking.framework.security;

import java.util.Set;

/**
 * Bluesking Security接口
 * <br/>
 * 可在应用中实现该接口,或者在bluesking.properties文件中提供一下基于SQL的配置想:
 * <ul>
 * 		<li>bluesking.plugin.security.jdbc.authc_query:根据用户名获取密码</li>
 * 		<li>bluesking.plugin.security.jdbc.roles_query:根据用户名获取角色名集合</li>
 * 		<li>bluesking.plugin.security.jdbc.permissions_query:根据角色名获取权限名集合</li>
 * </ul>
 * 
 * @author 随心
 *
 */
public interface BlueskingSecurity {

	/**
	 * 根据用户名获取密码
	 * @param userName [String]用户名
	 * @return [String]密码
	 */
	String getPassword(String userName);
	
	/**
	 * 根据用户名获取角色名集合
	 * @param userName [String]用户名
	 * @return [Set<String>]角色名集合
	 */
	Set<String> getRoleNameSet(String userName);
	
	/**
	 * 根据角色名获取权限名集合
	 * @param roleName [String]角色名
	 * @return [Set<String>]权限名集合
	 */
	Set<String> getPermissionNameSet(String roleName);
}
