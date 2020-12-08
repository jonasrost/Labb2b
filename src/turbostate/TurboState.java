package turbostate;

public interface TurboState {
    double stateIncrementSpeed(double currentSpeed, double amount, double enginePower);

    double stateDecrementSpeed(double currentSpeed, double amount);
}
