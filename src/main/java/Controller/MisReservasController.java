package Controller;

import Model.Agencia;
import Model.Reserva;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MisReservasController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private TableColumn columnaCantPersonas;

    @FXML
    private TableColumn columnaEstadoReserva;

    @FXML
    private TableColumn columnaFechaViaje;

    @FXML
    private TableColumn columnaNombrePaquete;

    @FXML
    private Label labelMIsReservas;

    @FXML
    private TableView<Reserva> tablaReservasClientes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaCantPersonas.setCellValueFactory( new PropertyValueFactory<>("cantPersonasViajan"));
        columnaEstadoReserva.setCellValueFactory( new PropertyValueFactory<>("estadoReserva"));
        columnaFechaViaje.setCellValueFactory( new PropertyValueFactory<>("fechaViaje"));
        columnaNombrePaquete.setCellValueFactory( new PropertyValueFactory<>("paqueteTuristico"));

        //there's a big problem here
        tablaReservasClientes.setItems( FXCollections.observableArrayList(agencia.getReservas()));

    }


}
