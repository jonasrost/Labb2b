package turbostate;

public class TurboOff implements TurboState{

    private final double factor = 125 * 0.01;

    @Override
    public double stateIncrementSpeed(double currentSpeed, double amount, double enginePower) {
        return Math.min(currentSpeed + factor * amount, enginePower);
    }

    @Override
    public double stateDecrementSpeed(double currentSpeed, double amount) {
        return Math.max(currentSpeed - factor * amount, 0);
    }
}
