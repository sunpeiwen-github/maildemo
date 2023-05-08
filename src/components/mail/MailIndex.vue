<template>
  <el-container>
    <el-aside style="width: 350px;margin-top: 20px">
      <!-- <switch></switch> -->
      <SideMenu @indexSelect="listByCategory" class="side-menu" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>

      <MailTable class="mail-area" ref="mailArea"></MailTable>
      
    </el-main>
  </el-container>
</template>
  
<script>
import SideMenu from './SideMenu.vue';
import MailTable from './MailTable.vue';


export default {
  name: 'AppMail',
  components: { SideMenu, MailTable },
  methods: {
    listByCategory() {
      var _this = this
      var status = this.$refs.sideMenu.status
      var url = 'categories/' + status+ '/mails'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.$refs.mailArea.mails = resp.data
          
      
        }
      })
    }

  },


}
//style中的
// .mail-area {
//   width: 100px;
//   margin-left: auto;
//   margin-right: auto;
// }
</script>
  
<style scoped>

.mail-area {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
  
  