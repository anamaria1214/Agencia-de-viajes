package Controller;

import App.AppPrincipal;
import Model.Agencia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class BuscadorController implements Initializable {
    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    @FXML
    private Button btnPorCiudad;

    @FXML
    private Button btnPorClima;

    @FXML
    private Button btnPorDestino;

    @FXML
    private Button btnPorFechas;

    @FXML
    private Button btnPorPaquete;

    @FXML
    private Button btnPorPrecio;

    @FXML
    private BorderPane centerPane;

    @FXML
    private Label labelBuscador;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void cambiarVentana(String fxmlname) {
        try {
            Node nodo = AppPrincipal.loadFXML(fxmlname);
            setCenter(nodo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("exports")
    public void setCenter(Node node) {
        centerPane.setCenter(node);
    }

    public void buscarPorCiudad() { cambiarVentana("/ViewBuscador/BuscadorPorCiudad");}
    public void buscarPorDestino() { cambiarVentana("/ViewBuscador/BuscadorPorDestino");}
    public void buscarPorClima() { cambiarVentana("/ViewBuscador/BuscadorPorClima");}
    public void buscarPorFecha() { cambiarVentana("/ViewBuscador/BuscadorPorFecha");}
    public void buscarPorPaquete() { cambiarVentana("/ViewBuscador/BuscadorPorPaquete");}
    public void buscarPorPrecio() { cambiarVentana("/ViewBuscador/BuscadorPorPrecio");}
}
