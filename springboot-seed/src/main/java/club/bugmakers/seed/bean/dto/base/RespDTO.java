package club.bugmakers.seed.bean.dto.base;


import cn.victorplus.fps.enums.base.RespStatus;
import lombok.Data;
import lombok.ToString;

/**
 * 相应信息 DTO
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Data
public class RespDTO<T> {

    private int status;
    private String msg;
    private T data;

    public RespDTO() {
    }

    public static <T> RespDTO<T> success() {
        RespDTO resp = new RespDTO();
        resp.status = RespStatus.SUCCESS.getCode();
        resp.msg = RespStatus.SUCCESS.getDesc();
        return resp;
    }

    public static <T> RespDTO<T> success(String msg, T data) {
        RespDTO resp = new RespDTO();
        resp.data = data;
        resp.status = RespStatus.SUCCESS.getCode();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> success(T data) {
        RespDTO resp = new RespDTO();
        resp.data = data;
        resp.status = RespStatus.SUCCESS.getCode();
        resp.msg = RespStatus.SUCCESS.getDesc();
        return resp;
    }

    public static <T> RespDTO<T> fail(String msg) {
        return fail(msg, null);
    }

    public static <T> RespDTO<T> fail(String msg, T data) {
        RespDTO resp = new RespDTO();
        resp.data = data;
        resp.status = RespStatus.FAIL.getCode();
        resp.msg = msg;
        return resp;
    }
    public static <T> RespDTO<T> error() {
        return error(RespStatus.SERVER_ERROR.getDesc(),null);
    }

    public static <T> RespDTO<T> error(String msg) {
        return error(msg, null);
    }

    public static <T> RespDTO<T> error(String msg, T data) {
        RespDTO resp = new RespDTO();
        resp.data = data;
        resp.status = RespStatus.SERVER_ERROR.getCode();
        resp.msg = msg;
        return resp;
    }

    public static <T> RespDTO<T> badRequest() {
        return badRequest(RespStatus.BAD_REQUEST.getDesc(), (T)null);
    }

    public static <T> RespDTO<T> badRequest(String msg) {
        return badRequest(msg, (T)null);
    }

    public static <T> RespDTO<T> badRequest(String msg, T data) {
        RespDTO<T> resp = new RespDTO();
        resp.data = data;
        resp.status = RespStatus.BAD_REQUEST.getCode();
        resp.msg = msg;
        return resp;
    }

    public boolean fail(){
        if (RespStatus.SUCCESS.getCode() == status){
            return false;
        }
        return true;
    }

}
