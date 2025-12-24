package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardApplication;
import com.library.mapper.mapper.LibraryCardApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.library.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.ILibraryCardApplicationService;

/**
 * 办卡申请Service业务层处理
 * 
 * @author echo
 * @date 2025-12-24
 */
@Service
public class LibraryCardApplicationServiceImpl implements ILibraryCardApplicationService 
{
    @Autowired
    private LibraryCardApplicationMapper libraryCardApplicationMapper;

    /**
     * 查询办卡申请
     * 
     * @param id 办卡申请主键
     * @return 办卡申请
     */
    @Override
    public LibraryCardApplication selectLibraryCardApplicationById(Long id)
    {
        return libraryCardApplicationMapper.selectLibraryCardApplicationById(id);
    }

    /**
     * 查询办卡申请列表
     * 
     * @param libraryCardApplication 办卡申请
     * @return 办卡申请
     */
    @Override
    public List<LibraryCardApplication> selectLibraryCardApplicationList(LibraryCardApplication libraryCardApplication)
    {
        return libraryCardApplicationMapper.selectLibraryCardApplicationList(libraryCardApplication);
    }

    /**
     * 新增办卡申请
     * 
     * @param libraryCardApplication 办卡申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLibraryCardApplication(LibraryCardApplication libraryCardApplication)
    {
        libraryCardApplication.setCreateTime(DateUtils.getNowDate());
        int rows = libraryCardApplicationMapper.insertLibraryCardApplication(libraryCardApplication);
        insertLibraryCard(libraryCardApplication);
        return rows;
    }

    /**
     * 修改办卡申请
     * 
     * @param libraryCardApplication 办卡申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLibraryCardApplication(LibraryCardApplication libraryCardApplication)
    {
        libraryCardApplication.setUpdateTime(DateUtils.getNowDate());
        libraryCardApplicationMapper.deleteLibraryCardById(libraryCardApplication.getId());
        insertLibraryCard(libraryCardApplication);
        return libraryCardApplicationMapper.updateLibraryCardApplication(libraryCardApplication);
    }

    /**
     * 批量删除办卡申请
     * 
     * @param ids 需要删除的办卡申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardApplicationByIds(Long[] ids)
    {
        libraryCardApplicationMapper.deleteLibraryCardByIds(ids);
        return libraryCardApplicationMapper.deleteLibraryCardApplicationByIds(ids);
    }

    /**
     * 删除办卡申请信息
     * 
     * @param id 办卡申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardApplicationById(Long id)
    {
        libraryCardApplicationMapper.deleteLibraryCardById(id);
        return libraryCardApplicationMapper.deleteLibraryCardApplicationById(id);
    }

    /**
     * 新增图书卡信息
     * 
     * @param libraryCardApplication 办卡申请对象
     */
    public void insertLibraryCard(LibraryCardApplication libraryCardApplication)
    {
        List<LibraryCard> libraryCardList = libraryCardApplication.getLibraryCardList();
        Long id = libraryCardApplication.getId();
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
                libraryCardApplicationMapper.batchLibraryCard(list);
            }
        }
    }
}
