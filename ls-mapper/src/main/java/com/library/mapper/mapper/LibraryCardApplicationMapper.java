package com.library.mapper.mapper;

import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardApplication;

import java.util.List;

/**
 * 办卡申请Mapper接口
 * 
 * @author echo
 * @date 2025-12-24
 */
public interface LibraryCardApplicationMapper 
{
    /**
     * 查询办卡申请
     * 
     * @param id 办卡申请主键
     * @return 办卡申请
     */
    public LibraryCardApplication selectLibraryCardApplicationById(Long id);

    /**
     * 查询办卡申请列表
     * 
     * @param libraryCardApplication 办卡申请
     * @return 办卡申请集合
     */
    public List<LibraryCardApplication> selectLibraryCardApplicationList(LibraryCardApplication libraryCardApplication);

    /**
     * 新增办卡申请
     * 
     * @param libraryCardApplication 办卡申请
     * @return 结果
     */
    public int insertLibraryCardApplication(LibraryCardApplication libraryCardApplication);

    /**
     * 修改办卡申请
     * 
     * @param libraryCardApplication 办卡申请
     * @return 结果
     */
    public int updateLibraryCardApplication(LibraryCardApplication libraryCardApplication);

    /**
     * 删除办卡申请
     * 
     * @param id 办卡申请主键
     * @return 结果
     */
    public int deleteLibraryCardApplicationById(Long id);

    /**
     * 批量删除办卡申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardApplicationByIds(Long[] ids);

    /**
     * 批量删除图书卡
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardByIds(Long[] ids);
    
    /**
     * 批量新增图书卡
     * 
     * @param libraryCardList 图书卡列表
     * @return 结果
     */
    public int batchLibraryCard(List<LibraryCard> libraryCardList);
    

    /**
     * 通过办卡申请主键删除图书卡信息
     * 
     * @param id 办卡申请ID
     * @return 结果
     */
    public int deleteLibraryCardById(Long id);
}
