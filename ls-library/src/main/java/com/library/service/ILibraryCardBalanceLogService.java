package com.library.service;

import com.library.mapper.domain.entity.LibraryCardBalanceLog;

import java.util.List;

/**
 * 押金 / 余额流水记录Service接口
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
public interface ILibraryCardBalanceLogService 
{
    /**
     * 查询押金 / 余额流水记录
     * 
     * @param id 押金 / 余额流水记录主键
     * @return 押金 / 余额流水记录
     */
    public LibraryCardBalanceLog selectLibraryCardBalanceLogById(Long id);

    /**
     * 查询押金 / 余额流水记录列表
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 押金 / 余额流水记录集合
     */
    public List<LibraryCardBalanceLog> selectLibraryCardBalanceLogList(LibraryCardBalanceLog libraryCardBalanceLog);

    /**
     * 新增押金 / 余额流水记录
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 结果
     */
    public int insertLibraryCardBalanceLog(LibraryCardBalanceLog libraryCardBalanceLog);

    /**
     * 修改押金 / 余额流水记录
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 结果
     */
    public int updateLibraryCardBalanceLog(LibraryCardBalanceLog libraryCardBalanceLog);

    /**
     * 批量删除押金 / 余额流水记录
     * 
     * @param ids 需要删除的押金 / 余额流水记录主键集合
     * @return 结果
     */
    public int deleteLibraryCardBalanceLogByIds(Long[] ids);

    /**
     * 删除押金 / 余额流水记录信息
     * 
     * @param id 押金 / 余额流水记录主键
     * @return 结果
     */
    public int deleteLibraryCardBalanceLogById(Long id);
}
