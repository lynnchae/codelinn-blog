CREATE TABLE t_user (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) DEFAULT NULL,
  `name` VARCHAR(30) DEFAULT '',
  `sex` VARCHAR(2) DEFAULT '',
  `birthday` VARCHAR(8) DEFAULT '',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL ,
  `create_user` VARCHAR(32) NULL ,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL ,
  `update_user` VARCHAR(32) NULL ,
  PRIMARY KEY (`id`),
  KEY `idx_userid`(`user_id`)
) ENGINE=INNODB   DEFAULT CHARSET=utf8 COMMENT='用户表';


CREATE TABLE t_blog (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) DEFAULT NULL,
  `title` VARCHAR(255) DEFAULT '' COMMENT '标题',
  `tags` VARCHAR(128) DEFAULT '' COMMENT '标签',
  `content` TEXT COMMENT '内容',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL ,
  `create_user` VARCHAR(32) NULL ,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL ,
  `update_user` VARCHAR(32) NULL ,
  PRIMARY KEY (`id`)
) ENGINE=INNODB COMMENT='博客表'  DEFAULT CHARSET=utf8;


insert  into `t_blog`(`user_id`,`title`,`tags`,`content`,`create_time`,`create_user`,`update_time`,`update_user`) values
(1,'Cmd Markdown编辑阅读器预览','概念','# 欢迎使用 Cmd Markdown 编辑阅读器\r\n\r\n------\r\n\r\n我们理解您需要更便捷更高效的工具记录思想，整理笔记、知识，并将其中承载的价值传播给他人，**Cmd Markdown** 是我们给出的答案 —— 我们为记录思想和分享知识提供更专业的工具。 您可以使用 Cmd Markdown：\r\n\r\n> * 整理知识，学习笔记\r\n> * 发布日记，杂文，所见所想\r\n> * 撰写发布技术文稿（代码支持）\r\n> * 撰写发布学术论文（LaTeX 公式支持）\r\n\r\n![cmd-markdown-logo](https://www.zybuluo.com/static/img/logo.png)\r\n\r\n除了您现在看到的这个 Cmd Markdown 在线版本，您还可以前往以下网址下载：\r\n\r\n### [Windows/Mac/Linux 全平台客户端](https://www.zybuluo.com/cmd/)\r\n\r\n> 请保留此份 Cmd Markdown 的欢迎稿兼使用说明，如需撰写新稿件，点击顶部工具栏右侧的 <i class=\"icon-file\"></i> **新文稿** 或者使用快捷键 `Ctrl+Alt+N`。\r\n\r\n------\r\n\r\n## 什么是 Markdown\r\n\r\nMarkdown 是一种方便记忆、书写的纯文本标记语言，用户可以使用这些标记符号以最小的输入代价生成极富表现力的文档：譬如您正在阅读的这份文档。它使用简单的符号标记不同的标题，分割不同的段落，**粗体** 或者 *斜体* 某些文字，更棒的是，它还可以\r\n\r\n### 1. 制作一份待办事宜 [Todo 列表](https://www.zybuluo.com/mdeditor?url=https://www.zybuluo.com/static/editor/md-help.markdown#13-待办事宜-todo-列表)\r\n\r\n- [ ] 支持以 PDF 格式导出文稿\r\n- [ ] 改进 Cmd 渲染算法，使用局部渲染技术提高渲染效率\r\n- [x] 新增 Todo 列表功能\r\n- [x] 修复 LaTex 公式渲染问题\r\n- [x] 新增 LaTex 公式编号功能\r\n\r\n### 2. 书写一个质能守恒公式[^LaTeX]\r\n\r\n$$E=mc^2$$\r\n\r\n### 3. 高亮一段代码[^code]\r\n\r\n```python\r\n@requires_authorization\r\nclass SomeClass:\r\n    pass\r\n\r\nif __name__ == \'__main__\':\r\n    # A comment\r\n    print \'hello world\'\r\n```\r\n\r\n### 7. 绘制表格\r\n\r\n| 项目        | 价格   |  数量  |\r\n| --------   | -----:  | :----:  |\r\n| 计算机     | \\$1600 |   5     |\r\n| 手机        |   \\$12   |   12   |\r\n| 管线        |    \\$1    |  234  |\r\n\r\n### 8. 更详细语法说明\r\n\r\n想要查看更详细的语法说明，可以参考我们准备的 [Cmd Markdown 简明语法手册][1]，进阶用户可以参考 [Cmd Markdown 高阶语法手册][2] 了解更多高级功能。\r\n\r\n总而言之，不同于其它 *所见即所得* 的编辑器：你只需使用键盘专注于书写文本内容，就可以生成印刷级的排版格式，省却在键盘和工具栏之间来回切换，调整内容和格式的麻烦。**Markdown 在流畅的书写和印刷级的阅读体验之间找到了平衡。** 目前它已经成为世界上最大的技术分享网站 GitHub 和 技术问答网站 StackOverFlow 的御用书写格式。\r\n\r\n---\r\n\r\n## 什么是 Cmd Markdown\r\n\r\n您可以使用很多工具书写 Markdown，但是 Cmd Markdown 是这个星球上我们已知的、最好的 Markdown 工具——没有之一 ：）因为深信文字的力量，所以我们和你一样，对流畅书写，分享思想和知识，以及阅读体验有极致的追求，我们把对于这些诉求的回应整合在 Cmd Markdown，并且一次，两次，三次，乃至无数次地提升这个工具的体验，最终将它演化成一个 **编辑/发布/阅读** Markdown 的在线平台——您可以在任何地方，任何系统/设备上管理这里的文字。','2018-09-06 15:22:52','system','2018-09-07 04:24:41','system'),
(1,'基于内存的Redis缓存简介','缓存','Redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。从2010年3月15日起，Redis的开发工作由VMware主持。从2013年5月开始，Redis的开发由Pivotal赞助。','2018-09-06 17:40:17','system','2018-09-07 02:36:30','system'),
(1,'ACID和CAP定义及相关理论','概念','## 什么是ACID？\r\n+ 事务的定义和实现一直随着数据管理的发展在演进，当计算机越来越强大，它们就能够被用来管理越来越多数据，最终，多个用户可以在一台计算机上共享数据，这就导致了一个问题，当一个用户修改了数据而另外一个还在使用旧数据进行计算过程中，这里就需要一些机制来保证这种情况不会发生。\r\n　　\r\n+ ACID规则原来是在1970被Jim Gray定义，ACID事务解决了很多问题，但是仍然需要和性能做平衡协调，事务越强，性能可能越低，安全可靠性和高性能是一对矛盾。\r\n　　\r\n+ 一个事务是指对数据库状态进行改变的一系列操作变成一个单个序列逻辑元操作，数据库一般在启动时会提供事务机制，包括事务启动 停止 取消或回滚。\r\n　　\r\n+ 但是上述事务机制并不真的实现“事务”，一个真正事务应该遵循ACID属性，ACID事务才真正解决事务，包括并发用户访问同一个数据表记录的头疼问题。\r\n\r\n### ACID的定义：\r\n\r\n+ Atomic原子性: 一个事务的所有系列操作步骤被看成是一个动作，所有的步骤要么全部完成要么一个也不会完成，如果事务过程中任何一点失败，将要被改变的数据库记录就不会被真正被改变。\r\n+ Consistent一致性: 数据库的约束 级联和触发机制Trigger都必须满足事务的一致性。也就是说，通过各种途径包括外键约束等任何写入数据库的数据都是有效的，不能发生表与表之间存在外键约束，但是有数据却违背这种约束性。所有改变数据库数据的动作事务必须完成，没有事务会创建一个无效数据状态，这是不同于CAP理论的一致性\"consistency\".\r\n+ Isolated隔离性: 主要用于实现并发控制, 隔离能够确保并发执行的事务能够顺序一个接一个执行，通过隔离，一个未完成事务不会影响另外一个未完成事务。\r\n+ Durable持久性: 一旦一个事务被提交，它应该持久保存，不会因为和其他操作冲突而取消这个事务。很多人认为这意味着事务是持久在磁盘上，但是规范没有特别定义这点。\r\n\r\n\r\n## 什么是CAP？\r\n**CAP是分布式系统中进行平衡的理论，它是由 Eric Brewer发布在2000年。**\r\n\r\n+ Consistent一致性: 同样数据在分布式系统中所有地方都是被复制成相同。\r\n+ Available可用性: 所有在分布式系统活跃的节点都能够处理操作且能响应查询。\r\n+ Partition Tolerant分区容错性: 在两个复制系统之间，如果发生了计划之外的网络连接问题，对于这种情况，有一套容错性设计来保证。','2018-09-07 02:44:24',NULL,'2018-09-07 04:07:29',NULL);


ALTER TABLE `lynn`.`t_blog`
  ADD COLUMN `like` INT(8) DEFAULT 0 NOT NULL COMMENT '喜欢' AFTER `content`;
