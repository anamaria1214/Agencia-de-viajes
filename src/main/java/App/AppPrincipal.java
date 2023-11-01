package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader( AppPrincipal.class.getResource("/View/IniciarSesion.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Agencia de viajes");
        stage.show();

    }

    public static void main(String[] args) {
        launch( AppPrincipal.class, args );
    }
}