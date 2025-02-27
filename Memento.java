
/*
    - Memento is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation.
    - Memento pattern is used to capture the current state of an object and store it in such a manner that it can be restored at a later time without breaking the rules of encapsulation.
    - Memento pattern is used when you need to save the state of an object to be able to restore it later.
    - Memento pattern is used when you need to provide undo/redo functionality in an application.
    - Memento pattern is used when you need to maintain a history of states of an object.
 */

import java.util.Stack;

// Step 1: Create the Memento Class
class TextMomento {
    private String text;

    public TextMomento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Step 2: Create the Originator Class (TextEditor)
class TextEditor {
    private String text = "";

    public void setText(String newText) {
        text = text + newText;
    }

    public String  getText() {
        return text;
    }

    public TextMomento save() {
        return new TextMomento(text);
    }

    public void restore(TextMomento memento) {
        text = memento.getText();
    }
}


// Step 3: Create the Caretaker Class (TextEditorApp)

class History {
    Stack<TextMomento> history= new Stack<>();
    void save(TextMomento memento) {
        history.push(memento);
    }

    void undo(TextEditor editor) {
        if (!history.isEmpty()) {
            TextMomento memento = history.pop();
            editor.restore(memento);
        }
    }
}



public class Memento {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        editor.setText("The quick brown fox jumps over the lazy dog.");
        history.save(editor.save());
        System.out.println(editor.getText());

        editor.setText("Hello, World!");
        history.save(editor.save());
        System.out.println(editor.getText());

        history.undo(editor);
        System.out.println(editor.getText());

        history.undo(editor);
        System.out.println(editor.getText());
    }
}
