<template>
  <el-card style="width: 100%; height: 100vh;">
    <h1>数据图表展示</h1>
    <el-card>
      <div ref="chartRef" style="width: 600px; height: 400px;"></div>
    </el-card> 
  </el-card>
</template>

<script setup name="Index">
import * as echarts from 'echarts';
import { ref, onMounted } from 'vue'

// 2. 使用 ref 定义 DOM 容器
const chartRef = ref(null);

onMounted(() => {
  // 3. 在挂载后初始化，确保能拿到 DOM
  const echart = echarts.init(chartRef.value);

  const option = {
    title: {
      text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
      data: ['销量']
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
});
</script>

<style>
* {
  margin: 0;
  padding: 0; /* 通常是 padding 而不是 border */
  box-sizing: border-box;
}
</style>