package com.library.mapper.domain.entity;

import java.util.List;

import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 图书物品对象 book_item
 * 
 * @author Echo
 * @date 2025-12-23
 */
public class BookItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书ID */
    private Long bookId;

    /** 图书条形码 */
    @Excel(name = "图书条形码")
    private String barcode;

    /** 状态 1可借、2、借出、3损坏，4丢失 */
    @Excel(name = "状态 1可借、2、借出、3损坏，4丢失")
    private Long status;

    /** 存放位置 */
    @Excel(name = "存放位置")
    private String localtion;

    /** 图书详情信息 */
    private List<BookMeta> bookMetaList;

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }

    public void setBarcode(String barcode) 
    {
        this.barcode = barcode;
    }

    public String getBarcode() 
    {
        return barcode;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setLocaltion(String localtion) 
    {
        this.localtion = localtion;
    }

    public String getLocaltion() 
    {
        return localtion;
    }

    public List<BookMeta> getBookMetaList()
    {
        return bookMetaList;
    }

    public void setBookMetaList(List<BookMeta> bookMetaList)
    {
        this.bookMetaList = bookMetaList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookId", getBookId())
            .append("barcode", getBarcode())
            .append("status", getStatus())
            .append("localtion", getLocaltion())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("bookMetaList", getBookMetaList())
            .toString();
    }
}
