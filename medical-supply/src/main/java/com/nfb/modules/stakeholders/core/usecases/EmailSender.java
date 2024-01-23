package com.nfb.modules.stakeholders.core.usecases;


import com.nfb.modules.stakeholders.core.domain.user.User;
import jakarta.activation.DataHandler;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class EmailSender {
    private static final String FROM_EMAIL = "lukaivanpk@gmail.com";
    private static final String CONTENT_TYPE = "application/octet-stream";

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String mail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail);
        mailMessage.setFrom(FROM_EMAIL);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        javaMailSender.send(mailMessage);
    }

    @Async
    public void sendEmailWithAttachment(String mail, String subject, String message, byte[] attachment, String attachmentName) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(mail);
        helper.setFrom(FROM_EMAIL);
        helper.setSubject(subject);
        helper.setText(message);

        MimeBodyPart textBodyPart = new MimeBodyPart();
        textBodyPart.setText(message);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(attachment, CONTENT_TYPE)));
        attachmentBodyPart.setFileName(attachmentName);

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        mimeMessage.setContent(multipart);

        javaMailSender.send(mimeMessage);
    }

    @Async
    public void sendHtmlEmail(User user, String subject) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(user.getUsername());
            helper.setFrom(FROM_EMAIL);
            helper.setSubject(subject);
            helper.setText(createHTML(user), true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    private String createHTML(User user){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Email Verification</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Arial', sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 30px auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #ffffff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h1 {\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "        p {\n" +
                "            color: #666666;\n" +
                "        }\n" +
                "        .verification-link {\n" +
                "            display: block;\n" +
                "            padding: 10px;\n" +
                "            margin: 20px 0;\n" +
                "            text-align: center;\n" +
                "            background-color: #007bff;\n" +
                "            color: #ffffff;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h1>Email Verification</h1>\n" +
                "        <p>Dear "+ user.getName() +",</p>\n" +
                "        <p>Thank you for choosing NFB Medical Supplies. To complete your registration, please click the link below to verify your email address:</p>\n" +
                "        \n" +
                "        <a href=\""+ buildServerUrl() +"/auth/activate/" +   user.getId() +"\" class=\"verification-link\">Verify Email Address</a>\n" +
                "\n" +
                "        <p>If you did not create an account with NFB Medical Supplies, you can safely ignore this email.</p>\n" +
                "\n" +
                "        <p>Best regards,<br>NFB Medical Supplies Team</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    private String buildServerUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}