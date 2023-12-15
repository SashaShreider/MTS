public class Main {

    public static void main(String[] args) {
        System.out.println("Первый объект:");
        Adder.printTotalSum(new Adder(13, 56.52, 0.75));

        System.out.println("\nВторой объект:");
        Adder.printTotalSum(new Adder(58, 21999.99, 42.575));

        System.out.println("\nТретий объект:");
        Adder.printTotalSum(new Adder(5, 118.76, 59.1));
    }
}
