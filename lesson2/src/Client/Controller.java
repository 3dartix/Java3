package Client;

import Server.AuthService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;


public class Controller implements Initializable {

    private boolean isAuthorized;
    private boolean isRegistration;

    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    @FXML
    TextArea OnlineArea;
    @FXML
    TitledPane onlinePanel;
    @FXML
    VBox authorizationPanel;
    @FXML
    HBox chatPanel;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    @FXML
    Label labelAuth;
    @FXML
    VBox registrationPanel;
    @FXML
    ListView<String> clientsList;

    List<TextArea> textAreas; //хранение поля вывода чата


    Socket socket;
    DataInputStream in; //входящий поток
    DataOutputStream out; //исходящий поток

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;


    public void RegistrationPanelEnable(ActionEvent actionEvent) {
        registrationPanel.setVisible(true);
        registrationPanel.setManaged(true);
        authorizationPanel.setVisible(false);
        authorizationPanel.setManaged(false);
    }

    public void RegistrationPanelDisable() {
        registrationPanel.setVisible(false);
        registrationPanel.setManaged(false);
        authorizationPanel.setVisible(true);
        authorizationPanel.setManaged(true);
        System.out.println("регистраци отключена");
    }

    public void RegisterStarts (ActionEvent actionEvent){
        RegistrationPanelDisable();
        //собираем данные и отправляем куда-то
    }

    private void setAuthrized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            authorizationPanel.setVisible(true);
            authorizationPanel.setManaged(true);
            chatPanel.setVisible(false);
            chatPanel.setManaged(false);
        } else {
            authorizationPanel.setVisible(false);
            authorizationPanel.setManaged(false);
            chatPanel.setVisible(true);
            chatPanel.setManaged(true);
        }
    }

    public void Connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthrized(true);
                                break;
                            } else {
                                textArea.appendText(str);
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/")) {
                                if (str.startsWith("/clientClose")) {
                                    break;
                                }

                                if (str.startsWith("/clientList")) {
                                    String[] tokens = str.split(" ");
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            clientsList.getItems().clear();
                                            for(int i = 1; i < tokens.length - 1; i++){
                                                clientsList.getItems().add(tokens[i]);
                                                //System.out.println("бла бла бла : " + tokens[i].length());
                                            }
                                        }
                                    });
                                }
                            } else {
                                textArea.appendText(str + "");
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthrized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SendMessage(){
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Dispose() {
        System.out.println("Отправляем сообщение на сервер о завершении работы");
        try {
            if (out != null) {
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if (socket == null || socket.isClosed()) {
            Connect();
        }

        try {
            out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
            //out.writeUTF("/auth " + "login1" + " " + "pass1");
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectClient(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            //clientsList.getSelectionModel() - получение выбранного в списке клиента
            //textAreas - это основное поле чата
            MiniStage ms = new MiniStage(clientsList.getSelectionModel().getSelectedItem(), out, textAreas);
            System.out.println(clientsList.getSelectionModel().getSelectedItem());
            ms.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textAreas = new ArrayList<>();
        textAreas.add(textArea);//
    }
}
