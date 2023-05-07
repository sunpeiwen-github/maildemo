<template>
    <el-container>
        <el-header style="text-align: left; font-size: 12px">
            <!--                发送按钮-->
            <el-button type="primary" @click="send">发送</el-button>
            <el-button type="primary" @click="toDraft">移动到草稿箱</el-button>

        </el-header>
        <el-main>
            <!--                输入-->
            <el-row>
                <el-col :span="2" align="center">收件人：</el-col>
                <el-col :span="22" align="center"><el-input v-model="mail.recipientAddress"
                        placeholder="请输入内容"></el-input></el-col>
            </el-row>
            <el-row align="center">
                <el-col :span="2" align="center">主题：</el-col>
                <el-col :span="22" align="center"><el-input v-model="mail.subject" placeholder="请输入内容"></el-input></el-col>
            </el-row>

            <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="mail.content">
            </el-input>

        </el-main>
    </el-container>
</template>

<script>
import App from '@/App.vue';
export default {

    name: "OutboxIndex",
    data() {
        return {
            mail: {
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
            }
        }
    },
    methods: {
        send() {
            //发送
            var _this = this
            //使用全局变量
            console.log('outbox')
            console.log(this.$root.userAddress)
            this.mail.fromAddress = this.$root.userAddress
           
      
            this.$axios.post('/send', this.mail ).then(resp => {
                if (resp && resp.status === 200) {
                    // _this.mail = resp.data
                    console.log('ok了')
                }
            })

          
        },


        toDraft() {
            //移动到草稿箱
        }

    }

}
</script>

<style></style>