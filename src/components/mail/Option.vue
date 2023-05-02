<template>
    <el-row>
        <el-col :span="4"><!--                删除按钮-->
            <el-button type="primary">删除</el-button></el-col>
        <el-col :span="4">  <!--选择器-->
            <template>
                <el-select v-model="value" placeholder="移动到">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
            </template></el-col>
        <el-col :span="4"></el-col>
        <el-col :span="4"></el-col>

        <el-col :span="4"></el-col>
        <el-col :span="4"><!--                用户名-->
            <!--                插值表达式-->
<!--                        <span>{{user.username}}</span></el-col>-->
            <!-- {{user.username}} -->
            </el-col>

    </el-row>
</template>

<script>
export default {
    data(){
        return{
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
        }
    }

}
</script>

<style>

</style>