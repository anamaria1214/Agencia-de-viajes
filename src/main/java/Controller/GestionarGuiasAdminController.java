package Controller;

import Exception.EmptyFieldException;
import Exception.ExistingCustomerException;
import Exception.NegativeNumberException;
import Model.Agencia;
import Model.GuiaTuristico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionarGuiasAdminController implements Initializable {

    private Agencia agencia= Agencia.getInstance();
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());
    private Propiedades propiedades = Propiedades.getInstance();

    GuiaTuristico guiaTuristicoSeleccionado;
    String idiomaSeleccionado;
    ObservableList<GuiaTuristico> listaIdiomas = FXCollections.observableArrayList();
    ObservableList<GuiaTuristico> listaGuiasTuristicos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<GuiaTuristico, Integer> columnAniosExperiencia;

    @FXML
    private TableColumn<GuiaTuristico, String> columnApellido;


    @FXML
    private TableColumn<GuiaTuristico, String> columnNombre;

    @FXML
    private TableView<GuiaTuristico> tableGuias;


    @FXML
    private TextField txtAniosExperiencia;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtId;

    @FXML
    private TextArea txtIdioma;



    @FXML
    void actualizarEvent(ActionEvent event) {
        String idViejo = guiaTuristicoSeleccionado.getIdGuia();
        try {

            agencia.actualizarGuiaTuristico(idViejo,txtNombre.getText(),txtApellido.getText(),txtAniosExperiencia.getText(),txtIdioma.getText(), txtId.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("El guia turistico ha sido actualizado satisfactoriamente");
            alert.setContentText("Por favor vuelva a presionar el boton de Gestionar Guias Turisticos");
            alert.show();
        }catch (NegativeNumberException | EmptyFieldException e){
            LOGGER.log(Level.WARNING, "No se pudo actualizar el Guia");
            Alert alert = new Alert( Alert.AlertType.WARNING);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Por favor vuelva a presionar el boton de Gestionar Guias Turisticos");
            alert.show();
        }
    }

    @FXML
    void crearEvent(ActionEvent event) {
        try {
            agencia.crearGuiaTuristico(txtNombre.getText(),txtApellido.getText(),txtAniosExperiencia.getText(),txtIdioma.getText(), txtId.getText());
            LOGGER.log(Level.INFO, "Se creó un Guia");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("El guia turistico ha sido creado satisfactoriamente");
            alert.setContentText("Por favor vuelva a presionar el boton de Gestionar Guias Turisticos");
            alert.show();
        }catch (NegativeNumberException | EmptyFieldException | ExistingCustomerException e){
            LOGGER.log(Level.WARNING, "No se pudo crear el Guia");
            Alert alert = new Alert( Alert.AlertType.WARNING);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("El guia turistico ha sido creado satisfactoriamente");
            alert.show();
        }
    }

    @FXML
    void eliminarEvent(ActionEvent event) {
        try {
            agencia.eliminarGuiaTuristico(txtId.getText());
            LOGGER.log(Level.INFO, "Se eliminó un Guia");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Por favor vuelva a presionar el boton de Gestionar Guias Turisticos");
            alert.setHeaderText("El guia turistico ha sido eliminado satisfactoriamente");
            alert.show();
        }catch (EmptyFieldException e){
            LOGGER.log(Level.WARNING, "No se pudo eliminar el Guia");
            Alert alert = new Alert( Alert.AlertType.WARNING);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Por favor vuelva a presionar el boton de Gestionar Guias Turisticos");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeTables();
        tableGuias.getItems().clear();
        tableGuias.setItems(getDatosListaGuias());

    }

    private void initializeTables() {

        this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreGuia"));
        this.columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoGuia"));
        this.columnAniosExperiencia.setCellValueFactory(new PropertyValueFactory<>("aniosExperiencia"));

        tableGuias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            guiaTuristicoSeleccionado = newSelection;
            mostrarInfoGuias(guiaTuristicoSeleccionado);
        });
    }


    private void  mostrarInfoGuias(GuiaTuristico guiaTuristicoSeleccionado) {

        if(guiaTuristicoSeleccionado != null){
            txtId.setText(guiaTuristicoSeleccionado.getIdGuia());
            txtNombre.setText(guiaTuristicoSeleccionado.getNombreGuia());
            txtApellido.setText(guiaTuristicoSeleccionado.getApellidoGuia());
            txtAniosExperiencia.setText(String.valueOf(guiaTuristicoSeleccionado.getAniosExperiencia()));
            String idiomas = String.valueOf(guiaTuristicoSeleccionado.getIdiomas()).replace("[", "").replace("]", "");
            txtIdioma.setText(idiomas);
        }
    }


    private ObservableList<GuiaTuristico> getDatosListaGuias() {
        listaGuiasTuristicos.addAll(agencia.getGuiasTuristicos());
        return listaGuiasTuristicos;
    }
}
