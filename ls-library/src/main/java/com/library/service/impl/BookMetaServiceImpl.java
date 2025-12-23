package com.library.service.impl;

import java.util.List;

import com.library.mapper.domain.entity.BookMeta;
import com.library.mapper.mapper.BookMetaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.service.IBookMetaService;

/**
 * 图书详情Service业务层处理
 * 
 * @author Echo
 * @date 2025-12-23
 */
@Service
public class BookMetaServiceImpl implements IBookMetaService 
{
    @Autowired
    private BookMetaMapper bookMetaMapper;

    /**
     * 查询图书详情
     * 
     * @param id 图书详情主键
     * @return 图书详情
     */
    @Override
    public BookMeta selectBookMetaById(Long id)
    {
        return bookMetaMapper.selectBookMetaById(id);
    }

    /**
     * 查询图书详情列表
     * 
     * @param bookMeta 图书详情
     * @return 图书详情
     */
    @Override
    public List<BookMeta> selectBookMetaList(BookMeta bookMeta)
    {
        return bookMetaMapper.selectBookMetaList(bookMeta);
    }

    /**
     * 新增图书详情
     * 
     * @param bookMeta 图书详情
     * @return 结果
     */
    @Override
    public int insertBookMeta(BookMeta bookMeta)
    {
        return bookMetaMapper.insertBookMeta(bookMeta);
    }

    /**
     * 修改图书详情
     * 
     * @param bookMeta 图书详情
     * @return 结果
     */
    @Override
    public int updateBookMeta(BookMeta bookMeta)
    {
        return bookMetaMapper.updateBookMeta(bookMeta);
    }

    /**
     * 批量删除图书详情
     * 
     * @param ids 需要删除的图书详情主键
     * @return 结果
     */
    @Override
    public int deleteBookMetaByIds(Long[] ids)
    {
        return bookMetaMapper.deleteBookMetaByIds(ids);
    }

    /**
     * 删除图书详情信息
     * 
     * @param id 图书详情主键
     * @return 结果
     */
    @Override
    public int deleteBookMetaById(Long id)
    {
        return bookMetaMapper.deleteBookMetaById(id);
    }
}
