<template>
    <div class="container">

        <el-row style="width: 100%">
            <el-col :span="4"><!--                删除按钮-->
                <el-button type="primary" @click="deleteMails">删除</el-button></el-col>

            <el-col :span="4">
                <!-- <template>
                    <el-select v-model="value" placeholder="移动到">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                </template> -->
            </el-col>
            <!--选择器-->
            <el-col :span="4">

            </el-col>
            <el-col :span="4"></el-col>
            <!-- <el-col :span="4"></el-col> -->
            <el-col :span="8">

            </el-col>
        </el-row>

        <!--  表格-->
        <el-table ref="multipleTable" :data="mails" tooltip-effect="dark" style="width: 100%" @row-click="handle"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column prop="fromName" label="来信人" align="center">
            </el-table-column>
            <el-table-column prop="subject" label="标题" align="center">
            </el-table-column>
            <el-table-column prop="sendtime" label="发送时间" align="center">
            </el-table-column>
        </el-table>

        <el-dialog title="邮件内容" :visible.sync="dialogVisible">
            <mail-content :mail="selectedMail"></mail-content>
        </el-dialog>
        <!-- <MailContent v-if="showMail" :mail="selectedMail"></MailContent> -->
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
            dialogVisible: false,
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
            this.$axios.post('/inbox', encodeURIComponent(this.$root.userAddress)).then(resp => {
                if (resp && resp.status === 200) {
                    _this.mails = resp.data
                    // console.log(_this.mails)
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
            this.dialogVisible = true
        },
        deleteMails() {
            console.log('delete')

//删除前端界面中的邮件
            for (let i = this.multipleSelection.length - 1; i >= 0; i--) {
                //删除 索引位置 1个
                // this.mails.splice(this.mails.findIndex(this.multipleSelection[i]), 1);
                this.mails.splice(this.mails.findIndex(item => item === this.multipleSelection[i]), 1);

            }
       


            this.$axios.post('/delete', this.multipleSelection).then(resp => {
                if (resp && resp.status === 200) {

                    console.log('delete ok')
                }
            })


        }
    },

    mounted: function () {
        this.loadMails()
    }


}
</script>
<style>
.container {
    display: flex;
    flex-direction: column;
}

.el-row {
    display: flex;
    flex-wrap: wrap;
}
</style>

