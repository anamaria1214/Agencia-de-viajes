package Controller;

import Model.Agencia;
import Model.PaqueteTuristico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ContenedorPaquetesController implements Initializable {

    private PaqueteTuristico paquete;
    Agencia agencia= Agencia.getInstance();
    private String idCliente;

    @FXML
    private Label destinos;

    @FXML
    private Button btnReservar;

    @FXML
    private ImageView imagenPack;

    @FXML
    private Label nombrePaquete;

    @FXML
    private Label precio;

    @FXML
    private Label servAdicionales;

    @FXML
    private Label verBoton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void pintarComponente(){
        setearTodo();
    }
    public void setNameDestinos(){
        destinos.setText(paquete.getDestinos().toString());
    }
    public void setNombrePaquete(){
        nombrePaquete.setText(paquete.getNombrePaquete());
    }
    public void setPrecio(){
        precio.setText(paquete.getPrecio()+"");
    }
    public void setAdicionales(){
        servAdicionales.setText(paquete.getServiciosAdicionales());
    }
    public void setImagen(){
        Image imagen = new Image(agencia.imagenAleatoria());
        imagenPack.setImage(imagen);
        imagenPack.setPreserveRatio(true);
        imagenPack.setFitHeight(82);
        imagenPack.setFitWidth(88);
    }
    public void setearTodo(){
        setNameDestinos();
        setNombrePaquete();
        setAdicionales();
        setPrecio();
        setImagen();
    }

    public void verImagen(){

    }
    public void setPaquete(PaqueteTuristico paqueteT){
        paquete = paqueteT;
    }
    public void setIdCliente(String id){
        idCliente=id;
    }
}
