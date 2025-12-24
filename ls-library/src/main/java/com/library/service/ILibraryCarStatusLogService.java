package com.library.service;

import com.library.mapper.domain.entity.LibraryCarStatusLog;

import java.util.List;

/**
 * 图书卡状态日志记录Service接口
 * 
 * @author Echo
 * @date 2025-12-24
 */
public interface ILibraryCarStatusLogService 
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
     * 批量删除图书卡状态日志记录
     * 
     * @param ids 需要删除的图书卡状态日志记录主键集合
     * @return 结果
     */
    public int deleteLibraryCarStatusLogByIds(Long[] ids);

    /**
     * 删除图书卡状态日志记录信息
     * 
     * @param id 图书卡状态日志记录主键
     * @return 结果
     */
    public int deleteLibraryCarStatusLogById(Long id);
}
