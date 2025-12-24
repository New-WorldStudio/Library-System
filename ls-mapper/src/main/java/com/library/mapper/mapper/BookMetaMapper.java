package com.library.mapper.mapper;

import com.library.mapper.domain.entity.BookMeta;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书详情Mapper接口
 * 
 * @author Echo
 * @date 2025-12-23
 */
public interface BookMetaMapper 
{
    /**
     * 查询图书详情
     * 
     * @param id 图书详情主键
     * @return 图书详情
     */
    public BookMeta selectBookMetaById(Long id);

    /**
     * 查询图书详情列表
     * 
     * @param bookMeta 图书详情
     * @return 图书详情集合
     */
    public List<BookMeta> selectBookMetaList(BookMeta bookMeta);

    /**
     * 新增图书详情
     * 
     * @param bookMeta 图书详情
     * @return 结果
     */
    public int insertBookMeta(BookMeta bookMeta);

    /**
     * 修改图书详情
     * 
     * @param bookMeta 图书详情
     * @return 结果
     */
    public int updateBookMeta(BookMeta bookMeta);

    /**
     * 删除图书详情
     * 
     * @param id 图书详情主键
     * @return 结果
     */
    public int deleteBookMetaById(Long id);

    /**
     * 批量删除图书详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookMetaByIds(Long[] ids);
}
