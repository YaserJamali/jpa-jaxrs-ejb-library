package tamin.library.model.service.BL;
@EightDigits
public class IssnGenerator implements NumberGenerator {
//    private static IssnGenerator instance;
//
//    private IssnGenerator() {
//    }
//
//    public static IssnGenerator getInstance() {
//        if (instance == null)
//            instance = new IssnGenerator();
//        return instance;
//    }
//

    private Integer nextNumber = 0;

    @EightDigits
    public String numberGenerator() {
        return "8-4532-" + nextNumber++;
    }
}
