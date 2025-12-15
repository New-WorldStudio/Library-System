package com.library.mapper.mapper;

import com.library.mapper.domain.entity.BookMeta;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 图书元信息Mapper接口
 * 
 * @author Echo
 * @date 2025-12-15
 */
@Mapper
public interface BookMetaMapper 
{
    /**
     * 查询图书元信息

     * 
     * @param id 图书元信息
主键
     * @return 图书元信息

     */
    public BookMeta selectBookMetaById(Long id);

    /**
     * 查询图书元信息
列表
     * 
     * @param bookMeta 图书元信息

     * @return 图书元信息
集合
     */
    public List<BookMeta> selectBookMetaList(BookMeta bookMeta);

    /**
     * 新增图书元信息

     * 
     * @param bookMeta 图书元信息

     * @return 结果
     */
    public int insertBookMeta(BookMeta bookMeta);

    /**
     * 修改图书元信息

     * 
     * @param bookMeta 图书元信息

     * @return 结果
     */
    public int updateBookMeta(BookMeta bookMeta);

    /**
     * 删除图书元信息

     * 
     * @param id 图书元信息
主键
     * @return 结果
     */
    public int deleteBookMetaById(Long id);

    /**
     * 批量删除图书元信息

     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookMetaByIds(Long[] ids);
}
