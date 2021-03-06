import turbostate.TurboOff;
import turbostate.TurboOn;

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
        this.cars.add(VehicleFactory.createVolvo240(0,0));
        this.cars.add(VehicleFactory.createSaab95(0, 160));
        this.cars.add(VehicleFactory.createScania(0, 320));
    }

    private void initObjectPoints() {
        for (Vehicle car: this.cars) {
            Point p = new Point((int) car.getXCoordinate(), (int) car.getYCoordinate());
            this.objectPoints.add(p);
        }
    }

    void addImage(String modelName) {
        try{
            this.objectImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/"+modelName+".jpg")));
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void initObjectImages() {
        for (Vehicle car : this.cars) {
            addImage(car.getModelName());
        }
    }

    private void moveit(int x, int y, int indexOfCar){
        Point p = objectPoints.get(indexOfCar);
        p.x = x;
        p.y = y;
    }

    public List<Vehicle> getCars() {
        List<Vehicle> listToReturn = new ArrayList<>();
        for (Vehicle car : cars) {
            listToReturn.add(car); //Kan inte skapa en exakt kopia av en godtycklig bil. Därav denna lösning
        }
        return listToReturn;
    }

    public List<BufferedImage> getObjectImages() {
        List<BufferedImage> listToReturn = new ArrayList<>();
        for (BufferedImage image : objectImages)
            listToReturn.add(image);
        return listToReturn;
    }

    public List<Point> getObjectPoints() {
        List<Point> listToReturn = new ArrayList<>();
        for (Point p : objectPoints) {
            Point returnP = new Point(p);
            listToReturn.add(returnP);
        }
        return listToReturn;
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
                ((Saab95) car).setTurboState(new TurboOn());
        }
    }

    void turboOff() {
        for (Vehicle car : cars) {
            if (checkIfVehicleCorrectClass(car, Saab95.class))
                ((Saab95) car).setTurboState(new TurboOff());
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

    void addVehicle() {
        if (this.cars.size() < 10) {
            double xCord = Math.random() * 500;
            double yCord = Math.random() * 300;
            this.cars.add(new Volvo240(xCord, yCord));
            this.objectPoints.add(new Point((int) xCord, (int) yCord));
            this.addImage("Volvo240");
        } else {
            System.out.println("Can't add more Vehicles");
        }
    }

    void removeVehicle() {
        if (cars.size() > 0) {
            this.objectPoints.remove(0);
            this.objectImages.remove(0);
            this.cars.remove(0);
        }
    }
}
