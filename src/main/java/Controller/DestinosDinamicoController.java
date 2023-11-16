package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DestinosDinamicoController implements Initializable {
    @FXML
    private VBox contenedorPaquetes;

    Agencia agencia = Agencia.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    public void pintarPaquetes(){
        for(int i=0;i<agencia.getPaquetesTuristicos().size();i++){
            FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/ContenedorPaquetes.fxml") );
            try {
                AnchorPane paquete = loader.load();
                ContenedorPaquetesController paqueteC = loader.getController();
                paqueteC.setPaquete(agencia.getPaquetesTuristicos().get(i));
                paqueteC.pintarComponente();
                contenedorPaquetes.getChildren().add(paquete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
