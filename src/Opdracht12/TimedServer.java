package Opdracht12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

class Worker extends Thread {
    private Socket connection;
    private Manager maxRes;
    private int resources;
    private Semaphore sem = new Semaphore(1);

    public Worker(Socket connection, Manager maxRes) {
        this.connection = connection;
        this.maxRes = maxRes;
        this.resources = 0;
    }

    public void run() {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter pout = new PrintWriter(connection.getOutputStream(), true);

            pout.println("Resources aanvragen");

            resources += 2;
            int result = maxRes.decreaseCount(resources, this);

            if(result == -1){
                pout.println("Er zijn geen resources beschikbaar!, wachten op resources");
                sem.acquire(2);
            }

            pout.println("Recourses verkregen.");

            for(int i = 0; i < 10; i++) {
                Thread.sleep(1500);
                pout.println(i);
            }

            pout.println("Gedoe uitgevoerd, wachten...");
            String line;
            boolean running = true;
            while(running){
                line = in.readLine();
                if(line == null){
                    in.close();
                    pout.close();
                    connection.close();
                    running = false;
                }
            }

            System.out.println("Client heeft de connectie verbroken");
            maxRes.increaseCount(resources);
            resources -= resources;

        }
        catch (IOException ioe) {}
        catch (InterruptedException e){}
        finally {
            maxRes.increaseCount(resources);
        }
    }

    public void releaseResources(){
        sem.release(2);
    }

}


public class TimedServer {
    public static final int PORT = 2500;
    private Manager maxRes;

    public static void main(String[] args) {
        TimedServer server = new TimedServer();
        server.startServer();
    }

    private TimedServer(){
        maxRes = new Manager();
    }

    private void startServer(){

        Socket connection;

        try {
            ServerSocket server = new ServerSocket(PORT);

            while (true) {
                System.out.println("Wachten op connecties");
                connection = server.accept();
                System.out.println("Client heeft connectie gemaakt");
                Thread worker = new Thread(new Worker(connection, maxRes));
                worker.start();
            }
        }
        catch (IOException ioe) { }
    }
}
