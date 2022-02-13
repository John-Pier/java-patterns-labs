package factory_method.exeptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
