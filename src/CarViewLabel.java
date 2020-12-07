import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CarViewLabel extends JPanel implements RefreshObserver{
    private Vehicle assignedVehicle;
    private JLabel label;

    public CarViewLabel(Vehicle vehicle){
        this.assignedVehicle = vehicle;
        this.createLabel();
        this.add(label);
    }

    private void createLabel(){
        label = (new JLabel(assignedVehicle.getModelName() +":" +Double.toString(assignedVehicle.getCurrentSpeed())));
    }

    public void updateLabel() {
        label.setText(assignedVehicle.getModelName() +":" +Double.toString(assignedVehicle.getCurrentSpeed()));
    }

    public void refreshWhenNotified(){
        updateLabel();
    }
}
