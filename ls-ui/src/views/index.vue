<template>
  <div class="page-container">
    <el-card class="main-card" shadow="never">
      <header class="welcome-header">
        <h1>欢迎回来，<span>{{ userStore.name || '同学' }}</span></h1>
      </header>
      <el-row :gutter="30" class="stat-container">
        <el-col :span="12">
          <el-card shadow="hover" class="chart-card">
            <template #header>
              <div class="card-header">
                <span>当前热门借阅书籍</span>
              </div>
            </template>
        
            <div class="chart-content">
            
            </div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card shadow="hover" class="chart-card">
            <template #header>
              <div class="card-header">
                <span>借阅情况</span>
              </div>
            </template>
            <div class="chart-content">
              <el-progress
                  type="circle"
                  :percentage="75"
                  :stroke-width="10"
                  color="#409eff"
              />
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div ref="chart" style="width: 600px; height: 400px;"></div>
    </el-card>
  </div>
</template>

<script setup name="Index">
import useUserStore from '@/store/modules/user'
import * as echarts from 'echarts'
import {ref, onMounted} from 'vue'
const userStore = useUserStore()
const chart = ref(null);

onMounted(() =>{
  const echart = echarts.init(chart.value);

  const option = {
    title: {
      text: '最近借阅情况'
    },
    tooltip: {},
    legend: {
      data: ['借阅数量']
    },
    xAxis: {
      data: ['水浒传', '三国', '西游记', '红楼梦', '人生', '袜子']
    },
    yAxis: {},
    series: [
      {
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
      }
    ]
  };

  // 4. 设置配置项
  echart.setOption(option);
})

</script>

<style scoped lang="scss">
// 页面整体容器
.page-container {
  padding: 20px;
  background-color: #f5f7fa; // 浅灰色背景，突出白色卡片
  min-height: 100vh;

// 主卡片样式
.main-card {
  min-height: 800px;
  border-radius: 8px;
  border: none;
}
.welcome-header {
  margin-bottom: 30px;
  h1 {
    font-size: 24px;
    font-weight: 600;
    color: #303133;
    span { color: #409eff; }
  }
  .subtitle {
    color: #909399;
    margin-top: 8px;
    font-size: 14px;
  }
}
.stat-container {
  margin-bottom: 30px;
}
// 单个图表卡片内部结构
.chart-card {
  border-radius: 10px;
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-weight: bold;
  }
}
// 图表内容区域（居中对齐）
.chart-content {
  height: 180px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #c3f0f6;
  border-radius: 8px;
  .info {
    text-align: center;
    .num {
      font-size: 36px;
      font-weight: bold;
      color: #409eff;
      margin: 0;
    }
    .label {
      font-size: 14px;
      color: #909399;
    }
  }
}
// 底部列表区域
.recent-activity {
  margin-top: 40px;
  h3 {
    margin-bottom: 20px;
    padding-left: 10px;
    border-left: 4px solid #409eff;
    font-size: 18px;
  }
  .empty-list {
    border: 1px dashed #dcdfe6; // 虚线边框更显轻盈
    }
  }
}
</style>