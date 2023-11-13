package Model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SesionCliente {
    private static SesionCliente instancia;
    private Cliente cliente;

    public static SesionCliente getInstance() {
        if (instancia == null) {
            instancia = new SesionCliente();
        }
        return instancia;
    }

}
