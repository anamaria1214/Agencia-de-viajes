package Model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SesionCliente {

    private static SesionCliente instancia;
    private Cliente cliente;

    public static SesionCliente getInstance() {
        if (instancia == null) {
            System.out.println("Creando nueva instancia de SesionCliente");
            instancia = new SesionCliente();
        }
        return instancia;
    }

}
