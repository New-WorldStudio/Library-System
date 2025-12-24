package com.library.service.impl;

import java.util.List;
import com.library.common.utils.DateUtils;
import com.library.mapper.domain.entity.LibraryCard;
import com.library.mapper.domain.entity.LibraryCardType;
import com.library.mapper.mapper.LibraryCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.library.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.library.service.ILibraryCardService;

/**
 * 图书卡Service业务层处理
 * 
 * @author Echo
 * @date 2025-12-24
 */
@Service
public class LibraryCardServiceImpl implements ILibraryCardService 
{
    @Autowired
    private LibraryCardMapper libraryCardMapper;

    /**
     * 查询图书卡
     * 
     * @param id 图书卡主键
     * @return 图书卡
     */
    @Override
    public LibraryCard selectLibraryCardById(Long id)
    {
        return libraryCardMapper.selectLibraryCardById(id);
    }

    /**
     * 查询图书卡列表
     * 
     * @param libraryCard 图书卡
     * @return 图书卡
     */
    @Override
    public List<LibraryCard> selectLibraryCardList(LibraryCard libraryCard)
    {
        return libraryCardMapper.selectLibraryCardList(libraryCard);
    }

    /**
     * 新增图书卡
     * 
     * @param libraryCard 图书卡
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLibraryCard(LibraryCard libraryCard)
    {
        libraryCard.setCreateTime(DateUtils.getNowDate());
        int rows = libraryCardMapper.insertLibraryCard(libraryCard);
        insertLibraryCardType(libraryCard);
        return rows;
    }

    /**
     * 修改图书卡
     * 
     * @param libraryCard 图书卡
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLibraryCard(LibraryCard libraryCard)
    {
        libraryCard.setUpdateTime(DateUtils.getNowDate());
        libraryCardMapper.deleteLibraryCardTypeById(libraryCard.getId());
        insertLibraryCardType(libraryCard);
        return libraryCardMapper.updateLibraryCard(libraryCard);
    }

    /**
     * 批量删除图书卡
     * 
     * @param ids 需要删除的图书卡主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardByIds(Long[] ids)
    {
        libraryCardMapper.deleteLibraryCardTypeByIds(ids);
        return libraryCardMapper.deleteLibraryCardByIds(ids);
    }

    /**
     * 删除图书卡信息
     * 
     * @param id 图书卡主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLibraryCardById(Long id)
    {
        libraryCardMapper.deleteLibraryCardTypeById(id);
        return libraryCardMapper.deleteLibraryCardById(id);
    }

    /**
     * 新增图书卡类型信息
     * 
     * @param libraryCard 图书卡对象
     */
    public void insertLibraryCardType(LibraryCard libraryCard)
    {
        List<LibraryCardType> libraryCardTypeList = libraryCard.getLibraryCardTypeList();
        Long id = libraryCard.getId();
        if (StringUtils.isNotNull(libraryCardTypeList))
        {
            List<LibraryCardType> list = new ArrayList<LibraryCardType>();
            for (LibraryCardType libraryCardType : libraryCardTypeList)
            {
                libraryCardType.setId(id);
                list.add(libraryCardType);
            }
            if (list.size() > 0)
            {
                libraryCardMapper.batchLibraryCardType(list);
            }
        }
    }
}
