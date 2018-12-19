<template>
  <div>
    <div class="fps-title">
      <el-row type="flex" justify="start">
        <el-col style="padding-top: 4px;">区域 - DEMO</el-col>
        <el-col :span="18" :offset="2" style="text-align: right; padding-right: 20px;">
          <el-button type="info" size="mini">返回</el-button>
        </el-col>
      </el-row>
    </div>
    <div class="fps-content">
      <el-form ref="form" :model="form" label-width="80px" height="80vh" style="padding: 20px 50px 10px 50px" size="medium">
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="活动区域">
          <el-col :span="11">
            <el-cascader
              size="medium"
              :options="options"
              v-model="selectedOptions"
              style="width: 100%"
              @change="handleChange">
            </el-cascader>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-cascader
              size="medium"
              :options="options"
              v-model="selectedOptions2"
              style="width: 100%"
              @change="handleChange" disabled>
            </el-cascader>
          </el-col>
        </el-form-item>
        <el-form-item label="活动时间">
          <el-col :span="11">
            <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
          </el-col>
          <el-col class="line" :span="2">-</el-col>
          <el-col :span="11">
            <el-time-picker type="fixed-time" placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
          </el-col>
        </el-form-item>
        <el-form-item label="即时配送">
          <el-switch v-model="form.delivery"></el-switch>
        </el-form-item>
        <el-form-item label="活动性质">
          <el-checkbox-group v-model="form.type">
            <el-checkbox  v-for="item in $store.state.dictionary['活动性质']" :key="item.id" :label="item.name" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="特殊资源">
          <el-radio-group v-model="form.resource">
            <el-radio v-for="item in $store.state.dictionary['特殊资源']" :key="item.id" :label="item.name"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="活动形式">
          <el-input type="textarea" v-model="form.desc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { regionDataPlus } from 'element-china-area-data'
export default {
  data () {
    return {
      options: regionDataPlus,
      selectedOptions: [],
      selectedOptions2: ['120000', '120100', '120103'],
      pcCodeList: ['120000', '120100', '120103'],
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  created () {
    console.log('in')
    console.log(this.$store.state)
  },
  methods: {
    handleChange (value) {
      console.log(value)
    },
    onSubmit () {

    }
  }
}
</script>
