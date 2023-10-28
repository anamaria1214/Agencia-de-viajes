package Model;

import lombok.*;

import java.util.ArrayList;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuiaTuristico {

    private String idGuia;
    private String nombreGuia;
    private String apellidoGuia;
    private ArrayList<String> idiomas;
    private int aniosExperiencia;
}
