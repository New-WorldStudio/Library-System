<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入图书标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书副标题" prop="subtitle">
        <el-input
          v-model="queryParams.subtitle"
          placeholder="请输入图书副标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卷册号" prop="volumeNumber">
        <el-input
          v-model="queryParams.volumeNumber"
          placeholder="请输入卷册号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列名" prop="seriesName">
        <el-input
          v-model="queryParams.seriesName"
          placeholder="请输入系列名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="并列书名" prop="parallelBookTitles">
        <el-input
          v-model="queryParams.parallelBookTitles"
          placeholder="请输入并列书名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="译者" prop="translator">
        <el-input
          v-model="queryParams.translator"
          placeholder="请输入译者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编者" prop="editor">
        <el-input
          v-model="queryParams.editor"
          placeholder="请输入编者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="丛书主编" prop="seriesEditor">
        <el-input
          v-model="queryParams.seriesEditor"
          placeholder="请输入丛书主编"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版次" prop="edition">
        <el-input
          v-model="queryParams.edition"
          placeholder="请输入版次"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="版本说明" prop="releaseRemark">
        <el-input
          v-model="queryParams.releaseRemark"
          placeholder="请输入版本说明"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版社" prop="publisher">
        <el-input
          v-model="queryParams.publisher"
          placeholder="请输入出版社"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出版日期" prop="publicationDate">
        <el-date-picker clearable
          v-model="queryParams.publicationDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择出版日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="分类号" prop="classificationNumber">
        <el-input
          v-model="queryParams.classificationNumber"
          placeholder="请输入分类号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="定价" prop="pricing">
        <el-input
          v-model="queryParams.pricing"
          placeholder="请输入定价"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="介质" prop="medium">
        <el-input
          v-model="queryParams.medium"
          placeholder="请输入介质"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['books:meta:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['books:meta:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['books:meta:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['books:meta:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
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
        <template #default="scope">
          <span>{{ parseTime(scope.row.publicationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="分类号" align="center" prop="classificationNumber" />
      <el-table-column label="定价" align="center" prop="pricing" />
      <el-table-column label="介质" align="center" prop="medium" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['books:meta:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['books:meta:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改图书详情对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="metaRef" :model="form" :rules="rules" label-width="80px">
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
            value-format="YYYY-MM-DD"
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
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Meta">
import { listMeta, getMeta, delMeta, addMeta, updateMeta } from "@/api/books/meta";

const { proxy } = getCurrentInstance();

const metaList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
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
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询图书详情列表 */
function getList() {
  loading.value = true;
  listMeta(queryParams.value).then(response => {
    metaList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
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
  proxy.resetForm("metaRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加图书详情";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMeta(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改图书详情";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["metaRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMeta(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMeta(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除图书详情编号为"' + _ids + '"的数据项？').then(function() {
    return delMeta(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('books/meta/export', {
    ...queryParams.value
  }, `meta_${new Date().getTime()}.xlsx`)
}

getList();
</script>
