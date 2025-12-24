package com.library.service;

import java.util.List;

import com.library.mapper.domain.entity.LibraryCard;

/**
 * 图书卡Service接口
 * 
 * @author Echo
 * @date 2025-12-24
 */
public interface ILibraryCardService 
{
    /**
     * 查询图书卡
     * 
     * @param id 图书卡主键
     * @return 图书卡
     */
    public LibraryCard selectLibraryCardById(Long id);

    /**
     * 查询图书卡列表
     * 
     * @param libraryCard 图书卡
     * @return 图书卡集合
     */
    public List<LibraryCard> selectLibraryCardList(LibraryCard libraryCard);

    /**
     * 新增图书卡
     * 
     * @param libraryCard 图书卡
     * @return 结果
     */
    public int insertLibraryCard(LibraryCard libraryCard);

    /**
     * 修改图书卡
     * 
     * @param libraryCard 图书卡
     * @return 结果
     */
    public int updateLibraryCard(LibraryCard libraryCard);

    /**
     * 批量删除图书卡
     * 
     * @param ids 需要删除的图书卡主键集合
     * @return 结果
     */
    public int deleteLibraryCardByIds(Long[] ids);

    /**
     * 删除图书卡信息
     * 
     * @param id 图书卡主键
     * @return 结果
     */
    public int deleteLibraryCardById(Long id);
}
