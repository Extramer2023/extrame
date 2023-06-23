package top.extrame.common.mail.domain;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;
import top.extrame.common.tool.util.FileUtils;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * MailMessageBuilder
 *
 * @author Jx-zou
 */
public class MailMessageBuilder {
    
    private final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(new MimeMessage((Session) null), true, StandardCharsets.UTF_8.name());
    private static MailMessageBuilder builder;
    
    private MailMessageBuilder() throws MessagingException {}

    public static MailMessageBuilder builder() throws MessagingException {
        if (Objects.isNull(builder)) {
            builder = new MailMessageBuilder();
            return builder;
        }
        return builder;
    }
    
    public MailMessageBuilder to(String... to) throws MessagingException {
        this.mimeMessageHelper.setTo(to);
        return this;
    }

    public MailMessageBuilder replyTo(String replyTo, String personal) throws MessagingException, UnsupportedEncodingException {
        this.mimeMessageHelper.setReplyTo(replyTo, personal);
        return this;
    }

    public MailMessageBuilder replyTo(String replyTo) throws MessagingException {
        this.mimeMessageHelper.setReplyTo(replyTo);
        return this;
    }

    public MailMessageBuilder bcc(String... bcc) throws MessagingException {
        this.mimeMessageHelper.setBcc(bcc);
        return this;
    }
    
    public MailMessageBuilder cc(String... cc) throws MessagingException {
        this.mimeMessageHelper.setCc(cc);
        return this;
    }
    
    public MailMessageBuilder subject(String subject) throws MessagingException {
        this.mimeMessageHelper.setSubject(subject);
        return this;
    }
    
    public MailMessageBuilder text(String text, boolean isHtml) throws MessagingException {
        this.mimeMessageHelper.setText(text, isHtml);
        return this;
    }

    public MailMessageBuilder text(String text) throws MessagingException {
        this.mimeMessageHelper.setText(text, true);
        return this;
    }

    public MailMessageBuilder multipartFile(MultipartFile... files) throws MessagingException {
        if (files != null) {
            for (MultipartFile file : files) {
                if (null != file && !file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    if (null != originalFilename && !originalFilename.isEmpty()) {
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length() - 1);
                        this.mimeMessageHelper.addAttachment(FileUtils.randomFilename(suffix), file);
                    }
                }
            }
        }
        return this;
    }
    
    public MimeMessage build() {
        return this.mimeMessageHelper.getMimeMessage();
    }
}
