package ${package.Mapper};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * ${table.comment!} Mapper接口
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends BaseMapper<${entity}> {

}
</#if>
