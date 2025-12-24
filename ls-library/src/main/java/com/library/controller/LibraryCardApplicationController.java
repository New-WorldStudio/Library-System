package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.LibraryCardApplication;
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
//import com.library.domain.LibraryCardApplication;
import com.library.service.ILibraryCardApplicationService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 办卡申请Controller
 * 
 * @author echo
 * @date 2025-12-24
 */
@RestController
@RequestMapping("/applications/application")
public class LibraryCardApplicationController extends BaseController
{
    @Autowired
    private ILibraryCardApplicationService libraryCardApplicationService;

    /**
     * 查询办卡申请列表
     */
    @PreAuthorize("@ss.hasPermi('applications:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(LibraryCardApplication libraryCardApplication)
    {
        startPage();
        List<LibraryCardApplication> list = libraryCardApplicationService.selectLibraryCardApplicationList(libraryCardApplication);
        return getDataTable(list);
    }

    /**
     * 导出办卡申请列表
     */
    @PreAuthorize("@ss.hasPermi('applications:application:export')")
    @Log(title = "办卡申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LibraryCardApplication libraryCardApplication)
    {
        List<LibraryCardApplication> list = libraryCardApplicationService.selectLibraryCardApplicationList(libraryCardApplication);
        ExcelUtil<LibraryCardApplication> util = new ExcelUtil<LibraryCardApplication>(LibraryCardApplication.class);
        util.exportExcel(response, list, "办卡申请数据");
    }

    /**
     * 获取办卡申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('applications:application:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(libraryCardApplicationService.selectLibraryCardApplicationById(id));
    }

    /**
     * 新增办卡申请
     */
    @PreAuthorize("@ss.hasPermi('applications:application:add')")
    @Log(title = "办卡申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LibraryCardApplication libraryCardApplication)
    {
        return toAjax(libraryCardApplicationService.insertLibraryCardApplication(libraryCardApplication));
    }

    /**
     * 修改办卡申请
     */
    @PreAuthorize("@ss.hasPermi('applications:application:edit')")
    @Log(title = "办卡申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LibraryCardApplication libraryCardApplication)
    {
        return toAjax(libraryCardApplicationService.updateLibraryCardApplication(libraryCardApplication));
    }

    /**
     * 删除办卡申请
     */
    @PreAuthorize("@ss.hasPermi('applications:application:remove')")
    @Log(title = "办卡申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(libraryCardApplicationService.deleteLibraryCardApplicationByIds(ids));
    }
}
