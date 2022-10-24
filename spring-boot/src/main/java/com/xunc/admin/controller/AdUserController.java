package com.xunc.admin.controller;

import com.xunc.common.annotations.Log;
import com.xunc.common.returnbody.ReturnBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.xunc.admin.service.AdUserService;
import com.xunc.admin.entity.AdUser;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author zfc generator
 * @since 2022-10-24
 */
@Api(tags = "系统-用户")
@RestController
@RequestMapping(value = "admin/adUser")
public class AdUserController {
    @Autowired
    private AdUserService adUserService;

    @Log("请求用户列表")
    @ApiOperation("分页列表")
    @PostMapping("list")
    public String list() {
        IPage<AdUser> pageResult = adUserService.page(new Page<>(1, 10), null);
        return ReturnBody.success(pageResult);
    }

}
