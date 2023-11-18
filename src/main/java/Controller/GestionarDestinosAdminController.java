package Controller;

import Model.Agencia;
import Model.Clima;
import Model.Destino;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.EmptyFieldException;


public class GestionarDestinosAdminController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private Label lblResgistrarDestino;
    @FXML
    private Label lblNombreDestino;
    @FXML
    private Label lblCiudad;
    @FXML
    private Label lblImagen;
    @FXML
    private Label lblClima;
    @FXML
    private Label lblDescrpcion;
    @FXML
    private TextField nombreDestino;
    @FXML
    private TextField ciudadDestino;
    @FXML
    private TextField imagenDestino;
    @FXML
    private ComboBox<Clima> climaCiudad;
    @FXML
    private TextArea descripcionDestino;
    @FXML
    private Button btnAgregarDestino;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        climaCiudad.getItems().addAll(Clima.values());

    }
    public void registrarDestino(){
        Destino destino = new Destino(nombreDestino.getText(), ciudadDestino.getText(), descripcionDestino.getText(), imagenDestino.getText(),climaCiudad.getValue());
        try {
            agencia.crearDestino(destino);
            LOGGER.log(Level.INFO, "Se registró un destino");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Destino registrado exitosamente");
            alert.setHeaderText(null);
            alert.show();
        } catch (EmptyFieldException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
}
