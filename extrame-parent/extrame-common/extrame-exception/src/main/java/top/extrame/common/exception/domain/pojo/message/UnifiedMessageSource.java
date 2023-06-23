package top.extrame.common.exception.domain.pojo.message;

import org.springframework.boot.autoconfigure.context.MessageSourceProperties;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * UnifiedMessageSource
 *
 * @author jx
 */
@Component
public class UnifiedMessageSource extends ReloadableResourceBundleMessageSource {

    private static final Locale defaultLocale = Locale.getDefault();

    private static final String codePrefix = "response.";

    /**
     * Instantiates a new Unified message source.
     *
     * @param messageSourceProperties the message source properties
     */
    public UnifiedMessageSource(MessageSourceProperties messageSourceProperties){
        super.setBasename(messageSourceProperties.getBasename());
        super.setDefaultEncoding(messageSourceProperties.getEncoding().name());
        super.setDefaultLocale(defaultLocale);
    }

    /**
     * 根据状态码、参数获取消息
     *
     * @param code 状态码
     * @param args 参数
     * @return 消息
     */
    public String getMessage(Integer code, Object[] args){
        return super.getMessage(codePrefix + code, args, defaultLocale);
    }

    /**
     * 根据状态码获取消息
     *
     * @param code 状态码
     * @return 消息
     */
    public String getMessage(Integer code){
        return super.getMessage(codePrefix + code, null, defaultLocale);
    }
}
