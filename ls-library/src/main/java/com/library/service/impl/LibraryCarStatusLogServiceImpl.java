package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.LibraryCarStatusLog;
import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.mapper.LibraryCarStatusLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.library.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.ILibraryCarStatusLogService;

/**
 * 图书卡状态日志记录Service业务层处理
 * 
 * @author Echo
 * @date 2025-12-24
 */
@Service
public class LibraryCarStatusLogServiceImpl implements ILibraryCarStatusLogService 
{
    @Autowired
    private LibraryCarStatusLogMapper libraryCarStatusLogMapper;

    /**
     * 查询图书卡状态日志记录
     * 
     * @param id 图书卡状态日志记录主键
     * @return 图书卡状态日志记录
     */
    @Override
    public LibraryCarStatusLog selectLibraryCarStatusLogById(Long id)
    {
        return libraryCarStatusLogMapper.selectLibraryCarStatusLogById(id);
    }

    /**
     * 查询图书卡状态日志记录列表
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 图书卡状态日志记录
     */
    @Override
    public List<LibraryCarStatusLog> selectLibraryCarStatusLogList(LibraryCarStatusLog libraryCarStatusLog)
    {
        return libraryCarStatusLogMapper.selectLibraryCarStatusLogList(libraryCarStatusLog);
    }

    /**
     * 新增图书卡状态日志记录
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLibraryCarStatusLog(LibraryCarStatusLog libraryCarStatusLog)
    {
        libraryCarStatusLog.setCreateTime(DateUtils.getNowDate());
        int rows = libraryCarStatusLogMapper.insertLibraryCarStatusLog(libraryCarStatusLog);
        insertLibraryCard(libraryCarStatusLog);
        return rows;
    }

    /**
     * 修改图书卡状态日志记录
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLibraryCarStatusLog(LibraryCarStatusLog libraryCarStatusLog)
    {
        libraryCarStatusLog.setUpdateTime(DateUtils.getNowDate());
        libraryCarStatusLogMapper.deleteLibraryCardById(libraryCarStatusLog.getId());
        insertLibraryCard(libraryCarStatusLog);
        return libraryCarStatusLogMapper.updateLibraryCarStatusLog(libraryCarStatusLog);
    }

    /**
     * 批量删除图书卡状态日志记录
     * 
     * @param ids 需要删除的图书卡状态日志记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCarStatusLogByIds(Long[] ids)
    {
        libraryCarStatusLogMapper.deleteLibraryCardByIds(ids);
        return libraryCarStatusLogMapper.deleteLibraryCarStatusLogByIds(ids);
    }

    /**
     * 删除图书卡状态日志记录信息
     * 
     * @param id 图书卡状态日志记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCarStatusLogById(Long id)
    {
        libraryCarStatusLogMapper.deleteLibraryCardById(id);
        return libraryCarStatusLogMapper.deleteLibraryCarStatusLogById(id);
    }

    /**
     * 新增图书卡信息
     * 
     * @param libraryCarStatusLog 图书卡状态日志记录对象
     */
    public void insertLibraryCard(LibraryCarStatusLog libraryCarStatusLog)
    {
        List<LibraryCard> libraryCardList = libraryCarStatusLog.getLibraryCardList();
        Long id = libraryCarStatusLog.getId();
        if (StringUtils.isNotNull(libraryCardList))
        {
            List<LibraryCard> list = new ArrayList<LibraryCard>();
            for (LibraryCard libraryCard : libraryCardList)
            {
                libraryCard.setId(id);
                list.add(libraryCard);
            }
            if (list.size() > 0)
            {
                libraryCarStatusLogMapper.batchLibraryCard(list);
            }
        }
    }
}
