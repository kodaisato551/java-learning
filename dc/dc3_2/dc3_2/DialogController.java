package dc3_2;

import dc3_2.setting.SupportedSettings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controll Dialog
 */
public class DialogController implements Initializable {
    @FXML
    private ComboBox<String> fontStyle;
    @FXML
    private ComboBox<String> fontSize;
    @FXML
    private ColorPicker fontColor;
    @FXML
    private ColorPicker bgColor;

    @FXML
    void btnOkClicked(ActionEvent event) {
        System.out.println("btnOKClicked");
        String type = fontStyle.getValue();
        int size = Integer.valueOf(fontSize.getValue());
        Color fColor = fontColor.getValue();
        Color bColor = bgColor.getValue();
        System.out.println("Setting : " + "[fontType=" + type + " fontSize=" + size +
                " ftColor=" + fColor.toString() + " bgColor=" + bColor.toString() + "]");
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fontStyle.setItems(FXCollections.observableArrayList(SupportedSettings.FONT_TYPE_LIST));
        fontSize.setItems(FXCollections.observableArrayList(SupportedSettings.FONT_SIZE));
    }
}


