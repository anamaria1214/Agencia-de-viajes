package Controller;

import Model.Destino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DestinosAdminController implements Initializable {
    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn clmClimaDestino;

    @FXML
    private TableColumn<?, ?> clmDescripcionDestino;

    @FXML
    private TableColumn<?, ?> clmNombreDestino;

    @FXML
    private TableView<Destino> tablaDestinos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
