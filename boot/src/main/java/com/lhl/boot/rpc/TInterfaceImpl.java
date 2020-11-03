package com.lhl.boot.rpc;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-03
 * @time 15:12
 * @describe:
 */
public class TInterfaceImpl implements TInterface{
    @Override
    public String getMsgFromServer(String msg) {
        return "message: " + msg;
    }
}
