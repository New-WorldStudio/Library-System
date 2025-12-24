package com.library.mapper.mapper;

import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardType;

import java.util.List;


/**
 * 图书卡Mapper接口
 * 
 * @author Echo
 * @date 2025-12-24
 */
public interface LibraryCardMapper 
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
     * 删除图书卡
     * 
     * @param id 图书卡主键
     * @return 结果
     */
    public int deleteLibraryCardById(Long id);

    /**
     * 批量删除图书卡
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardByIds(Long[] ids);

    /**
     * 批量删除图书卡类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardTypeByIds(Long[] ids);
    
    /**
     * 批量新增图书卡类型
     * 
     * @param libraryCardTypeList 图书卡类型列表
     * @return 结果
     */
    public int batchLibraryCardType(List<LibraryCardType> libraryCardTypeList);
    

    /**
     * 通过图书卡主键删除图书卡类型信息
     * 
     * @param id 图书卡ID
     * @return 结果
     */
    public int deleteLibraryCardTypeById(Long id);
}
