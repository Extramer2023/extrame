package top.extrame.common.mail.config;

import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.util.List;

/**
 * The type MailGroup.
 *
 * @author Jx-zou
 */
public class MailGroup {

    private String personal;
    private Integer attempts;
    private List<MailProperties> sender;

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public List<MailProperties> getSender() {
        return sender;
    }

    public void setSender(List<MailProperties> sender) {
        this.sender = sender;
    }
}
