package Controller;

import Model.Agencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.NonRegisteredCustomer;

public class IniciarSesionController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();


    @FXML
    private Label EmailLabel;
    @FXML
    private TextField emailCliente;
    @FXML
    private Label contraseniaLabel;
    @FXML
    private TextField contraseniaCliente;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnVentanaRegistrarse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailLabel.setText(propiedades.getBundle().getString("EmailLabel"));
        contraseniaLabel.setText(propiedades.getBundle().getString("idLabel"));
        btnIniciarSesion.setText(propiedades.getBundle().getString("btnIniciarSesion"));
        btnVentanaRegistrarse.setText(propiedades.getBundle().getString("btnVentanaRegistrarse"));
    }

    public void iniciarSesion(){
        try {
            agencia.iniciarSesionClienteRecur(emailCliente.getText(), contraseniaCliente.getText(), 0, false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Cliente registrado exitosamente");
            alert.setHeaderText(null);
            alert.show();
        } catch (NonRegisteredCustomer e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }

    }

    public void volverPrincipal(){
        try {
            agencia.abrirVentanaRegistro();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
