package Controller;

import Model.Agencia;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView logoAgencia;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailLabel.setText(propiedades.getBundle().getString("emailLabel"));
        contraseniaLabel.setText(propiedades.getBundle().getString("contraLabel"));
        btnIniciarSesion.setText(propiedades.getBundle().getString("btnIniciarSesion"));
        btnVentanaRegistrarse.setText(propiedades.getBundle().getString("btnVentanaRegistrarse"));
    }

    public void setTamaño(String confirmacion, double dimension){
        if(confirmacion.equals("ancho")){
            vBoxL.setPrefWidth(dimension);
        } else if (confirmacion.equals("alto")) {
            vBoxL.setPrefHeight(dimension);

        }
    }
    public void volverPrincipal(){


    }

    public void chgRegistrarse(){


        logoAgencia.setVisible(false);
        TranslateTransition slideLogo = new TranslateTransition();

        slideLogo.setDuration(Duration.seconds(0.7));
        slideLogo.setNode(logoAgencia);
        slideLogo.setToX(-427);
        slideLogo.play();
        logoAgencia.setVisible(true);
        double x = vBoxL.getParent().getScene().getWidth();
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(vBoxL);
        slide.setToX(x/2);
        slide.play();

    }
    public void iniciarSesion(ActionEvent actionEvent) {
    }
}
