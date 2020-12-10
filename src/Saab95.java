import turbostate.TurboOff;
import turbostate.TurboState;

import java.awt.*;

/***
 * Class that represents a Saab95, which is a subclass to 'Car'.
 */
public class Saab95 extends Car {

    /*** Keeps track of if the turbo is on or not */
    private TurboState turboState;

    /***
     * Constructor of the class that calls upon the constructor in 'Car'. 160
     */
    public Saab95(double xCoordinate, double yCoordinate){
        super(2, 125, Color.red, "Saab95", xCoordinate, yCoordinate);
        turboState = new TurboOff();
    }

    /***
     * Change if the turbo is on or off
     * @param newState new state of the turbo, either on or off
     */
    public void setTurboState(TurboState newState) {
        this.turboState = newState;
    }

    /***
     * Changes currentSpeed to the minimum of (currentSpeed + (the speed factor) * amount)
     * and enginePower.
     * @param amount the value that the speed factor is multiplied by.
     */
    @Override
    protected void incrementSpeed(double amount) {
        currentSpeed = turboState.stateIncrementSpeed(getCurrentSpeed(),amount,getEnginePower());
    }

    /***
     * Changes currentSpeed to the maximum of (currentSpeed - (the speed factor) * amount)
     * and 0.
     * @param amount the value that the speed factor is multiplied by.
     */
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = turboState.stateDecrementSpeed(getCurrentSpeed(),amount);
    }


}
