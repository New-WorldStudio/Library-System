<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡类型" prop="cardTypeId">
        <el-input
          v-model="queryParams.cardTypeId"
          placeholder="请输入卡类型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="谁审核的" prop="reviewBy">
        <el-input
          v-model="queryParams.reviewBy"
          placeholder="请输入谁审核的"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核通过时间" prop="reviewAt">
        <el-date-picker clearable
          v-model="queryParams.reviewAt"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择审核通过时间">
        </el-date-picker>
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
          v-hasPermi="['applications:application:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['applications:application:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['applications:application:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['applications:application:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="办卡申请ID" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="卡类型" align="center" prop="cardTypeId" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="谁审核的" align="center" prop="reviewBy" />
      <el-table-column label="审核通过时间" align="center" prop="reviewAt" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.reviewAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['applications:application:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['applications:application:remove']">删除</el-button>
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

    <!-- 添加或修改办卡申请对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="applicationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>
        <el-form-item label="卡类型" prop="cardTypeId">
          <el-input v-model="form.cardTypeId" placeholder="请输入卡类型" />
        </el-form-item>
        <el-form-item label="谁审核的" prop="reviewBy">
          <el-input v-model="form.reviewBy" placeholder="请输入谁审核的" />
        </el-form-item>
        <el-form-item label="审核通过时间" prop="reviewAt">
          <el-date-picker clearable
            v-model="form.reviewAt"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择审核通过时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">图书卡信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddLibraryCard">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteLibraryCard">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="libraryCardList" :row-class-name="rowLibraryCardIndex" @selection-change="handleLibraryCardSelectionChange" ref="libraryCard">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="用户ID" prop="userId" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.userId" placeholder="请输入用户ID" />
            </template>
          </el-table-column>
          <el-table-column label="卡类型" prop="cardTypeId" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.cardTypeId" placeholder="请输入卡类型" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status" width="150">
            <template #default="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="发卡时间" prop="issueDate" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.issueDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择发卡时间">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column label="到期时间" prop="expireDate" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.expireDate"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择到期时间">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column label="押金 / 余额" prop="balance" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.balance" placeholder="请输入押金 / 余额" />
            </template>
          </el-table-column>
          <el-table-column label="最大借书量" prop="borrowLimit" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.borrowLimit" placeholder="请输入最大借书量" />
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

<script setup name="Application">
import { listApplication, getApplication, delApplication, addApplication, updateApplication } from "@/api/applications/application";

const { proxy } = getCurrentInstance();

const applicationList = ref([]);
const libraryCardList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedLibraryCard = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    cardTypeId: null,
    status: null,
    reviewBy: null,
    reviewAt: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询办卡申请列表 */
function getList() {
  loading.value = true;
  listApplication(queryParams.value).then(response => {
    applicationList.value = response.rows;
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
    userId: null,
    cardTypeId: null,
    status: null,
    reviewBy: null,
    reviewAt: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  libraryCardList.value = [];
  proxy.resetForm("applicationRef");
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
  title.value = "添加办卡申请";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getApplication(_id).then(response => {
    form.value = response.data;
    libraryCardList.value = response.data.libraryCardList;
    open.value = true;
    title.value = "修改办卡申请";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["applicationRef"].validate(valid => {
    if (valid) {
      form.value.libraryCardList = libraryCardList.value;
      if (form.value.id != null) {
        updateApplication(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addApplication(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除办卡申请编号为"' + _ids + '"的数据项？').then(function() {
    return delApplication(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 图书卡序号 */
function rowLibraryCardIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 图书卡添加按钮操作 */
function handleAddLibraryCard() {
  let obj = {};
  obj.userId = "";
  obj.cardTypeId = "";
  obj.status = "";
  obj.issueDate = "";
  obj.expireDate = "";
  obj.balance = "";
  obj.borrowLimit = "";
  obj.remark = "";
  libraryCardList.value.push(obj);
}

/** 图书卡删除按钮操作 */
function handleDeleteLibraryCard() {
  if (checkedLibraryCard.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的图书卡数据");
  } else {
    const libraryCards = libraryCardList.value;
    const checkedLibraryCards = checkedLibraryCard.value;
    libraryCardList.value = libraryCards.filter(function(item) {
      return checkedLibraryCards.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleLibraryCardSelectionChange(selection) {
  checkedLibraryCard.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('applications/application/export', {
    ...queryParams.value
  }, `application_${new Date().getTime()}.xlsx`)
}

getList();
</script>
