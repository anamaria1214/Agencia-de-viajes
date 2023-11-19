package Controller;

import Model.*;
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
    private TableView<PaqueteTuristico> tablaPaquetes;
    @FXML
    private TableView<GuiaTuristico> tablaGuia;
    @FXML
    private TableColumn clmPaquete;
    @FXML
    private TableColumn clmGuia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmPaquete.setCellValueFactory( new PropertyValueFactory<>("nombrePaquete"));
        clmGuia.setCellValueFactory( new PropertyValueFactory<>("nombreGuia"));

        tablaPaquetes.setItems(FXCollections.observableArrayList(agencia.listarPaquetes(sesionCliente.getCliente().getIdCliente())));
        tablaGuia.setItems(FXCollections.observableArrayList(agencia.getGuiasTuristicos()));
    }

}
