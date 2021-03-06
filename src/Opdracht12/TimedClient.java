/**
 * TimedClient.java
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

package Opdracht12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TimedClient
{
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        BufferedReader bin = null;
        Socket sock = null;

        try {
            sock = new Socket("127.0.0.1",2500);
            in = sock.getInputStream();
            bin = new BufferedReader(new InputStreamReader(in));

            String line;
            while( (line = bin.readLine()) != null)
                System.out.println(line);
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            sock.close();
        }
    }
}
