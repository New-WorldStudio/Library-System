package com.library.mapper.mapper;

import com.library.mapper.domain.entity.LibraryCardType;

import java.util.List;


/**
 * 图书卡类型Mapper接口
 * 
 * @author Echo
 * @date 2025-12-24
 */
public interface LibraryCardTypeMapper 
{
    /**
     * 查询图书卡类型
     * 
     * @param id 图书卡类型主键
     * @return 图书卡类型
     */
    public LibraryCardType selectLibraryCardTypeById(Long id);

    /**
     * 查询图书卡类型列表
     * 
     * @param libraryCardType 图书卡类型
     * @return 图书卡类型集合
     */
    public List<LibraryCardType> selectLibraryCardTypeList(LibraryCardType libraryCardType);

    /**
     * 新增图书卡类型
     * 
     * @param libraryCardType 图书卡类型
     * @return 结果
     */
    public int insertLibraryCardType(LibraryCardType libraryCardType);

    /**
     * 修改图书卡类型
     * 
     * @param libraryCardType 图书卡类型
     * @return 结果
     */
    public int updateLibraryCardType(LibraryCardType libraryCardType);

    /**
     * 删除图书卡类型
     * 
     * @param id 图书卡类型主键
     * @return 结果
     */
    public int deleteLibraryCardTypeById(Long id);

    /**
     * 批量删除图书卡类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardTypeByIds(Long[] ids);
}
