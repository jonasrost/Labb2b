import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarModel {
    private List<BufferedImage> objectImages;

    private List<Point> objectPoints;

    private List<Vehicle> cars;

    private List<RefreshObserver> observers;

    public CarModel() {
        objectImages = new ArrayList<>();
        objectPoints = new ArrayList<>();
        cars = new ArrayList<>();
        observers = new ArrayList<>();
        initCars();
        initObjectPoints();
        initObjectImages();
    }

    private void initCars() {
        this.cars.add(new Volvo240());
        this.cars.add(new Scania());
        this.cars.add(new Saab95());
    }

    private void initObjectPoints() {
        for (Vehicle car: this.cars) {
            Point p = new Point((int) car.getXCoordinate(), (int) car.getYCoordinate());
            this.objectPoints.add(p);
        }
    }

    private void initObjectImages() {
        for (Vehicle car : this.cars) {
            try{
                this.objectImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/"+car.getModelName()+".jpg")));
            }catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void moveit(int x, int y, int indexOfCar){
        Point p = objectPoints.get(indexOfCar);
        p.x = x;
        p.y = y;
    }

    // TODO: Fix so that it doesnt return original list
    public List<BufferedImage> getObjectImages() {
        return objectImages;
    }
    // TODO: Fix so that it doesnt return original list
    public List<Point> getObjectPoints() {
        return objectPoints;
    }

    public void addObserver(RefreshObserver newObserver) {
        this.observers.add(newObserver);
    }

    public void timerUpdate() {
        for (int i = 0; i < cars.size(); i++) {
            Vehicle car = cars.get(i);

            if (checkIfVehicleInBoundaries(car)) {
                car.turn180Degrees();
            }

            car.move();

            int x = (int) Math.round(car.getXCoordinate());
            int y = (int) Math.round(car.getYCoordinate());
            moveit(x, y, i);
            // calls update method in all observers
            for (RefreshObserver observer : this.observers)
                observer.refreshWhenNotified();
        }
    }

    private boolean checkIfVehicleInBoundaries(Vehicle car) {
        Point currentPos = new Point((int)car.getXCoordinate(),(int)car.getYCoordinate());

        return (currentPos.y > 500 || currentPos.y < 0 || currentPos.x < 0 || currentPos.x >700);
    }

    private boolean checkIfVehicleCorrectClass(Vehicle car, Class<? extends Vehicle> T ) {
        return car.getClass() == T;
    }

    // Start the engine of all cars
    void startVehicles() {
        for (Vehicle car : cars)
            car.startEngine();
    }

    // Stop the engine of all cars
    void stopVehicles() {
        for (Vehicle car : cars)
            car.stopEngine();
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    // Calls the brake method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (checkIfVehicleCorrectClass(car, Saab95.class))
                ((Saab95) car).setTurboOn();
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (checkIfVehicleCorrectClass(car, Saab95.class))
                ((Saab95) car).setTurboOff();
        }
    }

    void liftBed() {
        for (Vehicle car: cars) {
            if (checkIfVehicleCorrectClass(car, Scania.class))
                ((Scania) car).increaseAngleOfTruckBed(10);
        }
    }

    void lowerBed() {
        for (Vehicle car: cars) {
            if (checkIfVehicleCorrectClass(car, Scania.class))
                ((Scania) car).decreaseAngleOfTruckBedTo(5);
        }
    }



}
