package com.lhl.boot.rpc;

import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-03
 * @time 15:01
 * @describe:
 */
@Slf4j
public class RPCClient<T> {

    /**
     * 通过动态代理将参数发送过去到 RPCServer, RPCServer 返回结果这个方法处理成为正确的实体
     */
    public static <T> T getRemoteProxyObj(final Class<T> service, final InetSocketAddress addr) {
        log.info("client get result form server");
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new ServiceHandler(addr, service));
    }

    private static class ServiceHandler implements InvocationHandler {

        private InetSocketAddress addr;

        private Class service;

        public ServiceHandler(InetSocketAddress addr, Class service) {
            this.addr = addr;
            this.service = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream out = null;
            ObjectInputStream input = null;
            try {
                socket = new Socket();
                socket.connect(addr);
                // 将实体类,参数,发送给远程调用方
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeUTF(service.getSimpleName());
                out.writeUTF(method.getName());
                out.writeObject(method.getParameterTypes());
                out.writeObject(args);
                input = new ObjectInputStream(socket.getInputStream());
                return input.readObject();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                out.close();
                input.close();
                socket.close();
            }
            return null;
        }
    }

}
