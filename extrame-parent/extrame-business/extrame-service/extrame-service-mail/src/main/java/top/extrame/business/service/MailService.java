package top.extrame.business.service;

public interface MailService {

    void sendCaptcha(String mail, String clientId) throws Exception;
}
