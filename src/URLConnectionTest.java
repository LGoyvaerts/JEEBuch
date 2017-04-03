/**
 * Created by gol on 21.03.2017.
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionTest {
    public static void main(String[] args) {
        try {
            //Erzeugen einer URL
            URL url = new URL("https://collab.ti8m.ch");

            //Verbindung zur Ressource bereitstellen
            URLConnection connection = url.openConnection();

            // (1) Typ der Verbindung erfragen
            System.out.println("Typ des URLConnection-Objektes:");
            System.out.println(connection.getClass());

            //Verbindung herstellen
            connection.connect();

            // (2) Lesen der HTTP-Version
            System.out.print("\nVersion des HTTP-Protokolls: ");
            System.out.println(connection.getHeaderField(0));

            // (3) Typ der abrufbaren Daten erfragen
            System.out.print("\nTyp der Daten: ");
            System.out.println(connection.getContentType());

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
