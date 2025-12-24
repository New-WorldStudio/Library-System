package com.library.mapper.mapper;

import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardBalanceLog;

import java.util.List;
/**
 * 押金 / 余额流水记录Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
public interface LibraryCardBalanceLogMapper 
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
     * 删除押金 / 余额流水记录
     * 
     * @param id 押金 / 余额流水记录主键
     * @return 结果
     */
    public int deleteLibraryCardBalanceLogById(Long id);

    /**
     * 批量删除押金 / 余额流水记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCardBalanceLogByIds(Long[] ids);

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
     * 通过押金 / 余额流水记录主键删除图书卡信息
     * 
     * @param id 押金 / 余额流水记录ID
     * @return 结果
     */
    public int deleteLibraryCardById(Long id);
}
