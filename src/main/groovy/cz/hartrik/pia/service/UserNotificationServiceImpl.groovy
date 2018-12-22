package cz.hartrik.pia.service

import cz.hartrik.pia.model.User
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig

import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 *
 * @version 2018-12-22
 * @author Patrik Harag
 */
@Service
class UserNotificationServiceImpl implements UserNotificationService {

    private static final def LOGGER = LoggerFactory.getLogger(UserNotificationServiceImpl)

    @Autowired
    private FreeMarkerConfig freeMarkerConfig

    @Override
    void onUserCreated(User newUser, String rawPassword) {
        // for testing purposes
        LOGGER.info("User created: login=${newUser.login} password=${rawPassword}")

        def message = formatMessage("email-welcome.ftl",
                newUser.properties + [rawPassword: rawPassword])

        sendMail(newUser.email, "JavaBank - account created", message)
    }

    private void sendMail(String email, String subject, String htmlContent) {
        Properties props = new Properties()
        System.getenv()
                .findAll { it.key.startsWith('mail.smtp.') }
                .findAll { !it.key.endsWithAny('.user', ".pass") }
                .each { props.put(it.key, it.value) }

        if (props.isEmpty()) {
            LOGGER.error('SMTP server not configured!')
            return
        }
        LOGGER.info("Sending email to $email, config: $props")

        def from = System.getenv("mail.smtp.user")
        def pass = System.getenv("mail.smtp.pass")

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass)
            }
        })

        Message message = new MimeMessage(session)
        message.setFrom(new InternetAddress(from))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email))
        message.setSubject(subject)
        message.setContent(htmlContent, "text/html")

        Transport.send(message)

        LOGGER.info("Email sent successfully")
    }

    private String formatMessage(String templateName, Map parameters) {
        Configuration cfg = new Configuration()
        cfg.setClassForTemplateLoading(getClass(), "/cz/hartrik/pia/service/")
        cfg.setDefaultEncoding("UTF-8")
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER)

        Template template = cfg.getTemplate(templateName)

        def writer = new StringWriter()
        template.process(parameters, writer)
        writer.toString()
    }

}
