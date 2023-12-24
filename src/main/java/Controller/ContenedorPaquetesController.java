package Controller;

import App.AppPrincipal;
import Model.Agencia;
import Model.ConocerPaquete;
import Model.PaqueteTuristico;
import Model.SesionCliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContenedorPaquetesController implements Initializable {

    private PaqueteTuristico paquete;
    private SesionCliente sesionCliente= SesionCliente.getInstance();
    Agencia agencia= Agencia.getInstance();
    ConocerPaquete paqueteNecesario= ConocerPaquete.getInstance();
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
        System.out.println(paquete);


        //pintarComponente();
    }
    public void pintarComponente(){
        setearTodo();
    }
    public void setNameDestinos(){
        String destinos1 = "";
        for (int i =0 ; i<paquete.getDestinos().size();i++){
            destinos1 = destinos1 + paquete.getDestinos().get(i).getNombreDestino();
        }
        destinos.setText(destinos1);
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
        paqueteNecesario.setPaquete(paquete);
    }

    public void verImagen(){

    }
    public void setPaquete(PaqueteTuristico paqueteT){
        this.paquete = paqueteT;
        pintarComponente();
    }
    public void setIdCliente(String id){
        idCliente=id;
    }

    public void abrirVentanaMasDetalles(){
        if(sesionCliente.getCliente()==null){
            try{
                FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/IniciarAnimado.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Agencia de viajes");
                stage.show();
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }

        }else{
            try {
                FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/Reservar.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Agencia de viajes");
                stage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
