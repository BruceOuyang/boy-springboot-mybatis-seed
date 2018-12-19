<template>
  <el-card :name="title" :ref="title" v-show="displayCardFlag">
    <div slot="header" class="card-title" @click="cardTitleClickHandler">
      <span style="font-weight: bold">{{ title }}</span>
      <slot name="header"></slot>
      <el-button style="float: right; padding: 12px 20px; margin-top: -8px;" type="text"><i class="el-icon-arrow-up" v-show="visibled"/><i class="el-icon-arrow-down" v-show="!visibled"/></el-button>
    </div>
    <div v-show="visibled">
      <slot></slot>
    </div>
  </el-card>
</template>
<script>
export default {
  name: 'bdCard',
  data () {
    return {
      visibled: true,
      displayCardFlag: true
    }
  },
  props: {
    title: {
      type: String,
      required: true
    },
    supportDisplay: {
      type: Boolean,
      default: true
    }
  },
  mounted () {
    this.$bus.$on('collapseCard', this.collapseCard)
    this.$bus.$on('extendCard', this.extendCard)
    if (this.supportDisplay) {
      this.$bus.$on('displayCard', this.displayCard)
      this.$bus.$on('hiddenCard', this.hiddenCard)
    }
  },
  destroyed () {
    this.$bus.$off('collapseCard')
    this.$bus.$off('extendCard')
    this.$bus.$off('displayCard')
    this.$bus.$off('hiddenCard')
  },
  methods: {
    cardTitleClickHandler () {
      this.visibled = !this.visibled
    },
    collapseCard () {
      this.visibled = false
    },
    extendCard () {
      this.visibled = true
    },
    displayCard () {
      this.displayCardFlag = true
    },
    hiddenCard () {
      this.displayCardFlag = false
    }
  }
}
</script>
