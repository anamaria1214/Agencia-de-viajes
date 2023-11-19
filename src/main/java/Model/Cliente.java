package Model;

import lombok.*;

import java.io.Serializable;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    private String idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefono;
    private String direccion;
    private String contraseniaCliente;

}
