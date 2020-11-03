package com.lhl.boot.rpc;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-03
 * @time 15:13
 * @describe:
 */
@Slf4j
public class RunTest {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            RPCServer rpcServer = new RPCServer();
            rpcServer.register(TInterface.class, TInterfaceImpl.class);
            rpcServer.start(4700);
        }).start();
        Thread.sleep(200);
        TInterface service = RPCClient.getRemoteProxyObj(TInterface.class, new InetSocketAddress("127.0.0.1", 4700));
        String msg = service.getMsgFromServer("Hello RPC!");
        log.info(msg);
    }
}
