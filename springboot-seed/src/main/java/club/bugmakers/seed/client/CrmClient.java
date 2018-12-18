package club.bugmakers.seed.client;

import club.bugmakers.seed.bean.bo.user.CrmUserBO;
import club.bugmakers.seed.bean.dto.base.RespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务地址的方式调用
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@FeignClient(name="ms-user", url = "${boss.user.url}")
public interface CrmClient {

    /**
     * 根据Id查询用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/user/getById")
    RespDTO<CrmUserBO> getUserById(@RequestParam("uid") Long userId);
}
