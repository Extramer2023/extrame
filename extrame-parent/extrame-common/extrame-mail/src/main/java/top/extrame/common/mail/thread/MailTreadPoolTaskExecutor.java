package top.extrame.common.mail.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import xyz.jxzou.extra.mail.config.MailConfiguration;
import xyz.jxzou.extra.mail.config.MailThreadPool;

/**
 * MailTreadPoolTaskExecutor
 *
 * @author Jx
 */
@Component("mailTreadPoolTaskExecutor")
public class MailTreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private static final long serialVersionUID = -7071021297991383051L;

    private static final String THREAD_NAME_PREFIX = "[Mail] mailTaskExecutor--";
    private MailThreadPool config;

    /**
     * 根据mail配置获取连接池配置对象
     *
     * @param mailProperties mail配置对象
     */
    @Autowired
    public MailTreadPoolTaskExecutor(MailConfiguration mailProperties) {
        this.config = mailProperties.getPool();
    }

    /**
     * Instantiates a new Mail tread pool task executor.
     */
    public MailTreadPoolTaskExecutor() {
        super.setCorePoolSize(config.getCorePoolSize());
        super.setMaxPoolSize(config.getMaxPoolSize());
        super.setQueueCapacity(config.getQueueCapacity());
        super.setKeepAliveSeconds(config.getKeepAliveSeconds());
        super.setThreadNamePrefix(THREAD_NAME_PREFIX);
        super.setWaitForTasksToCompleteOnShutdown(config.isWaitForTasksToCompleteOnShutdown());
        super.setAwaitTerminationMillis(config.getAwaitTerminationMillis());
    }
}
