<template>
    <div>
        <!--  表格-->
        <el-table ref="multipleTable" :data="mails" tooltip-effect="dark" style="width: 100%" @row-click="handle"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="recipientName" label="来信人" align="center">
            </el-table-column>
            <el-table-column prop="subject" label="标题" align="center">
            </el-table-column>
            <el-table-column prop="sendtime" label="发送时间" align="center">
            </el-table-column>
        </el-table>

        <MailContent v-if="showMail" :mail="selectedMail"></MailContent>


    </div>
</template>

<script>
import MailContent from './MailContent.vue'

export default {
    name: 'MailTable',
    components: { MailContent },
    data() {
        return {
            //表格数据
            mails: [{
                id: '',
                fromId: '',
                fromName: '',
                fromAddress: '',
                recipientId: '',
                recipientName: '',
                recipientAddress: '',
                sendtime: '',
                subject: '',
                mailsize: '',
                status: '',
                content: ''
            }],

            //复选框
            multipleSelection: [],
            //选择框
            options: [{
                value: '选项1',
                label: '收信箱'
            }, {
                value: '选项2',
                label: '发送'
            }, {
                value: '选项3',
                label: '草稿箱'
            }],
            value: '',
            // 测试来用 应该使用费id来查询
            username: "李四",
            user: {
                id: "",
                username: "",
                address: "",
                password: "",
                phone: "",
            },
            //控制MailContent组件显示
            showMail: false,
            selectedMail: null,
        }

    },
    methods: {
        loadMails() {
            // var _this = this
            // this.$axios.get('/mails').then(resp => {
            //     if (resp && resp.status === 200) {
            //         _this.mails = resp.data
            //     }
            // })

               //接收测试
               var _this = this
            //使用全局变量
          console.log('inbox')
          console.log(this.$root.userAddress)
            this.$axios.post('/receive', encodeURIComponent(this.$root.userAddress) ).then(resp => {
                if (resp && resp.status === 200) {
                     _this.mails = resp.data
                    console.log('ok了2')
                }
            })
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        //复选框
        handleSelectionChange(val) {
            this.multipleSelection = val;
            console.log(this.multipleSelection)
        },
        //单机表格的一行后 进入mail页面
        handle(row) {
            this.selectedMail = row
            this.showMail = true

            // console.log('hh')
            // console.log(row)
            // this.selectedMail = row
            // this.$router.push({ name: 'MailContent', params: { id: row.id } })
        },
        //通过provide方法注入一个名为getTableInstance的方法
        // provide() {
        //     console.log("Debug information provide");

        //     return {
        //         getTableInstance: () => this
        //     };
        // },


    },

    mounted: function () {
        this.loadMails()
    }


}
</script>

<style></style>
