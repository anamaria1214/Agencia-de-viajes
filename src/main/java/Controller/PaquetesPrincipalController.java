package Controller;

import Model.Agencia;
import Model.PaqueteTuristico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PaquetesPrincipalController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private TableView<PaqueteTuristico> tablaPaquetes;
    @FXML
    private TableColumn clmNombrePaquete;
    @FXML
    private TableColumn clmDuracionPaquetes;
    @FXML
    private TableColumn clmServiciosPaquetes;
    @FXML
    private TableColumn clmPrecioPaquete;

    public void saberMasSobrePaquete(){



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNombrePaquete.setCellValueFactory( new PropertyValueFactory<>("Nombre"));
        clmDuracionPaquetes.setCellValueFactory( new PropertyValueFactory<>("Modelo"));
        clmServiciosPaquetes.setCellValueFactory( new PropertyValueFactory<>("Precio"));
        clmPrecioPaquete.setCellValueFactory( new PropertyValueFactory<>("Marca"));

        tablaPaquetes.setItems( FXCollections.observableArrayList(agencia.getPaquetesTuristicos()));
    }
}
