<template>
    <div>
        <el-row>
            <el-col :span="2" align="center">发信人：</el-col>
            <el-col :span="22">{{ mail.fromName }}</el-col>
        </el-row>
        <el-row align="center">
            <el-col :span="2" align="center">主题：</el-col>
            <el-col :span="22">{{ mail.subject }}</el-col>
        </el-row>
        <el-row align="center">
            <el-col :span="2" align="center">内容：</el-col>
            <el-col :span="22">{{ mail.content }}</el-col>
        </el-row>

    </div>
</template>

<script>
export default {
    //通过prop传送数据 通过this.mail访问传送的数据
    name: 'MailContent',
    props: {
        mail: Object
    },

//计算属性可以动态响应
    // computed: {
    //     mail() {
    //         const mailId = this.$route.params.id;
    //         // 根据 mailId 获取对应的邮件数据
    //         const mail = this.mails.find(mail => mail.id === mailId);
    //         return mail;
    //     }
    // },
    // 在 MailContent 组件中
  

    mounted() {
        console.log('mailcontetn')
        // const id = this.$route.params.id;
        // console.log(id)
        // console.log(selectedMail)

    }
}
</script>

<style></style>