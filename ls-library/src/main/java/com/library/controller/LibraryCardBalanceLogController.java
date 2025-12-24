package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.LibraryCardBalanceLog;
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
//import com.library.domain.LibraryCardBalanceLog;
import com.library.service.ILibraryCardBalanceLogService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 押金 / 余额流水记录Controller
 * 
 * @author ruoyi
 * @date 2025-12-24
 */
@RestController
@RequestMapping("/balance/log")
public class LibraryCardBalanceLogController extends BaseController
{
    @Autowired
    private ILibraryCardBalanceLogService libraryCardBalanceLogService;

    /**
     * 查询押金 / 余额流水记录列表
     */
    @PreAuthorize("@ss.hasPermi('balance:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(LibraryCardBalanceLog libraryCardBalanceLog)
    {
        startPage();
        List<LibraryCardBalanceLog> list = libraryCardBalanceLogService.selectLibraryCardBalanceLogList(libraryCardBalanceLog);
        return getDataTable(list);
    }

    /**
     * 导出押金 / 余额流水记录列表
     */
    @PreAuthorize("@ss.hasPermi('balance:log:export')")
    @Log(title = "押金 / 余额流水记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LibraryCardBalanceLog libraryCardBalanceLog)
    {
        List<LibraryCardBalanceLog> list = libraryCardBalanceLogService.selectLibraryCardBalanceLogList(libraryCardBalanceLog);
        ExcelUtil<LibraryCardBalanceLog> util = new ExcelUtil<LibraryCardBalanceLog>(LibraryCardBalanceLog.class);
        util.exportExcel(response, list, "押金 / 余额流水记录数据");
    }

    /**
     * 获取押金 / 余额流水记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('balance:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(libraryCardBalanceLogService.selectLibraryCardBalanceLogById(id));
    }

    /**
     * 新增押金 / 余额流水记录
     */
    @PreAuthorize("@ss.hasPermi('balance:log:add')")
    @Log(title = "押金 / 余额流水记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LibraryCardBalanceLog libraryCardBalanceLog)
    {
        return toAjax(libraryCardBalanceLogService.insertLibraryCardBalanceLog(libraryCardBalanceLog));
    }

    /**
     * 修改押金 / 余额流水记录
     */
    @PreAuthorize("@ss.hasPermi('balance:log:edit')")
    @Log(title = "押金 / 余额流水记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LibraryCardBalanceLog libraryCardBalanceLog)
    {
        return toAjax(libraryCardBalanceLogService.updateLibraryCardBalanceLog(libraryCardBalanceLog));
    }

    /**
     * 删除押金 / 余额流水记录
     */
    @PreAuthorize("@ss.hasPermi('balance:log:remove')")
    @Log(title = "押金 / 余额流水记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(libraryCardBalanceLogService.deleteLibraryCardBalanceLogByIds(ids));
    }
}
