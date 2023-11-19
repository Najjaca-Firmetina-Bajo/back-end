package com.nfb.modules.stakeholders.core.usecases;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class EmailService {
    private final MailjetClient client = new MailjetClient("e6484e76886fcfce8389e075b46a012e","e9737c93bad248f1bc2648eea8586117" , new ClientOptions("v3.1"));
    public String createHTML(String verificationCodeLink){
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
                "        <p>Dear [Customer Name],</p>\n" +
                "        <p>Thank you for choosing NFB Medical Supplies. To complete your registration, please click the link below to verify your email address:</p>\n" +
                "        \n" +
                "        <a href=\""+ verificationCodeLink +"\" class=\"verification-link\">Verify Email Address</a>\n" +
                "\n" +
                "        <p>If you did not create an account with NFB Medical Supplies, you can safely ignore this email.</p>\n" +
                "\n" +
                "        <p>Best regards,<br>NFB Medical Supplies Team</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    public void sendRegistrationEmail(String to) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = this.client;
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "medicinskaopremagas@outlook.com")
                                        .put("Name", "Dragomir"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", to)))
                                .put(Emailv31.Message.SUBJECT, "Authentication")
                                .put(Emailv31.Message.TEXTPART, "Greetings from NFB.")
                                .put(Emailv31.Message.HTMLPART, createHTML("http://localhost:4200/") )
                        ));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }


}