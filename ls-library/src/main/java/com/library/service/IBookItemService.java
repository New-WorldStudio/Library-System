package com.library.service;

import com.library.mapper.domain.entity.BookItem;

import java.util.List;

/**
 * 图书物品Service接口
 * 
 * @author Echo
 * @date 2025-12-23
 */
public interface IBookItemService 
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
     * 批量删除图书物品
     * 
     * @param bookIds 需要删除的图书物品主键集合
     * @return 结果
     */
    public int deleteBookItemByBookIds(Long[] bookIds);

    /**
     * 删除图书物品信息
     * 
     * @param bookId 图书物品主键
     * @return 结果
     */
    public int deleteBookItemByBookId(Long bookId);
}
