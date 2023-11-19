package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
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

    private int calificacionGuia=0;
    private int calificacionPaq=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clmPaquete.setCellValueFactory( new PropertyValueFactory<>("nombrePaquete"));
        clmGuia.setCellValueFactory( new PropertyValueFactory<>("nombreGuia"));

        tablaPaquetes.setItems(FXCollections.observableArrayList(agencia.listarPaquetes(sesionCliente.getCliente().getIdCliente())));
        tablaGuia.setItems(FXCollections.observableArrayList(agencia.getGuiasTuristicos()));
    }
    public void calificarGuia1(){
        estrellaGuia1.setFill(Color.PURPLE);
        estrellaGuia2.setFill(Color.WHITE);
        estrellaGuia3.setFill(Color.WHITE);
        estrellaGuia4.setFill(Color.WHITE);
        estrellaGuia5.setFill(Color.WHITE);
        calificacionGuia=1;
    }
    public void calificarGuia2(){
        estrellaGuia1.setFill(Color.PURPLE);
        estrellaGuia2.setFill(Color.PURPLE);
        estrellaGuia3.setFill(Color.WHITE);
        estrellaGuia4.setFill(Color.WHITE);
        estrellaGuia5.setFill(Color.WHITE);
        calificacionGuia = 2;
    }
    public void calificarGuia3(){
        estrellaGuia1.setFill(Color.PURPLE);
        estrellaGuia2.setFill(Color.PURPLE);
        estrellaGuia3.setFill(Color.PURPLE);
        estrellaGuia4.setFill(Color.WHITE);
        estrellaGuia5.setFill(Color.WHITE);
        calificacionGuia = 3;
    }
    public void calificarGuia4(){
        estrellaGuia1.setFill(Color.PURPLE);
        estrellaGuia2.setFill(Color.PURPLE);
        estrellaGuia3.setFill(Color.PURPLE);
        estrellaGuia4.setFill(Color.PURPLE);
        estrellaGuia5.setFill(Color.WHITE);
        calificacionGuia= 4;

    }
    public void calificarGuia5(){
        estrellaGuia1.setFill(Color.PURPLE);
        estrellaGuia2.setFill(Color.PURPLE);
        estrellaGuia3.setFill(Color.PURPLE);
        estrellaGuia4.setFill(Color.PURPLE);
        estrellaGuia5.setFill(Color.PURPLE);
        calificacionGuia= 5;
    }
    public void calificarPaq1(){
        estrellaPaquete1.setFill(Color.PURPLE);
        estrellaPaquete2.setFill(Color.WHITE);
        estrellaPaquete3.setFill(Color.WHITE);
        estrellaPaquete4.setFill(Color.WHITE);
        estrellaPaquete5.setFill(Color.WHITE);
        calificacionPaq= 1;
    }
    public void calificarPaq2(){
        estrellaPaquete1.setFill(Color.PURPLE);
        estrellaPaquete2.setFill(Color.PURPLE);
        estrellaPaquete3.setFill(Color.WHITE);
        estrellaPaquete4.setFill(Color.WHITE);
        estrellaPaquete5.setFill(Color.WHITE);
        calificacionPaq= 2;
    }
    public void calificarPaq3(){
        estrellaPaquete1.setFill(Color.PURPLE);
        estrellaPaquete2.setFill(Color.PURPLE);
        estrellaPaquete3.setFill(Color.PURPLE);
        estrellaPaquete4.setFill(Color.WHITE);
        estrellaPaquete5.setFill(Color.WHITE);
        calificacionPaq= 3;
    }
    public void calificarPaq4(){
        estrellaPaquete1.setFill(Color.PURPLE);
        estrellaPaquete2.setFill(Color.PURPLE);
        estrellaPaquete3.setFill(Color.PURPLE);
        estrellaPaquete4.setFill(Color.PURPLE);
        estrellaPaquete5.setFill(Color.WHITE);
        calificacionPaq= 4;
    }
    public void calificarPaq5(){
        estrellaPaquete1.setFill(Color.PURPLE);
        estrellaPaquete2.setFill(Color.PURPLE);
        estrellaPaquete3.setFill(Color.PURPLE);
        estrellaPaquete4.setFill(Color.PURPLE);
        estrellaPaquete5.setFill(Color.PURPLE);
        calificacionPaq= 5;
    }



}
