package com.xunc.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.xunc.system.service.SysLogService;

/**
 * @author zfc generator
 * @since 2022-10-24
 */
@Api(tags = "")
@RestController
@RequestMapping(value = "system/sysLog", produces = "text/plain;charset=utf-8")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

}
