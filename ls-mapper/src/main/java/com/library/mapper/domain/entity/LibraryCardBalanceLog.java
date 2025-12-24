package com.library.mapper.domain.entity;

import java.math.BigDecimal;
import java.util.List;

import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 押金 / 余额流水记录对象 library_card_balance_log
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
public class LibraryCardBalanceLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图书卡ID */
    @Excel(name = "图书卡ID")
    private Long cardId;

    /** 改变的数量 */
    @Excel(name = "改变的数量")
    private BigDecimal changeAmount;

    /** 改变后余额数量 */
    @Excel(name = "改变后余额数量")
    private BigDecimal balanceAfter;

    /** 类型（1充值、2扣款、3退还押金 */
    @Excel(name = "类型", readConverterExp = "类型（1充值、2扣款、3退还押金")
    private Long type;

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

    public void setCardId(Long cardId) 
    {
        this.cardId = cardId;
    }

    public Long getCardId() 
    {
        return cardId;
    }

    public void setChangeAmount(BigDecimal changeAmount) 
    {
        this.changeAmount = changeAmount;
    }

    public BigDecimal getChangeAmount() 
    {
        return changeAmount;
    }

    public void setBalanceAfter(BigDecimal balanceAfter) 
    {
        this.balanceAfter = balanceAfter;
    }

    public BigDecimal getBalanceAfter() 
    {
        return balanceAfter;
    }

    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
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
            .append("cardId", getCardId())
            .append("changeAmount", getChangeAmount())
            .append("balanceAfter", getBalanceAfter())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("libraryCardList", getLibraryCardList())
            .toString();
    }
}
