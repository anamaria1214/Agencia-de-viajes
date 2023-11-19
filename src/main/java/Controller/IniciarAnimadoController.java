package Controller;

import App.AppPrincipal;
import Model.Agencia;
import Model.Cliente;
import Model.SesionCliente;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.*;

public class IniciarAnimadoController implements Initializable {
    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    private final SesionCliente sesionCliente= SesionCliente.getInstance();


    @FXML
    private Label EmailLabel;

    @FXML
    private Label EmailLabel1;

    @FXML
    private Label IdLbl;

    @FXML
    private Label apellidoLbl;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegistro;

    @FXML
    private Button btnRegresar;

    @FXML
    private Button btnRegresar1;

    @FXML
    private Button btnVentanaRegistrarse;
    @FXML
    private PasswordField contraseniaIniciar;
    @FXML
    private Label contraseniaLabel;

    @FXML
    private Label contraseniaLabel1;

    @FXML
    private Label direccionLbl;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField emailRegistro;

    @FXML
    private TextField idTextField;

    @FXML
    private Label labelInicioSesion;

    @FXML
    private Label labelRegistro;

    @FXML
    private ImageView logoAgencia;

    @FXML
    private Label nombreLbl;

    @FXML
    private TextField nombreTextField;

    @FXML
    private Label telefonoLbl;

    @FXML
    private VBox vBoxL;

    @FXML
    private VBox vBoxR;
    @FXML
    private PasswordField passwordRegistro;
    @FXML
    private TextField telefonoTextF;
    @FXML
    private TextField direccionTextF;
    @FXML
    private Button btnIniciarSesion1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailLabel.setText(propiedades.getBundle().getString("emailLabel"));
        contraseniaLabel.setText(propiedades.getBundle().getString("contraLabel"));
        btnIniciarSesion.setText(propiedades.getBundle().getString("btnIniciarSesion"));
        btnVentanaRegistrarse.setText(propiedades.getBundle().getString("btnVentanaRegistrarse"));
        labelInicioSesion.setText(propiedades.getBundle().getString("labelInicioSesion"));
        vBoxR.setVisible(false);


    }

    public void volverPrincipal(){
        try{
            FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/PaginaPrincipal.fxml") );
            Parent parent = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
            ((Stage) vBoxR.getScene().getWindow()).close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void registrarse(){
        Cliente cliente = new Cliente(idTextField.getText(), nombreTextField.getText(), apellidoTextField.getText(), emailRegistro.getText(),telefonoTextF.getText() ,direccionTextF.getText(), passwordRegistro.getText() );
        try{
            System.out.println(emailRegistro.getText());
            agencia.registrarCliente(cliente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Cliente registrado exitosamente");
            alert.setHeaderText(null);
            alert.show();
            //sesionCliente.setCliente(cliente);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }

    }
    public void chgRegistrarse(){
        logoAgencia.setVisible(false);
        TranslateTransition slideLogo = new TranslateTransition();
        double x = vBoxL.getParent().getScene().getWidth();
        double y= vBoxL.getParent().getScene().getHeight();
        slideLogo.setDuration(Duration.seconds(0.7));
        slideLogo.setNode(logoAgencia);
        slideLogo.setToX(-x/2);
        slideLogo.setToY(45);
        slideLogo.play();
        logoAgencia.setVisible(true);
        vBoxR.setVisible(false);
        TranslateTransition slide1= new TranslateTransition();
        slide1.setDuration(Duration.seconds(0.7));
        slide1.setNode(vBoxR);
        slide1.setToX(0);
        slide1.play();
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(vBoxL);
        slide.setToX(x/2);
        slide.play();
        slide.setOnFinished(event -> {
            vBoxL.setVisible(false); // Hacer invisible después de desvanecer
            vBoxR.setVisible(true);
        });

    }
    public void iniciarSesion() {
        try {

            agencia.iniciarSesionClienteRecur(emailCliente.getText(),contraseniaIniciar.getText(),0, false);
            Cliente cliente= agencia.encontrarCliente(emailCliente.getText(), 0, false, new Cliente());
            sesionCliente.setCliente(cliente);
            System.out.println(sesionCliente.getCliente());
        } catch (NonRegisteredCustomer e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
    public void chgIniciarSesion(){
        logoAgencia.setVisible(false);
        TranslateTransition slideLogo = new TranslateTransition();
        double x = vBoxL.getParent().getScene().getWidth();
        double y= vBoxL.getParent().getScene().getHeight();
        slideLogo.setDuration(Duration.seconds(0.7));
        slideLogo.setNode(logoAgencia);
        slideLogo.setToX(0);
        slideLogo.play();
        logoAgencia.setVisible(true);
        vBoxL.setVisible(true);
        TranslateTransition slide1= new TranslateTransition();
        slide1.setDuration(Duration.seconds(0.7));
        slide1.setNode(vBoxL);
        slide1.setToX(2);
        slide1.play();
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(vBoxR);
        slide.setToX(-x/2);
        slide.play();
        slide.setOnFinished(event -> {
            vBoxR.setVisible(false); // Hacer invisible después de desvanecer
            vBoxL.setVisible(true);
        });


    }

}
