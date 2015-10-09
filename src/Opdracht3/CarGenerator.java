package Opdracht3;

import java.util.*;

/**
 * Created by Sergen Nurel
 * Date of creation 6-10-2015, 11:31
 *
 * Authors: Sergen Nurel,
 *
 * Version: 1.0
 * Package: Opdracht3
 * Class: CarGenerator
 * Description:
 * This class creates new cars at random intervals and sends them to a random queue
 *
 * Changelog:
 * 1.0: added the run function
 */

public class CarGenerator extends TimerTask{

    private Bridge bridge;
    private Timer timer;
    private Random random;
    private boolean entrance;

    /**
     * CarGenerator,
     * This function creates a new instance of the car generator
     * @param bridge the bridge where northbound and southbound come together
     * @param entrance the entrance where the new car should arrive
     */
    public CarGenerator(Bridge bridge, boolean entrance){
        this.bridge = bridge;
        this.timer = new Timer();
        this.random = new Random();
        this.entrance = entrance;
    }

    /**
     * run,
     * this function will create a new car to the given entrance
     * then it will give a message when and where another new car will arrive
     * finally it will create a new timer schedule with the given direction and delay
     * (yes, this could also be possible with a while true loop and a wait of random generated seconds)
     */
    public void run() {
        //message new car arriving
        if(entrance){
            //System.out.println("New car arrived at southern entrance of the bridge");
            bridge.addSouthernCar();
        } else {
            //System.out.println("New car arrived at Northern entrance of the bridge");
            bridge.addNorthernCar();
        }

        //plan in new car timing + direction + message
        int delay = (5 + random.nextInt(10)) * 1000;

        boolean newDirection = random.nextBoolean();
        String dir = newDirection ? "Southern" : "Northern";
        int secondsDelay = delay/1000;
        System.out.println("New car departed to "+dir+" entrance, it will arrive in "+secondsDelay+" seconds.");

        //schedule a new car driving
        timer.schedule(new CarGenerator(bridge, newDirection), delay);
    }
}
