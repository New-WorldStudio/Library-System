package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardBalanceLog;
import com.library.mapper.mapper.LibraryCardBalanceLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.library.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.ILibraryCardBalanceLogService;

/**
 * 押金 / 余额流水记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
@Service
public class LibraryCardBalanceLogServiceImpl implements ILibraryCardBalanceLogService 
{
    @Autowired
    private LibraryCardBalanceLogMapper libraryCardBalanceLogMapper;

    /**
     * 查询押金 / 余额流水记录
     * 
     * @param id 押金 / 余额流水记录主键
     * @return 押金 / 余额流水记录
     */
    @Override
    public LibraryCardBalanceLog selectLibraryCardBalanceLogById(Long id)
    {
        return libraryCardBalanceLogMapper.selectLibraryCardBalanceLogById(id);
    }

    /**
     * 查询押金 / 余额流水记录列表
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 押金 / 余额流水记录
     */
    @Override
    public List<LibraryCardBalanceLog> selectLibraryCardBalanceLogList(LibraryCardBalanceLog libraryCardBalanceLog)
    {
        return libraryCardBalanceLogMapper.selectLibraryCardBalanceLogList(libraryCardBalanceLog);
    }

    /**
     * 新增押金 / 余额流水记录
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLibraryCardBalanceLog(LibraryCardBalanceLog libraryCardBalanceLog)
    {
        libraryCardBalanceLog.setCreateTime(DateUtils.getNowDate());
        int rows = libraryCardBalanceLogMapper.insertLibraryCardBalanceLog(libraryCardBalanceLog);
        insertLibraryCard(libraryCardBalanceLog);
        return rows;
    }

    /**
     * 修改押金 / 余额流水记录
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLibraryCardBalanceLog(LibraryCardBalanceLog libraryCardBalanceLog)
    {
        libraryCardBalanceLog.setUpdateTime(DateUtils.getNowDate());
        libraryCardBalanceLogMapper.deleteLibraryCardById(libraryCardBalanceLog.getId());
        insertLibraryCard(libraryCardBalanceLog);
        return libraryCardBalanceLogMapper.updateLibraryCardBalanceLog(libraryCardBalanceLog);
    }

    /**
     * 批量删除押金 / 余额流水记录
     * 
     * @param ids 需要删除的押金 / 余额流水记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardBalanceLogByIds(Long[] ids)
    {
        libraryCardBalanceLogMapper.deleteLibraryCardByIds(ids);
        return libraryCardBalanceLogMapper.deleteLibraryCardBalanceLogByIds(ids);
    }

    /**
     * 删除押金 / 余额流水记录信息
     * 
     * @param id 押金 / 余额流水记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardBalanceLogById(Long id)
    {
        libraryCardBalanceLogMapper.deleteLibraryCardById(id);
        return libraryCardBalanceLogMapper.deleteLibraryCardBalanceLogById(id);
    }

    /**
     * 新增图书卡信息
     * 
     * @param libraryCardBalanceLog 押金 / 余额流水记录对象
     */
    public void insertLibraryCard(LibraryCardBalanceLog libraryCardBalanceLog)
    {
        List<LibraryCard> libraryCardList = libraryCardBalanceLog.getLibraryCardList();
        Long id = libraryCardBalanceLog.getId();
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
                libraryCardBalanceLogMapper.batchLibraryCard(list);
            }
        }
    }
}
