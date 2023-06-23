package top.extrame.common.tool.util;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class BeetlUtils {

    private static final Logger log = LoggerFactory.getLogger(BeetlUtils.class);

    private static final GroupTemplate groupTemplate;

    static {
        try {
            StringTemplateResourceLoader stringTemplateResourceLoader = new StringTemplateResourceLoader();
            Configuration configuration = Configuration.defaultConfiguration();
            groupTemplate = new GroupTemplate(stringTemplateResourceLoader, configuration);
        } catch (IOException e) {
            log.error("初始化Beetl失败");
            throw new RuntimeException(e);
        }
    }

    public static String process(String templateContent, Map<String, String> params) {
        Template template = groupTemplate.getTemplate(templateContent);
        template.binding(params);
        return template.render();
    }
}
