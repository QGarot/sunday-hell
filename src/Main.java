import boot.IBoot;
import boot.SundayHell;

public class Main {
    public static void main(String[] args) {
        IBoot boot = new SundayHell();
        boot.run();
    }
}