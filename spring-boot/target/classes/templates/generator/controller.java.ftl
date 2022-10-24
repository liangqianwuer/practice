package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chinaweal.supervision.shop.auth.org.entity.page.ResultDto;
import com.chinaweal.youfool.framework.springboot.mybatis.plus.page.PageRequestDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author ${author}
 * @since ${date}
 */
<#if swagger2>
@Api(tags = "${table.comment!}")
</#if>
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping(value = "<#if package.ModuleName?? && package.ModuleName != "">${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>", produces = "text/plain;charset=utf-8")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @ApiOperation("分页列表")
    @PostMapping("list")
    public ResultDto list(HttpServletRequest request, PageRequestDto pageRequestDto) {
        IPage<${entity}> pageResult = ${table.serviceName?uncap_first}(new Page<${entity}>(pageRequestDto.getCurPage(), pageRequestDto.getPageSize()),
            new QueryWrapper<${entity}>().lambda()
            .orderByDesc(${entity}::getCreateTime));
        return ResultDto.commonPage(pageResult);
    }


    @ApiOperation("添加")
    @PostMapping({"add"})
    public ResultDto save(HttpServletRequest request, ${entity} ${entity?uncap_first}) {

        return ResultDto.OK;
    }

    @ApiOperation("修改")
    @PostMapping({"update"})
    public ResultDto update(HttpServletRequest request, ${entity} ${entity?uncap_first}) {

        return ResultDto.OK;
    }


    @ApiOperation("删除")
    @PostMapping("delete")
    public ResultDto delete(HttpServletRequest request, String id) {

        return ResultDto.OK;
    }

}
</#if>
