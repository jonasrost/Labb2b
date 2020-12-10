public class VehicleFactory {
    public static Vehicle createVolvo240(double x, double y) {
        return new Volvo240(x, y);
    }

    public static Vehicle createSaab95(double x, double y) {
        return new Saab95(x, y);
    }

    public static Vehicle createScania(double x, double y) {
        return new Scania(x, y);
    }

    public static Vehicle createArticulatedLorry(double x, double y) {
        return new ArticulatedLorry(0,0);
    }

}
