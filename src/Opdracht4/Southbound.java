package Opdracht4;

/**
 * Created by Sergen Nurel
 * Date of creation 6-10-2015, 11:31
 *
 * Authors: Sergen Nurel,
 *
 * Version: 1.0
 * Package: Opdracht3
 * Class: SouthBound
 * Description:
 * This class is responsible for letting cars pass through the southern border
 * it will try to acquire a permit, once received it will let all cars in the queue pass, then wait for 5 seconds till the cars have passed
 * once the 5 seconds are over it will release a permit for the northern traffic light
 * if there are no cars in the queue at the beginning it will skip directly to the 5 seconds wait time "Orange trafficLight?"
 * 
 * Changelog:
 * 1.0: created the runnable function
 */
public class Southbound implements Runnable {

    private Bridge bridge;

    /**
     * SouthBound,
     * create an instance of the southbound class
     * @param bridge the bridge where Northbound and Southbound come together
     */
    public Southbound(Bridge bridge){
        this.bridge = bridge;
    }

    /**
     * run,
     * this function will allow southern cars to pass once a permit is given
     * see class description for detailed information
     */
    public void run() {
        try {
            while (true) {
                bridge.letSouthPass();
                while (bridge.southHasCars()) {
                    bridge.releaseSouthCar();
                    Thread.sleep(3000);
                }
                Thread.sleep(5000);
                bridge.alertNorth();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
