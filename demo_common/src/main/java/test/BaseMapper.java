package test;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper 不能被扫描 单独放 全路径配置到 properties(yml)中 mapper.mappers
 * 
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
