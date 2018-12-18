package club.bugmakers.seed.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum RespStatus {

    SUCCESS(0, ""),
    FAIL(1, "操作失败"),
    STRONG_MSG(2, "操作成功"),
    MOVED_TEMPORARILY(302, "临时跳转"),
    BAD_REQUEST(400, "参数异常"),
    FORBIDDEN(403, "没有访问权限"),
    PAGE_NOT_FOUND(404, "页面不存在"),
    SERVER_ERROR(500, "服务器开小差"),
    NEED_LOGIN(600, "请先登录"),
    KICK_OFF(601, "您的账号已经在其他设备登录")

    //
    ;

    private int code;
    private String desc;

}
