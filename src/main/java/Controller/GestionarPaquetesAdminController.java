package Controller;

import Model.Agencia;
import Model.Destino;
import Model.PaqueteTuristico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class GestionarPaquetesAdminController implements Initializable {

        private Agencia agencia= Agencia.getInstance();
        private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
        private Propiedades propiedades = Propiedades.getInstance();

        @FXML
        private Button btnAgregarPaquete;
        @FXML
        private Spinner<Integer> duracionPaquetes;
        @FXML
        private Label lblDestinos;
        @FXML
        private Label lblDuracionPaquete;
        @FXML
        private Label lblNombre;
        @FXML
        private Label lblPrecio;
        @FXML
        private Label lblServiciosAd;
        @FXML
        private TextField nombrePaquetes;
        @FXML
        private TextField precioPaquete;
        @FXML
        private TextArea serviviosAdicionales;
        @FXML
        private TableView<Destino> tablaDestinos;
        @FXML
        private TableColumn columnCiudad;
        @FXML
        private TableColumn<Destino,String> columnNombre;

        public GestionarPaquetesAdminController() {
        }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnNombre.setCellValueFactory( new PropertyValueFactory<>("nombreDestino"));
        columnCiudad.setCellValueFactory( new PropertyValueFactory<>("ciudad"));

        tablaDestinos.setItems(FXCollections.observableArrayList(agencia.getDestinos()));
    }
}
