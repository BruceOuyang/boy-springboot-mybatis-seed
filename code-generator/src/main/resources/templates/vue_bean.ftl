<template>
  <div>
    <!-- 顶部查询框 -->
    <div class="fps-title">
      <el-row type="flex" justify="start">
        <el-col>
          <el-input v-model="queryParam.id" placeholder="请输入id" size="mini"></el-input>
        </el-col>
        <el-col :span="18" :offset="2" style="text-align: right; padding-right: 20px;">
          <el-button type="info" plain size="mini" @click="queryList">查询</el-button>
          <el-button type="primary" size="mini" @click="toAdd">新增</el-button>
        </el-col>
      </el-row>
    </div>

    <!-- 数据列表 -->
    <div class="fps-content">
      <el-table :data="list" border style="width: 100%" cell-class-name="list-table" size="mini" highlight-current-row stripe height="80vh">
        <el-table-column prop="id" label="ID" width="60"/>
        <#list table.propertyList as col>
        <el-table-column prop="${col.propertyName}" label="${col.columnComment}"/>
        </#list>
        <el-table-column label="操作" width="220">
          <template slot-scope="scope">
            <el-button size="mini" @click="toView(scope.row)">查看</el-button>
            <el-button size="mini" @click="toUpdate(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="remove(scope.row)">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="padding: 5px;">
        <el-pagination background layout="prev, pager, next, total, sizes"
                       :total="page.totalCount"
                       :page-size="page.pageSize"
                       :current-page.sync="page.pageNo"
                       :page-sizes="[100, 200, 300]"
                       @size-change="handleSizeChange"
                       @current-change="queryList"></el-pagination>
      </div>
    </div>

    <!-- 操作框 -->
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" top="0" width="60%" :append-to-body="true" :close-on-press-escape="false" :before-close="handleDialogClose">
      <div  style="max-height: 68vh; overflow: auto;">
        <el-form ref="${table.beanName}Form" :model="dialog.formData" :rules="dialog.rules" :disabled="!enableOp" label-width="120px" size="mini">
        <el-row>
          <el-col :span="22">
            <el-table-column prop="id" label="ID" width="60"/>
            <#list table.propertyList as col>
            <el-form-item label="${col.columnComment}" prop="${col.propertyName}">
              <el-input v-model="dialog.formData.${col.propertyName}" placeholder="请输入"></el-input>
            </el-form-item>
            </#list>
          </el-col>
        </el-row>
        <el-row v-if="!enableOp">
          <el-col :span="22">
            <el-form-item label="创建人" required>
              <el-input v-model="dialog.formData.createBy"></el-input>
            </el-form-item>
            <el-form-item label="修改人" required>
              <el-input v-model="dialog.formData.updateBy"></el-input>
            </el-form-item>
            <el-form-item label="创建时间" required>
              <el-date-picker v-model="dialog.formData.createDt" type="datetime"></el-date-picker>
            </el-form-item>
            <el-form-item label="修改时间" required>
              <el-date-picker v-model="dialog.formData.updateDt" type="datetime"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      </div>
      <span slot="footer" v-if="enableOp">
        <el-button @click="handleDialogClose" size="mini">取 消</el-button>
        <el-button type="primary" @click="handleSubmit" size="mini">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: '${table.beanName}',
  data () {
    return {
      queryParam: {},
      list: [],
      page: {
        pageNo: 1,
        pageSize: 100,
        totalCount: 20
      },
      dialog: {
        loading: false,
        visible: false,
        title: '${table.beanName} - 新建',
        submitUrl: String,
        formData: {
<#list table.propertyList as col>
          ${col.propertyName}: null,
</#list>
        },
        rules: {
<#list table.propertyList as col>
          ${col.propertyName}: [{required: true, message: '${col.columnComment}不能为空'}],
</#list>
        }
      }
    }
  },
  mounted: function () {
    this.queryList()
  },
  methods: {
    queryList () {
      this.$http.post('/api/boss-fps/${table.beanName}/queryList', this.queryParam, {
        params: {
          pageNo: this.page.pageNo,
          pageSize: this.page.pageSize
        }
      }).then(resp => {
        this.list = resp.list
        this.page.pageNo = resp.pageNo
        this.page.totalCount = resp.totalCount
      })
    },
    handleSizeChange (size) {
      this.page.pageSize = size
      this.queryList()
    },
    toAdd () {
      this.dialog.title = '${table.tableComment} - 新建'
      this.dialog.submitUrl = '/api/boss-fps/${table.beanName}/add'
      this.dialog.visible = true
    },
    toUpdate (row) {
      this.dialog.title = '${table.tableComment} - 编辑'
      this.dialog.submitUrl = '/api/boss-fps/${table.beanName}/update'
      this.view(row)
    },
    handleSubmit () {
      this.$refs.${table.beanName}Form.validate((valid) => {
        if (valid) {
          this.$http.post(this.dialog.submitUrl, this.dialog.formData).then(resp => {
            this.$message({top: 0, type: 'success', message: '操作成功'})
            this.queryList()
            this.handleDialogClose()
          })
        }
      })
    },
    toView (row) {
      this.dialog.title = '${table.tableComment} - 查看'
      this.view(row)
    },
    view (row) {
      this.$http.get('/api/boss-fps/${table.beanName}/get', {params: {id: row.id}}).then(resp => {
        this.dialog.formData = resp
        this.showDialog()
      })
    },
    remove (row) {
      this.$confirm(`确定要删除吗?`, '提示', {
        confirmButtonText: '确定',
        type: 'warning'
      }).then(() => {
        let params = {id: row.id}
        this.$http.get('/api/boss-fps/${table.beanName}/remove', {params}).then(resp => {
          this.$message({
            top: 0,
            type: 'success',
            message: '删除成功'
          })
          this.queryList()
        })
      }).catch(() => {})
    },
    showDialog () {
      this.dialog.visible = true
    },
    hideDialog () {
      this.dialog.visible = false
    },
    handleDialogClose () {
      this.dialog.visible = false
      this.$refs.${table.beanName}Form.resetFields()
    }
  },
  computed: {
    enableOp () {
      return this.dialog.title !== '${table.tableComment} - 查看'
    }
  }
}
</script>

<style scoped>

</style>
