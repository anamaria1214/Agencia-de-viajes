package Model;

import App.AppPrincipal;
import Archivos.ArchivoUtils;
import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import Exception.EmptyFieldException;
import Exception.ExistingCustomerException;
import Exception.NonRegisteredCustomer;
import Exception.NegativeNumberException;
import Exception.MaximumCapacityException;
@Getter
public class Agencia {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<Destino> destinos = new ArrayList<>();
    private ArrayList<GuiaTuristico> guiasTuristicos = new ArrayList<>();
    private ArrayList<PaqueteTuristico> paquetesTuristicos = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();

    private static Agencia agencia;
    private static final Logger LOGGER = Logger.getLogger(Agencia.class.getName());

    public static Agencia getInstance() {
        if (agencia == null) {
            agencia = new Agencia();
        }
        return agencia;
    }

    private Agencia() {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Archivo no encontrado");
        }
        LOGGER.log(Level.INFO, "Se creó una nueva instancia");

        this.clientes = new ArrayList<>();
        leerClientes();
        this.administradores = new ArrayList<>();
        // leeerAdministradores
        this.destinos = new ArrayList<>();
        // leerDestinos();
        this.guiasTuristicos = new ArrayList<>();
        // leerGuiasTuristicos();
        this.paquetesTuristicos = new ArrayList<>();
        // leerPaquetesTuristicos();
        this.reservas = new ArrayList<>();
        //leerReservas();
    }

    private void leerReservas() {
        try {
            this.reservas = (ArrayList<Reserva>) ArchivoUtils.deserializarObjeto("src/main/resources/persistencia/reservas.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void leerClientes() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/persistencia/clientes.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] valores = linea.split(";");
                this.clientes.add(new Cliente(valores[0], valores[1], valores[2], (valores[3]), valores[4], valores[5], valores[6]));
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }


    public void registrarCliente(Cliente cliente) throws EmptyFieldException, ExistingCustomerException {
        if (cliente.getIdCliente() == null || cliente.getIdCliente().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su cedula");
            throw new EmptyFieldException("La cedula es obligatoria");
        }
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su nombre");
            throw new EmptyFieldException("El nombre es obligatorio");
        }
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su apellido");
            throw new EmptyFieldException("El apellido es obligatorio");
        }
        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su correo electronico");
            throw new EmptyFieldException("El correo electronico es obligatorio");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su telefono");
            throw new EmptyFieldException("El telefono es obligatorio");
        }
        if (cliente.getDireccion() == null || cliente.getDireccion().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no ingreso su direccion");
            throw new EmptyFieldException("La dirección es obligatoria");
        }
        if (cliente.getContraseniaCliente() == null || cliente.getContraseniaCliente().isBlank()) {
            LOGGER.log(Level.SEVERE, "La persona no se asigno una contraseña");
            throw new EmptyFieldException("La contraseña es obligatoria");
        }
        if (comprobarExistenciaClienteRecur(cliente.getIdCliente(), 0, false)) {
            LOGGER.log(Level.SEVERE, "El cliente ya se encuentra registrado");
            throw new ExistingCustomerException("El cliente ya se encuentra registrado");
        }

        try {
            FileWriter fw = new FileWriter(new File("src/main/resources/persistencia/clientes.txt"), true);
            Formatter ft = new Formatter(fw);
            ft.format(cliente.getIdCliente() + ";" + cliente.getNombreCliente()
                    + ";" + cliente.getApellidoCliente()
                    + ";" + cliente.getTelefono() + ";" + cliente.getEmailCliente()
                    + ";" + cliente.getTelefono() + ";" + cliente.getDireccion()
                    + ";" + cliente.getContraseniaCliente() + "%n");
            ft.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        clientes.add(cliente);
        LOGGER.log(Level.INFO, "Cliente registrado exitosamente");

    }

    public boolean comprobarExistenciaClienteRecur(String email, int i, boolean flag) {
        if (i < clientes.size() && !flag) {
            if (email.equals(clientes.get(i).getEmailCliente()
            )) {
                return comprobarExistenciaClienteRecur(email, i, true);
            } else {
                return comprobarExistenciaClienteRecur(email, i + 1, false);
            }
        } else {
            return flag;
        }
    }

    public void iniciarSesionClienteRecur(String email, String contrasenia, int i, boolean flag) throws NonRegisteredCustomer {
        if (i <clientes.size() && !flag) {
            if (!comprobarExistenciaClienteRecur(clientes.get(i).getEmailCliente(), 0, false)) {
                throw new NonRegisteredCustomer("El cliente que ingresó no se encuentra registrado");
            }else{
                if(clientes.get(i).getContraseniaCliente().equals(contrasenia)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/InicioCliente.fxml"));
                        Parent parent = loader.load();

                        Stage stage = new Stage();

                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.setTitle("Agencia de viajes");
                        stage.show();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La contraseña que ingresó es incorrecta. Ingresela de nuevo");
                    alert.setHeaderText(null);
                    alert.show();
                }
                iniciarSesionClienteRecur(email, contrasenia, i, true);
            }
        }else{
            iniciarSesionClienteRecur(email, contrasenia, i+1, false);
        }

    }
    public void crearReserva(Reserva reserva) throws EmptyFieldException, NegativeNumberException, MaximumCapacityException {
        if(reserva.getFechaSolicitud()==null){
            LOGGER.log(Level.SEVERE, "La fecha de solicitud es nula");
            throw new EmptyFieldException("La fecha de solicitud es nula");
        }if(reserva.getFechaViaje()==null){
            LOGGER.log(Level.SEVERE, "La fecha de solicitud es nula");
            throw new EmptyFieldException("No ingreso la fecha del viaje");
        }if(reserva.getCliente()==null){
            LOGGER.log(Level.SEVERE, "El cliente es nulo");
            throw new EmptyFieldException("El cliente es nulo");
        }if(reserva.getCantPersonasViajan()<=0){
            LOGGER.log(Level.SEVERE, "La persona no ingreso un numero valido de personas que viajan");
            throw new NegativeNumberException("Ingrese un número válido de personas que viajan");
        }if(reserva.getCantPersonasViajan()>30){
            LOGGER.log(Level.SEVERE, "Se excedió el cupo máximo de personas para la reserva");
            throw new MaximumCapacityException("Se excedió el cupo máximo de personas para la reserva. Ingrese un número válido");
        }if(reserva.getPaqueteTuristico()==null){
            LOGGER.log(Level.SEVERE, "No se seleccionó un paquete turistico");
            throw new EmptyFieldException("Sellecione un paquete turístico");
        }if(reserva.getEstadoReserva()==null){
            LOGGER.log(Level.SEVERE, "No se le asignó un estado a la reserva");
            throw new EmptyFieldException("No se le asignó un estado a la reserva");
        }
        reservas.add(reserva);
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/reservas.data", reservas);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        LOGGER.log(Level.INFO, "Se registró la reserva correctamente");
    }

    public void cancelarReserva(Reserva reserva){
        reservas.remove(reserva);
    }

    public ArrayList<Reserva> encontrarReservasRecur(String id, int i, ArrayList<Reserva> reser){
        if(i<reservas.size()){
            if(reservas.get(i).getCliente().getIdCliente().equals(id)){
                reser.add(reservas.get(i));
            }
            return encontrarReservasRecur(id,i+1,reser);
        }else{
            return reser;
        }
    }
    public void abrirVentanaRegistro() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/View/RegistroCliente.fxml"));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Agencia de viajes");
        stage.show();
    }

    //Metodos de busqueda dado atributos dados


}

