package club.bugmakers.seed.service.user;

import club.bugmakers.seed.bean.po.user.UserInfoPO;
import club.bugmakers.seed.bean.dto.user.UserInfoDTO;
import club.bugmakers.seed.bean.bo.user.UserInfoBoPage;
import club.bugmakers.seed.bean.bo.user.UserInfoBO;
import club.bugmakers.seed.mapper.user.UserInfoMapper;

import club.bugmakers.seed.bean.dto.base.PageResultDTO;
import club.bugmakers.seed.bean.dto.base.RespDTO;

import club.bugmakers.seed.exception.ServiceException;
import club.bugmakers.seed.utils.UserContextUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 用户信息表
 * @author BOY_Code_Generator
 * @date 2019-01-09 15:15:00
 */
@Service
@Slf4j
public class UserInfoService {

    @Resource
    private UserInfoMapper mapper;

    public RespDTO<String> add(UserInfoBO bo) {
        UserInfoPO po = new UserInfoPO();
        BeanUtils.copyProperties(bo, po);
        int i = mapper.add(po);

        // set createBy & updateBy
        po.setCreateBy(UserContextUtil.getUser().getName());
        po.setUpdateBy(UserContextUtil.getUser().getName());

        // todo save an op-log

        log.info("新增费用项成功，受影响行数：{}", i);
        return RespDTO.success();
    }

    public RespDTO<String> remove(Integer id) {
        UserInfoPO existsPO = mapper.get(id);
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }

        // todo save an op-log

        int i = mapper.remove(id, UserContextUtil.getUser().getName());

        log.info("删除费用项成功，受影响行数：{}", i);
        return RespDTO.success();
    }

    public RespDTO<String> update(UserInfoBO bo) {
        UserInfoPO existsPO = mapper.get(bo.getId());
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }
        UserInfoPO updatePO = new UserInfoPO();
        BeanUtils.copyProperties(bo, updatePO);

        // set updateBy
        updatePO.setUpdateBy(UserContextUtil.getUser().getName());

        // todo save an op-log

        int i = mapper.updateIgnoreNull(updatePO);

        log.info("删除费用项成功，受影响行数：{}", i);
        return RespDTO.success();
    }

    public RespDTO<UserInfoDTO> get(Integer id) {
        UserInfoPO existsPO = mapper.get(id);
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }
        UserInfoDTO dto = new UserInfoDTO();
        BeanUtils.copyProperties(existsPO, dto);
        return RespDTO.success(dto);
    }

    public RespDTO<PageResultDTO<UserInfoDTO>> listByPage(UserInfoBoPage bo) {

        PageHelper.startPage(bo.getPageNo(), bo.getPageSize(), bo.getSort());

        List<UserInfoPO> poList = mapper.list();

        List<UserInfoDTO> dtoList = new ArrayList<>();

        Optional.ofNullable(poList).orElse(new ArrayList<>()).stream().forEach(po->{
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(po, dto);
            dtoList.add(dto);
        });
        PageInfo pageInfo = new PageInfo(poList);
        PageResultDTO<UserInfoDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setData(dtoList);
        pageResultDTO.setPageNo(pageInfo.getPageNum());
        pageResultDTO.setPageSize(pageInfo.getPageSize());
        pageResultDTO.setTotalCount(pageInfo.getTotal());
        return RespDTO.success(pageResultDTO);
    }
}