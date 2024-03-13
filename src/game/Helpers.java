package game;

public class Helpers {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value *= factor;
        long temp;
        if (value > 0) {
            temp = (long) Math.ceil(value);
        } else {
            temp = (long) Math.floor(value);
        }
        return (double) temp / factor;
    }
}
