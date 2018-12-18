package club.bugmakers;

import club.bugmakers.service.CodeGenService;
import club.bugmakers.util.SpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@SpringBootApplication
public class CodeGenAppBootstrap {

    public static void main(String[] args) throws Exception {
        SpringBeanUtil.context = SpringApplication.run(CodeGenAppBootstrap.class, args);
        CodeGenService codeGenService = SpringBeanUtil.getBean(CodeGenService.class);
        codeGenService.genCode();
    }
}
