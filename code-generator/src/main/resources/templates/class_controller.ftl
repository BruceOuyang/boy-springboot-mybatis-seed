package ${cfg.beanControllerPackage};

import ${cfg.beanBoPackage}.${table.beanName}BO;
import ${cfg.beanBoPackage}.${table.beanName}BoPage;
import ${cfg.beanDtoPackage}.${table.beanName}DTO;
import ${cfg.beanServicePackage}.${table.beanName}Service;

import club.bugmakers.seed.bean.dto.base.PageResultDTO;
import club.bugmakers.seed.bean.dto.base.RespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ${table.tableComment}
 * @author BOY_Code_Generator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Api(value = "${table.tableComment}接口", description = "${table.tableComment}接口")
@RestController
@RequestMapping("/${table.beanName}/mgt")
public class ${table.beanName}Controller {

    @Resource
    private ${table.beanName}Service service;

    @ApiOperation("${table.tableComment} - 表分页列表")
    @PostMapping("listByPage")
    public RespDTO<PageResultDTO<${table.beanName}DTO>> listByPage(@RequestBody ${table.beanName}BoPage boPage){
        return service.listByPage(boPage);
    }

    @ApiOperation("${table.tableComment} - 新增")
    @PostMapping("add")
    public RespDTO add(@RequestBody ${table.beanName}BO bo) {
        return service.add(bo);
    }

    @ApiOperation("${table.tableComment} - 删除")
    @GetMapping("remove")
    public RespDTO remove(@RequestParam("id") Integer id) {
        return service.remove(id);
    }

    @ApiOperation("${table.tableComment} - 修改")
    @PostMapping("update")
    public RespDTO update(@RequestBody ${table.beanName}BO bo) {
        return service.update(bo);
    }

    @ApiOperation("${table.tableComment} - 查询")
    @GetMapping("get")
    public RespDTO get(@RequestParam("id") Integer id) {
        return service.get(id);
    }
}