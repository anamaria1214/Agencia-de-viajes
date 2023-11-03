package Controller;

import Model.Agencia;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IniciarAnimadoController implements Initializable {
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
    @FXML
    private VBox vBoxR;
    @FXML
    private VBox vBoxL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailLabel.setText(propiedades.getBundle().getString("emailLabel"));
        contraseniaLabel.setText(propiedades.getBundle().getString("contraLabel"));
        btnIniciarSesion.setText(propiedades.getBundle().getString("btnIniciarSesion"));
        btnVentanaRegistrarse.setText(propiedades.getBundle().getString("btnVentanaRegistrarse"));
    }


    public void volverPrincipal(){


    }

    public void chgRegistrarse(){
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(vBoxL);
    }
}
