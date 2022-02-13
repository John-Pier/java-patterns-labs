package factory_method.exeptions;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String errorMessage) {
        super(errorMessage);
    }
}
