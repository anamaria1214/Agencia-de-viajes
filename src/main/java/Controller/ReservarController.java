package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.EmptyFieldException;
import Exception.NegativeNumberException;
import Exception.MaximumCapacityException;
import Exception.WrongUseOfDatesException;

public class ReservarController implements Initializable {

    private SesionCliente sesionCliente= SesionCliente.getInstance();
    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();
    private ConocerPaquete paqueteNecesario= ConocerPaquete.getInstance();

    @FXML
    private Button btnReservar;

    @FXML
    private TextField cantPersonasViajan;

    @FXML
    private DatePicker fechaViaje;

    @FXML
    private RadioButton mandarCorreo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void hacerReserva(){
        Reserva reserva= new Reserva(LocalDateTime.now(), fechaViaje.getValue().atStartOfDay(),sesionCliente.getCliente(),Integer.parseInt(cantPersonasViajan.getText()),paqueteNecesario.getPaquete(), EstadoReserva.CONFIRMADA);
        try {
            agencia.crearReserva(reserva);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("La reserva se ha registardo correctamente");
            alert.setHeaderText(null);
            alert.show();
            System.out.println(reserva.getCliente());
            System.out.println(reserva.getPaqueteTuristico());
        } catch (EmptyFieldException | NegativeNumberException | MaximumCapacityException | WrongUseOfDatesException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();
        }
    }
}
