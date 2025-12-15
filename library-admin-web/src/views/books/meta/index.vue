<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入图书标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书副标题" prop="subtitle">
        <el-input
          v-model="queryParams.subtitle"
          placeholder="请输入图书副标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卷册号" prop="volumeNumber">
        <el-input
          v-model="queryParams.volumeNumber"
          placeholder="请输入卷册号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列名" prop="seriesName">
        <el-input
          v-model="queryParams.seriesName"
          placeholder="请输入系列名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="并列书名" prop="parallelBookTitles">
        <el-input
          v-model="queryParams.parallelBookTitles"
          placeholder="请输入并列书名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="译者" prop="translator">
        <el-input
          v-model="queryParams.translator"
          placeholder="请输入译者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编者" prop="editor">
        <el-input
          v-model="queryParams.editor"
          placeholder="请输入编者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="丛书主编" prop="seriesEditor">
        <el-input
          v-model="queryParams.seriesEditor"
          placeholder="请输入丛书主编"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版次" prop="edition">
        <el-input
          v-model="queryParams.edition"
          placeholder="请输入版次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本说明" prop="releaseRemark">
        <el-input
          v-model="queryParams.releaseRemark"
          placeholder="请输入版本说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input
          v-model="queryParams.publisher"
          placeholder="请输入出版社"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版日期" prop="publicationDate">
        <el-date-picker clearable
          v-model="queryParams.publicationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出版日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="分类号" prop="classificationNumber">
        <el-input
          v-model="queryParams.classificationNumber"
          placeholder="请输入分类号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="定价" prop="pricing">
        <el-input
          v-model="queryParams.pricing"
          placeholder="请输入定价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="介质" prop="medium">
        <el-input
          v-model="queryParams.medium"
          placeholder="请输入介质"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['books:meta:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['books:meta:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['books:meta:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['books:meta:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="metaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书元信息ID" align="center" prop="id" />
      <el-table-column label="图书ID" align="center" prop="bookId" />
      <el-table-column label="图书标题" align="center" prop="title" />
      <el-table-column label="图书副标题" align="center" prop="subtitle" />
      <el-table-column label="卷册号" align="center" prop="volumeNumber" />
      <el-table-column label="系列名" align="center" prop="seriesName" />
      <el-table-column label="并列书名" align="center" prop="parallelBookTitles" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="译者" align="center" prop="translator" />
      <el-table-column label="编者" align="center" prop="editor" />
      <el-table-column label="丛书主编" align="center" prop="seriesEditor" />
      <el-table-column label="版次" align="center" prop="edition" />
      <el-table-column label="版本说明" align="center" prop="releaseRemark" />
      <el-table-column label="出版社" align="center" prop="publisher" />
      <el-table-column label="出版日期" align="center" prop="publicationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publicationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类号" align="center" prop="classificationNumber" />
      <el-table-column label="定价" align="center" prop="pricing" />
      <el-table-column label="介质" align="center" prop="medium" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['books:meta:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['books:meta:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改图书元信息
对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入图书标题" />
        </el-form-item>
        <el-form-item label="图书副标题" prop="subtitle">
          <el-input v-model="form.subtitle" placeholder="请输入图书副标题" />
        </el-form-item>
        <el-form-item label="卷册号" prop="volumeNumber">
          <el-input v-model="form.volumeNumber" placeholder="请输入卷册号" />
        </el-form-item>
        <el-form-item label="系列名" prop="seriesName">
          <el-input v-model="form.seriesName" placeholder="请输入系列名" />
        </el-form-item>
        <el-form-item label="并列书名" prop="parallelBookTitles">
          <el-input v-model="form.parallelBookTitles" placeholder="请输入并列书名" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="译者" prop="translator">
          <el-input v-model="form.translator" placeholder="请输入译者" />
        </el-form-item>
        <el-form-item label="编者" prop="editor">
          <el-input v-model="form.editor" placeholder="请输入编者" />
        </el-form-item>
        <el-form-item label="丛书主编" prop="seriesEditor">
          <el-input v-model="form.seriesEditor" placeholder="请输入丛书主编" />
        </el-form-item>
        <el-form-item label="版次" prop="edition">
          <el-input v-model="form.edition" placeholder="请输入版次" />
        </el-form-item>
        <el-form-item label="版本说明" prop="releaseRemark">
          <el-input v-model="form.releaseRemark" placeholder="请输入版本说明" />
        </el-form-item>
        <el-form-item label="出版社" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入出版社" />
        </el-form-item>
        <el-form-item label="出版日期" prop="publicationDate">
          <el-date-picker clearable
            v-model="form.publicationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出版日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="分类号" prop="classificationNumber">
          <el-input v-model="form.classificationNumber" placeholder="请输入分类号" />
        </el-form-item>
        <el-form-item label="定价" prop="pricing">
          <el-input v-model="form.pricing" placeholder="请输入定价" />
        </el-form-item>
        <el-form-item label="介质" prop="medium">
          <el-input v-model="form.medium" placeholder="请输入介质" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMeta, getMeta, delMeta, addMeta, updateMeta } from "@/api/books/meta";

export default {
  name: "Meta",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 图书元信息表格数据
      metaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        subtitle: null,
        volumeNumber: null,
        seriesName: null,
        parallelBookTitles: null,
        author: null,
        translator: null,
        editor: null,
        seriesEditor: null,
        edition: null,
        releaseRemark: null,
        publisher: null,
        publicationDate: null,
        classificationNumber: null,
        pricing: null,
        medium: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询图书元信息
列表 */
    getList() {
      this.loading = true;
      listMeta(this.queryParams).then(response => {
        this.metaList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        bookId: null,
        title: null,
        subtitle: null,
        volumeNumber: null,
        seriesName: null,
        parallelBookTitles: null,
        author: null,
        translator: null,
        editor: null,
        seriesEditor: null,
        edition: null,
        releaseRemark: null,
        publisher: null,
        publicationDate: null,
        classificationNumber: null,
        pricing: null,
        medium: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加图书元信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMeta(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改图书元信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMeta(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMeta(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除图书元信息编号为"' + ids + '"的数据项？').then(function() {
        return delMeta(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('books/meta/export', {
        ...this.queryParams
      }, `meta_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
