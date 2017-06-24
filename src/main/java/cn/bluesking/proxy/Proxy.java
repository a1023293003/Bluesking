package cn.bluesking.proxy;

/**
 * 代理接口
 * 
 * @author 随心
 *
 */
public interface Proxy {

	Object doProxy(ProxyChain proxyChain) throws Throwable;
}
