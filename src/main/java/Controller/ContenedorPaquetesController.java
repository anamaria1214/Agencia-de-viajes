package Controller;

import Model.Agencia;
import Model.PaqueteTuristico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ContenedorPaquetesController implements Initializable {

    @FXML
    private Label nombrePaquete;
    private PaqueteTuristico paquete;
    Agencia agencia= Agencia.getInstance();
    private String idCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void pintarComponente(){

    }
    public void setPaquete(PaqueteTuristico paqueteT){
        paquete = paqueteT;
    }
    public void setIdCliente(String id){
        idCliente=id;
    }
}
