package club.bugmakers.seed.utils.validate;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数校验
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
public class ValidateParam {

    /**
     * 处理 BindingResult 错误信息
     * @param result 错误结果集
     */
    public static String handleBindingResult(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            return allErrors.get(0).getDefaultMessage();
        }
        return "";
    }

}
