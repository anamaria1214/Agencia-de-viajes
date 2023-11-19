package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PaginaPrincipalClienteController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private Button btnCalificar;

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

    @FXML
    private SVGPath perfilCliente;
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

}
