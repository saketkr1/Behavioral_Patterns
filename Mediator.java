
/*
    * Mediator pattern is used to reduce communication complexity between multiple objects or classes.
    * Mediator pattern provides a mediator class that handles all the communications between different classes.
    * Mediator pattern allows you to decouple classes by having them communicate through a mediator object.
    * Mediator pattern is used when the communication logic between objects is complex, and it becomes difficult to maintain.
    * Mediator pattern centralizes the communication logic, making it easier to maintain and manage.
 */

interface IMediator {
    void notify(String message,  Department department);
}

interface Department {
    void setMediator(IMediator mediator);
    void send(String message);
    void receive(String message);
}

class HRDepartment implements Department {
    private IMediator mediator;

    @Override
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void send(String message) {
        mediator.notify(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("HR received: " + message);
    }
}

class FinanceDepartment implements Department {
    private IMediator mediator;

    @Override
    public void setMediator(IMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void send(String message) {
        mediator.notify(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println("Finance received: " + message);
    }
}

class Company implements IMediator {
    private Department hrDepartment;
    private Department financeDepartment;

    public void setHRDepartment(HRDepartment hr) {
        this.hrDepartment = hr;
    }

    public void setFinanceDepartment(FinanceDepartment finance) {
        this.financeDepartment = finance;
    }

    @Override
    public void notify(String message, Department department) {
        if (department.equals(hrDepartment) && financeDepartment != null) {
            financeDepartment.receive(message);
        } else if (department.equals(financeDepartment) && hrDepartment != null) {
            hrDepartment.receive(message);
        }
    }
}

public class Mediator {
    public static void main(String[] args) {
        Company company = new Company();
        HRDepartment hr = new HRDepartment();
        FinanceDepartment finance = new FinanceDepartment();

        company.setHRDepartment(hr);
        company.setFinanceDepartment(finance);

        hr.setMediator(company);
        finance.setMediator(company);

        hr.send("Hello, this is HR.");

    }
}
