package com.lhl.boot.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 *
 * @author liuhaolu01
 * @date 2020-11-28
 * @time 15:38
 * @describe:
 */
public class AddressHelper {

    public static final String IPV4_ERROR = "ipv4_error";

    private static String ipv4;

    public static String getLocalhostIPV4() {
        if (ipv4 == null) {
            synchronized (AddressHelper.class) {
                if (ipv4 == null) {
                    ipv4 = IPV4_ERROR;
                    try {
                        for (InetAddress inetAddress : getInetAddresses("e(\\w)+\\d")) {
                            ipv4 = inetAddress.getHostAddress();
                            System.out.println("scanning ip ---> " + ipv4);
                            if (!ipv4.contains(":")) break;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return ipv4;
    }

    /**
     * 获取某个网卡的 ipv6与ipv4地址
     *
     * @param regex e.g.: "^eth\\d"
     * @return 返回数组中, 第2个为ipv6地址 第1个为ipv4地址
     * @throws SocketException
     */
    public static InetAddress[] getInetAddresses(String regex) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            if (Pattern.matches(regex, netint.getDisplayName())) {
                // out.printf("Display name: %s\n", netint.getDisplayName());
                // out.printf("Name: %s\n", netint.getName());
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                ArrayList<InetAddress> list = Collections.list(inetAddresses);
                InetAddress[] addrs = list.toArray(new InetAddress[list.size()]);
                if (list.size() > 1) {
                    if (list.get(0).getHostAddress().contains(":")) {
                        ArrayUtils.reverse(addrs);
                    }
                }
                return addrs;
            }
        }
        return new InetAddress[0];
    }

    @Deprecated
    public static void getCurrentMacAddress() {
        InetAddress ip;
        try {
            ip = InetAddress.getByName("127.0.0.1");
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-"
                        : ""));
            }
            System.out.println(sb.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
