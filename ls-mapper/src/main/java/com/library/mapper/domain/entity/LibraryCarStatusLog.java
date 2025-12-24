package com.library.mapper.domain.entity;

import java.util.List;

import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 图书卡状态日志记录对象 library_car_status_log
 * 
 * @author Echo
 * @date 2025-12-24
 */
public class LibraryCarStatusLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 卡ID */
    @Excel(name = "卡ID")
    private Long carId;

    /** 旧状态 */
    @Excel(name = "旧状态")
    private Long oldStatus;

    /** 新状态 */
    @Excel(name = "新状态")
    private Long newStatus;

    /** 原因 */
    @Excel(name = "原因")
    private String reason;

    /** 图书卡信息 */
    private List<LibraryCard> libraryCardList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCarId(Long carId) 
    {
        this.carId = carId;
    }

    public Long getCarId() 
    {
        return carId;
    }

    public void setOldStatus(Long oldStatus) 
    {
        this.oldStatus = oldStatus;
    }

    public Long getOldStatus() 
    {
        return oldStatus;
    }

    public void setNewStatus(Long newStatus) 
    {
        this.newStatus = newStatus;
    }

    public Long getNewStatus() 
    {
        return newStatus;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    public List<LibraryCard> getLibraryCardList()
    {
        return libraryCardList;
    }

    public void setLibraryCardList(List<LibraryCard> libraryCardList)
    {
        this.libraryCardList = libraryCardList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carId", getCarId())
            .append("oldStatus", getOldStatus())
            .append("newStatus", getNewStatus())
            .append("reason", getReason())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("libraryCardList", getLibraryCardList())
            .toString();
    }
}
