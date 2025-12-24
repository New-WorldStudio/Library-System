package com.library.service;

import com.library.mapper.domain.entity.LibraryCardApplication;

import java.util.List;

/**
 * 办卡申请Service接口
 * 
 * @author echo
 * @date 2025-12-24
 */
public interface ILibraryCardApplicationService 
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
     * 批量删除办卡申请
     * 
     * @param ids 需要删除的办卡申请主键集合
     * @return 结果
     */
    public int deleteLibraryCardApplicationByIds(Long[] ids);

    /**
     * 删除办卡申请信息
     * 
     * @param id 办卡申请主键
     * @return 结果
     */
    public int deleteLibraryCardApplicationById(Long id);
}
