package Model;

import lombok.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class PaqueteTuristico implements Serializable {

    private static final long serialVersionUID = 1499350257449678668L;

    private ArrayList<Destino> destinos= new ArrayList<>();
    private String nombrePaquete;
    private int cupoMaximo;
    private String serviciosAdicionales;
    private double precio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

}
