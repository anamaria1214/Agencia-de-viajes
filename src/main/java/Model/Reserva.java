package Model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva implements Serializable {

    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaViaje;
    private Cliente cliente;
    private int cantPersonasViajan;
    private PaqueteTuristico paqueteTuristico;
    private EstadoReserva estadoReserva;

}
