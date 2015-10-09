package Opdracht3;

import java.util.concurrent.Semaphore;

/**
 * Created by Sergen Nurel
 * Date of creation 6-10-2015, 11:30
 *
 * Authors: Sergen Nurel,
 *
 * Version: 1.0
 * Package: Opdracht3
 * Class: Bridge
 * Description:
 * This class manages the car queue's from the northern and southern entrance
 * It has functions to change the traffic lights and edit the car queue's
 * Changelog:
 * 1.0: initially added the add, release, acquire, and alert functions
 */
public class Bridge {

    private int southQueue, northQueue;
    private Semaphore northernLight, southernLight;

    /**
     * Bridge,
     * this function will initialize all cars in the northern and southern queue,
     * then it will initialize all semaphores 'traffic lights'
     */
    public Bridge(){
        southQueue = 5;
        northQueue = 9;
        northernLight = new Semaphore(1);
        southernLight = new Semaphore(0);
    }

    /**
     * letNorthPass,
     * this will try to acquire a permit to let the northern cars pass
     */
    public void letNorthPass() {
        try {
            northernLight.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * northHasCars,
     * This will check if there are cars left in the northern entrance queue
     * @return true if there are cars left in the Queue
     */
    public boolean northHasCars(){
        return northQueue > 0;
    }

    /**
     * releaseNorthCar,
     * This will release a car from the northern entrance queue and print a message
     */
    public void releaseNorthCar(){
        if(northQueue > 0) {
            northQueue--;
            System.out.println("Car released from northern queue, "+northQueue +" cars left in the queue");
        }
    }

    /**
     * letSouthPass,
     * This will try to acquire a permit to let the southern cars pass
     */
    public void letSouthPass() {
        try {
            southernLight.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * southHasCars,
     * This will check if there are cars left in the southern entrance queue
     * @return true if there are cars left in the Queue
     */
    public boolean southHasCars(){
        return southQueue > 0;
    }

    /**
     * releaseSouthCar,
     * This will release a car from the southern entrance queue and print a message
     */
    public void releaseSouthCar(){
        if(southQueue > 0){
            southQueue--;
            System.out.println("Car released from southern queue, "+southQueue +" cars left in the queue");
        }
    }

    /**
     * alertNorth,
     * This will release a permit for for the northern traffic light
     */
    public void alertNorth(){
        northernLight.release();
    }

    /**
     * alertSouth,
     * This will release a permit for for the southern traffic light
     */
    public void alertSouth(){
        southernLight.release();
    }

    /**
     * addNorthernCar,
     * this will add a car to the northern queue
     */
    public void addNorthernCar(){
        northQueue++;
    }

    /**
     * addSouthernCar,
     * this will add a car to the southern queue
     */
    public void addSouthernCar(){
        southQueue++;
    }
}
