package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.LibraryCardType;
import com.library.mapper.mapper.LibraryCardTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.service.ILibraryCardTypeService;

/**
 * 图书卡类型Service业务层处理
 * 
 * @author Echo
 * @date 2025-12-24
 */
@Service
public class LibraryCardTypeServiceImpl implements ILibraryCardTypeService 
{
    @Autowired
    private LibraryCardTypeMapper libraryCardTypeMapper;

    /**
     * 查询图书卡类型
     * 
     * @param id 图书卡类型主键
     * @return 图书卡类型
     */
    @Override
    public LibraryCardType selectLibraryCardTypeById(Long id)
    {
        return libraryCardTypeMapper.selectLibraryCardTypeById(id);
    }

    /**
     * 查询图书卡类型列表
     * 
     * @param libraryCardType 图书卡类型
     * @return 图书卡类型
     */
    @Override
    public List<LibraryCardType> selectLibraryCardTypeList(LibraryCardType libraryCardType)
    {
        return libraryCardTypeMapper.selectLibraryCardTypeList(libraryCardType);
    }

    /**
     * 新增图书卡类型
     * 
     * @param libraryCardType 图书卡类型
     * @return 结果
     */
    @Override
    public int insertLibraryCardType(LibraryCardType libraryCardType)
    {
        libraryCardType.setCreateTime(DateUtils.getNowDate());
        return libraryCardTypeMapper.insertLibraryCardType(libraryCardType);
    }

    /**
     * 修改图书卡类型
     * 
     * @param libraryCardType 图书卡类型
     * @return 结果
     */
    @Override
    public int updateLibraryCardType(LibraryCardType libraryCardType)
    {
        libraryCardType.setUpdateTime(DateUtils.getNowDate());
        return libraryCardTypeMapper.updateLibraryCardType(libraryCardType);
    }

    /**
     * 批量删除图书卡类型
     * 
     * @param ids 需要删除的图书卡类型主键
     * @return 结果
     */
    @Override
    public int deleteLibraryCardTypeByIds(Long[] ids)
    {
        return libraryCardTypeMapper.deleteLibraryCardTypeByIds(ids);
    }

    /**
     * 删除图书卡类型信息
     * 
     * @param id 图书卡类型主键
     * @return 结果
     */
    @Override
    public int deleteLibraryCardTypeById(Long id)
    {
        return libraryCardTypeMapper.deleteLibraryCardTypeById(id);
    }
}
