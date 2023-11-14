package Controller;

import App.AppPrincipal;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/ContenedorPaquetes.fxml") );
        try {
            AnchorPane paquete = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
