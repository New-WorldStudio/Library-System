package com.library.mapper.domain.entity;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 办卡申请对象 library_card_application
 * 
 * @author echo
 * @date 2025-12-24
 */
public class LibraryCardApplication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 办卡申请ID */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 卡类型 */
    @Excel(name = "卡类型")
    private Long cardTypeId;

    /** 状态（0待审核、1通过、2拒绝） */
    @Excel(name = "状态", readConverterExp = "0=待审核、1通过、2拒绝")
    private Long status;

    /** 谁审核的 */
    @Excel(name = "谁审核的")
    private Long reviewBy;

    /** 审核通过时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核通过时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewAt;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setCardTypeId(Long cardTypeId) 
    {
        this.cardTypeId = cardTypeId;
    }

    public Long getCardTypeId() 
    {
        return cardTypeId;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setReviewBy(Long reviewBy) 
    {
        this.reviewBy = reviewBy;
    }

    public Long getReviewBy() 
    {
        return reviewBy;
    }

    public void setReviewAt(Date reviewAt) 
    {
        this.reviewAt = reviewAt;
    }

    public Date getReviewAt() 
    {
        return reviewAt;
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
            .append("userId", getUserId())
            .append("cardTypeId", getCardTypeId())
            .append("status", getStatus())
            .append("reviewBy", getReviewBy())
            .append("reviewAt", getReviewAt())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("libraryCardList", getLibraryCardList())
            .toString();
    }
}
