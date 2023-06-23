package top.extrame.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "extra.safety.captcha")
public class CaptchaProperties {

    private Code mail;
    private Code sms;

    public Code getSms() {
        return sms;
    }

    public void setSms(Code sms) {
        this.sms = sms;
    }

    public Code getMail() {
        return mail;
    }

    public void setMail(Code mail) {
        this.mail = mail;
    }

    public static class Code {
        private Integer size;
        private Integer expire;

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public Integer getExpire() {
            return expire;
        }

        public void setExpire(Integer expire) {
            this.expire = expire;
        }
    }
}
