# taotaoMall(基于SOA架构的分布式电商项目)
### 淘淘网上商城是一个综合性的B2C平台
> * 类似京东商城、天猫商城。会员可以在商城浏览商品、下订单，以及参加各种活动。
> * 管理员、运营可以在平台后台管理系统中管理商品、订单、会员等。
> * 客服可以在后台管理系统中处理用户的询问以及投诉。
### 电商模式：
* B2B：商家到商家。阿里巴巴，慧聪网、铭万网。
* B2C：商家到用户。京东。
* C2C：用户到用户。淘宝。
* B2B2C：商家到商家到用户。天猫。
* O2O：线上到线下。百度外卖、美团、饿了么。
### 技术使用
+ spring (使用aop定义切点,进行事务声明;控制反转管理组件对象;注解和xml联合使用)
+ springmvc
+ mybatis和mybatis逆向工程
+ dubbo服务中间件
+ mysql数据库
+ 使用阿里druid数据库连接池
+ EasyUI(后台管理操作的表现层)
+ redis数据缓存
## 开发计划
### *壹*
+ 功能分析
+ 架构分析
+ 工程搭建(后台工程)
>>+ 使用maven搭建工程
>>+ 使用maven的tomcat插件启动工程
### *贰*
+ 服务中间件dubbo           **重 难**
+ Ssm框架整合。
+ 整合测试  
+ 商品列表查询功能实现。
### *叁*
+ 商品类目选择
+ 图片上传
a)图片服务器FastDFS
b)图片上传功能实现
+ 富文本编辑器的使用KindEditor
+ 商品添加功能完成
### *肆*
+ 前台系统搭建
+ Cms系统的实现
a)内容分类管理
b)内容管理
+ 前台内容动态展示     
### *伍*
+ Redis服务器搭建      
+ 向业务逻辑中添加缓存。  **重 难**
+ 使用redis做缓存
+ 缓存同步。               **重 难**
+ Solr服务器安装
### *陆*
+ Solrj使用测试
+ 把数据库中的数据导入索引库
+ 搜索功能的实现
### *柒*
+ solr集群搭建              **难**
+ 使用solrj管理solr集群
+ 把搜索功能切换到集群版
### *捌*
+ 什么是MQ
+ MQ的应用场景
+ ActiveMQ的使用方法。 
+ 使用消息队列实现商品同步。    **难**
### *玖*
+ 商品详情页面展示，动态展示 jsp + redis
+ 使用freemarker实现网页静态化   
+ ActiveMq同步生成静态网页
### *拾*                 **重 难**
+ nginx的安装        
+ Nginx配置虚拟机
+ Nginx实现反向代理       
+ Nginx实现负载均衡       
+ Sso系统工程搭建
### *拾壹*                   **重 难**
+ sso注册功能实现              
+ sso登录功能实现
+ 通过token获得用户信息       
+ Ajax跨域请求（jsonp）
### *拾贰*
+ 购物车实现
+ 订单确认页面展示
### *拾叁*
+ 订单系统完成
+ 系统部署
##### 分布式架构：多个子系统相互协作才能完成业务流程。系统之间需要进行通信。(webService,rs, dubbo);
##### 集群：同一个工程部署到多台服务器上;
#### 分布式架构：
把系统按照模块拆分成多个子系统。
###### 优点：
+ 把模块拆分，使用接口通信，降低模块之间的耦合度。
+ 把项目拆分成若干个子项目，不同的团队负责不同的子项目。
+ 增加功能时只需要再增加一个子项目，调用其他系统的接口就可以。
+ 可以灵活的进行分布式部署。
###### 缺点：
+ 系统之间交互需要使用远程通信，接口开发增加工作量。
+ 各个模块有一些通用的业务逻辑无法公用。
#### 基于SOA架构
##### SOA：Service Oriented Architecture面向服务的架构。也就是把工程拆分成服务层、表现层两个工程。服务层中包含业务逻辑，只需要对外提供服务即可。表现层只需要处理和页面的交互，业务逻辑都是调用服务层的服务来实现。
#### 工程搭建使用maven,使用maven管理工程(好处)
* Jar包的管理
* 工程之间的依赖管理
* 自动打包
#### Maven的常见打包方式：jar、war、pom
##### Pom工程一般都是父工程，管理jar包的版本、maven插件的版本、统一的依赖管理。聚合工程。
>+ Taotao-parent：父工程，打包方式pom，管理jar包的版本号。 项目中所有工程都应该继承父工程。
  >>+ Taotao-commom：通用的工具类通用的pojo。打包方式jar
  >>+ Taotao-manager：服务层工程。聚合工程。Pom工程
   >>>+ taotao-manager-dao：打包方式jar
   >>>+ taotao-manager-pojo：打包方式jar
   >>>+ taotao-manager-interface：打包方式jar
   >>>+ taotao-manager-service：打包方式：war
  >>+ taotao-manager-web：表现层工程。打包方式war
