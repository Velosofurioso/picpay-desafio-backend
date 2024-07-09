package com.lvb.challenge.picpay.PicpayBackendChallenge.service.email;

import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;

//TODO organizar melhor essa clase
//TODO criar os templates necessario para email em arquivos separados
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmailCode(final AccountBase accountBase, final long code) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        final Resource htmlResource = resourceLoader.getResource("classpath:" + "/email/welcome/email_confirm.html");

        String htmlContent = readContent (htmlResource.getInputStream());
        htmlContent = htmlContent.replace("{name}", accountBase.getFirstname());
        htmlContent = htmlContent.replace("{code}", String.valueOf(code));

        helper.setTo(accountBase.getEmail());
        helper.setSubject("Validar E-mail");
        helper.setText(htmlContent, true);

        // Add logo
        Resource logoResource = resourceLoader.getResource("classpath:" + "/email/welcome/resources/picpay-logo.png");
        helper.addInline("logoImg", logoResource);

        mailSender.send(message);
    }

    public void sendHtmlEmail() throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        //message.setFrom(new InternetAddress("sender@example.com"));
        //message.setRecipients(MimeMessage.RecipientType.TO, "l.brando815@gmail.com");
        //message.setSubject("Test email from Spring");



        /*String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";*/

        Resource htmlResource = resourceLoader.getResource("classpath:" + "/email/welcome/email_confirm.html");
        String htmlContent = readContent (htmlResource.getInputStream());

        htmlContent = htmlContent.replace("{name}", "Lucas Veloso");

        helper.setFrom("sender@example.com");
        helper.setTo("l.brando815@gmail.com");
        helper.setSubject("Test email from Spring");
        helper.setText(htmlContent, true); // Indica que o corpo do e-mail é HTML

        // Adiciona a imagem incorporada
        Resource logoResource = resourceLoader.getResource("classpath:" + "/email/welcome/resources/picpay-logo.png");
        helper.addInline("logoImg", logoResource);


        //message.setContent(htmlContent, "text/html; charset=utf-8");


        mailSender.send(message);
    }

    public String readContent(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /*public void sendEmailFromTemplate() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        // Read the HTML template into a String variable
        String htmlTemplate = readFile("template.html");

        // Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
        htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String body) throws MessagingException, IOException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);

        FileSystemResource file = new FileSystemResource(new File("attachment.jpg"));
        helper.addAttachment("attachment.jpg", file);

        mailSender.send(message);
    }*/


}
