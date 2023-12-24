package Controller;

import Model.Agencia;
import Model.Destino;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DestinosAdminController implements Initializable {
    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn clmClimaDestino;

    @FXML
    private TableColumn clmDescripcionDestino;

    @FXML
    private TableColumn clmNombreDestino;

    @FXML
    private TableView<Destino> tablaDestinos;
    @FXML
    private TableColumn clmCiudadDestino;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clmNombreDestino.setCellValueFactory( new PropertyValueFactory<>("nombreDestino"));
        clmCiudadDestino.setCellValueFactory( new PropertyValueFactory<>("ciudad"));
        clmDescripcionDestino.setCellValueFactory( new PropertyValueFactory<>("descripcion"));
        clmClimaDestino.setCellValueFactory( new PropertyValueFactory<>("clima"));

        tablaDestinos.setItems( FXCollections.observableArrayList(agencia.getDestinos()));
    }

    public void eliminarDestino(){
        agencia.eliminarDestinos(tablaDestinos.getSelectionModel().getSelectedItem());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se eliminó correctamente");
        alert.setHeaderText(null);
        alert.show();
    }
}
