<template>
  <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 100%">
    <el-table-column label="名称" prop="name">
    </el-table-column>
    <el-table-column label="地址" prop="address">
    </el-table-column>
    <el-table-column align="right">
      <template slot="header">
        <el-input v-model="search" size="mini" placeholder="输入关键字搜索" />
      </template>
      <template slot-scope="scope">
        <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
        <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>

export default {
  name: 'AppContact',
  data() {
    return {
      tableData: [{
        id: '',
        name: '',
        address: '',
        host_address: '',
        group: ''
      }],
      search: ''
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    loadContacts() {
      //接收测试
      var _this = this
      //使用全局变量
      console.log('contact')
      console.log(this.$root.userAddress)
      this.$axios.post('/contact', encodeURIComponent(this.$root.userAddress)).then(resp => {
        if (resp && resp.status === 200) {
          _this.tableData = resp.data
          console.log(_this.tableData)
        }
      })
    }

  },
  mounted() {
    this.loadContacts()
  }
}
</script>
<style scoped></style>
  
  