import models.*;

public class MainProxyClient {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Отсутствуют аргументы!");
            return;
        }
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        Multiple helper = new MultipleProxy();
        var result = helper.multiple(a, b);
        System.out.println("First Multiple result: " + result);
        System.out.println("Second Multiple result: " + helper.multiple(a, b));
    }
}
