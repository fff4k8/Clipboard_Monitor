package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;

import java.io.Serializable;

public class Clipboard implements Serializable {

    public Clipboard() {
        this("", "");
    }

    final private StringProperty ip;
    final private StringProperty message;
    final private Button button;

    public final StringProperty ipProperty() {
        return ip;
    }

    public final StringProperty messageProperty() {
        return message;
    }


    public Clipboard(final String ip, final String message) {
        this.ip = new SimpleStringProperty(ip);
        this.message = new SimpleStringProperty(message);

        this.button = setButtonProperty();

    }


    public final String getIp() {
        return ip.get();
    }

    public void setIp(final String ip) {
        this.ip.set(ip);
    }

    public final String getMessage() {
        return message.get();
    }

    public void setMessage(final String message) {
        this.message.set(message);
    }


    public final Button getButton() {
        return button;
    }


    public Button setButtonProperty() {
        Button button = new Button("copy");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
                final ClipboardContent content = new ClipboardContent();
                content.putString(getMessage());
                clipboard.setContent(content);
            }
        });

        return button;
    }


    @Override
    public String toString() {
        return "Clipboard{" +
                "ip=" + ip +
                ", message=" + message +
                ", button=" + button +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clipboard clipboard = (Clipboard) o;

        if (ip != null ? !ip.equals(clipboard.ip) : clipboard.ip != null) return false;
        if (message != null ? !message.equals(clipboard.message) : clipboard.message != null) return false;
        return button != null ? button.equals(clipboard.button) : clipboard.button == null;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (button != null ? button.hashCode() : 0);
        return result;
    }



}





