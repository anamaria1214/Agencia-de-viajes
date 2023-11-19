package Model;

import App.AppPrincipal;
import Archivos.ArchivoUtils;
import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;
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
import Exception.WrongUseOfDatesException;

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
        leerDestinos();
        this.guiasTuristicos = new ArrayList<>();
        // leerGuiasTuristicos();
        this.paquetesTuristicos = new ArrayList<>();
        leerPaquetesTuristicos();
        this.reservas = new ArrayList<>();
        leerReservas();

        Administrador admin1= new Administrador("1090272715","admin1");
        administradores.add(admin1);
    }

    private void leerPaquetesTuristicos() {
        try {
            Object objeto = ArchivoUtils.deserializarObjeto("src/main/resources/persistencia/paquetes.data");
            this.paquetesTuristicos = (ArrayList<PaqueteTuristico>) objeto;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//dame el control cómo?no te muevas

    private void escribirPaquetesTuristicos() {
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/paquetes.data", paquetesTuristicos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerDestinos() {
        try {
            this.destinos = (ArrayList<Destino>) ArchivoUtils.deserializarObjeto("src/main/resources/persistencia/destinos.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void leerReservas() {
        try {
            this.reservas = (ArrayList<Reserva>) ArchivoUtils.deserializarObjeto("src/main/resources/persistencia/reservas.data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarPaquete(PaqueteTuristico paqueteTuristico) throws NegativeNumberException, WrongUseOfDatesException, EmptyFieldException {
        crearPaquete(paqueteTuristico);
        //paquetesTuristicos.add(paqueteTuristico);
        escribirPaquetesTuristicos();
    }

    private void leerClientes() {
        try (Scanner scanner = new Scanner(new File("src/main/resources/persistencia/clientes.txt"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] valores = linea.split(";");
                System.out.println(new Cliente(valores[0], valores[1], valores[2], (valores[3]), valores[4], valores[5], valores[6]));
                this.clientes.add(new Cliente(valores[0], valores[1], valores[2], (valores[3]), valores[4], valores[5], valores[6]));
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }


    public void registrarCliente(Cliente cliente) throws EmptyFieldException, ExistingCustomerException, Exception {
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
        if (comprobarExistenciaClienteRecur(cliente.getEmailCliente(), 0, false)) {
            LOGGER.log(Level.SEVERE, "El cliente ya se encuentra registrado");
            throw new ExistingCustomerException("El cliente ya se encuentra registrado");
        }

        clientes.add(cliente);

        try {
            FileWriter fw = new FileWriter(new File("src/main/resources/persistencia/clientes.txt"), true);
            Formatter ft = new Formatter(fw);
            ft.format(cliente.getIdCliente() + ";" + cliente.getNombreCliente()
                    + ";" + cliente.getApellidoCliente()
                    + ";" + cliente.getEmailCliente() + ";" + cliente.getTelefono()
                    + ";" + cliente.getDireccion()  + ";" + cliente.getContraseniaCliente() + "%n");
            ft.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new Exception("El cliente no se pudo guardar");//ahí lo guardas, pero donde lo lees?
        }

        LOGGER.log(Level.INFO, "Cliente registrado exitosamente");

    }

    public boolean comprobarExistenciaClienteRecur(String email, int i, boolean flag) {
        if (i < clientes.size() && !flag) {
            if (email.equals(clientes.get(i).getEmailCliente())) {
                return true;
            } else {
                return comprobarExistenciaClienteRecur(email, i + 1, false);
            }
        } else {
            return flag;
        }
    }
    public Cliente encontrarCliente(String email, int i, boolean flag, Cliente cliente){
        if(i < clientes.size() && !flag){
            if(email.equals(clientes.get(i).getEmailCliente())){
                return encontrarCliente(email, i, true, clientes.get(i));
            }else{
                return encontrarCliente(email,i+1,false, cliente);
            }
        }else{
            return cliente;
        }
    }

    /*public void iniciarSesionClienteRecur(String email, String contrasenia, int i, boolean flag) throws NonRegisteredCustomer {
        if (i < clientes.size() && !flag) {
            if (!comprobarExistenciaClienteRecur(clientes.get(i).getEmailCliente(), 0, false)) {

                throw new NonRegisteredCustomer("El cliente que ingresó no se encuentra registrado");
            } else {
                if (clientes.get(i).getContraseniaCliente().equals(contrasenia)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/PaginaPrincipalCliente.fxml"));
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
            }
        } else {
            iniciarSesionClienteRecur(email, contrasenia, i + 1, false);
        }
    }*/
    public void iniciarSesionClienteRecur(String email, String contrasenia, int i, boolean flag) throws NonRegisteredCustomer {
        if (i < clientes.size() && !flag) {
            if (!comprobarExistenciaClienteRecur(clientes.get(i).getEmailCliente(), 0, false)) {
                throw new NonRegisteredCustomer("El cliente que ingresó no se encuentra registrado");
            } else {
                if (clientes.get(i).getContraseniaCliente().equals(contrasenia)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(AppPrincipal.class.getResource("/View/PaginaPrincipalCliente.fxml"));
                        Parent parent = loader.load();
                        Stage stage = new Stage();
                        Scene scene = new Scene(parent);
                        stage.setScene(scene);
                        stage.setTitle("Agencia de viajes");
                        stage.show();
                        return; // Salir del método después de iniciar sesión exitosamente
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("La contraseña que ingresó es incorrecta. Ingresela de nuevo");
                    alert.setHeaderText(null);
                    alert.show();
                }
            }
        }

        iniciarSesionClienteRecur(email, contrasenia, i + 1, false);
    }

    public void crearPaquete(PaqueteTuristico paquete) throws EmptyFieldException, NegativeNumberException, WrongUseOfDatesException {
        if(paquete.getDestinos()==null){
            LOGGER.log(Level.SEVERE, "No tienen destinos");
            throw new EmptyFieldException("Ingrese los destinos del paquete");
        }if(paquete.getNombrePaquete()==null){
            LOGGER.log(Level.SEVERE, "El nombre del paquete está vacio");
            throw new EmptyFieldException("El nombre del destino esta vacio. Ingrese el nombre");
        }if(paquete.getCupoMaximo()<=0){
            LOGGER.log(Level.SEVERE, "Duración de paquete no válido");
            throw new NegativeNumberException("Ingrese una duración valida del paquete");
        }if(paquete.getServiciosAdicionales()==null){
            LOGGER.log(Level.SEVERE, "No se ingresaron los servicios");
            throw new NegativeNumberException("Ingrese una duración valida del paquete");
        }if(paquete.getPrecio()<=0){
            LOGGER.log(Level.SEVERE, "Precio no válido");
            throw new NegativeNumberException("Ingrese un precio valido");
        }if(paquete.getFechaFin().isBefore(paquete.getFechaInicio())){
            LOGGER.log(Level.SEVERE, "Fecha no válida");
            throw new WrongUseOfDatesException("Ingreso una la fecha fin no válida");
        }if(paquete.getFechaInicio().isAfter(paquete.getFechaFin())){
            LOGGER.log(Level.SEVERE, "Fecha no válida");
            throw new WrongUseOfDatesException("Ingreso una la fecha inicio no válida");
        }
        paquetesTuristicos.add(paquete);
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/paquetes.data", paquetesTuristicos);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }

    }
    public void crearDestino(Destino destino) throws EmptyFieldException {
        if(destino.getNombreDestino()==null){
            LOGGER.log(Level.SEVERE, "El nombre del destino esta vacio");
            throw new EmptyFieldException("El nombre del destino esta vacio. Ingrese el nombre");
        }if(destino.getCiudad()==null){
            LOGGER.log(Level.SEVERE, "La ciudad del destino esta vacia");
            throw new EmptyFieldException("La ciudad del destino esta vacia. Ingrese la ciudad");
        }if(destino.getDescripcion()==null){
            LOGGER.log(Level.SEVERE, "La descripcion del destino esta vacia");
            throw new EmptyFieldException("La descripcion del destino esta vacia. Ingrese la descripción");
        }if(destino.getImagenRepresentativa()==null){
            LOGGER.log(Level.SEVERE, "No se agregó imagen");
            throw new EmptyFieldException("Agregué una imagen");
        }if(destino.getClima()==null){
            LOGGER.log(Level.SEVERE, "No se agregó el clima");
            throw new EmptyFieldException("Agregué el clima");
        }
        destinos.add(destino);
        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/destinos.data", destinos);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
    }
    public void crearReserva(Reserva reserva) throws EmptyFieldException, NegativeNumberException, MaximumCapacityException, WrongUseOfDatesException {
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
        }if(reserva.getPaqueteTuristico()==null){
            LOGGER.log(Level.SEVERE, "No se seleccionó un paquete turistico");
            throw new EmptyFieldException("Selecione un paquete turístico");
        }if(reserva.getCantPersonasViajan()>reserva.getPaqueteTuristico().getCupoMaximo()){
            LOGGER.log(Level.SEVERE, "Se excedió el cupo máximo de personas para la reserva");
            throw new MaximumCapacityException("Se excedió el cupo máximo de personas para la reserva. Ingrese un número válido");
        }if(reserva.getEstadoReserva()==null){
            LOGGER.log(Level.SEVERE, "No se le asignó un estado a la reserva");
            throw new EmptyFieldException("No se le asignó un estado a la reserva");
        }if(reserva.getFechaViaje().isBefore(reserva.getPaqueteTuristico().getFechaInicio())){
            LOGGER.log(Level.SEVERE, "Fecha escogida no es válida");
            throw new WrongUseOfDatesException("La fecha que escogio no corresponde a las fechas disponibles del paquete. " +
                    "Estas fechas van desde: "+reserva.getPaqueteTuristico().getFechaInicio().toString()+" hasta: "
                    +reserva.getPaqueteTuristico().getFechaFin().toString());
        }if(reserva.getFechaViaje().isAfter(reserva.getPaqueteTuristico().getFechaInicio().plusDays(ChronoUnit.DAYS.between
                        (reserva.getPaqueteTuristico().getFechaInicio(), reserva.getPaqueteTuristico().getFechaFin())))){
            LOGGER.log(Level.SEVERE, "Fecha escogida no es válida");
            throw new WrongUseOfDatesException("La fecha que escogio no corresponde a las fechas disponibles del paquete. " +
                    "Estas fechas van desde: "+reserva.getPaqueteTuristico().getFechaInicio().toString()+" hasta: "
                    +reserva.getPaqueteTuristico().getFechaFin().toString());
        }

        try {
            ArchivoUtils.serializarObjeto("src/main/resources/persistencia/reservas.data", reservas);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        LOGGER.log(Level.INFO, "Se registró la reserva correctamente");

        reservas.add(reserva);
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


    public void filtrarClima(Clima clima, int i, int j, ArrayList<PaqueteTuristico> paqueteFiltrado){
        if(paquetesTuristicos.size()>i){
            if(paquetesTuristicos.get(i).getDestinos().size()>j){
                if(paquetesTuristicos.get(i).getDestinos().get(j).getClima().equals(clima)){
                    paqueteFiltrado.add(paquetesTuristicos.get(i));
                    filtrarClima(clima,i,j+1,paqueteFiltrado);
                }else{
                    filtrarClima(clima,i,j+1,paqueteFiltrado);
                }
            }else{
                filtrarClima(clima,i+1,0, paqueteFiltrado);
            }
        }
    }
    public void filtrarPrecio(double precio, int i, ArrayList<PaqueteTuristico> paquetesFiltrados) {
        if(paquetesTuristicos.size()>i){
            if(paquetesTuristicos.get(i).getPrecio()<=precio){
                paquetesFiltrados.add(paquetesTuristicos.get(i));
                filtrarPrecio(precio,i+1,paquetesFiltrados);
            }else{
                filtrarPrecio(precio,i+1,paquetesFiltrados);
            }
        }
    }
    public void filtrarCiudad(String ciudad, int i,int j, ArrayList<PaqueteTuristico> paqueteFiltrados){
        if(paquetesTuristicos.size()>i){
            if(paquetesTuristicos.get(i).getDestinos().size()>j){
                if(paquetesTuristicos.get(i).getDestinos().get(j).getCiudad().equals(ciudad)){
                    paqueteFiltrados.add(paquetesTuristicos.get(i));
                    filtrarCiudad(ciudad,i,j+1,paqueteFiltrados);
                }else{
                    filtrarCiudad(ciudad,i,j+1,paqueteFiltrados);
                }
            }else{
                filtrarCiudad(ciudad,i+1,0,paqueteFiltrados);
            }
        }
    }
    public void filtrarDestino(String nombreDestino, int i, int j, ArrayList<PaqueteTuristico> paquetesFiltrados){
        if(paquetesTuristicos.size()>i){
            if(paquetesTuristicos.get(i).getDestinos().size()>j){
                if(paquetesTuristicos.get(i).getDestinos().get(j).getNombreDestino().toLowerCase().contains(nombreDestino.toLowerCase())){
                    paquetesFiltrados.add(paquetesTuristicos.get(i));
                    filtrarDestino(nombreDestino,i,j+1,paquetesFiltrados);
                }else{
                    filtrarDestino(nombreDestino,i,j+1,paquetesFiltrados);
                }
            }else{
                filtrarDestino(nombreDestino, i+1,0,paquetesFiltrados);
            }
        }
    }
    public void eliminarRepetidosPaquetes(ArrayList<PaqueteTuristico> paquetes){
        for(int i=0;i< paquetes.size();i++){
            for(int j=i+1;j<paquetes.size();j++){
                if(paquetes.get(i).equals(paquetes.get(j))){
                    paquetes.remove(paquetes.get(j));
                    j--;
                }
            }
        }
    }
    public ArrayList<PaqueteTuristico> filtrar(String nombreDestino, String ciudad, double precio, Clima clima){
        ArrayList<PaqueteTuristico> paquetesFiltrados=new ArrayList<>();

        if(nombreDestino!=null && !nombreDestino.isEmpty()){
            filtrarDestino(nombreDestino,0,0,paquetesFiltrados);
        }

        if(ciudad!=null && !ciudad.isEmpty()){
            filtrarCiudad(ciudad,0,0, paquetesFiltrados);
        }

        if(precio>0){
            filtrarPrecio(precio,0,paquetesFiltrados);
        }

        if (clima!=null){
            filtrarClima(clima,0,0,paquetesFiltrados);
        }
        eliminarRepetidosPaquetes(paquetesFiltrados);
        return paquetesFiltrados;
    }
    public ArrayList<Reserva> listarReserva(String id) {
        ArrayList<Reserva> reservasCliente= new ArrayList<>();
        for (Reserva reserva : reservas) {
            System.out.println(reserva + "--------" + reserva.getCliente());
            if (reserva.getCliente().getIdCliente().equals(id)) {
                reservasCliente.add(reserva);
            }
        }
        return reservasCliente;
    }
    public ArrayList<PaqueteTuristico> listarPaquetes(String id){
        ArrayList<PaqueteTuristico> paquetes= new ArrayList<>();
        for(int i=0;i<listarReserva(id).size();i++){
            System.out.println(listarReserva(id).get(i).getPaqueteTuristico());
            paquetes.add(listarReserva(id).get(i).getPaqueteTuristico());
        }
        return paquetes;
    }
    public ArrayList<Reserva> listarReserva(String id, int i, ArrayList<Reserva> reservasCliente){
        if(reservas.size()>i){
            if (reservas.get(i).getCliente().getIdCliente().equals(id)){
                System.out.println(reservas.get(i).getCliente().getIdCliente());
                reservasCliente.add(reservas.get(i));
                return listarReserva(id,i+1, reservasCliente);
            }else{
                return listarReserva(id,i+1, reservasCliente);
            }
        }else{
            return reservasCliente;
        }
    }

    public boolean iniciarSesionAdmin(String id, String contrasenia, int i){

        if(i<administradores.size()){

            if(administradores.get(i).getIdAdministrador().equals(id)) {
                if (administradores.get(i).getContrasenia().equals(contrasenia)) {
                    return true;
                }
            }

            return iniciarSesionAdmin(id, contrasenia,i+1);

        }

        return false;

    }

    //Metodos de busqueda dado atributos dados

    public String imagenAleatoria(){
        File carpetaDestinosImg =  new File("src/main/resources/Imagenes/Paquetes");
        File[] archivos  = carpetaDestinosImg.listFiles();

        String ruta="";

        if (archivos != null && archivos.length>0){
            Random random = new Random();
            int indiceR = random.nextInt(archivos.length);
            ruta = archivos[indiceR].getAbsolutePath();
            return ruta;
        }else{
            throw new RuntimeException("No hay imágenes");
        }
    }
}

