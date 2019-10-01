package trialClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IP {
    public static void main(String[] args) {
        String hostname;
        String ip;

        try {
            ip = InetAddress.getLocalHost().toString();
            IPAdressDomain ipAddress = new IPAdressDomain(ip, '/');
            hostname = InetAddress.getLocalHost().getHostName();
            System.out.println("Your current IP address with hostname : " + ip);
            System.out.println("Your current IP address : " + ipAddress.extension());
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}