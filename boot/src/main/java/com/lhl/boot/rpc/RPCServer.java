package com.lhl.boot.rpc;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-03
 * @time 14:58
 * @describe:
 */
@Slf4j
public class RPCServer {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final ConcurrentHashMap<String, Class> serviceRegister = new ConcurrentHashMap<>();

    /**
     * 注册方法
     *
     * @param service
     * @param impl
     */
    public void register(Class service, Class impl) {
        serviceRegister.put(service.getSimpleName(), impl);
    }

    /**
     * 启动方法
     *
     * @param port
     */
    public void start(int port) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket();
            socket.bind(new InetSocketAddress(port));
            log.info("server start: {}", JSON.toJSONString(serviceRegister));
            while (true) {
                executor.execute(new Task(socket.accept()));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private static class Task implements Runnable {
        Socket client = null;

        public Task(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                // 按照顺序读取对方写过来的内容
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class serviceClass = serviceRegister.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + " 没有找到!");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    output.close();
                    input.close();
                    client.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}
