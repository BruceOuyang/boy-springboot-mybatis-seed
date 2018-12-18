package ${cfg.beanServicePackage};

import ${cfg.beanPoPackage}.${table.beanName}PO;
import ${cfg.beanDtoPackage}.${table.beanName}DTO;
import ${cfg.beanBoPackage}.${table.beanName}BoPage;
import ${cfg.beanBoPackage}.${table.beanName}BO;
import ${cfg.beanMapperPackage}.${table.beanName}Mapper;

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
 * ${table.tableComment}
 * @author BOY_Code_Generator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
@Slf4j
public class ${table.beanName}Service {

    @Resource
    private ${table.beanName}Mapper mapper;

    public RespDTO<String> add(${table.beanName}BO bo) {
        ${table.beanName}PO po = new ${table.beanName}PO();
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
        ${table.beanName}PO existsPO = mapper.get(id);
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }

        // todo save an op-log

        int i = mapper.remove(id, UserContextUtil.getUser().getName());

        log.info("删除费用项成功，受影响行数：{}", i);
        return RespDTO.success();
    }

    public RespDTO<String> update(${table.beanName}BO bo) {
        ${table.beanName}PO existsPO = mapper.get(bo.getId());
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }
        ${table.beanName}PO updatePO = new ${table.beanName}PO();
        BeanUtils.copyProperties(bo, updatePO);

        // set updateBy
        updatePO.setUpdateBy(UserContextUtil.getUser().getName());

        // todo save an op-log

        int i = mapper.updateIgnoreNull(updatePO);

        log.info("删除费用项成功，受影响行数：{}", i);
        return RespDTO.success();
    }

    public RespDTO<${table.beanName}DTO> get(Integer id) {
        ${table.beanName}PO existsPO = mapper.get(id);
        if (null == existsPO) {
            throw new ServiceException("参数有误-id对应数据不存在");
        }
        ${table.beanName}DTO dto = new ${table.beanName}DTO();
        BeanUtils.copyProperties(existsPO, dto);
        return RespDTO.success(dto);
    }

    public RespDTO<PageResultDTO<${table.beanName}DTO>> listByPage(${table.beanName}BoPage bo) {

        PageHelper.startPage(bo.getPageNo(), bo.getPageSize(), bo.getSort());

        List<${table.beanName}PO> poList = mapper.list();

        List<${table.beanName}DTO> dtoList = new ArrayList<>();

        Optional.ofNullable(poList).orElse(new ArrayList<>()).stream().forEach(po->{
            ${table.beanName}DTO dto = new ${table.beanName}DTO();
            BeanUtils.copyProperties(po, dto);
            dtoList.add(dto);
        });
        PageInfo pageInfo = new PageInfo(poList);
        PageResultDTO<${table.beanName}DTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setData(dtoList);
        pageResultDTO.setPageNo(pageInfo.getPageNum());
        pageResultDTO.setPageSize(pageInfo.getPageSize());
        pageResultDTO.setTotalCount(pageInfo.getTotal());
        return RespDTO.success(pageResultDTO);
    }
}