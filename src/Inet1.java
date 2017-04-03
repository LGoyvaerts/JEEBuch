/**
 * Created by gol on 20.03.2017.
 */

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet1 {
    public static void main(String[] args) {
        try {
            String host = "www.ti8m.ch";

            //Erfragen der IP-Adresse von ti8m.ch
            InetAddress adress = InetAddress.getByName(host);
            System.out.println(host + " hat die IP-Adresse " + adress.getHostAddress());
            host = "www.google.com";

            //Alle IP-Adressen erfragen, unter denen der Server www.google.ch erreichbar ist
            InetAddress[] alleAdressen = InetAddress.getAllByName(host);
            System.out.println(host + " ist unter folgenden Adressen erreichbar:");
            for (InetAddress a : alleAdressen) {
                System.out.println("\t" + a.getHostAddress());
            }

            //Die lokale Adresse nachfragen
            InetAddress lokaleAdresse = InetAddress.getLocalHost();
            System.out.print("Die IP-Adresse dieses Rechners lautet: " + lokaleAdresse.getHostAddress());

        } catch (UnknownHostException e) {
            System.out.print("Adresse nicht ermittelbar: ");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
