package com.library.mapper.domain.entity;

import java.math.BigDecimal;

import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 图书卡类型对象 library_card_type
 * 
 * @author Echo
 * @date 2025-12-24
 */
public class LibraryCardType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 卡类型ID */
    private Long id;

    /** 编号代码 */
    @Excel(name = "编号代码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 最大借阅数量 */
    @Excel(name = "最大借阅数量")
    private Long macBorrowCount;

    /** 是否需要押金 */
    @Excel(name = "是否需要押金")
    private Long needDeposit;

    /** 押金价格 */
    @Excel(name = "押金价格")
    private BigDecimal depositAmount;

    /** 介绍 */
    @Excel(name = "介绍")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setMacBorrowCount(Long macBorrowCount) 
    {
        this.macBorrowCount = macBorrowCount;
    }

    public Long getMacBorrowCount() 
    {
        return macBorrowCount;
    }

    public void setNeedDeposit(Long needDeposit) 
    {
        this.needDeposit = needDeposit;
    }

    public Long getNeedDeposit() 
    {
        return needDeposit;
    }

    public void setDepositAmount(BigDecimal depositAmount) 
    {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getDepositAmount() 
    {
        return depositAmount;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("macBorrowCount", getMacBorrowCount())
            .append("needDeposit", getNeedDeposit())
            .append("depositAmount", getDepositAmount())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