### *壹*
+ 运行数据库文件.
+ pojo类和mapper接口以及sql定义文件使用mybatis逆向工程生成.
  > 逆向工程只能处理单表.如有多表联合操作,需要自己写sql.xml文件
+ linux系统(服务器的部署多使用Linux系统)
>##### 基本指令
  >1. pwd -----查看当前所处目录的绝对路径
  >2. mkdir -p ./a/b/c  如果要创建的文件夹的父目录不存在，则自动创建
+ SecureCRT/Xshell/Putty这些软件都是基于一种通用的安全通信协议进行远程登录：SSH协议（安全外壳协议）.SSH协议要求对远程登录者进行身份验证，并有两种认证方式：
  >+ 口令机制——即 “用户名+密码”验证机制<默认机制>
  >+ 密钥机制<需要配置才能生效>
### *贰*
#### 系统间通信dubbo
由于淘淘商城是基于soa的架构，表现层和服务层是不同的工程。所以要实现商品列表查询需要两个系统之间进行通信。
+ 如何实现远程通信？
  >1. Webservice：效率不高基于soap协议。项目中不推荐使用。
  >2. 使用restful形式的服务：http+json。很多项目中应用。如果服务太多，服务之间调用关系混乱，需要治疗服务。
  >3. 使用dubbo。使用rpc协议进行远程调用，直接使用socket通信。传输效率高，并且可以统计出系统之间的调用关系、调用次数。
  >4. Dubbox
+ RPC(Remote Procedure Call Protocol)
  >+ 远程过程调用协议
  >- RPC解析:客户端(A)通过互联网调用远程服务器,不知道远程服务器具体实现,只知道远程服务器提供了什么功能.
  >- RPC优点:数据安全
+ 节点角色说明：
  >+ Provider: 暴露服务的服务提供方。provider项目使用接口,因为dubbo不希望consumer调用时知道其实现情况.
  >+ Consumer: 调用远程服务的服务消费方。
  >+ Registry: 服务注册与发现的注册中心。
  >+ Monitor: 统计服务的调用次调和调用时间的监控中心。
  >+ Container: 服务运行容器。
+ 调用关系说明：
  >0. 服务容器负责启动，加载，运行服务提供者。
  >1. 服务提供者在启动时，向注册中心注册自己提供的服务。
  >2. 服务消费者在启动时，向注册中心订阅自己所需的服务。
  >3. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
  >4. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
  >5. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。
+ 运行原理
  >启动容器,相当于在启动Dubbo的Provider
    >>+ 启动后会去注册中心进行注册.注册所有可以提供的服务列表
    >>+ 在Consumer启动后会去Registry中获取服务列表和Provider的地址.进行订阅.
    >>+ 当Provider有修改后,注册中心会把消息推送给Consummer
     >>> 使用了观察者设计模式(又叫发布/订阅设计模式)
    >>+ 根据获取到的Provider地址,真实调用Provider中功能.
     >>> 在Consumer方使用了代理设计模式.创建一个Provider方类的一个代理对象.通过代理对象获取Provider中真实功能,起到保护Provider真实功能的作用.
    >>+ Consumer和Provider每隔1分钟向Monitor发送统计信息,统计信息包含,访问次数,频率等.
+ dubbo支持的注册中心
 >+ Zookeeper
   >>1. 优点:支持网络集群 2. 缺点:稳定性受限于Zookeeper
 >+ Redis 
   >>1. 优点:性能高. 2. 缺点:对服务器环境要求较高.
 >+ Multicast
   >>1 优点:面中心化,不需要额外安装软件. 2.缺点:建议同机房(局域网)内使用
 >+ Simple
   >>适用于测试环境.不支持集群.
