package Controller;

import App.AppPrincipal;
import Model.Agencia;
import Model.Clima;
import Model.PaqueteTuristico;
import Model.SesionCliente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class DestinosDinamicoController implements Initializable {
    @FXML
    private VBox contenedorPaquetes;
    @FXML
    private TextField ciudadField;

    @FXML
    private ComboBox<Clima> climasCombo;
    @FXML
    private TextField destinoField;

    @FXML
    private TextField precioField;

    SesionCliente sesion = SesionCliente.getInstance();

    Agencia agencia = Agencia.getInstance();
    ArrayList<PaqueteTuristico> paquetesFiltrados;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.paquetesFiltrados = agencia.getPaquetesTuristicos();
        pintarPaquetes(paquetesFiltrados);
        climasCombo.getItems().addAll(Clima.values());
    }

    public void pintarPaquetes(ArrayList<PaqueteTuristico> paquetes){
        contenedorPaquetes.getChildren().clear();
        contenedorPaquetes.setSpacing(0);

        System.out.println( paquetes );

        for(int i=0;i<paquetes.size();i++){
            FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/ContenedorPaquetes.fxml"));
            try {
                AnchorPane paquete = loader.load();
                ContenedorPaquetesController paqueteC = loader.getController();
                paqueteC.setPaquete(paquetes.get(i));
                paqueteC.pintarComponente();
                paqueteC.setIdCliente(sesion.getCliente().getIdCliente());
                contenedorPaquetes.getChildren().add(paquete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void filtrarPaquetes(){
        ArrayList<PaqueteTuristico> lista = agencia.filtrar( destinoField.getText(), ciudadField.getText(), Double.parseDouble( precioField.getText() ), climasCombo.getValue() );
        System.out.println("Lista:"+lista);
        pintarPaquetes(lista);
    }

}
