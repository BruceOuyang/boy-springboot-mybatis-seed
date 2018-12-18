package club.bugmakers.seed;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 * @Author Bruce
 * @Date 10:15 2018/12/18
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"club.bugmakers.seed"})
@EnableTransactionManagement
@MapperScan("club.bugmakers.seed.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class SeedAppBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SeedAppBootstrap.class,args);
    }
}
