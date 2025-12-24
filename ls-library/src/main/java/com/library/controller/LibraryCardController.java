package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.LibraryCard;
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
//import com.library.domain.LibraryCard;
import com.library.service.ILibraryCardService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 图书卡Controller
 * 
 * @author Echo
 * @date 2025-12-24
 */
@RestController
@RequestMapping("/cards/card")
public class LibraryCardController extends BaseController
{
    @Autowired
    private ILibraryCardService libraryCardService;

    /**
     * 查询图书卡列表
     */
    @PreAuthorize("@ss.hasPermi('cards:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(LibraryCard libraryCard)
    {
        startPage();
        List<LibraryCard> list = libraryCardService.selectLibraryCardList(libraryCard);
        return getDataTable(list);
    }

    /**
     * 导出图书卡列表
     */
    @PreAuthorize("@ss.hasPermi('cards:card:export')")
    @Log(title = "图书卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LibraryCard libraryCard)
    {
        List<LibraryCard> list = libraryCardService.selectLibraryCardList(libraryCard);
        ExcelUtil<LibraryCard> util = new ExcelUtil<LibraryCard>(LibraryCard.class);
        util.exportExcel(response, list, "图书卡数据");
    }

    /**
     * 获取图书卡详细信息
     */
    @PreAuthorize("@ss.hasPermi('cards:card:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(libraryCardService.selectLibraryCardById(id));
    }

    /**
     * 新增图书卡
     */
    @PreAuthorize("@ss.hasPermi('cards:card:add')")
    @Log(title = "图书卡", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LibraryCard libraryCard)
    {
        return toAjax(libraryCardService.insertLibraryCard(libraryCard));
    }

    /**
     * 修改图书卡
     */
    @PreAuthorize("@ss.hasPermi('cards:card:edit')")
    @Log(title = "图书卡", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LibraryCard libraryCard)
    {
        return toAjax(libraryCardService.updateLibraryCard(libraryCard));
    }

    /**
     * 删除图书卡
     */
    @PreAuthorize("@ss.hasPermi('cards:card:remove')")
    @Log(title = "图书卡", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(libraryCardService.deleteLibraryCardByIds(ids));
    }
}
