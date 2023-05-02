const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
   lintOnSave: false,//组件多单词命名问题
  devServer: {
    proxy: {
        '/api': {
            target: 'http://localhost:8443',//目的端口
            changeOrigin: true,//跨域
            pathRewrite: {
                '/api': ''
            }
        }
    }
}
})
