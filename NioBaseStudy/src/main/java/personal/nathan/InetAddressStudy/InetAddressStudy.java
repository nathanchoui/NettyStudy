package personal.nathan.InetAddressStudy;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by za-zhangwei002 on 19-4-11.
 */
public class InetAddressStudy {

    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println("address: "+ inetAddress.getAddress());
            System.out.println("canionicalHostName: " + inetAddress.getCanonicalHostName());
            System.out.println("hostAddress: " + inetAddress.getHostAddress());
            System.out.println("hostName: " + inetAddress.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
