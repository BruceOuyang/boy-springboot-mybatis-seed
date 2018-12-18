package club.bugmakers.seed.mapper.user;

import club.bugmakers.seed.bean.po.user.UserInfoPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表
 * @author BOY_Code_Generator
 * @date 2018-12-18 13:02:11
 */
public interface UserInfoMapper {

    UserInfoPO get(Integer id);

    int add(UserInfoPO t);

    int remove(@Param("id") Integer id, @Param("opUser") String opUser);

    int update(UserInfoPO t);

    int updateIgnoreNull(UserInfoPO t);

    List<UserInfoPO> list();
}