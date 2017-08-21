package cn.bluesking.framework.demo;
import java.io.Serializable;
import java.util.Stack;

public class HelloShiro implements Serializable {

	/**
	 * slf4j日志配置
	 */
//	private static final Logger _LOG = LoggerFactory.getLogger(HelloShiro.class);

//	public static void main(String[] args) {
//		ArrayList demo;
//		LinkedList demo1;
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//		SecurityManager securityManager = factory.getInstance();
//		SecurityUtils.setSecurityManager(securityManager);
//		
//		Subject subject = SecurityUtils.getSubject();
//		UsernamePasswordToken token = new UsernamePasswordToken("shiro", "201314");
//		try {
//			subject.login(token);
//		} catch (AuthenticationException e) {
//			_LOG.error("登录失败！");
//			return;
//		}
//		_LOG.info("登录成功！Hello" + subject.getPrincipal());
//		// 注销
//		subject.logout();
//	}
    
	public void HelloShiro() {
		
	}
	
    public static void main(String[] args) {
    	System.out.println(hash(100));
    	System.out.println(hash(0));
    	System.out.println(hash(-100));
	}
    
    //求hash值的方法，重新计算hash值  
    static int hash(int h) {    
        h ^= (h >>> 20) ^ (h >>> 12);    
        return h ^ (h >>> 7) ^ (h >>> 4);    
    } 
    
    // h & (length-1)保证返回值的小于length    
    static int indexFor(int h, int length) {    
        return h & (length-1);    
    }    
}
