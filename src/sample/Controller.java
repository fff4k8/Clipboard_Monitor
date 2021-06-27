package sample;


import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.io.IOException;


public class Controller {

    private ObservableList<Clipboard> observableList = FXCollections.observableArrayList();
    @FXML private TextField messageField;
    @FXML private TableView<Clipboard> tableClipboard = new TableView<>(observableList);

    RcvPacket rcv ;

    @FXML
    private void initialize() throws IOException, InterruptedException {

        observableList.addListener((ListChangeListener<Clipboard>) change -> {
            while (change.next()) {
                if (change.wasAdded()) { tableClipboard.setItems(observableList); } } });

        rcv = new RcvPacket(observableList, 256);
        rcv.joinGroup();

        Thread rcvThread = new Thread(rcv::receive);
        rcvThread.setDaemon(true);
        rcvThread.start();

    }



    public void buttonOnSend(ActionEvent actionEvent) throws IOException {
             new Thread(){
                 @Override
                 public void run() {
                     try {
                         new SendPacket().sendPacket(messageField.getText());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }
             }.start();
    }

    public void leaveMSGroup()  {

                    try {
                        rcv.leaveGroup();
                        System.out.println("leaving group");
                    } catch (Exception e) {
                        System.err.print("failed to leave group");e.printStackTrace();
                    }

        }
    }



