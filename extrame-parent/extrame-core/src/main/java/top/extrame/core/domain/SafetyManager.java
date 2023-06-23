package top.extrame.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.extrame.common.tool.pojo.RSAKeyPair;
import top.extrame.common.tool.util.RSAUtils;
import top.extrame.core.config.RsaProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class SafetyManager {

    private final RSAKeyPair rsaKeyPair;

    @Autowired
    public SafetyManager(RsaProperties rsaProperties) {
        rsaKeyPair = RSAUtils.generateRSAKeyPair(rsaProperties.getSize());
    }

    public final RSAPublicKey getRsaPublicKey() {
        return this.rsaKeyPair.getPublicKey();
    }
    public final String getBase64RsaPublicKey() {
        return this.rsaKeyPair.getBase64PublicKey();
    }
    public final RSAPrivateKey getRsaPrivetKey() {
        return this.rsaKeyPair.getPrivateKey();
    }
    public final String  getBase64RsaPrivetKey() {
        return this.rsaKeyPair.getBase64PrivateKey();
    }
}
