package Controller;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Propiedades {


    private static Propiedades instance;

    @Getter
    private String idioma;

    @Getter
    private ResourceBundle bundle;

    public static Propiedades getInstance(){
        if(instance==null){
            instance=new Propiedades();
        }
        return instance;
    }

    private Propiedades(){
        idioma = leerIdioma();
        bundle= ResourceBundle.getBundle("propiedades", new Locale(idioma) );
    }

    public String leerIdioma(){
        try {
            Properties ppr = new Properties();
            FileInputStream fos = new FileInputStream("idioma.properties");
            ppr.load(fos);
            idioma = ppr.getProperty("idioma");
            fos.close();
            return idioma;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void escribirIdioma(String idioma){
        try {
            this.idioma = idioma;
            this.bundle = ResourceBundle.getBundle("propiedades", new Locale(idioma));
            Properties ppr = new Properties();
            FileOutputStream fos = new FileOutputStream("idioma.properties");
            ppr.setProperty("idioma", idioma);
            ppr.store(fos,"");
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
