package club.bugmakers.seed.manager;

import club.bugmakers.seed.bean.dto.base.RespDTO;
import club.bugmakers.seed.bean.dto.external.ArgumentReqDTO;
import club.bugmakers.seed.bean.dto.external.ArgumentRespDTO;
import club.bugmakers.seed.client.CasClient;
import club.bugmakers.seed.enums.error.ErrorCodeEnum;
import club.bugmakers.seed.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 本项目调用其他项目接口的处理类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Service
@Slf4j
public class CasManager {

    @Resource
    private CasClient casClient;

    public ArgumentRespDTO getArgumentById(ArgumentReqDTO argumentReqDTO){
        RespDTO<ArgumentRespDTO> result = casClient.getArgumentById(argumentReqDTO);
        if (result.fail()){
            log.error("参数ID：{},获取参数信息错误：{}",argumentReqDTO,result);
            throw new ServiceException(result.getMsg());
        }
        if (null == result.getData()){
            throw new ServiceException(ErrorCodeEnum.ARGUMENT_RESP_NOT_EXIST.getMessage());
        }
        return result.getData();
    }
}
