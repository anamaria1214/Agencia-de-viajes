package Model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class PaqueteTuristico implements Serializable {

    private ArrayList<Destino> destinos= new ArrayList<>();
    @EqualsAndHashCode.Include
    private String nombrePaquete;
    private int duracion;
    private String serviciosAdicionales;
    private double precio;
}
