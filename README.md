# spring-boot-netty-socket.io---chat
注 ： 改为java 版本，是因为个人倾向java的代码的编写 非其他。

node.js + socket.io 版本改 为spring + boot + socket.io 形式 目前实现了注册，登录与一对一聊天群聊，及 创建群，搜索群，加群功能(验证需同意)，

页面使用了layim 框架，如需在生产环境中使用，请去官方网站授权使用

前端页面 借用自 https://github.com/scalad/LayIM 的前端， @scalad

mongodb 数据库连接，IM 地址和端口 请修改 application.properties 文件  

前端页面的websocket连接地址 请自行修改resource/static/js/websocket.js 

![image](https://github.com/githuanl/spring-boot-netty-socket.io---chat/blob/master/img/QQ20180629-165244.png)
 
![image](https://github.com/githuanl/spring-boot-netty-socket.io---chat/blob/master/img/QQ20180629-165327.png)
  
![image](https://github.com/githuanl/spring-boot-netty-socket.io---chat/blob/master/img/QQ20180629-165341.png)
   
![image](https://github.com/githuanl/spring-boot-netty-socket.io---chat/blob/master/img/QQ20180629-165500.png)
