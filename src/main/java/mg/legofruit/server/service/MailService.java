package mg.legofruit.server.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;

    public void send(String to,String htmlTemplate, String subject) {

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("no-reply.legofruit@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("no-reply.legofruit@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlTemplate, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        javaMailSender.send(message);
    }
}