+ dubbo支持的协议
>+ Dubbo
 >>1. Dubbo官方推荐的协议.
 >>2. 本质:使用NIO和线程池进行处理. 缺点:大文件传输时可能出现文件传输失败问题.
>+ RMI
 >>1. JDK提供的协议,远程方法调用协议.
 >>2. 缺点:偶尔连接失败. 优点:JDK原生,不需要进行额外配置(导入jar)
>+ Hession
  >>1. 优点:基于http协议,http请求支持.
  >>2. 缺点:需要额外导入jar,并在短连接时性能低
+ 远程服务
  >+ 将服务定义部分放在服务提供方remote-provider.xml，将服务引用部分放在服务消费方remote-consumer.xml。
  >+ 在提供方增加暴露服务配置<dubbo:service>，在消费方增加引用服务配置<dubbo:reference>。
  >+ 暴露服务时,默认服务调用超时时间时1s,需要通过timeout进行设置
+ 注册中心
  >+ 注册中心负责服务地址的注册与查找，相当于目录服务，服务提供者和消费者只在启动时与注册中心交互，注册中心不转发请求，压力较小。
  >+ 使用dubbo-2.3.3以上版本，建议使用zookeeper注册中心。
Zookeeper是Apacahe Hadoop的子项目，是一个树型的目录服务，支持变更推送，适合作为Dubbo服务的注册中心，工业强度较高，可用于生产环境，并推荐使用
+ dubbo相关依赖
  > 需要注意服务层和表现层都要添加;注意排除项目中依赖,如去除旧版本的spring
#### druid数据库连接池
  > Druid是目前最好的数据库连接池，在功能、性能、扩展性方面，都超过其他数据库连接池，包括DBCP、C3P0、BoneCP、Proxool、JBoss DataSource。
#### mybatis分页插件Pagehelp
  >+ 逆向工程生成的代码是不支持分页处理的，如果想进行分页需要自己编写mapper，这样就失去逆向工程的意义了。为了提高开发效率可以使用mybatis的分页插件PageHelper。
  >+ 该插件目前支持Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库分页。 
  >+ pagehelp依赖版本尽可能是4.0以后的版本或者pagehelp-fx版本(分页插件对逆向工程的支持不太好)
    <plugins>
         <!-- com.github.pagehelper为PageHelper类所在包名 -->
         <plugin interceptor="com.github.pagehelper.PageHelper">
             <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库-->        
             <property name="dialect" value="mysql"/>
         </plugin>
     </plugins>
### *叁*
#### 商品类目选择
> 类目的查询使用的是easyUI的异步tree;{"id(当前节点的id)","text(当前节点的名称)","state(当前节点无子节点设置为'open',有子节点为'closed')"}
#### 图片上传
>+ 文件的上传
  >>+ spring / strus2 /servlet
  >>+ 页面:三要素
   >>>1. form-post
   >>>2. form-entype="mutipart/form-data"
   >>>2. form-input:name属性
  >>+ 服务端servlet:原始的,创建磁盘等等
  >>+ 服务端stusts2:默认18个拦截器中有专门处理图片的拦截器
  >>+ 服务端springmvc:核心配置文件中配置图片解析器,形参中MutiPartFile aa;
>+ 图片服务器FastDFS
 >>+ 搭载专门处理图片的服务器.这里使用分布式文件系统FastDFS。
 >>+ FastDFS是用c语言编写的一款开源的分布式文件系统。FastDFS为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用FastDFS很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。
 >>+ FastDFS架构包括 Tracker server和Storage server。客户端请求Tracker server进行文件上传、下载，通过Tracker server调度最终由Storage server完成文件上传和下载。
 >>+ Tracker server作用是负载均衡和调度，通过Tracker server在文件上传时可以根据一些策略找到Storage server提供文件上传服务。可以将tracker称为追踪服务器或调度服务器。
 >>+ Storage server作用是文件存储，客户端上传的文件最终存储在Storage服务器上，Storage server没有实现自己的文件系统而是利用操作系统 的文件系统来管理文件。可以将storage称为存储服务器。
>>>+ 服务端两个角色：
   >>>+ Tracker：管理集群，tracker也可以实现集群。每个tracker节点地位平等。收集Storage集群的状态。
   >>>+ Storage：实际保存文件
   >>>+ Storage分为多个组，每个组之间保存的文件是不同的。每个组内部可以有多个成员，组成员内部保存的内容是一样的，组成员的地位是一致的，没有主从的概念。
