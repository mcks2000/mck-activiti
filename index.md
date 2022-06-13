---
layout: home
---

工作流管理

[![springboot Version](https://img.shields.io/badge/springboot-%3E=2.3.4.RELEASE-brightgreen.svg?maxAge=2592000&color=yellow)](https://spring.io/projects/spring-boot)
[![JDK Version](https://img.shields.io/badge/JDK-%3E=1.8.0_191-gread.svg?maxAge=2592000)](https://www.oracle.com/java/technologies/downloads/)
[![Mysql Version](https://img.shields.io/badge/mysql-%3E=8.0.19-brightgreen.svg?maxAge=2592000&color=orange)](https://www.mysql.com/)
[![Activiti Version](https://img.shields.io/badge/Activiti-=5.22.0-brightgreen.svg?maxAge=2592000)](https://www.activiti.org/)
[![Layui Version](https://img.shields.io/badge/layui-=2.5.5-brightgreen.svg?maxAge=2592000&color=critical)](https://github.com/sentsin/layui)
[![Layuimini Version](https://img.shields.io/badge/layuimini-%3E=2.0.4.2-brightgreen.svg?maxAge=2592000&color=ff69b4)](https://github.com/zhongshaofa/layuimini)
[![mck-activiti Doc](https://img.shields.io/badge/docs-passing-green.svg?maxAge=2592000)](https://mcks2000.github.io/mck-activiti/)
[![mck-activiti License](https://img.shields.io/badge/license-MIT-green?maxAge=2592000&color=blue)](https://github.com/mcks2000/mck-activiti/blob/main/LICENSE)

## 项目介绍

基于springboot v2.3.4.RELEASE和layui的快速开发的工作流后台管理系统。

技术交流QQ群：[271143506](https://jq.qq.com/?_wv=1027&k=yR07XYw1) `加群请备注来源：如gitee、github、官网等`。

## 安装教程

#### 安装步骤
- 1. idea下载项目自动从maven库下载依赖
- 2. 安装MySQL数据库(8.0.19版本)并设置忽略大小写导入SQL脚本、安装redis并开启密码
- 3. [数据库-包含表结构和数据，mck-activiti.sql](db/mck-activiti.sql)
- 4. 修改application-dev.yml数据库的配置和redis配置为自己的配置
- 5. 启动项目访问默认7010端口
- 6. **登录用户名默认密码：123**
- 7. **用户级别关系图**

| 用户名  | 级别               |
|-----|-------------------|
| sunqi  | 最底层员工|
| zhaoliu | 最底层员工             |
| wangwu | sunqi和zhaoliu的直接上级领导      |
| lisi | wangwu的直接上级领导      |
| zhangsan| lisi的直接上级领导，最高领导(无需填写请假申请)|

#### 本地开发建议下载Docker 安装mysql和redis

- 1.下载按照docker [Docker下载地址](https://www.docker.com/get-started/)
- 2.使用一下命令按照数据库和redis
```bash
# 数据库安装命令 默认账号密码端口 root 123456 3306
docker run --name mysql8.0.19_new -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:8.0.19
# redis安装命令 默认密码和端口 12345  6379
docker run -d --name redis_rc-alpine -p 6379:6379 redis:rc-alpine --requirepass "123456"
```
## 站点地址

* 官方网站：

* 文档地址：[https://mcks2000.github.io/mck-activiti/](https://mcks2000.github.io/mck-activiti/)

* 演示地址：

## 代码仓库

* GitHub地址：[https://mcks2000.github.io/mck-activiti/](https://github.com/mcks2000/mck-activiti)

* Gitee地址：
*

## 接口规范

* 按照restful接口设计规范
  GET （SELECT）：从服务器检索特定资源，或资源列表。

  POST （CREATE）：在服务器上创建一个新的资源。

  PUT （UPDATE）：更新服务器上的资源，提供整个资源。

  PATCH （UPDATE）：更新服务器上的资源，仅提供更改的属性。

  DELETE （DELETE）：从服务器删除资源。

* 接口尽量使用名词，禁止使用动词，下面是一些例子

```java
GET         /zoos：列出所有动物园
POST        /zoos：新建一个动物园
GET         /zoos/{id}：获取某个指定动物园的信息
PUT         /zoos/{id}：更新某个指定动物园的信息（提供该动物园的全部信息）
PATCH       /zoos/{id}：更新某个指定动物园的信息（提供该动物园的部分信息）
DELETE      /zoos/{id}：删除某个动物园
GET         /zoos/{id}/animals：列出某个指定动物园的所有动物
DELETE      /zoos/{zId}/animals/{aId}：删除某个指定动物园的指定动物
```

### 后端返回JSON

* 后端统一返回 com.central.common.model.Result 对象
  * datas：具体响应的其他信息
  * resp_code：响应码，目前200是成功、0是失败   200=0  0=1
  * resp_msg：响应消息

## 模块说明

```lua
mck-activiti--父项目
├─com.mck.activiti.common--公共依赖
│  ├─com.mck.activiti.common.config--公共配置
│  │  ├─com.mck.activiti.common.config.activiti--工作流配置项
│  │  ├─com.mck.activiti.common.config.db--mybatis相关使用配置
│  │  ├─com.mck.activiti.common.config.exception--异常配置项
│  ├─com.mck.activiti.common.entity--共同实体
│  ├─com.mck.activiti.common.flow--流程绘制
│  ├─com.mck.activiti.common.lock--分布式锁
│  ├─com.mck.activiti.common.mapper--顶级Mapper
│  ├─com.mck.activiti.common.schedule--定时任务配置
│  ├─com.mck.activiti.common.service--公共服务
│  ├─com.mck.activiti.common.util--工具类
├─com.mck.activiti.controller--服务控制
├─com.mck.activiti.listener--流程监听
├─com.mck.activiti.module--服务模块
│  ├─com.mck.activiti.module.flow--业务服务模块
│  ├─com.mck.activiti.module.system--系统服务模块
```

## 特别感谢

以下项目排名不分先后
* microservices-platform [microservices-platform](https://gitee.com/zlt2000/microservices-platform)

* sunny-activiti [sunny-activiti](https://gitee.com/itsunny/sunny-activiti)

* Layuimini：[https://github.com/zhongshaofa/layuimini](https://github.com/zhongshaofa/layuimini)

* Layui：[https://github.com/sentsin/layui](https://github.com/sentsin/layui)

* Jquery：[https://github.com/jquery/jquery](https://github.com/jquery/jquery)

* RequireJs：[https://github.com/requirejs/requirejs](https://github.com/requirejs/requirejs)

* CKEditor：[https://github.com/ckeditor/ckeditor4](https://github.com/ckeditor/ckeditor4)

* Echarts：[https://github.com/apache/incubator-echarts](https://github.com/apache/incubator-echarts)

## 免责声明

>任何用户在使用`mck-activiti`后台框架前，请您仔细阅读并透彻理解本声明。您可以选择不使用`mck-activiti`后台框架，若您一旦使用`mck-activiti`后台框架，您的使用行为即被视为对本声明全部内容的认可和接受。

* `mck-activiti`后台框架是一款开源免费的后台快速开发框架 ，主要用于更便捷地开发后台管理；其尊重并保护所有用户的个人隐私权，不窃取任何用户计算机中的信息。更不具备用户数据存储等网络传输功能。
* 您承诺秉着合法、合理的原则使用`mck-activiti`后台框架，不利用`mck-activiti`后台框架进行任何违法、侵害他人合法利益等恶意的行为，亦不将`mck-activiti`后台框架运用于任何违反我国法律法规的 Web 平台。
* 任何单位或个人因下载使用`mck-activiti`后台框架而产生的任何意外、疏忽、合约毁坏、诽谤、版权或知识产权侵犯及其造成的损失 (包括但不限于直接、间接、附带或衍生的损失等)，本开源项目不承担任何法律责任。
* 用户明确并同意本声明条款列举的全部内容，对使用`mck-activiti`后台框架可能存在的风险和相关后果将完全由用户自行承担，本开源项目不承担任何法律责任。
* 任何单位或个人在阅读本免责声明后，应在《MIT 开源许可证》所允许的范围内进行合法的发布、传播和使用`mck-activiti`后台框架等行为，若违反本免责声明条款或违反法律法规所造成的法律责任(包括但不限于民事赔偿和刑事责任），由违约者自行承担。
* 如果本声明的任何部分被认为无效或不可执行，其余部分仍具有完全效力。不可执行的部分声明，并不构成我们放弃执行该声明的权利。
* 本开源项目有权随时对本声明条款及附件内容进行单方面的变更，并以消息推送、网页公告等方式予以公布，公布后立即自动生效，无需另行单独通知；若您在本声明内容公告变更后继续使用的，表示您已充分阅读、理解并接受修改后的声明内容。


## 捐赠支持

开源项目不易，若此项目能得到你的青睐，可以捐赠支持作者持续开发与维护，感谢所有支持开源的朋友。
