import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {
    public static void main(String[] args) {
        CarModel cm = new CarModel();
        CarController cc = new CarController(cm);
        CarView cv = new CarView("CarSim 2.0", cc, cm);
        cm.addObserver(cv);
        CarViewSpeedometer cvs = new CarViewSpeedometer(cm);
        cv.add(cvs);
        cm.addObserver(cvs);
        //VehicleAddOrRemovePanel vaor = new VehicleAddOrRemovePanel(cc);
        //cv.add(vaor);

        // The delay (ms) corresponds to 20 updates a sec (hz)
        final int delay = 50;
        // The timer is started with an listener (see below) that executes the statements
        // each step between delays.
        Timer timer = new Timer(delay, new TimerListener(cm));


        // Start the timer
        timer.start();
    }


    private static class TimerListener implements ActionListener {
        CarModel cm;
        TimerListener(CarModel cm) {
            this.cm = cm;
        }

        public void actionPerformed(ActionEvent e) {
            cm.timerUpdate();
        }
    }



}
