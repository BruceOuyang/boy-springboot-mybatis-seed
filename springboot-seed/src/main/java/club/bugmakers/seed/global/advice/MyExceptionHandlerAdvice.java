package club.bugmakers.seed.global.advice;

import club.bugmakers.seed.bean.dto.base.RespDTO;
import club.bugmakers.seed.enums.error.ErrorCodeEnum;
import club.bugmakers.seed.exception.ServiceException;
import club.bugmakers.seed.utils.validate.ValidateParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandlerAdvice {

    /**
     * 处理参数检验异常
     */
    @ExceptionHandler(BindException.class)
    public <T> ResponseEntity<RespDTO<T>> handle(BindException e) {
        log.warn("参数检验发生异常，异常信息为：{}",e.toString(),e);
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String msg = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("、"));
        return ResponseEntity.ok(RespDTO.fail(msg));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespDTO> handle(MethodArgumentNotValidException ex) {
        log.warn("参数校验错误：{}",ex.toString(),ex);
        return ResponseEntity.ok(RespDTO.fail(ValidateParam.handleBindingResult(ex.getBindingResult())));
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<RespDTO> handle(ServiceException ex) {
        if (ex.getWithNotify()) {
            log.error("业务发生异常，异常信息为：{}", ex.toString(),ex);
        } else {
            log.warn("业务发生异常，异常信息为：{}", ex.toString(),ex);
        }
        return ResponseEntity.ok(RespDTO.fail(ex.getMessage()));
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RespDTO> handle(MissingServletRequestParameterException ex) {
        log.warn("业务发生异常，非法请求为：{}",ex.toString(),ex);
        return ResponseEntity.ok(RespDTO.fail(ErrorCodeEnum.ILLEGALITY_REQUEST.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @Order()
    public <T> ResponseEntity<RespDTO<T>> handle(Exception e) {
        log.error("发生未知异常，异常信息为：{}",e.toString(),e);
        return ResponseEntity.ok(RespDTO.fail(ErrorCodeEnum.SYSTEM_ERROR.getMessage()));
    }
}
