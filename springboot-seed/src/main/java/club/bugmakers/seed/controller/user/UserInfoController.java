package club.bugmakers.seed.controller.user;

import club.bugmakers.seed.bean.bo.user.UserInfoBO;
import club.bugmakers.seed.bean.bo.user.UserInfoBoPage;
import club.bugmakers.seed.bean.dto.base.PageResultDTO;
import club.bugmakers.seed.bean.dto.base.RespDTO;
import club.bugmakers.seed.bean.dto.user.UserInfoDTO;
import club.bugmakers.seed.service.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户信息表
 * @author BOY_Code_Generator
 * @date 2018-12-18 12:00:26
 */
@Api(value = "用户信息表接口", description = "用户信息表接口")
@RestController
@RequestMapping("/UserInfo/mgt")
public class UserInfoController {

    @Resource
    private UserInfoService service;

    @ApiOperation("用户信息表 - 表分页列表")
    @PostMapping("listByPage")
    public RespDTO<PageResultDTO<UserInfoDTO>> listByPage(@RequestBody UserInfoBoPage boPage){
        return service.listByPage(boPage);
    }

    @ApiOperation("用户信息表 - 新增")
    @PostMapping("add")
    public RespDTO add(@RequestBody UserInfoBO bo) {
        return service.add(bo);
    }

    @ApiOperation("用户信息表 - 删除")
    @GetMapping("remove")
    public RespDTO remove(@RequestParam("id") Integer id) {
        return service.remove(id);
    }

    @ApiOperation("用户信息表 - 修改")
    @PostMapping("update")
    public RespDTO update(@RequestBody UserInfoBO bo) {
        return service.update(bo);
    }

    @ApiOperation("用户信息表 - 查询")
    @GetMapping("get")
    public RespDTO get(@RequestParam("id") Integer id) {
        return service.get(id);
    }
}