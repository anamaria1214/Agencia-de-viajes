package Model;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConocerPaquete {

    private static ConocerPaquete instancia;
    private PaqueteTuristico paquete;

    public static ConocerPaquete getInstance() {
        if (instancia == null) {
            instancia = new ConocerPaquete();
        }
        return instancia;
    }
}
