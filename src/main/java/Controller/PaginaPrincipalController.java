package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

public class PaginaPrincipalController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    @FXML
    private SVGPath iconoUsuario;
    @FXML
    private SVGPath btnCambioIdioma;
    @FXML
    private Button btnDestinos;
    @FXML
    private Button btnPaquetes;
    @FXML
    private Button btnAdministrador;
    @FXML
    private BorderPane centerPane;
    @FXML
    private Label labelAgenciaUQ;
    @FXML
    private HBox hbIniciarSesion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDestinos.setText(propiedades.getBundle().getString("btnDestinos"));
        btnPaquetes.setText(propiedades.getBundle().getString("btnPaquetes"));
        btnAdministrador.setText(propiedades.getBundle().getString("btnAdministrador"));
        labelAgenciaUQ.setText(propiedades.getBundle().getString("labelAgenciaUQ"));

    }
    private void cambiarVentana(String fxmlname) {
        try {
            Node nodo = AppPrincipal.loadFXML(fxmlname);
            setCenter(nodo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("exports")
    public void setCenter(Node node) {
        centerPane.setCenter(node);
    }

    public void mostrarPaquetes(){
        cambiarVentana("PaquetesPrincipal");
    }
    public void mostrarDestinos(){
        cambiarVentana("DestinosPrincipal");
    }
    public void mostarInicioSesionAdmin(){
        cambiarVentana("IniciarSesionAdmin");
    }
    public void abrirInicioSesionRegistrar(){
        try {
            ((Stage) btnCambioIdioma.getScene().getWindow()).close();
            System.out.println("Se crea ventana inicio sesion animado");
            FXMLLoader loader = new FXMLLoader( AppPrincipal.class.getResource("/View/IniciarAnimado.fxml") );
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Agencia de viajes");

            stage.setOnShown(event -> {
                IniciarAnimadoController c = loader.getController();
            });
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Ventana no encontrada");
        }
    }

    public void cambiarIdioma(){
        if(propiedades.getIdioma().equals("es")){
            propiedades.escribirIdioma("en");
        }else{
            propiedades.escribirIdioma("es");
        }

        try {
            FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/PaginaPrincipal.fxml") );

            Parent parent = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();

            ((Stage) btnCambioIdioma.getScene().getWindow()).close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
