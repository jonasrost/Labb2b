import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/***
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */
public class CarController {
    // member fields:

    private CarModel carM;

    public CarController(CarModel cm) {
        this.carM = cm;
    }

    //methods:

    // Start the engine of all cars
    void startVehicles() {
        carM.startVehicles();
    }

    // Stop the engine of all cars
    void stopVehicles() {
        carM.stopVehicles();
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        carM.gas(amount);
    }

    // Calls the brake method for each car once
    void brake(int amount) {
        carM.brake(amount);
    }

    void turboOn() {
        carM.turboOn();
    }

    void turboOff() {
        carM.turboOff();
    }

    void liftBed() {
        carM.liftBed();
    }

    void lowerBed() {
        carM.lowerBed();
    }

    void addVehicle() {
        carM.addVehicle();
    }

    void removeVehicle() {
        carM.removeVehicle();
    }
}
