package top.extrame.common.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * The type MailProperties.
 *
 * @author Jx-zou
 */
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailConfiguration {

    private Map<String, MailGroup> group;
    private Map<String, String> properties = new HashMap<>();
    private MailThreadPool pool;

    public Map<String, MailGroup> getGroup() {
        return group;
    }

    public void setGroup(Map<String, MailGroup> group) {
        this.group = group;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public MailThreadPool getPool() {
        return pool;
    }

    public void setPool(MailThreadPool pool) {
        this.pool = pool;
    }
}
