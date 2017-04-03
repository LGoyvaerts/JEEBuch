/**
 * Created by gol on 21.03.2017.
 */

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) {
        try {
            String urlString = "http://docs.oracle.com/javabase/7/docs/api/index.html";

            //Erzeugen der URL
            URL url = new URL(urlString);

            //Ausgabe der Bestandteile
            System.out.println("Protokoll: " + url.getProtocol());
            System.out.println("Rechner: " + url.getHost());
            System.out.println("Datei: " + url.getFile());

        } catch (MalformedURLException e) {
            //Der Aufruf des Konstruktors wirft eine Exception,
            //wenn der übergebene String keine gültige URL darstellt
            System.out.println(e.getMessage());
        }
    }
}
