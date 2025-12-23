package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.BookItem;
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
import com.library.common.enums.BusinessType;
import com.library.service.IBookItemService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 图书物品Controller
 * 
 * @author Echo
 * @date 2025-12-23
 */
@RestController
@RequestMapping("/books/item")
public class BookItemController extends BaseController
{
    @Autowired
    private IBookItemService bookItemService;

    /**
     * 查询图书物品列表
     */
    @PreAuthorize("@ss.hasPermi('books:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookItem bookItem)
    {
        startPage();
        List<BookItem> list = bookItemService.selectBookItemList(bookItem);
        return getDataTable(list);
    }

    /**
     * 导出图书物品列表
     */
    @PreAuthorize("@ss.hasPermi('books:item:export')")
    @Log(title = "图书物品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookItem bookItem)
    {
        List<BookItem> list = bookItemService.selectBookItemList(bookItem);
        ExcelUtil<BookItem> util = new ExcelUtil<BookItem>(BookItem.class);
        util.exportExcel(response, list, "图书物品数据");
    }

    /**
     * 获取图书物品详细信息
     */
    @PreAuthorize("@ss.hasPermi('books:item:query')")
    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId)
    {
        return success(bookItemService.selectBookItemByBookId(bookId));
    }

    /**
     * 新增图书物品
     */
    @PreAuthorize("@ss.hasPermi('books:item:add')")
    @Log(title = "图书物品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookItem bookItem)
    {
        return toAjax(bookItemService.insertBookItem(bookItem));
    }

    /**
     * 修改图书物品
     */
    @PreAuthorize("@ss.hasPermi('books:item:edit')")
    @Log(title = "图书物品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookItem bookItem)
    {
        return toAjax(bookItemService.updateBookItem(bookItem));
    }

    /**
     * 删除图书物品
     */
    @PreAuthorize("@ss.hasPermi('books:item:remove')")
    @Log(title = "图书物品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds)
    {
        return toAjax(bookItemService.deleteBookItemByBookIds(bookIds));
    }
}
