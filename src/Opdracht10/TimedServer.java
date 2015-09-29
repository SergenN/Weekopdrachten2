package Opdracht10;
import java.net.*;
import java.io.*;
import java.util.concurrent.Semaphore;

class Worker implements Runnable
{
    private int sleepTime = 30;

    private Socket connection;
    private Semaphore maxConn;

    public Worker(Socket connection, Semaphore maxConn) {
        this.connection = connection;
        this.maxConn = maxConn;
    }

    public void run() {
        try {
            PrintWriter pout = new PrintWriter(connection.getOutputStream(), true);

            while (sleepTime > 0) {
                String s = (sleepTime == 1) ? " second." : " seconds.";
                pout.println("Sleeping " + sleepTime + " more " + s);
                Thread.sleep(1000);
                sleepTime -= 1;
            }

            // now close the socket connection
            connection.close();

        }
        catch (InterruptedException ie) { }
        catch (IOException ioe) { }
        finally {
            maxConn.release();
        }
    }
}


public class TimedServer
{
    public static final int PORT = 2500;
    private Semaphore maxConn;

    public static void main(String[] args) {
        TimedServer server = new TimedServer();
        server.startServer();
    }

    private TimedServer(){
        maxConn = new Semaphore(3, true);
    }

    private void startServer(){

        Socket connection;

        try {
            ServerSocket server = new ServerSocket(PORT);

            while (true) {
                try {
                    maxConn.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                connection = server.accept();

                Thread worker = new Thread(new Worker(connection, maxConn));
                worker.start();
            }
        }
        catch (java.io.IOException ioe) { }
    }
}
