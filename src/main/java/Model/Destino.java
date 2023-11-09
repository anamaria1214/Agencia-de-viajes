package Model;

import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destino implements Serializable {

    private String nombreDestino;
    private String ciudad;
    private String descripcion;
    private String imagenRepresentativa;
    private Clima clima;
}
