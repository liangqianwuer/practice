package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * ${table.comment!} 实现类
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ServiceImpl<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {

}
</#if>
