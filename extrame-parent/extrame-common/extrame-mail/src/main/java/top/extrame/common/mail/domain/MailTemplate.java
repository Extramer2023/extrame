package top.extrame.common.mail.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import top.extrame.common.mail.config.MailGroup;
import top.extrame.common.mail.domain.pojo.MailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * MailTemplate
 *
 * @author Jx-zou
 */
public class MailTemplate {

    private final Logger log = LoggerFactory.getLogger(MailTemplate.class);

    /**
     * mail发送器
     */
    private List<MailSender> senders;
    private String personal;
    private int attempts;

    /**
     * MailTemplate构造器
     *
     * @param properties mail配置对象
     */
    public MailTemplate(Properties properties, MailGroup mailGroup) {
        this.attempts = mailGroup.getAttempts();
        this.personal = mailGroup.getPersonal();
        this.senders = this.mailSenders(new MailSenderFactory(properties), mailGroup.getSender());
    }

    /**
     * 初始化sender
     * @param factory   sender工厂
     * @param groupMailProperties mailGroup配置
     * @return  mailSender列表
     */
    private List<MailSender> mailSenders(MailSenderFactory factory, List<MailProperties> groupMailProperties) {
        List<MailSender> mailSenders = new ArrayList<>();
        groupMailProperties.forEach(mail -> {
            try {
                mailSenders.add(factory.createMailSender(mail, this.personal));
            } catch (UnsupportedEncodingException | MessagingException e) {
                log.error("初始化MailTemplate失败", e);
            }
        });
        return mailSenders;
    }

    /**
     * 轮询发送
     *
     * @param mimeMessages MimeMessage集合
     */
    @Async("mailTreadPoolTaskExecutor")
    public void send(MimeMessage... mimeMessages) {
        for (MailSender sender : senders) {
            if (sender.isEnabled(attempts)) {
                try {
                    sender.send(mimeMessages);
                    return;
                } catch (MessagingException | MailException e) {
                    sender.failed();
                    log.info("[" + sender.getFrom() + "]发送错误.");
                }
            }
        }
    }

    public List<MailSender> getSenders() {
        return senders;
    }

    public void setSenders(List<MailSender> senders) {
        this.senders = senders;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
        this.senders.forEach(sender -> {
            try {
                sender.setPersonal(personal);
            } catch (UnsupportedEncodingException | MessagingException e) {
                log.error("修改personal失败.");
                throw new RuntimeException(e);
            }
        });
    }
}
