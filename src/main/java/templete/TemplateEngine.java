package templete;

import entyty.Product;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;


public class TemplateEngine {
    private final Configuration conf;

    private TemplateEngine() {
        this.conf = new Configuration(Configuration.VERSION_2_3_31);
        this.conf.setClassLoaderForTemplateLoading(TemplateEngine.class.getClassLoader(), "/templates");
        this.conf.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        this.conf.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static TemplateEngine folder() {return new TemplateEngine();}


    public void render(String template, Map<String, Object> data, HttpServletResponse resp) {
        try (PrintWriter w = resp.getWriter()) {
            conf.getTemplate(template).process(data, w);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("Freemarker error", e);
        }
    }

}
