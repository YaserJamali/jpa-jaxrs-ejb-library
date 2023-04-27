package tamin.library.model.service.BL;


import java.io.Serializable;

@ThirteenDigit
public class IsbnGenerator implements Serializable, NumberGenerator {
    private static volatile IsbnGenerator instance;

    private IsbnGenerator() {
    }

    public static IsbnGenerator getInstance() {
        if (instance == null) {
            synchronized (IsbnGenerator.class) {
                if (instance == null) {
                    instance = new IsbnGenerator();
                }
            }
        }
        return instance;
    }

    private Integer nextNumber = 1;

    @ThirteenDigit
    public String numberGenerator() {
        return "13-15489-" + nextNumber++;
    }


}
