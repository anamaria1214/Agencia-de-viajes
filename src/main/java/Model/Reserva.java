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
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Reserva implements Serializable {

    private static final long serialVersionUID= 5497592443841536490L;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaViaje;
    private Cliente cliente;
    private int cantPersonasViajan;
    private PaqueteTuristico paqueteTuristico;
    private EstadoReserva estadoReserva;

}
