package com.library.mapper.mapper;

import com.library.mapper.domain.entity.LibraryCarStatusLog;
import com.library.mapper.domain.entity.LibraryCard;

import java.util.List;

/**
 * 图书卡状态日志记录Mapper接口
 * 
 * @author Echo
 * @date 2025-12-24
 */
public interface LibraryCarStatusLogMapper 
{
    /**
     * 查询图书卡状态日志记录
     * 
     * @param id 图书卡状态日志记录主键
     * @return 图书卡状态日志记录
     */
    public LibraryCarStatusLog selectLibraryCarStatusLogById(Long id);

    /**
     * 查询图书卡状态日志记录列表
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 图书卡状态日志记录集合
     */
    public List<LibraryCarStatusLog> selectLibraryCarStatusLogList(LibraryCarStatusLog libraryCarStatusLog);

    /**
     * 新增图书卡状态日志记录
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 结果
     */
    public int insertLibraryCarStatusLog(LibraryCarStatusLog libraryCarStatusLog);

    /**
     * 修改图书卡状态日志记录
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 结果
     */
    public int updateLibraryCarStatusLog(LibraryCarStatusLog libraryCarStatusLog);

    /**
     * 删除图书卡状态日志记录
     * 
     * @param id 图书卡状态日志记录主键
     * @return 结果
     */
    public int deleteLibraryCarStatusLogById(Long id);

    /**
     * 批量删除图书卡状态日志记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLibraryCarStatusLogByIds(Long[] ids);

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
     * 通过图书卡状态日志记录主键删除图书卡信息
     * 
     * @param id 图书卡状态日志记录ID
     * @return 结果
     */
    public int deleteLibraryCardById(Long id);
}
