package Model;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Properties;

public class EnvioCorreo {
    private static String emailFrom="anicu2314@gmail.com";
    private static String passwordFrom="kgkkchddlicsssbp";
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public EnvioCorreo() {
        // Inicializa mProperties en el constructor o en algún otro lugar según tu lógica
        this.mProperties = new Properties();
    }

    public void createEmail(String emailTo, String content) {
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.port", "587");
        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.auth", "true");

        mSession = Session.getInstance(mProperties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, passwordFrom);
            }
        });

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject("Detalles de su reserva");
            mCorreo.setText(content, "ISO-8859-1", "html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /*public void createEmail(String emailTo, String content){

        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.port", "587");

        mProperties.put("mail.smtp.starttls.enable", "true");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.put("mail.smtp.auth", "true");
        mProperties.put("mail.user", emailFrom);
        mProperties.put("mail.password", passwordFrom);
        mProperties.setProperty("mail.smtp.user",emailFrom);
        //mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        mSession =Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject("Detalles de su reserva");
            mCorreo.setText(content, "ISO-8859-1", "html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }*/
    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect();
            mTransport.sendMessage(mCorreo, mCorreo.getAllRecipients());
            mProperties.put("mail.debug", "true");
            mTransport.close();

            System.out.println("Correo enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    /*public void sendEmail(){

        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            System.out.println("Correo enviado");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }*/

    }}
