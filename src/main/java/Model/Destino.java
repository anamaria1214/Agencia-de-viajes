package Model;

import lombok.*;

import java.io.Serializable;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Destino implements Serializable {

    @EqualsAndHashCode.Include
    private String nombreDestino;

    @EqualsAndHashCode.Include
    private String ciudad;
    private String descripcion;
    private String imagenRepresentativa;
    private Clima clima;
}
