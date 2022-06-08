# mck-activiti

工作流管理

代码架构

总体架构图

产品介绍

功能介绍

软件架构

功能亮点

项目两点

技术栈

#### 软件架构

| 定位    | 技术栈     |
|-------|---------------------------|
| 后端    | SpringBoot v2.3.4.RELEASE |
| 工作流   | Activiti v5.22.0          |
| 数据库   | MySQL v8.0.19             |
| 数据库   | mybatis-plus v3.3.1       |
| 缓存    | redis   v4.0.0            |
| 前端    | layui、thymeleaf           |
| maven | 3.6.3                     |

#### 安装步骤

-
    1. idea下载项目自动从maven库下载依赖
-
    2. 安装MySQL数据库(8.0.19版本)并设置忽略大小写导入SQL脚本、安装redis并开启密码
-
    3. [数据库-包含表结构和数据，mck-activiti.sql](db/mck-activiti.sql)
-
    4. 修改application-dev.yml数据库的配置和redis配置为自己的配置
-
    5. 启动项目访问默认7004端口
-
    6. **登录用户名默认密码：123**
-
    7. **用户级别关系图**

| 用户名  | 级别               |
|-----|-------------------|
| sunqi  | 最底层员工|
| zhaoliu | 最底层员工             |
| wangwu | sunqi和zhaoliu的直接上级领导      |
| lisi | wangwu的直接上级领导      |
| zhangsan| lisi的直接上级领导，最高领导(无需填写请假申请)|

模块说明

```java
mck-activiti--父项目
        │  ├─com.mck.activiti.common--公共依賴
        │  │  ├─com.mck.activiti.common.config--公共配置
        │  │  │  ├─com.mck.activiti.common.config.db--mybatis相關使用配置
        │  │  ├─com.mck.activiti.common.entity--共同實體
        │  │  ├─com.mck.activiti.common.flow--流程繪製
        │  │  ├─com.mck.activiti.common.schedule--定時任務
        │  │  ├─com.mck.activiti.common.util--工具類
        │  ├─com.mck.activiti.controller--服務控制
        │  ├─com.mck.activiti.listener--流程監聽
        │  ├─com.mck.activiti.mapper--mybatis映射
        │  ├─com.mck.activiti.service--功能服務
```

安装部署

项目截图

交流与反馈
![微信二维码](./images/2fe827338ec8c673e5473365680265a.jpg)

參考項目

- [microservices-platform](https://gitee.com/zlt2000/microservices-platform)
- [sunny-activiti](https://gitee.com/itsunny/sunny-activiti)