package cn.bluesking.framework.security.exception;

/**
 * 授权异常(当权限无效时时抛出)
 * 
 * @author 随心
 *
 */
public class AuthzException extends Exception {

	public AuthzException() {
		super();
	}
	
	public AuthzException(String message) {
		super(message);
	}
	
	public AuthzException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AuthzException(Throwable cause) {
		super(cause);
	}
}
