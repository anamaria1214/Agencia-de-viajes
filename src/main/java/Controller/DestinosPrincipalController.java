package Controller;

import Model.Agencia;
import Model.Destino;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class DestinosPrincipalController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private TableView<Destino> tablaDestinos;
    @FXML
    private TableColumn clmNombreDestino;
    @FXML
    private TableColumn clmCiudadDestino;
    @FXML
    private TableColumn clmDescripcionDestino;
    @FXML
    private TableColumn clmClimaDestinos;
    @FXML
    private Button btnVerImagen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //btnVerImagen.setText(propiedades.getBundle().getString("btnVerImagen"));

        clmNombreDestino.setCellValueFactory( new PropertyValueFactory<>("nombreDestino"));
        clmCiudadDestino.setCellValueFactory( new PropertyValueFactory<>("ciudad"));
        clmDescripcionDestino.setCellValueFactory( new PropertyValueFactory<>("descripcion"));
        clmClimaDestinos.setCellValueFactory( new PropertyValueFactory<>("clima"));

        tablaDestinos.setItems( FXCollections.observableArrayList(agencia.getDestinos()));

    }

    public void mostrarImagen(){

    }
}
