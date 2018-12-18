<template>
  <div id="app">
    <router-view/>
    <div style="text-align: right">
      <el-tag type="info">发版时间：$appPublishDateTime</el-tag>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  route: {
    data: function (transition) {
      //  canActivate阶段，可以做一些用户验证的事情(是否可以被激活)
      //  在验证阶段，当一个组件将要被切入的时候被调用。
      console.log(new Date().getTime() + '=============================')
      setTimeout(function () {
        // 这里 _this.$loadingRouteData 是 true
        transition.next({msg: '加载后的数据'})
        console.log(new Date().getTime() + '=============================')
        // 在调用完transition.next 后，_this.$loadingRouteData 为 false
      }, 4000)
    }
  }
}
</script>

<style>
@import "assets/css/index.css";

#app {
  /* fonts-family: 'Avenir', Helvetica, Arial, sans-serif; */
  /* -webkit-fonts-smoothing: antialiased; */
  /* -moz-osx-fonts-smoothing: grayscale; */
  /* text-align: center; */
  /* color: #2c3e50; */
  /* margin-top: 60px; */
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  height: 100vh;
}
</style>
