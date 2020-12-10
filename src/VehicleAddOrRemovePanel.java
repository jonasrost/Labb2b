import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleAddOrRemovePanel extends JPanel {

    private CarController carC;
    private JButton addVehicleButton = new JButton("Add Vehicle");
    private JButton removeVehicleButton = new JButton("Remove Vehicle");

    public VehicleAddOrRemovePanel(CarController cc) {
        this.setLayout(new GridLayout(2,1));
        this.carC = cc;
        this.initButtons();
    }

    private void initButtons() {
        this.add(addVehicleButton, 0);
        this.add(removeVehicleButton, 1);

        addVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { carC.addVehicle(); }
        });

        removeVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { carC.removeVehicle(); }
        });
    }


}
