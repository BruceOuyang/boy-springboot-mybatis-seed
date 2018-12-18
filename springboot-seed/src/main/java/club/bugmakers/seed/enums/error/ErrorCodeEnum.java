package club.bugmakers.seed.enums.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 所有错误码都在此类中定义，如果太多则建议拆成多个类
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常！"),
    ILLEGALITY_REQUEST("ILLEGALITY_REQUEST", "非法请求！"),


    //参数不存在
    ARGUMENT_REQ_NOT_EXIST("ARGUMENT_REQ_NOT_EXIST", "请求参数信息不存在！"),
    ARGUMENT_RESP_NOT_EXIST("ARGUMENT_RESP_NOT_EXIST", "响应参数信息不存在！"),

    //
    ;
    String code;
    String message;

}
