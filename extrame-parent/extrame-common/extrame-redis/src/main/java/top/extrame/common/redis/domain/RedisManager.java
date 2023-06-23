package top.extrame.common.redis.domain;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.extrame.common.redis.config.RedisProperties;

import java.time.Duration;
import java.util.Objects;

/**
 * The type Redis template manager.
 */
@Component
public class RedisManager {

    private static final String REDIS_TEMPLATE_SUFFIX = "RedisTemplate";

    /**
     * The Redis properties.
     */
    private final RedisProperties redisProperties;

    private final GenericObjectPoolConfig<Object> genericObjectPoolConfig;

    /**
     * Instantiates a new Redis template manager.
     *
     * @param redisProperties the redis properties
     */
    @Autowired
    public RedisManager(RedisProperties redisProperties, ConfigurableApplicationContext applicationContext) {
        this.redisProperties = redisProperties;
        this.genericObjectPoolConfig = genericObjectPoolConfig();
        this.processTemplateRegistry(applicationContext);
    }
    /**
     * 初始化RedisManager
     */
    private void processTemplateRegistry(ConfigurableApplicationContext applicationContext) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getBeanFactory();

        redisProperties.getDatabases().forEach((name, index) -> {
            if (!Objects.isNull(index)){
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(StringRedisTemplate.class);
                builder.addConstructorArgValue(redisConnectionFactory(index));
                builder.setInitMethodName("afterPropertiesSet");
                BeanDefinition beanDefinition = builder.getBeanDefinition();
                registry.registerBeanDefinition(name + REDIS_TEMPLATE_SUFFIX, beanDefinition);
            }
        });
    }

    /**
     * 获取redis连接工厂
     *
     * @param database Redis库编号
     * @return redis连接工厂 redis connection factory
     */
    protected LettuceConnectionFactory redisConnectionFactory(Integer database) {
        LettucePoolingClientConfiguration poolConfig = LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration(database), poolConfig);
        factory.afterPropertiesSet();
        return factory;
    }

    /**
     * 获取通用线程池配置对象,此类为commons-pool2中一个类
     *
     * @return 通用线程池配置对象
     */
    protected GenericObjectPoolConfig<Object> genericObjectPoolConfig() {
        GenericObjectPoolConfig<Object> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        RedisProperties.Pool pool = redisProperties.getPool();
        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(pool.getMinIdle());
        genericObjectPoolConfig.setMaxTotal(pool.getMaxActive());
        genericObjectPoolConfig.setMaxWait(Duration.ofMillis(pool.getMaxWait()));
        return genericObjectPoolConfig;
    }

    /**
     * 获取单机redis配置
     *
     * @param database 数据编号
     * @return redis单机配置
     */
    protected RedisStandaloneConfiguration redisStandaloneConfiguration(Integer database) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        return redisStandaloneConfiguration;
    }
}
