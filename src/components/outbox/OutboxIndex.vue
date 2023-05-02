<template>
    <el-container>
        <el-header style="text-align: left; font-size: 12px">
<!--                发送按钮-->
            <el-button type="primary" @click="send">发送</el-button>
            <!--选择器-->
            <template>
                <el-select v-model="value" placeholder="移动到">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </template>
            <span>{{user}}</span>
        </el-header>
        <el-main>
<!--                输入-->
            <el-row >
                <el-col :span="2" align="center">收件人：</el-col>
                <el-col :span="22" align="center"><el-input v-model="mail.recipientAddress" placeholder="请输入内容"></el-input></el-col>
            </el-row>
            <el-row align="center">
                <el-col :span="2" align="center">主题：</el-col>
                <el-col :span="22" align="center"><el-input v-model="mail.subject" placeholder="请输入内容"></el-input></el-col>
            </el-row>

            <el-input
                    type="textarea"
                    :rows="5"
                    placeholder="请输入内容"
                    v-model="mail.content">
            </el-input>

        </el-main>
    </el-container>
</template>

<script>
export default {
    name:"OutboxIndex",
    data(){
        return{
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
    methods:{
        send(){
            //发送
        }

    }

}
</script>

<style>

</style>