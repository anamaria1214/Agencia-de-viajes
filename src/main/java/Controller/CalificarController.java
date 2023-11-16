package Controller;

import Model.Agencia;
import Model.Reserva;
import Model.SesionCliente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CalificarController implements Initializable {
    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    private SesionCliente sesionCliente= SesionCliente.getInstance();
    @FXML
    private Button btnEnviarCalificar;
    @FXML
    private TableColumn clmGuiaCalificar;

    @FXML
    private TableColumn clmPaqueteCalificar;

    @FXML
    private TextArea comentario;

    @FXML
    private SVGPath estrellaGuia1;

    @FXML
    private SVGPath estrellaGuia2;

    @FXML
    private SVGPath estrellaGuia3;

    @FXML
    private SVGPath estrellaGuia4;

    @FXML
    private SVGPath estrellaGuia5;

    @FXML
    private SVGPath estrellaPaquete1;

    @FXML
    private SVGPath estrellaPaquete2;

    @FXML
    private SVGPath estrellaPaquete3;

    @FXML
    private SVGPath estrellaPaquete4;

    @FXML
    private SVGPath estrellaPaquete5;

    @FXML
    private Label labelCalificarGuia;

    @FXML
    private Label labelCalificarPaquete;

    @FXML
    private Label labelComentarios;

    @FXML
    private TableView<Reserva> tablaCalificar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmPaqueteCalificar.setCellValueFactory( new PropertyValueFactory<>("nombreDestino"));
        clmGuiaCalificar.setCellValueFactory( new PropertyValueFactory<>("ciudad"));

        tablaCalificar.setItems(FXCollections.observableArrayList(agencia.listarReserva(sesionCliente.getCliente().getIdCliente(), 0,new ArrayList<>())));

    }

}
