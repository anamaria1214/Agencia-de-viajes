package Model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuiaTuristico implements Serializable {

    private String idGuia;
    private String nombreGuia;
    private String apellidoGuia;
    private ArrayList<String> idiomas;
    private int aniosExperiencia;
}
