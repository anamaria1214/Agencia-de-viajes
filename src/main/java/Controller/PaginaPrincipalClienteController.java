package Controller;

import App.AppPrincipal;
import Model.Agencia;
import Model.SesionCliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PaginaPrincipalClienteController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    private SesionCliente sesionCliente= SesionCliente.getInstance();

    @FXML
    private Button btnCalificar;
    @FXML
    private SVGPath perfilCliente;
    @FXML
    private Button btnDestinosCliente;

    @FXML
    private Button btnMisReservas;

    @FXML
    private Button btnPaquetesCliente;

    @FXML
    private SVGPath buscador;

    @FXML
    private BorderPane centerPane;

    @FXML
    private Label labelAgencia;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
    /*public void setCenter(Node node) {
        centerPane.getChildren().add(node);
    }*/

    public void abrirMisReservas(){ cambiarVentana("MisReservas");}
    public void abrirPaquetes(){
        cambiarVentana("DestinosDinamico");
    }
    public void abrirCalificar(){ cambiarVentana("Calificar");}

    public void cerrarSesion(){
        try{
            System.out.println("Se crea ventana inicio sesion animado");
            FXMLLoader loader = new FXMLLoader( AppPrincipal.class.getResource("/View/PaginaPrincipal.fxml") );
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setResizable(false);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Agencia de viajes");
            ((Stage) perfilCliente.getScene().getWindow()).close();
            sesionCliente.setCliente(null);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
