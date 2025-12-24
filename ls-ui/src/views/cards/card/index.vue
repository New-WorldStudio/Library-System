<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
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
      <el-form-item label="发卡时间" prop="issueDate">
        <el-date-picker clearable
          v-model="queryParams.issueDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择发卡时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="到期时间" prop="expireDate">
        <el-date-picker clearable
          v-model="queryParams.expireDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择到期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="押金 / 余额" prop="balance">
        <el-input
          v-model="queryParams.balance"
          placeholder="请输入押金 / 余额"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大借书量" prop="borrowLimit">
        <el-input
          v-model="queryParams.borrowLimit"
          placeholder="请输入最大借书量"
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
          v-hasPermi="['cards:card:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['cards:card:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['cards:card:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['cards:card:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书证ID" align="center" prop="id" />
      <el-table-column label="图书证号" align="center" prop="cardNo" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="卡类型" align="center" prop="cardTypeId" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="发卡时间" align="center" prop="issueDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.issueDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" align="center" prop="expireDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.expireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="押金 / 余额" align="center" prop="balance" />
      <el-table-column label="最大借书量" align="center" prop="borrowLimit" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['cards:card:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['cards:card:remove']">删除</el-button>
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

    <!-- 添加或修改图书卡对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="cardRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="卡类型" prop="cardTypeId">
          <el-input v-model="form.cardTypeId" placeholder="请输入卡类型" />
        </el-form-item>
        <el-form-item label="发卡时间" prop="issueDate">
          <el-date-picker clearable
            v-model="form.issueDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择发卡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="到期时间" prop="expireDate">
          <el-date-picker clearable
            v-model="form.expireDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择到期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="押金 / 余额" prop="balance">
          <el-input v-model="form.balance" placeholder="请输入押金 / 余额" />
        </el-form-item>
        <el-form-item label="最大借书量" prop="borrowLimit">
          <el-input v-model="form.borrowLimit" placeholder="请输入最大借书量" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">图书卡类型信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddLibraryCardType">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteLibraryCardType">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="libraryCardTypeList" :row-class-name="rowLibraryCardTypeIndex" @selection-change="handleLibraryCardTypeSelectionChange" ref="libraryCardType">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="编号代码" prop="code" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.code" placeholder="请输入编号代码" />
            </template>
          </el-table-column>
          <el-table-column label="名称" prop="name" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.name" placeholder="请输入名称" />
            </template>
          </el-table-column>
          <el-table-column label="最大借阅数量" prop="macBorrowCount" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.macBorrowCount" placeholder="请输入最大借阅数量" />
            </template>
          </el-table-column>
          <el-table-column label="是否需要押金" prop="needDeposit" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.needDeposit" placeholder="请输入是否需要押金" />
            </template>
          </el-table-column>
          <el-table-column label="押金价格" prop="depositAmount" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.depositAmount" placeholder="请输入押金价格" />
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

<script setup name="Card">
import { listCard, getCard, delCard, addCard, updateCard } from "@/api/cards/card";

const { proxy } = getCurrentInstance();

const cardList = ref([]);
const libraryCardTypeList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedLibraryCardType = ref([]);
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
    issueDate: null,
    expireDate: null,
    balance: null,
    borrowLimit: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询图书卡列表 */
function getList() {
  loading.value = true;
  listCard(queryParams.value).then(response => {
    cardList.value = response.rows;
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
    cardNo: null,
    userId: null,
    cardTypeId: null,
    status: null,
    issueDate: null,
    expireDate: null,
    balance: null,
    borrowLimit: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  libraryCardTypeList.value = [];
  proxy.resetForm("cardRef");
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
  title.value = "添加图书卡";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getCard(_id).then(response => {
    form.value = response.data;
    libraryCardTypeList.value = response.data.libraryCardTypeList;
    open.value = true;
    title.value = "修改图书卡";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["cardRef"].validate(valid => {
    if (valid) {
      form.value.libraryCardTypeList = libraryCardTypeList.value;
      if (form.value.id != null) {
        updateCard(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addCard(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除图书卡编号为"' + _ids + '"的数据项？').then(function() {
    return delCard(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 图书卡类型序号 */
function rowLibraryCardTypeIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 图书卡类型添加按钮操作 */
function handleAddLibraryCardType() {
  let obj = {};
  obj.code = "";
  obj.name = "";
  obj.macBorrowCount = "";
  obj.needDeposit = "";
  obj.depositAmount = "";
  obj.description = "";
  obj.remark = "";
  libraryCardTypeList.value.push(obj);
}

/** 图书卡类型删除按钮操作 */
function handleDeleteLibraryCardType() {
  if (checkedLibraryCardType.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的图书卡类型数据");
  } else {
    const libraryCardTypes = libraryCardTypeList.value;
    const checkedLibraryCardTypes = checkedLibraryCardType.value;
    libraryCardTypeList.value = libraryCardTypes.filter(function(item) {
      return checkedLibraryCardTypes.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleLibraryCardTypeSelectionChange(selection) {
  checkedLibraryCardType.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('cards/card/export', {
    ...queryParams.value
  }, `card_${new Date().getTime()}.xlsx`)
}

getList();
</script>
