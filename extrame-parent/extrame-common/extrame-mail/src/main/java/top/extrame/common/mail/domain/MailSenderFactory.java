package top.extrame.common.mail.domain;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import top.extrame.common.mail.domain.pojo.MailSender;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * The type MailTemplateFactory.
 *
 * @author Jx-zou
 */
public class MailSenderFactory {

    private final Properties properties;

    public MailSenderFactory(Properties properties) {
        this.properties = properties;
    }

    /**
     * 根据mail配置获取mail发送器
     *
     * @param mailProperties mail配置
     * @return mail发送器 java mail sender
     */
    public MailSender createMailSender(MailProperties mailProperties) throws UnsupportedEncodingException, MessagingException {
        return new MailSender(createSender(mailProperties));
    }

    /**
     * 根据mail配置获取mail发送器
     *
     * @param mailProperties mail配置
     * @return mail发送器 java mail sender
     */
    public MailSender createMailSender(MailProperties mailProperties, String personal) throws UnsupportedEncodingException, MessagingException {
        JavaMailSenderImpl sender = createSender(mailProperties);

        MimeMessage mimeMessage = sender.createMimeMessage();
        mimeMessage.setFrom(new InternetAddress(sender.getUsername(), personal, sender.getDefaultEncoding()));
        return new MailSender(sender, mimeMessage);
    }

    private JavaMailSenderImpl createSender(MailProperties mailProperties) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailProperties.getHost());
        sender.setPort(mailProperties.getPort());
        sender.setUsername(mailProperties.getUsername());
        sender.setPassword(mailProperties.getPassword());
        sender.setProtocol(mailProperties.getProtocol());
        sender.setDefaultEncoding(mailProperties.getDefaultEncoding().name());
        sender.setJavaMailProperties(this.properties);
        return sender;
    }
}
