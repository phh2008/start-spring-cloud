package com.phh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phh.entity.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GeneratorMapper extends BaseMapper<TableEntity> {

    IPage<TableEntity> selectAllTables(Page<TableEntity> page, @Param(value = "vo") TableEntity vo);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
