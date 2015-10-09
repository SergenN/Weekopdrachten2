package Opdracht4;

import java.util.Random;

/**
 * Created by Sergen Nurel
 * Date of creation 6-10-2015, 11:33
 *
 * Authors: Sergen Nurel,
 *
 * Version: 1.0
 * Package: Opdracht3
 * Class: Main
 * Description:
 * This class is responsible for initializing the threads and creating an instance of the bridge
 *
 * Changelog:
 * 1.0: created the threads
 */
public class Main {

    /**
     * main,
     * this function will initialize the bridge
     * then it will initialize all threads, pass the bridge parameter and start the threads.
     * @param args ?
     */
    public static void main(String[] args) {
        Bridge bridge = new Bridge();

        boolean dir = new Random().nextBoolean();
        String dirString = dir ? "Southern" : "Northern";
        System.out.println("Car arrived at the "+dirString+" entrance");

        Thread generator = new Thread (new CarGenerator(bridge, dir));
        Thread north = new Thread(new Northbound(bridge));
        Thread south = new Thread(new Southbound(bridge));

        generator.start();
        north.start();
        south.start();
    }
}
