package App;

import Controller.IniciarAnimadoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppPrincipal extends Application {

    //Contraseña para enviar Email: ebmn eeui oixa ayga

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppPrincipal.class.getResource("/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader( AppPrincipal.class.getResource("/View/PaginaPrincipal.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Agencia de viajes");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launch( AppPrincipal.class, args );
    }
}
