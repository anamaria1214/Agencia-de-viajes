package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PaginaPrincipalAdminController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();


    @FXML
    private BorderPane centerPane;
    @FXML
    private Button btnGestionarDestinos;
    @FXML
    private Button btnGestionarPaquetes;
    @FXML
    private Button btnGestionarGuiaTuristico;
    @FXML
    private Button btnEstadisticas;
    @FXML
    private Button btnPaquetes;
    @FXML
    private Button btnDestinos;

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
    public void abrirGestonarDestinos(){
        cambiarVentana("GestionarDestinosAdmin");
    }
}
