package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
        idAdmin.setText("1090272715");
        contraseniaAdmin.setText("admin1");
    }

    public void iniciarSesionAdmin(){

        boolean esValido = agencia.iniciarSesionAdmin(
                idAdmin.getText(), contraseniaAdmin.getText(), 0
        );

        if(esValido){
            try {
                ((Stage) idAdmin.getScene().getWindow()).close();
                FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/PaginaPrincipalAdmin.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setTitle("Agencia de viajes");
                stage.show();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("La contraseña que ingresó es incorrecta. Ingresela de nuevo");
            alert.setHeaderText(null);
            alert.show();
        }

    }
}
