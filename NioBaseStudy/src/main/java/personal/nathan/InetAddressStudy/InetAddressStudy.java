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
            System.out.println(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
