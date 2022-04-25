import java.util.Random;

public class ScooterTestMethods {
    Random random = new Random();

    public String genRandomAlfaString() {
        StringBuilder buffer = new StringBuilder();
        int size = random.nextInt(16);

        return buffer.toString();
    }
}
