package top.extrame.common.mail.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import top.extrame.common.mail.config.MailConfiguration;
import top.extrame.common.mail.config.MailGroup;

import java.util.Map;

/**
 * Mail管理类
 */
@Component
public class MailManager {

    private static final String MAIL_TEMPLATE_SUFFIX = "MailTemplate";

    private final MailConfiguration mailConfiguration;
    private final DefaultListableBeanFactory beanFactory;

    /**
     * 初始化mail管理类
     *
     * @param mailConfiguration mail配置
     */
    @Autowired
    public MailManager(MailConfiguration mailConfiguration, ConfigurableApplicationContext applicationContext) {
        this.mailConfiguration = mailConfiguration;
        this.beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        this.processMailTemplateRegistry();
    }

    private void processMailTemplateRegistry() {
        this.add(mailConfiguration.getGroup());
    }

    public void add(Map<String, MailGroup> mailGroupMap) {
        //注入mailTemplate
        mailGroupMap.forEach((name, mailGroup) -> {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MailTemplate.class);
            builder.addConstructorArgValue(mailConfiguration.getProperties());
            builder.addConstructorArgValue(mailGroup);
            beanFactory.registerBeanDefinition(name + MAIL_TEMPLATE_SUFFIX, builder.getBeanDefinition());
        });
    }

    public void remove(String name) {
        this.beanFactory.removeBeanDefinition(name + MAIL_TEMPLATE_SUFFIX);
    }

    public MailTemplate getTemplate(String name) {
        return this.beanFactory.getBean(name + MAIL_TEMPLATE_SUFFIX, MailTemplate.class);
    }

    public Map<String, MailGroup> getMailGroups() {
        return this.mailConfiguration.getGroup();
    }
}
