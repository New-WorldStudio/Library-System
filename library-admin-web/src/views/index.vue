<template>
  <el-card class="app-container home">
    <h1>数据统计看台</h1>
    <div ref="main" :style="{width:'600px',height:'400px'}"></div>
  </el-card>
</template>

<script>
import * as echarts from 'echarts';

// 定义 ECharts 配置项
const options = {
  title: {
    text: '测试 - 销量图表',
    left: 'center'
  },
  tooltip: {},
  xAxis: {
    data: ['红楼梦', '西游记', '哈利波特', '小夫人', '斗罗大陆', '龙族']
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

export default {
  name: 'EchartsOptionAPI',
  // 1. 使用 data 存储 ECharts 实例
  data() {
    return {
      // 用于存储 ECharts 实例
      testCharts: null,
    };
  },

  methods: {
    // 2. 初始化 ECharts 实例的函数
    initEchart() {
      // 通过 this.$refs 访问模板中的 DOM 元素
      const chartDom = this.$refs.main;
      if (chartDom) {
        // 初始化图表
        this.testCharts = echarts.init(chartDom);
        this.testCharts.setOption(options);
      }
    },

    // 3. 窗口尺寸变化时的处理函数
    handleResize() {
      // 检查实例是否存在并调用 resize 方法
      if (this.testCharts) {
        this.testCharts.resize();
      }
    }
  },

  // 4. 生命周期钩子：组件挂载后执行初始化
  mounted() {
    this.initEchart();
    // 监听窗口变化，确保图表自适应
    window.addEventListener('resize', this.handleResize);
  },

  // 5. 生命周期钩子：组件卸载前执行资源清理
  // Vue 3 使用 beforeUnmount
  beforeUnmount() {
    // 移除监听器
    window.removeEventListener('resize', this.handleResize);

    // 销毁 ECharts 实例，释放资源
    if (this.testCharts) {
      this.testCharts.dispose();
      this.testCharts = null;
    }
  }
};
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
