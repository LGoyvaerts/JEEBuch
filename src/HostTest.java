import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by gol on 03.04.2017.
 */
public class HostTest {
    private static String host;
    private static URL url;

    public static void main(String[] args) throws Exception {


        Scanner in = new Scanner(System.in);

        System.out.println("(1) Host-Adressen oder (2) Protokoll herausfinden");

        System.out.print("Host: ");
        host = in.nextLine();

        InetAddress[] hostAdressen = InetAddress.getAllByName(host);
        System.out.println("Dieser host ist unter folgenden Adressen erreichbar:");
        for (InetAddress a : hostAdressen) {
            System.out.println("\t" + a.getHostAddress());
        }

        url = new URL(host);
        System.out.println("Protokoll " + url.getProtocol());
        System.out.println("Rechner: " + url.getHost());
        System.out.println("Datei " + url.getFile());
        in.close();

    }
}
