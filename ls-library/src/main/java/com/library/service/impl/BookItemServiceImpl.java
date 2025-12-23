package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.BookItem;
import com.library.mapper.domain.entity.BookMeta;
import com.library.mapper.mapper.BookItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.library.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.IBookItemService;

/**
 * 图书物品Service业务层处理
 * 
 * @author Echo
 * @date 2025-12-23
 */
@Service
public class BookItemServiceImpl implements IBookItemService 
{
    @Autowired
    private BookItemMapper bookItemMapper;

    /**
     * 查询图书物品
     * 
     * @param bookId 图书物品主键
     * @return 图书物品
     */
    @Override
    public BookItem selectBookItemByBookId(Long bookId)
    {
        return bookItemMapper.selectBookItemByBookId(bookId);
    }

    /**
     * 查询图书物品列表
     * 
     * @param bookItem 图书物品
     * @return 图书物品
     */
    @Override
    public List<BookItem> selectBookItemList(BookItem bookItem)
    {
        return bookItemMapper.selectBookItemList(bookItem);
    }

    /**
     * 新增图书物品
     * 
     * @param bookItem 图书物品
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBookItem(BookItem bookItem)
    {
        bookItem.setCreateTime(DateUtils.getNowDate());
        int rows = bookItemMapper.insertBookItem(bookItem);
        insertBookMeta(bookItem);
        return rows;
    }

    /**
     * 修改图书物品
     * 
     * @param bookItem 图书物品
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBookItem(BookItem bookItem)
    {
        bookItem.setUpdateTime(DateUtils.getNowDate());
        bookItemMapper.deleteBookMetaByBookId(bookItem.getBookId());
        insertBookMeta(bookItem);
        return bookItemMapper.updateBookItem(bookItem);
    }

    /**
     * 批量删除图书物品
     * 
     * @param bookIds 需要删除的图书物品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBookItemByBookIds(Long[] bookIds)
    {
        bookItemMapper.deleteBookMetaByBookIds(bookIds);
        return bookItemMapper.deleteBookItemByBookIds(bookIds);
    }

    /**
     * 删除图书物品信息
     * 
     * @param bookId 图书物品主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBookItemByBookId(Long bookId)
    {
        bookItemMapper.deleteBookMetaByBookId(bookId);
        return bookItemMapper.deleteBookItemByBookId(bookId);
    }

    /**
     * 新增图书详情信息
     * 
     * @param bookItem 图书物品对象
     */
    public void insertBookMeta(BookItem bookItem)
    {
        List<BookMeta> bookMetaList = bookItem.getBookMetaList();
        Long bookId = bookItem.getBookId();
        if (StringUtils.isNotNull(bookMetaList))
        {
            List<BookMeta> list = new ArrayList<BookMeta>();
            for (BookMeta bookMeta : bookMetaList)
            {
                bookMeta.setBookId(bookId);
                list.add(bookMeta);
            }
            if (list.size() > 0)
            {
                bookItemMapper.batchBookMeta(list);
            }
        }
    }
}
