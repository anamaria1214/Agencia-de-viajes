package Model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String telefono;
    private String direccion;
    private String contraseniaCliente;

}
