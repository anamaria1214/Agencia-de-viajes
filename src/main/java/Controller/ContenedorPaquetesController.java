package Controller;

import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ContenedorPaquetesController implements Initializable {

    @FXML
    private Label nombrePaquete;
    Agencia agencia= Agencia.getInstance();
    private int i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
