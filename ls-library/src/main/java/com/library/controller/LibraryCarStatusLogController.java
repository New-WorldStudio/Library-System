package com.library.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.library.mapper.domain.entity.LibraryCarStatusLog;
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
//import com.library.domain.LibraryCarStatusLog;
import com.library.service.ILibraryCarStatusLogService;
import com.library.common.utils.poi.ExcelUtil;
import com.library.common.core.page.TableDataInfo;

/**
 * 图书卡状态日志记录Controller
 * 
 * @author Echo
 * @date 2025-12-24
 */
@RestController
@RequestMapping("/statusLog/statusLogs")
public class LibraryCarStatusLogController extends BaseController
{
    @Autowired
    private ILibraryCarStatusLogService libraryCarStatusLogService;

    /**
     * 查询图书卡状态日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:list')")
    @GetMapping("/list")
    public TableDataInfo list(LibraryCarStatusLog libraryCarStatusLog)
    {
        startPage();
        List<LibraryCarStatusLog> list = libraryCarStatusLogService.selectLibraryCarStatusLogList(libraryCarStatusLog);
        return getDataTable(list);
    }

    /**
     * 导出图书卡状态日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:export')")
    @Log(title = "图书卡状态日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LibraryCarStatusLog libraryCarStatusLog)
    {
        List<LibraryCarStatusLog> list = libraryCarStatusLogService.selectLibraryCarStatusLogList(libraryCarStatusLog);
        ExcelUtil<LibraryCarStatusLog> util = new ExcelUtil<LibraryCarStatusLog>(LibraryCarStatusLog.class);
        util.exportExcel(response, list, "图书卡状态日志记录数据");
    }

    /**
     * 获取图书卡状态日志记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(libraryCarStatusLogService.selectLibraryCarStatusLogById(id));
    }

    /**
     * 新增图书卡状态日志记录
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:add')")
    @Log(title = "图书卡状态日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LibraryCarStatusLog libraryCarStatusLog)
    {
        return toAjax(libraryCarStatusLogService.insertLibraryCarStatusLog(libraryCarStatusLog));
    }

    /**
     * 修改图书卡状态日志记录
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:edit')")
    @Log(title = "图书卡状态日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LibraryCarStatusLog libraryCarStatusLog)
    {
        return toAjax(libraryCarStatusLogService.updateLibraryCarStatusLog(libraryCarStatusLog));
    }

    /**
     * 删除图书卡状态日志记录
     */
    @PreAuthorize("@ss.hasPermi('statusLog:statusLogs:remove')")
    @Log(title = "图书卡状态日志记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(libraryCarStatusLogService.deleteLibraryCarStatusLogByIds(ids));
    }
}
