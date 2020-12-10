import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CarViewSpeedometer extends JPanel implements RefreshObserver{
    private CarModel carM;
    private List<JLabel> labels;

    public CarViewSpeedometer(CarModel cm){
        this.carM = cm;
        this.createLabels();
        this.addLabels();
    }

    private void createLabels(){
        this.removeAll();
        this.revalidate();

        labels = new ArrayList<>();
        for (Vehicle v : carM.getCars())
            labels.add(new JLabel(v.getModelName() +":" +Double.toString(v.getCurrentSpeed())));
    }


    private void addLabels() {
        for (JLabel label : labels)
            this.add(label);
    }

    public void refreshWhenNotified(){
        createLabels();
        addLabels();
    }
}
