package App;

import Controller.IniciarAnimadoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader( AppPrincipal.class.getResource("/View/IniciarAnimado.fxml") );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Agencia de viajes");
        stage.setOnShown(event -> {
            double anchoEscena = stage.getScene().getWidth();
            double altoEscena = stage.getScene().getHeight();
            IniciarAnimadoController c = loader.getController();
            c.setTamaño("ancho",anchoEscena/2);
            c.setTamaño("alto",altoEscena);
        });
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public static void main(String[] args) {
        launch( AppPrincipal.class, args );
    }
}
