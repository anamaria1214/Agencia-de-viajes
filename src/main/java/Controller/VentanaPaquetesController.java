package Controller;


import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaPaquetesController implements Initializable {
    @FXML
    private GridPane gridPaquetes;
    private Agencia agencia = Agencia.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void crearElementosScroll() throws IOException {
        gridPaquetes.getColumnConstraints().add(new ColumnConstraints(1));
        gridPaquetes.getRowConstraints().add(new RowConstraints(agencia.getPaquetesTuristicos().size()));
        Node node = AppPrincipal.loadFXML("PaqueteComponente");
        gridPaquetes.add(node, 0,0);


    }
}
