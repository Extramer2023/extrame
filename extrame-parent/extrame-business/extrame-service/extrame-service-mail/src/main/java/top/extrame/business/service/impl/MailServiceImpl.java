package top.extrame.business.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.extrame.business.dao.TemplateDao;
import top.extrame.business.enums.MailType;
import top.extrame.business.service.MailService;
import top.extrame.common.exception.enums.BusinessResponse;
import top.extrame.common.mail.domain.MailMessageBuilder;
import top.extrame.common.mail.domain.MailTemplate;
import top.extrame.common.tool.util.BeetlUtils;
import top.extrame.common.tool.util.NanoUtils;
import top.extrame.common.tool.util.RSAUtils;
import top.extrame.core.config.CaptchaProperties;
import top.extrame.core.domain.RedisContent;
import top.extrame.core.domain.SafetyManager;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private static final String CAPTCHA_MAIL_SUBJECT = "Extra captcha";

    private final CaptchaProperties captchaProperties;
    private final TemplateDao templateDao;
    private final SafetyManager safetyManager;

    @Resource
    private StringRedisTemplate captchaRedisTemplate;
    @Resource
    private MailTemplate businessMailTemplate;

    @Autowired
    public MailServiceImpl(CaptchaProperties captchaProperties, TemplateDao templateDao, SafetyManager safetyManager) {
        this.captchaProperties = captchaProperties;
        this.templateDao = templateDao;
        this.safetyManager = safetyManager;
    }

    @Override
    public void sendCaptcha(String mail, String clientId) throws Exception {
        //解密mail并校验
        String plaintMail = RSAUtils.base64Decrypt(mail, safetyManager.getRsaPrivetKey());
        BusinessResponse.MAIL_BOX_ERROR.validateMail(plaintMail);

        //校验是否已发送
        String captchaName = RedisContent.PREFIX_CAPTCHA + clientId;
        if (Boolean.TRUE.equals(captchaRedisTemplate.hasKey(captchaName))) {
            //校验是否过期
            Long expire = captchaRedisTemplate.getExpire(captchaName);
            BusinessResponse.REDIS_EXPIRE_ERROR.isNull(expire);
            if (expire < captchaProperties.getMail().getExpire()) {
                throw BusinessResponse.MAIL_RESEND_ERROR.newException();
            }
        }

        //数据库提取模板内容
        String captchaTemplate = templateDao.findContentByType(MailType.CAPTCHA.getId());
        if (StringUtils.isBlank(captchaTemplate)) {
            log.error("未找到模板");
            return;
        }

        //生成默认随机验证码
        String code = NanoUtils.createNumeric(captchaProperties.getMail().getSize());

        //使用模板生成内容发送
        Map<String, String> params = new HashMap<>(1);
        params.put("code", code);


        MimeMessage mimeMessage = MailMessageBuilder.builder()
                .text(BeetlUtils.process(captchaTemplate, params))
                .subject(CAPTCHA_MAIL_SUBJECT)
                .to(mail)
                .build();
        businessMailTemplate.send(mimeMessage);

        //存入captcha redis库, 并设置过期时间
        captchaRedisTemplate.opsForValue().set(captchaName, code);
        captchaRedisTemplate.expire(captchaName, captchaProperties.getMail().getExpire(), TimeUnit.SECONDS);
    }
}
