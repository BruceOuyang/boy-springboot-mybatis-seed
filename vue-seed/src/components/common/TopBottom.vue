<template>
  <div>
    <transition name='slide-fade'>
      <div class='page-component-top' v-if='isShowGoTop' @click='goTop'>
        <i class="el-icon-caret-top"></i>
      </div>
    </transition>
    <transition name='slide-fade'>
      <div class='page-component-bottom' v-if='isShowGoBottom' @click='goBottom'>
        <i class="el-icon-caret-bottom"></i>
      </div>
    </transition>
  </div>
</template>
<script>
export default {
  name: 'TopBottom',
  data () {
    return {
      isShowGoTop: false,
      isShowGoBottom: false
    }
  },
  methods: {
    showIcon () {
      // 向下按钮
      let marginBottom = (document.documentElement.scrollHeight - document.documentElement.scrollTop)
      if (marginBottom > document.documentElement.clientHeight + 100) {
        this.isShowGoBottom = true
      } else if (marginBottom <= document.documentElement.clientHeight + 100) {
        this.isShowGoBottom = false
      }
      // 向上按钮
      if (document.documentElement.scrollTop > 100) {
        this.isShowGoTop = true
      } else if (document.documentElement.scrollTop < 100) {
        this.isShowGoTop = false
      }
    },
    goTop () {
      document.documentElement.scrollTop = 0
    },
    goBottom () {
      document.documentElement.scrollTop = document.documentElement.scrollHeight
    }
  },
  mounted () {
    this.showIcon()
    window.addEventListener('scroll', this.showIcon)
  },
  beforeDestroy () {
    window.removeEventListener('scroll', this.showIcon)
  }
}
</script>

<style lang="scss" rel="stylesheet/scss">
.slide-fade-enter-active {
  transition: all .1s ease;
}
.slide-fade-leave-active {
  transition: all .1s cubic-bezier(1.0, 0.3, 0.8, 1.0);
  opacity: 0;
}
.slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active 在低于 2.1.8 版本中 */ {
  // transform: translateY(-20px);
  opacity: 0;
}
.page-component-bottom {
  background-color: #FFFFFF;
  box-shadow: 0 0 6px rgba(0,0,0,.12);
  position: fixed;
  right: 2.5rem;
  bottom: 4rem;
  width: 40px;
  height: 40px;
  border-radius: 25px;
  cursor: pointer;
  opacity: 1;
  transition: .3s;
  text-align: center;
  z-index: 999;
}
.page-component-top {
  background-color: #FFFFFF;
  box-shadow: 0 0 6px rgba(0,0,0,.12);
  position: fixed;
  right: 2.5rem;
  bottom: 7rem;
  width: 40px;
  height: 40px;
  border-radius: 25px;
  cursor: pointer;
  opacity: 1;
  transition: .3s;
  text-align: center;
  z-index: 999;
}
.el-icon-caret-bottom {
  font-size: 18px;
  margin-top: 12px;
  color: #409EFF;
  text-align: center;
  z-index: 1000
}
.el-icon-caret-top {
  font-size: 18px;
  margin-top: 11px;
  color: #409EFF;
  text-align: center;
  z-index: 1000
}
</style>
