/**
 * Класс Adder, который хранит информацию о товаре
 * Содержит поля: колличесвто товара, сумма товаара, скидка на товар
 * СОдержит метод: вывод общей суммы покупки без скидки и со скидкой
 *
**/
public class Adder {
//    колличество товара
    private final int num;
//    сумма товара
    private final double sum;
//    скидка
    private final double discount;

    public Adder(int num, double sum, double discount) {
        this.num = num;
        this.sum = sum;
        this.discount = discount;
    }

    public static void printTotalSum(Adder adder) {
        System.out.println("Итоговая сумма без скидки: " + String.format("%.2f", adder.num * adder.sum));
        System.out.println("Итоговая сумма со скидкой: " + String.format("%.2f", adder.num * adder.sum * (100 - adder.discount)/100));
    }


}