>>+  客户端上传文件后存储服务器将文件ID返回给客户端，此文件ID用于以后访问该文件的索引信息。文件索引信息包括：组名，虚拟磁盘路径，数据两级目录，文件名。
>+ 图片上传功能实现
 >> 请求的url：/pic/upload    参数：MultiPartFile uploadFile
#### 富文本编辑器的使用KindEditor
> KindEditor的图片上传插件，对浏览器兼容性不好。 使用@ResponseBody注解返回java对象，
  Content-Type:application/json;charset=UTF-8
  返回字符串时： Content-Type:text/plan;charset=UTF-8
#### 商品添加功能完成
### *肆*
#### 前台系统搭建
> 需要创建一个内容服务系统。参考taotao-manager创建。
  >> Taotao-content：聚合工程打包方式pom
   >>>- taotao-content-interface jar
   >>>- taotao-content-Service  war
#### Cms系统的实现
>1. 内容分类管理 (easyui异步tree)
>2. 内容管理(cms)
 >> mybatis实现主键返回需求有两种方法:
  >>>1. 插入返回插入记录的主键id
       	keyProperty		===>	映射到数据库的主键为当前pojo的哪个字段
       	resultType		===>	字段的类型
       	order			===>	排序，要插入后才知道id是多少，所以当然是AFTER（之后），触发是UUID就可以是BEFORE-->
       	selectKey keyProperty="id" resultType="java.lang.Long" order="AFTER"(SELECT LAST_INSERT_ID() [mysql自带的函数] )
  >>>2. useGeneratedKeys="true" keyProperty="主键名" 
### *伍*
#### Redis服务器搭建
>1. Redis是当前比较热门的NOSQL系统之一，它是一个开源的使用 c语言编写的key-value存储系统（区别于MySQL的二维表格的形式存储。）。
>和Memcache类似，但很大程度补偿了Memcache的不足。和Memcache一样，Redis数据都是缓存在计算机内存中，不同的是，Memcache只能将数
>据缓存到内存中，无法自动定期写入硬盘，这就表示，一断电或重启，内存清空，数据丢失。所以Memcache的应用场景适用于缓存无需持久化
>的数据。而Redis不同的是它会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，实现数据的持久化
>2. Redis读取的速度是110000次/s，写的速度是81000次/s,
>3. 支持多种数据结构：string（字符串）；list（列表）；hash（哈希），	set（集合）；zset(有序集合)
>4. 持久化，主从复制（集群）支持过期时间，支持事务，消息订阅。官方不支持window,但是又第三方版本。
##### 安装
> Redis是c语言开发的。
安装redis需要c语言的编译环境。如果没有gcc需要在线安装。yum install gcc-c++
安装步骤：
 >>1. redis的源码包上传到linux系统。
 >>2. 解压缩redis。
 >>3. 编译。make 
 >>4. 安装。make install PREFIX=/usr/local/redis
##### key的过期时间 expire keyname seconds
##### redis的持久化方案
>+ Redis的所有数据都是保存到内存中的。
>+ Rdb,快照形式，定期把内存中当前时刻的数据保存到磁盘。Redis默认支持的持久化方案。
>+ aof形式：append only file。把所有对redis数据库操作的命令，增删改操作的命令。保存到文件中。数据库恢复时把所有的命令执行一遍即可。
##### redis集群
>+ 架构细节:
  >>+ 所有的redis节点彼此互联(PING-PONG机制),内部使用二进制协议优化传输速度和带宽.
  >>+ 节点的fail是通过集群中超过半数的节点检测失效时才生效.
  >>+ 客户端与redis节点直连,不需要中间proxy层.客户端不需要连接集群所有节点,连接集群中任何一个可用节点即可
  >>+ redis-cluster把所有的物理节点映射到[0-16383]slot上,cluster 负责维护node<->slot<->value
>+ Redis 集群中内置了 16384 个哈希槽，当需要在 Redis 集群中放置一个 key-value 时，redis 先对 key 使用 crc16 算法算出一个结果，
>然后把结果对 16384 求余数，这样每个 key 都会对应一个编号在 0-16383 之间的哈希槽，redis 会根据节点数量大致均等的将哈希槽映
>射到不同的节点
#### 使用redis做缓存
#### 缓存同步。
>对内容信息做增删改操作后只需要把对应缓存删除即可。
