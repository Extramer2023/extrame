package top.extrame.common.tool.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.extrame.common.tool.pojo.RSAKeyPair;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * RSA工具类
 */
public class RSAUtils {

    private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
    private static final String ALGORITHM = "RSA";
    private static final int SIZE = 2048;
    private static final KeyPairGenerator keyPairGenerator;
    private static final KeyFactory rsaKeyFactory;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            rsaKeyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            log.error("RSA初始化失败");
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成rsa密钥对对象
     *
     * @return RSAKeyPair秘钥对象
     */
    public static RSAKeyPair generateRSAKeyPair() {
        return generateRSAKeyPair(SIZE);
    }

    /**
     * 生成RSA密钥对对象
     *
     * @param size 秘钥长度
     * @return RSA密钥对对象
     */
    public static RSAKeyPair generateRSAKeyPair(int size) {
        if (size < 1024) {
            size = SIZE;
        }
        keyPairGenerator.initialize(size);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new RSAKeyPair((RSAPublicKey) keyPair.getPublic(), (RSAPrivateKey) keyPair.getPrivate());
    }

    /**
     * 根据字节数组私钥生成RSAPrivateKey对象
     *
     * @param privateKey 字节数组私钥
     * @return RSAPrivateKey私钥对象
     * @throws InvalidKeySpecException 秘钥格式错误异常
     */
    public static RSAPrivateKey bytePrivateKey(byte[] privateKey) throws InvalidKeySpecException {
        return (RSAPrivateKey) rsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
    }

    /**
     * 根据字节数组公钥生成RSAPublicKey对象
     *
     * @param publicKey 字节数组公钥
     * @return RSAPublicKey公钥对象
     * @throws InvalidKeySpecException 秘钥格式错误异常
     */
    public static RSAPublicKey bytePublicKey(byte[] publicKey) throws InvalidKeySpecException {
        return (RSAPublicKey) rsaKeyFactory.generatePublic(new PKCS8EncodedKeySpec(publicKey));
    }

    /**
     * 根据RSA公钥、明文加密返回加密后的字节数组
     *
     * @param plaintext                                                         待加密明文
     * @param rsaPublicKey                                                      rsa公钥
     * @return                                                                  加密后的字节数组
     * @throws Exception                                                        加密失败异常
     */
    public static byte[] encrypt(byte[] plaintext, RSAPublicKey rsaPublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return cipher.doFinal(plaintext);
    }

    /**
     * Base64格式化rsa公钥加密
     *
     * @param plaintext                                                          待加密明文
     * @param rsaPublicKey                                                       rsa公钥
     * @return                                                                   base64处理后的加密文本
     * @throws Exception                                                         加密失败异常
     */
    public static String base64Encrypt(String plaintext, RSAPublicKey rsaPublicKey) throws Exception {
        return Base64.encodeBase64String(encrypt(plaintext.getBytes(StandardCharsets.UTF_8), rsaPublicKey));
    }

    /**
     * 根据密文、RSA私钥解密并返回明文字节数组
     *
     * @param ciphertext                                                          密文
     * @param rsaPrivateKey                                                       rsa私钥
     * @return                                                                    解密后的明文字节数组
     * @throws Exception                                                          解密失败异常
     */
    public static byte[] decrypt(byte[] ciphertext, RSAPrivateKey rsaPrivateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        return cipher.doFinal(ciphertext);
    }

    /**
     * Base64格式化rsa私钥解密
     *
     * @param ciphertext    密文
     * @param rsaPrivateKey rsa私钥
     * @return base64处理后的明文
     * @throws Exception 解密失败异常
     */
    public static String base64Decrypt(String ciphertext, RSAPrivateKey rsaPrivateKey) throws Exception {
        return new String(decrypt(Base64.decodeBase64(ciphertext), rsaPrivateKey));
    }
}
