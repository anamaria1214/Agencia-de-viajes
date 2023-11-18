package Controller;

import Model.Agencia;
import Model.Destino;
import Model.PaqueteTuristico;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.NegativeNumberException;
import Exception.EmptyFieldException;
import Exception.WrongUseOfDatesException;

public class GestionarPaquetesAdminController implements Initializable {

        private Agencia agencia= Agencia.getInstance();
        private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
        private Propiedades propiedades = Propiedades.getInstance();

        @FXML
        private Button btnAgregarPaquete;
        @FXML
        private TextField cupoMaximo;
        @FXML
        private Label lblDestinos;
        @FXML
        private Label lblDuracionPaquete;
        @FXML
        private Label lblNombre;
        @FXML
        private Label lblPrecio;
        @FXML
        private Label lblServiciosAd;
        @FXML
        private TextField nombrePaquetes;
        @FXML
        private TextField precioPaquete;
        @FXML
        private TextArea serviviosAdicionales;
        @FXML
        private TableView<Destino> tablaDestinos;
        @FXML
        private TableColumn columnCiudad;
        @FXML
        private TableColumn<Destino,String> columnNombre;
        @FXML
        private DatePicker fechaInicio;
        @FXML
        private DatePicker fechaFin;

        private ArrayList<Destino> destinosSeleccionados;

        public GestionarPaquetesAdminController() {
        }


        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            destinosSeleccionados = new ArrayList<>();

        columnNombre.setCellValueFactory( new PropertyValueFactory<>("nombreDestino"));
        columnCiudad.setCellValueFactory( new PropertyValueFactory<>("ciudad"));

        tablaDestinos.setItems(FXCollections.observableArrayList(agencia.getDestinos()));

        tablaDestinos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Destino>() {
             @Override
             public void changed(ObservableValue<? extends Destino> observableValue, Destino destino, Destino t1) {

                 if (!destinosSeleccionados.contains(t1)) {
                     destinosSeleccionados.add(t1);
                     System.out.println("Se ha agregado el destino");
                 } else {
                     destinosSeleccionados.remove(t1);
                     System.out.println("Se ha eliminado el destino");
                 }

             }
        });

    }

    public void registrarPaquete (){
            PaqueteTuristico paquete = new PaqueteTuristico(destinosSeleccionados, nombrePaquetes.getText(),
                    Integer.parseInt(cupoMaximo.getText()), serviviosAdicionales.getText(), Double.parseDouble(precioPaquete.getText()),fechaInicio.getValue(), fechaFin.getValue());

        try {
            agencia.agregarPaquete(paquete);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El paquete fue añadido correctamente");
            alert.setHeaderText(null);
            alert.show();
        } catch (NegativeNumberException | WrongUseOfDatesException | EmptyFieldException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }


        //if(!agencia.getPaquetesTuristicos().contains(paquete)){
             //   agencia.getPaquetesTuristicos().add(paquete);

            //}
            //else{
            //    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            //    alert.setContentText("Este paquete ya existe");
            //    alert.setHeaderText(null);
            //    alert.show();
            //}
    }
}
