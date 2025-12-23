package com.library.mapper.mapper;

import com.library.mapper.domain.entity.BookItem;
import com.library.mapper.domain.entity.BookMeta;

import java.util.List;
/**
 * 图书物品Mapper接口
 * 
 * @author Echo
 * @date 2025-12-23
 */
public interface BookItemMapper 
{
    /**
     * 查询图书物品
     * 
     * @param bookId 图书物品主键
     * @return 图书物品
     */
    public BookItem selectBookItemByBookId(Long bookId);

    /**
     * 查询图书物品列表
     * 
     * @param bookItem 图书物品
     * @return 图书物品集合
     */
    public List<BookItem> selectBookItemList(BookItem bookItem);

    /**
     * 新增图书物品
     * 
     * @param bookItem 图书物品
     * @return 结果
     */
    public int insertBookItem(BookItem bookItem);

    /**
     * 修改图书物品
     * 
     * @param bookItem 图书物品
     * @return 结果
     */
    public int updateBookItem(BookItem bookItem);

    /**
     * 删除图书物品
     * 
     * @param bookId 图书物品主键
     * @return 结果
     */
    public int deleteBookItemByBookId(Long bookId);

    /**
     * 批量删除图书物品
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookItemByBookIds(Long[] bookIds);

    /**
     * 批量删除图书详情
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookMetaByBookIds(Long[] bookIds);
    
    /**
     * 批量新增图书详情
     * 
     * @param bookMetaList 图书详情列表
     * @return 结果
     */
    public int batchBookMeta(List<BookMeta> bookMetaList);
    

    /**
     * 通过图书物品主键删除图书详情信息
     * 
     * @param bookId 图书物品ID
     * @return 结果
     */
    public int deleteBookMetaByBookId(Long bookId);
}
