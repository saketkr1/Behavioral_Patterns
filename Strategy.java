/*
  - Strategy Pattern
    - The Strategy Pattern is used to define a family of algorithms, encapsulate each one of them, and make them interchangeable.
    - The Strategy Pattern allows the client to choose the algorithm to use at runtime.
    - The Strategy Pattern is used when you want to define a class that will have one behavior that is similar to other behaviors in a list.
    - The Strategy Pattern is used when you need to use one of several behaviors dynamically.
    - The Strategy Pattern is used when you want to switch from one algorithm to another during runtime.
 */
interface PaymentStrategy {
    void pay(int amount);
}

class UPIPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using UPI Payment");
    }
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Credit Card Payment");
    }
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Cash Payment");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class Strategy {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new UPIPayment());
        cart.pay(100);

        cart.setPaymentStrategy(new CreditCardPayment());
        cart.pay(200);

        cart.setPaymentStrategy(new CashPayment());
        cart.pay(300);
    }
}
