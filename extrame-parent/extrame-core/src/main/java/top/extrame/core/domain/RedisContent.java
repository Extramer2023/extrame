package top.extrame.core.domain;

/**
 * The type RedisContent.
 *
 * @author Jx-zou
 */
public interface RedisContent {

    /**
     * redis 前缀
     */
    String PREFIX_CAPTCHA = "EXTRA-CAPTCHA-";
    String PREFIX_CLIENT = "EXTRA-CLIENT-";
    String PREFIX_USER = "EXTRA-USER-";

    /**
     * redis 全名
     */
    String FULL_ARTICLE_PAGE = "EXTRA-ARTICLE";
    String FULL_ARTICLE_TOTAL = "ARTICLE-TOTAL";
}
