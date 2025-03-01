/*
  - The State Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes.
    It helps in encapsulating different states within separate classes, promoting better code organization and flexibility.
    - The State Pattern is used when an object's behavior depends on its state and it must change its behavior at runtime.
    - The State Pattern is used when you want to avoid using multiple if-else statements to manage the state transitions of an object.
 */

/*
 Key Features of the State Pattern
    - The State Pattern allows an object to change its behavior when its internal state changes.
    - The State Pattern promotes better code organization by encapsulating different states within separate classes.
    - The State Pattern helps in avoiding multiple if-else statements to manage the state transitions of an object.
    - The State Pattern makes it easier to add new states to an object without changing the existing code.
    - The State Pattern follows the Open/Closed Principle, allowing you to introduce new states without modifying the existing code.
 */

interface DocumentState {
    void publish();
    void approve();
}

class Document {
    private DocumentState state;

    public Document() {
        this.state = new DraftState();
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public void publish() {
        state.publish();
    }

    public void approve() {
        state.approve();
    }
}

class DraftState implements DocumentState {
    @Override
    public void publish() {
        System.out.println("The document is published in draft state.");
    }

    @Override
    public void approve() {
        System.out.println("The document is approved in draft state.");
    }
}

class ModerationState implements DocumentState {
    @Override
    public void publish() {
        System.out.println("The document is published in moderation state.");
    }

    @Override
    public void approve() {
        System.out.println("The document is approved in moderation state.");
    }
}

class PublishedState implements DocumentState {
    @Override
    public void publish() {
        System.out.println("The document is already published.");
    }

    @Override
    public void approve() {
        System.out.println("The document is approved in published state.");
    }
}

public class State {
    public static void main(String[] args) {
        Document document = new Document();

        document.publish();
        document.approve();

        document.setState(new ModerationState());

        document.publish();
        document.approve();

        document.setState(new PublishedState());

        document.publish();
        document.approve();
    }
}

interface TrafficLightState {
    void changeColour(TrafficLight context);
}

class TrafficLight {
    private TrafficLightState state;

    public TrafficLight() {
        this.state = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    public void changeColour() {
        state.changeColour(this);
    }
}

class RedLightState implements TrafficLightState {
    @Override
    public void changeColour(TrafficLight context) {
        System.out.println("Red Light → Changing to Green.");
        context.setState(new GreenLightState());
    }
}

class GreenLightState implements TrafficLightState {
    @Override
    public void changeColour(TrafficLight context) {
        System.out.println("Green Light → Changing to Yellow.");
        context.setState(new YellowLightState());
    }
}

class YellowLightState implements TrafficLightState {
    @Override
    public void changeColour(TrafficLight context) {
        System.out.println("Yellow Light → Changing to Red.");
        context.setState(new RedLightState());
    }
}

class State1 {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        trafficLight.changeColour();
        trafficLight.changeColour();
        trafficLight.changeColour();
        trafficLight.changeColour();
    }
}
