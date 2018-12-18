package club.bugmakers.seed.utils;

import club.bugmakers.seed.bean.bo.user.CrmUserBO;
import club.bugmakers.seed.client.CrmClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户信息工具类
 * @Author: Bruce
 * @Date: 2018/12/14 18:46
 * @Version 1.0
 */
@Component
public class UserContextUtil {

	private static final String SYS_USER_NAME = "系统";
	private static final Long SYS_USER_ID = -1L;

	@Resource
	private CrmClient crmClient;

	private static ThreadLocal<CrmUserBO> threadLocal = new ThreadLocal<>();

	/**
	 * 获取当前线程用户信息
	 *
	 * @return
	 */
	public static CrmUserBO getUser() {
		CrmUserBO crmUserBO = threadLocal.get();
		if (crmUserBO == null){
			crmUserBO = new CrmUserBO();
			crmUserBO.setId(SYS_USER_ID);
			crmUserBO.setName(SYS_USER_NAME);
		}
		return crmUserBO;
	}

	public boolean setUser(Long userId){
		CrmUserBO syUser  = crmClient.getUserById(userId).getData();
		threadLocal.set(syUser);
		return true;
	}

}
