package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.common.enums.BusinessType;
import com.library.mapper.domain.entity.BookMeta;
import com.library.service.IBookMetaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.common.annotation.Log;
import com.library.common.core.controller.BaseController;
import com.library.common.core.domain.AjaxResult;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 图书元信息
 Controller
 *
 * @author Echo
 * @date 2025-12-15
 */
@RestController
@RequestMapping("/meta/meta")
public class BookMetaController extends BaseController
{
    @Autowired
    private IBookMetaService bookMetaService;

    /**
     * 查询图书元信息
     列表
     */
    @PreAuthorize("@ss.hasPermi('meta:meta:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookMeta bookMeta)
    {
        startPage();
        List<BookMeta> list = bookMetaService.selectBookMetaList(bookMeta);
        return getDataTable(list);
    }

    /**
     * 导出图书元信息
     列表
     */
    @PreAuthorize("@ss.hasPermi('meta:meta:export')")
    @Log(title = "图书元信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookMeta bookMeta)
    {
        List<BookMeta> list = bookMetaService.selectBookMetaList(bookMeta);
        ExcelUtil<BookMeta> util = new ExcelUtil<BookMeta>(BookMeta.class);
        util.exportExcel(response, list, "图书元信息数据");
    }

    /**
     * 获取图书元信息
     详细信息
     */
    @PreAuthorize("@ss.hasPermi('meta:meta:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bookMetaService.selectBookMetaById(id));
    }

    /**
     * 新增图书元信息

     */
    @PreAuthorize("@ss.hasPermi('meta:meta:add')")
    @Log(title = "图书元信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookMeta bookMeta)
    {
        return toAjax(bookMetaService.insertBookMeta(bookMeta));
    }

    /**
     * 修改图书元信息

     */
    @PreAuthorize("@ss.hasPermi('meta:meta:edit')")
    @Log(title = "图书元信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookMeta bookMeta)
    {
        return toAjax(bookMetaService.updateBookMeta(bookMeta));
    }

    /**
     * 删除图书元信息

     */
    @PreAuthorize("@ss.hasPermi('meta:meta:remove')")
    @Log(title = "图书元信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bookMetaService.deleteBookMetaByIds(ids));
    }
}