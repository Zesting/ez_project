package Ezen.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    private String resourcePath = "/upload/**"; // view 에서 접근할 경로
    // private String savePath = System.getProperty("user.dir")+"/src/main/resources/static/images/"; // 실제 파일 저장 경로(win)
    private String savePath = "file:/C:/Users/abcmi/OneDrive/\uBC14\uD0D5 \uD654\uBA74/fp/ez_project/src/main/resources/static/images"; // 실제 파일 저장 경로(win)

    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(resourcePath)
            .addResourceLocations(savePath);
    }
    
}
