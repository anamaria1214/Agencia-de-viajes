package Controller;

import App.AppPrincipal;
import Model.Agencia;
import Model.PaqueteTuristico;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecomendadosController implements Initializable {
    Agencia agencia = Agencia.getInstance();
    ArrayList<PaqueteTuristico> paquetesRandom;
    @FXML
    private VBox contenedorPaquetes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.paquetesRandom = agencia.paquetesAleatorios();
        pintarPaquetes(paquetesRandom);
    }
    public void pintarPaquetes(ArrayList<PaqueteTuristico> paquetes){
        contenedorPaquetes.setSpacing(0);

        for(int i=0;i<paquetes.size();i++){
            FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/ContenedorPaquetes.fxml"));
            try {
                AnchorPane paquete = loader.load();
                ContenedorPaquetesController paqueteC = loader.getController();
                paqueteC.setPaquete(paquetes.get(i));
                paqueteC.pintarComponente();
                contenedorPaquetes.getChildren().add(paquete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
