package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.LibraryCardType;
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
//import com.library.domain.LibraryCardType;
import com.library.service.ILibraryCardTypeService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 图书卡类型Controller
 * 
 * @author Echo
 * @date 2025-12-24
 */
@RestController
@RequestMapping("/types/type")
public class LibraryCardTypeController extends BaseController
{
    @Autowired
    private ILibraryCardTypeService libraryCardTypeService;

    /**
     * 查询图书卡类型列表
     */
    @PreAuthorize("@ss.hasPermi('types:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(LibraryCardType libraryCardType)
    {
        startPage();
        List<LibraryCardType> list = libraryCardTypeService.selectLibraryCardTypeList(libraryCardType);
        return getDataTable(list);
    }

    /**
     * 导出图书卡类型列表
     */
    @PreAuthorize("@ss.hasPermi('types:type:export')")
    @Log(title = "图书卡类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LibraryCardType libraryCardType)
    {
        List<LibraryCardType> list = libraryCardTypeService.selectLibraryCardTypeList(libraryCardType);
        ExcelUtil<LibraryCardType> util = new ExcelUtil<LibraryCardType>(LibraryCardType.class);
        util.exportExcel(response, list, "图书卡类型数据");
    }

    /**
     * 获取图书卡类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('types:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(libraryCardTypeService.selectLibraryCardTypeById(id));
    }

    /**
     * 新增图书卡类型
     */
    @PreAuthorize("@ss.hasPermi('types:type:add')")
    @Log(title = "图书卡类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LibraryCardType libraryCardType)
    {
        return toAjax(libraryCardTypeService.insertLibraryCardType(libraryCardType));
    }

    /**
     * 修改图书卡类型
     */
    @PreAuthorize("@ss.hasPermi('types:type:edit')")
    @Log(title = "图书卡类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LibraryCardType libraryCardType)
    {
        return toAjax(libraryCardTypeService.updateLibraryCardType(libraryCardType));
    }

    /**
     * 删除图书卡类型
     */
    @PreAuthorize("@ss.hasPermi('types:type:remove')")
    @Log(title = "图书卡类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(libraryCardTypeService.deleteLibraryCardTypeByIds(ids));
    }
}
