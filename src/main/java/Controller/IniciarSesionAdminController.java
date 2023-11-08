package Controller;

import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class IniciarSesionAdminController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    @FXML
    private TextField idAdmin;
    @FXML
    private TextField contraseniaAdmin;
    @FXML
    private Button btnIniciarSesionAdmin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void iniciarSesionAdmin(){
        agencia.iniciarSesionAdmin(idAdmin.getText(),contraseniaAdmin.getText(),0, false);
    }
}
