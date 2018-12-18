package club.bugmakers.seed.client;

import club.bugmakers.seed.bean.dto.base.RespDTO;
import club.bugmakers.seed.bean.dto.external.ArgumentReqDTO;
import club.bugmakers.seed.bean.dto.external.ArgumentRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 服务名称的方式调用（注册中心寻址模式）
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@FeignClient(name="ms-cas")
public interface CasClient {

    /**
     * 根据参数ID获取参数对象
     * @param argumentReqDTO 参数请求对象，ID
     * @return 参数响应对象
     */
    @PostMapping("/argControl/queryById")
    RespDTO<ArgumentRespDTO> getArgumentById(@RequestBody ArgumentReqDTO argumentReqDTO);
}
