package club.bugmakers.seed.global.interceptor;

import club.bugmakers.seed.utils.SpringBeanUtil;
import club.bugmakers.seed.utils.UserContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String USER_ID_ATTR ="x-victor-plus-userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 此处的处理，根据自己的架构做调整，种子项目是在请求头信息中获取用户id的
        try {
            String userId = request.getHeader(USER_ID_ATTR);
            if (StringUtils.isNotBlank(userId)){
                UserContextUtil userContextUtil = SpringBeanUtil.getBean(UserContextUtil.class);
                userContextUtil.setUser(Long.parseLong(userId));
            }
        } catch (NumberFormatException e) {
            log.error("获取用户信息出错！:{}",e.toString(),e);
        }

        //此处添加拦截器信息
        return super.preHandle(request, response, handler);
    }
}
