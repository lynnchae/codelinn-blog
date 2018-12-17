## Codelinn blogNet
A personal springboot framework project，a personal blog site.

> BaseOn: SpringBoot + Thymeleaf + myBatis + MySql
#### 运行

+ 修改resources目录下application.yml -> spring:datasource:url 为自己的数据库，并修改对应的用户名密码
+ 执行sql目录下sql.sql
+ main方法启动CodelinnApplication

### 功能模块

+ 基本功能 ( > . <持续开发中，目前完成了部分功能 )
 + **浏览**
 + **评论**
 + **点赞**
 + **留言**
 + **写作**（ *暂只对站主开放* ）
 + **搜索**
 + ...
 
本站的文章主要使用Markdown语法编辑，我把它理解为编程版的word，如果有兴趣的学习一下这个语法，还是很有意思的

+ *new features & update*
	 + 新增cookie，发布一次评论后，不需要再次输入名字和邮箱
	 + 优化评论、回复内容布局
	 + 新增回复时间显示
	 + 首页底部新增微信号点击展示
	 + 评论回复添加emoji表情栏（2018-11-20）
	 + 调整评论、博客模块样式，红色主题调整为git蓝（2018-11-29）
	 + 评论加入随机头像功能（2018-11-29）
	 + 添加搜索功能，频繁搜索将被拦截（2018-12-01）
	 + 首页图片随机挑选展现 (2018-12-05)
	 + 更新中的文章有动态图标标识 (2018-12-05)
	 + 网站全面升级到https
	 + 搜索支持标题+内容(标题like，标题+内容mysql全文检索，中文未做分词等所以不是很好用，考虑ElasticSearch)
	 + 首页博客新增分页功能
	 + 支持微博分享

+ *bug list*
	 + ~~不支持部分emoji表情~~ 🤪

    
