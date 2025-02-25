

/*
    * Chain of Responsibility
    * The Chain of Responsibility pattern is a behavioral design pattern that allows an object to pass a request along a chain of handlers.
    * Upon receiving a request, each handler decides either to process the request or to pass it along the chain.
    * The Chain of Responsibility pattern allows multiple objects to handle the request without coupling sender class to the concrete classes of the receivers.
 */

abstract class CashDispenser {
    protected CashDispenser nextDispenser;

    public void setNextDispenser(CashDispenser nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    public void dispense(int amount) {
        if (amount > 0) {
            if (canHandle(amount)) {
                processDispense(amount);
            } else if (nextDispenser != null) {
                nextDispenser.dispense(amount);
            } else {
                System.out.println("Cannot dispense the remaining amount: " + amount);
            }
        }
    }

    protected abstract boolean canHandle(int amount);
    protected abstract void processDispense(int amount);
}

class Rupee100Dispenser extends CashDispenser {
    @Override
    protected boolean canHandle(int amount) {
        return amount >= 100;
    }

    @Override
    protected void processDispense(int amount) {
        int numNotes = amount / 100;
        int remainder = amount % 100;
        System.out.println("Dispensing " + numNotes + " x ₹100 notes");
        if (remainder > 0) {
            System.out.println("Cannot dispense remaining ₹" + remainder);
        }
    }
}

class Rupee200Dispenser extends CashDispenser {
    @Override
    protected boolean canHandle(int amount) {
        return amount >= 200;
    }

    @Override
    protected void processDispense(int amount) {
        int numNotes = amount / 200;
        int remainder = amount % 200;
        System.out.println("Dispensing " + numNotes + " x ₹200 notes");
        if (remainder > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}

class Rupee500Dispenser extends CashDispenser {
    @Override
    protected boolean canHandle(int amount) {
        return amount >= 500;
    }

    @Override
    protected void processDispense(int amount) {
        int numNotes = amount / 500;
        int remainder = amount % 500;
        System.out.println("Dispensing " + numNotes + " x ₹500 notes");
        if (remainder > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}

class Rupee2000Dispenser extends CashDispenser {
    @Override
    protected boolean canHandle(int amount) {
        return amount >= 2000;
    }

    @Override
    protected void processDispense(int amount) {
        int numNotes = amount / 2000;
        int remainder = amount % 2000;
        System.out.println("Dispensing " + numNotes + " x ₹2000 notes");
        if (remainder > 0 && nextDispenser != null) {
            nextDispenser.dispense(remainder);
        }
    }
}


public class ChainOfResponsibility {
    // Create dispensers
    public static void main(String[] args) {
        CashDispenser dispenser2000 = new Rupee2000Dispenser();
        CashDispenser dispenser500 = new Rupee500Dispenser();
        CashDispenser dispenser200 = new Rupee200Dispenser();
        CashDispenser dispenser100 = new Rupee100Dispenser();
        dispenser2000.setNextDispenser(dispenser500);
        dispenser500.setNextDispenser(dispenser200);
        dispenser200.setNextDispenser(dispenser100);

        // Set up the chain: ₹2000 → ₹500 → ₹200 → ₹100

        // Test withdrawal
        int amount = 6800;
        System.out.println("Withdrawing ₹" + amount + "...");
        dispenser2000.dispense(amount);
    }
}

