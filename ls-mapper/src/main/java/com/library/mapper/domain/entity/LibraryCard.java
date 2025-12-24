package com.library.mapper.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 图书卡对象 library_card
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
public class LibraryCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书证ID */
    private Long id;

    /** 图书证号 */
    private String cardNo;

    /** 图书卡类型列表 */
    private List<LibraryCardType> libraryCardTypeList;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 卡类型 */
    @Excel(name = "卡类型")
    private Long cardTypeId;

    /** 状态（0未激活、1正常、2冻结） */
    @Excel(name = "状态", readConverterExp = "0=未激活、1正常、2冻结")
    private Long status;

    /** 发卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发卡时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issueDate;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expireDate;

    /** 押金 / 余额 */
    @Excel(name = "押金 / 余额")
    private BigDecimal balance;

    /** 最大借书量 */
    @Excel(name = "最大借书量")
    private Long borrowLimit;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCardNo(String cardNo) 
    {
        this.cardNo = cardNo;
    }

    public String getCardNo() 
    {
        return cardNo;
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
    public void setIssueDate(Date issueDate) 
    {
        this.issueDate = issueDate;
    }

    public Date getIssueDate() 
    {
        return issueDate;
    }
    public void setExpireDate(Date expireDate) 
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() 
    {
        return expireDate;
    }
    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBalance() 
    {
        return balance;
    }
    public void setBorrowLimit(Long borrowLimit) 
    {
        this.borrowLimit = borrowLimit;
    }

    public Long getBorrowLimit() 
    {
        return borrowLimit;
    }

    public List<LibraryCardType> getLibraryCardTypeList()
    {
        return libraryCardTypeList;
    }
    public void setLibraryCardTypeList(List<LibraryCardType> libraryCardTypeList)
    {
        this.libraryCardTypeList = libraryCardTypeList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cardNo", getCardNo())
            .append("userId", getUserId())
            .append("cardTypeId", getCardTypeId())
            .append("status", getStatus())
            .append("issueDate", getIssueDate())
            .append("expireDate", getExpireDate())
            .append("balance", getBalance())
            .append("borrowLimit", getBorrowLimit())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }

}
