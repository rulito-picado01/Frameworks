package workflow.framework.nodos;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import workflow.framework.Params;

import java.util.Properties;

public class EmailSender extends AbstractNode {
    public static final String TO_EMAIL_KEY = "toEmailAddress";
    public static final String SUBJECT_KEY = "subject";
    public static final String BODY_KEY = "body";
    public static final String DEFAULT_SUBJECT = "Tienes un nuevo email!";
    public static final String DEFAULT_TO_EMAIL = "email@default.com";
    private static final String FROM_EMAIL = "from@example.com";
    private final Crendenciales credenciales;

    public EmailSender(String name, Crendenciales credenciales) {
        super(name, "Send Mail");
        this.credenciales = credenciales;
    }

    @Override
    public void execute(Params params) {
        assertRequiredParamsAreThere(params);
        initializeEmptyParams(params);
        final String username = this.credenciales.username();
        final String password = this.credenciales.password();
        var props = smtpProperties();
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse((String) params.get(TO_EMAIL_KEY))
            );
            message.setSubject((String) params.get(SUBJECT_KEY));
            message.setText((String) params.get(BODY_KEY));
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeEmptyParams(Params params) {
        if (params.get(TO_EMAIL_KEY) == null) {
            params.set(TO_EMAIL_KEY, DEFAULT_TO_EMAIL);
        }
        if (params.get(SUBJECT_KEY) == null) {
            params.set(SUBJECT_KEY, DEFAULT_SUBJECT);
        }
    }

    private void assertRequiredParamsAreThere(Params params) {
        if (params.get(BODY_KEY) == null) {
            throw new RuntimeException("Missing required parameters: " + BODY_KEY);
        }
    }

    private Properties smtpProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");
        return props;
    }
}
