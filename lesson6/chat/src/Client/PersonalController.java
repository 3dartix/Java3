package Client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.DataOutputStream;
import java.io.IOException;

public class PersonalController {
    @FXML
    Button btn;

    @FXML
    TextArea textArea;


    public void btnClick() {
        // экземпляр класса MiniStage по сути является сценой (дополнительное окно)
        //берем кнопку btn , .getScene() берем сцену этой кнопки , .getWindow() берем окно и у этого окна
        // берем как раз out (out в экземпляре класса MiniStage)
        DataOutputStream out = ((MiniStage)btn.getScene().getWindow()).out;
        String nickTo = ((MiniStage)btn.getScene().getWindow()).nickTo;
        try {
            out.writeUTF("/w " + nickTo + " " + textArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.clear();
    }
}
