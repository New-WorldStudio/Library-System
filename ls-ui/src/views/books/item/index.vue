<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书条形码" prop="barcode">
        <el-input
          v-model="queryParams.barcode"
          placeholder="请输入图书条形码"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存放位置" prop="localtion">
        <el-input
          v-model="queryParams.localtion"
          placeholder="请输入存放位置"
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
          v-hasPermi="['books:item:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['books:item:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['books:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['books:item:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书ID" align="center" prop="bookId" />
      <el-table-column label="图书条形码" align="center" prop="barcode" />
      <el-table-column label="状态 1可借、2、借出、3损坏，4丢失" align="center" prop="status" />
      <el-table-column label="存放位置" align="center" prop="localtion" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['books:item:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['books:item:remove']">删除</el-button>
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

    <!-- 添加或修改图书物品对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="itemRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书条形码" prop="barcode">
          <el-input v-model="form.barcode" placeholder="请输入图书条形码" />
        </el-form-item>
        <el-form-item label="存放位置" prop="localtion">
          <el-input v-model="form.localtion" placeholder="请输入存放位置" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">图书详情信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddBookMeta">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteBookMeta">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="bookMetaList" :row-class-name="rowBookMetaIndex" @selection-change="handleBookMetaSelectionChange" ref="bookMeta">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="图书标题" prop="title" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.title" placeholder="请输入图书标题" />
            </template>
          </el-table-column>
          <el-table-column label="图书副标题" prop="subtitle" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.subtitle" placeholder="请输入图书副标题" />
            </template>
          </el-table-column>
          <el-table-column label="卷册号" prop="volumeNumber" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.volumeNumber" placeholder="请输入卷册号" />
            </template>
          </el-table-column>
          <el-table-column label="系列名" prop="seriesName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.seriesName" placeholder="请输入系列名" />
            </template>
          </el-table-column>
          <el-table-column label="并列书名" prop="parallelBookTitles" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.parallelBookTitles" placeholder="请输入并列书名" />
            </template>
          </el-table-column>
          <el-table-column label="作者" prop="author" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.author" placeholder="请输入作者" />
            </template>
          </el-table-column>
          <el-table-column label="译者" prop="translator" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.translator" placeholder="请输入译者" />
            </template>
          </el-table-column>
          <el-table-column label="编者" prop="editor" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.editor" placeholder="请输入编者" />
            </template>
          </el-table-column>
          <el-table-column label="丛书主编" prop="seriesEditor" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.seriesEditor" placeholder="请输入丛书主编" />
            </template>
          </el-table-column>
          <el-table-column label="版次" prop="edition" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.edition" placeholder="请输入版次" />
            </template>
          </el-table-column>
          <el-table-column label="版本说明" prop="releaseRemark" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.releaseRemark" placeholder="请输入版本说明" />
            </template>
          </el-table-column>
          <el-table-column label="出版社" prop="publisher" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.publisher" placeholder="请输入出版社" />
            </template>
          </el-table-column>
          <el-table-column label="出版日期" prop="publicationDate" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.publicationDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择出版日期">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column label="分类号" prop="classificationNumber" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.classificationNumber" placeholder="请输入分类号" />
            </template>
          </el-table-column>
          <el-table-column label="定价" prop="pricing" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.pricing" placeholder="请输入定价" />
            </template>
          </el-table-column>
          <el-table-column label="介质" prop="medium" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.medium" placeholder="请输入介质" />
            </template>
          </el-table-column>
        </el-table>
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

<script setup name="Item">
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/books/item";

const { proxy } = getCurrentInstance();

const itemList = ref([]);
const bookMetaList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedBookMeta = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    barcode: null,
    status: null,
    localtion: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询图书物品列表 */
function getList() {
  loading.value = true;
  listItem(queryParams.value).then(response => {
    itemList.value = response.rows;
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
    bookId: null,
    barcode: null,
    status: null,
    localtion: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  bookMetaList.value = [];
  proxy.resetForm("itemRef");
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
  ids.value = selection.map(item => item.bookId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加图书物品";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _bookId = row.bookId || ids.value
  getItem(_bookId).then(response => {
    form.value = response.data;
    bookMetaList.value = response.data.bookMetaList;
    open.value = true;
    title.value = "修改图书物品";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["itemRef"].validate(valid => {
    if (valid) {
      form.value.bookMetaList = bookMetaList.value;
      if (form.value.bookId != null) {
        updateItem(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addItem(form.value).then(response => {
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
  const _bookIds = row.bookId || ids.value;
  proxy.$modal.confirm('是否确认删除图书物品编号为"' + _bookIds + '"的数据项？').then(function() {
    return delItem(_bookIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 图书详情序号 */
function rowBookMetaIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 图书详情添加按钮操作 */
function handleAddBookMeta() {
  let obj = {};
  obj.title = "";
  obj.subtitle = "";
  obj.volumeNumber = "";
  obj.seriesName = "";
  obj.parallelBookTitles = "";
  obj.author = "";
  obj.translator = "";
  obj.editor = "";
  obj.seriesEditor = "";
  obj.edition = "";
  obj.releaseRemark = "";
  obj.publisher = "";
  obj.publicationDate = "";
  obj.classificationNumber = "";
  obj.pricing = "";
  obj.medium = "";
  bookMetaList.value.push(obj);
}

/** 图书详情删除按钮操作 */
function handleDeleteBookMeta() {
  if (checkedBookMeta.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的图书详情数据");
  } else {
    const bookMetas = bookMetaList.value;
    const checkedBookMetas = checkedBookMeta.value;
    bookMetaList.value = bookMetas.filter(function(item) {
      return checkedBookMetas.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleBookMetaSelectionChange(selection) {
  checkedBookMeta.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('books/item/export', {
    ...queryParams.value
  }, `item_${new Date().getTime()}.xlsx`)
}

getList();
</script>
