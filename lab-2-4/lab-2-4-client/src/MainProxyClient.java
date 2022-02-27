public class MainProxyClient {
    public static void main(String[] args) {
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        Multiple helper = new MultipleProxy();
        var result = helper.multiple(a, b);
        System.out.println("Multiple result: " + result);
    }
}
