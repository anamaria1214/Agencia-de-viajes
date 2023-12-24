package Controller;

import Model.Agencia;
import Model.Reserva;
import Model.SesionCliente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MisReservasController implements Initializable {

    private SesionCliente sesionCliente= SesionCliente.getInstance();
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
    @FXML
    private Button btnEliminarCliente;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        columnaCantPersonas.setCellValueFactory( new PropertyValueFactory<>("cantPersonasViajan"));
        columnaEstadoReserva.setCellValueFactory( new PropertyValueFactory<>("estadoReserva"));
        columnaFechaViaje.setCellValueFactory( new PropertyValueFactory<>("fechaViaje"));
        columnaNombrePaquete.setCellValueFactory( new PropertyValueFactory<>("paqueteTuristico"));
        System.out.println(sesionCliente.getCliente());

        //tablaReservasClientes.setItems( FXCollections.observableArrayList(agencia.listarReserva(sesionCliente.getCliente().getIdCliente(),0,new ArrayList<>())));
        tablaReservasClientes.setItems( FXCollections.observableArrayList(agencia.listarReserva(sesionCliente.getCliente().getIdCliente())));
    }

    public void eliminarReseva(){
        agencia.cancelarReserva(tablaReservasClientes.getSelectionModel().getSelectedItem());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Se eliminó correctamente");
        alert.setHeaderText(null);
        alert.show();
    }
}
