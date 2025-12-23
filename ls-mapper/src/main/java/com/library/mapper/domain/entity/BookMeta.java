package com.library.mapper.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.library.common.annotation.Excel;

/**
 * 图书详情对象 book_meta
 * 
 * @author Echo
 * @date 2025-12-23
 */
public class BookMeta extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书元信息ID */
    private Long id;

    /** 图书ID */
    private Long bookId;

    /** 图书标题 */
    @Excel(name = "图书标题")
    private String title;

    /** 图书副标题 */
    @Excel(name = "图书副标题")
    private String subtitle;

    /** 卷册号 */
    @Excel(name = "卷册号")
    private String volumeNumber;

    /** 系列名 */
    @Excel(name = "系列名")
    private String seriesName;

    /** 并列书名 */
    @Excel(name = "并列书名")
    private String parallelBookTitles;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 译者 */
    @Excel(name = "译者")
    private String translator;

    /** 编者 */
    @Excel(name = "编者")
    private String editor;

    /** 丛书主编 */
    @Excel(name = "丛书主编")
    private String seriesEditor;

    /** 版次 */
    @Excel(name = "版次")
    private String edition;

    /** 版本说明（修订版、精装版） */
    @Excel(name = "版本说明", readConverterExp = "修=订版、精装版")
    private String releaseRemark;

    /** 出版社 */
    @Excel(name = "出版社")
    private String publisher;

    /** 出版日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出版日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publicationDate;

    /** 分类号 */
    @Excel(name = "分类号")
    private String classificationNumber;

    /** 定价（价格以整数存储） */
    @Excel(name = "定价", readConverterExp = "价=格以整数存储")
    private Long pricing;

    /** 介质（纸质、电子书） */
    @Excel(name = "介质", readConverterExp = "纸=质、电子书")
    private String medium;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setBookId(Long bookId) 
    {
        this.bookId = bookId;
    }

    public Long getBookId() 
    {
        return bookId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setSubtitle(String subtitle) 
    {
        this.subtitle = subtitle;
    }

    public String getSubtitle() 
    {
        return subtitle;
    }

    public void setVolumeNumber(String volumeNumber) 
    {
        this.volumeNumber = volumeNumber;
    }

    public String getVolumeNumber() 
    {
        return volumeNumber;
    }

    public void setSeriesName(String seriesName) 
    {
        this.seriesName = seriesName;
    }

    public String getSeriesName() 
    {
        return seriesName;
    }

    public void setParallelBookTitles(String parallelBookTitles) 
    {
        this.parallelBookTitles = parallelBookTitles;
    }

    public String getParallelBookTitles() 
    {
        return parallelBookTitles;
    }

    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }

    public void setTranslator(String translator) 
    {
        this.translator = translator;
    }

    public String getTranslator() 
    {
        return translator;
    }

    public void setEditor(String editor) 
    {
        this.editor = editor;
    }

    public String getEditor() 
    {
        return editor;
    }

    public void setSeriesEditor(String seriesEditor) 
    {
        this.seriesEditor = seriesEditor;
    }

    public String getSeriesEditor() 
    {
        return seriesEditor;
    }

    public void setEdition(String edition) 
    {
        this.edition = edition;
    }

    public String getEdition() 
    {
        return edition;
    }

    public void setReleaseRemark(String releaseRemark) 
    {
        this.releaseRemark = releaseRemark;
    }

    public String getReleaseRemark() 
    {
        return releaseRemark;
    }

    public void setPublisher(String publisher) 
    {
        this.publisher = publisher;
    }

    public String getPublisher() 
    {
        return publisher;
    }

    public void setPublicationDate(Date publicationDate) 
    {
        this.publicationDate = publicationDate;
    }

    public Date getPublicationDate() 
    {
        return publicationDate;
    }

    public void setClassificationNumber(String classificationNumber) 
    {
        this.classificationNumber = classificationNumber;
    }

    public String getClassificationNumber() 
    {
        return classificationNumber;
    }

    public void setPricing(Long pricing) 
    {
        this.pricing = pricing;
    }

    public Long getPricing() 
    {
        return pricing;
    }

    public void setMedium(String medium) 
    {
        this.medium = medium;
    }

    public String getMedium() 
    {
        return medium;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookId", getBookId())
            .append("title", getTitle())
            .append("subtitle", getSubtitle())
            .append("volumeNumber", getVolumeNumber())
            .append("seriesName", getSeriesName())
            .append("parallelBookTitles", getParallelBookTitles())
            .append("author", getAuthor())
            .append("translator", getTranslator())
            .append("editor", getEditor())
            .append("seriesEditor", getSeriesEditor())
            .append("edition", getEdition())
            .append("releaseRemark", getReleaseRemark())
            .append("publisher", getPublisher())
            .append("publicationDate", getPublicationDate())
            .append("classificationNumber", getClassificationNumber())
            .append("pricing", getPricing())
            .append("medium", getMedium())
            .toString();
    }
}
