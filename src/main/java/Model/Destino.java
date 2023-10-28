package Model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Destino {

    private String nombreDestino;
    private String ciudad;
    private String descripcion;
    private String imagenRepresentativa;
    private Clima clima;
}
