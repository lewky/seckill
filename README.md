# SSM实战项目——Java高并发秒杀API

### 本文包括了项目的完整流程+开发过程中遇到的各种坑的总结+学习笔记和问题扩展，如果觉得README太长，我在blog里进行了分章，[点击前往](http://blog.csdn.net/lewky_liu/article/details/78154502)

### ~~这是完成后的项目，<a href="#">点我看看实际效果</a>(阿里云到期了orz)~~
---
## 项目截图

### 秒杀列表

![秒杀列表](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E7%A7%92%E6%9D%80%E5%88%97%E8%A1%A8.jpg)

### 秒杀详情页

![秒杀详情页](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E7%A7%92%E6%9D%80%E8%AF%A6%E6%83%85%E9%A1%B5.jpg)

### 错误提示

![错误提示](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E9%94%99%E8%AF%AF%E6%8F%90%E7%A4%BA.jpg)

### 开始秒杀

![开始秒杀](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E5%BC%80%E5%A7%8B%E7%A7%92%E6%9D%80.jpg)

### 秒杀成功

![秒杀成功](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E7%A7%92%E6%9D%80%E6%88%90%E5%8A%9F.jpg)

### 重复秒杀

![重复秒杀](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E9%87%8D%E5%A4%8D%E7%A7%92%E6%9D%80.jpg)

### 秒杀倒计时

![秒杀倒计时](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E7%A7%92%E6%9D%80%E5%80%92%E8%AE%A1%E6%97%B6.jpg)

### 秒杀结束

![秒杀结束](https://raw.githubusercontent.com/lewky/markdownImages/master/resource/seckill/%E7%A7%92%E6%9D%80%E7%BB%93%E6%9D%9F.jpg)

## 项目介绍

>何为秒杀？

所谓“秒杀”，就是网络卖家发布一些超低价格的商品，所有买家在同一时间网上抢购的一种销售方式。由于商品价格低廉，往往一上架就被抢购一空，有时只用一秒钟。

>为何选择Java高并发秒杀作为实战项目？

* 秒杀业务场景具有典型事务特性
* 秒杀/红包类需求越来越常见

>为何使用SpringMVC+Spring+MyBatis框架

* 框架易于使用和轻量级
* 低代码侵入性
* 成熟的社区和用户群

>能从该项目得到什么收获？

* 框架的使用和整合技巧
* 秒杀分析过程与优化思路

>项目来源

这是慕课网上的一个免费项目教学视频，名为Java高并发秒杀API，一共有如下四节课程，附带视频传送门（在视频中老师是用IDEA，本文用的是Eclipse）

* [Java高并发秒杀API之业务分析与DAO层](http://www.imooc.com/learn/587)
* [Java高并发秒杀API之Service层](http://www.imooc.com/learn/631)
* [Java高并发秒杀API之Web层](http://www.imooc.com/learn/630)
* [Java高并发秒杀API之高并发优化](http://www.imooc.com/learn/632)

## 相关技术介绍

>MySQL

* 表设计
* SQL技巧
* 事务和行级锁

>MyBatis

* DAO层设计与开发
* MyBatis合理使用
* 与Spring整合

>Spring

* Spring IOC整合Service
* 声明式事务运用

>SpringMVC

* Restful借口设计和使用
* 框架运作流程
* Controller开发技巧

>前端

* 交互设计
* Bootstrap
* jQuery

>高并发

* 高并发点和高并发分析
* 优化思路并实现

## 开发环境

* **操作系统**：Windows 8
* **IDE工具**：Eclipse
* **JDK**：JDK1.7
* **中间件**：Tomcat 7.0
* **数据库**：MySQL 5.0
* **构建工具**：Maven
* **框架**：SSM

## 项目总结

>本文根据慕课网的视频教学进行了相应的学习总结，全文较长，分为四节，附带CSDN传送门

* [**Java高并发秒杀API(一)之业务分析与DAO层**](http://blog.csdn.net/lewky_liu/article/details/78159983)
* [**Java高并发秒杀API(二)之Service层**](http://blog.csdn.net/lewky_liu/article/details/78162149)
* [**Java高并发秒杀API(三)之Web层**](http://blog.csdn.net/lewky_liu/article/details/78162153)
* [**Java高并发秒杀API(四)之高并发优化**](http://blog.csdn.net/lewky_liu/article/details/78166080)

>项目源码

* [**源码下载**](http://download.csdn.net/download/lewky_liu/10013556)
* [**GitHub地址**](https://github.com/lewky/Seckill)

>How to play

* 将下载的源码解压后作为Maven项目导入到IDE工具中；或者将从GitHub克隆下来的项目作为Maven项目导入到IDE工具中
* 打开项目中的jdbc.properties文件，修改里边的url,username和password
* 将项目部署到Tomcat上并启动
	* 可以直接用IDE内嵌的Tomcat启动项目
	* 或者将本项目通过**mvn clean package**命令打成war包并丢到本地安装的Tomcat的webapps目录下，接着启动Tomcat即可
* 在浏览器上访问：`http://localhost:8080/seckill`

---

# [Java高并发秒杀API(一)之业务分析与DAO层](http://blog.csdn.net/lewky_liu/article/details/78159983)

>本SSM实战项目使用了Maven进行依赖管理，如果有不清楚Maven是什么的可以参考[这篇文章](http://blog.csdn.net/lewky_liu/article/details/78138381)

## 1. 创建Maven项目和依赖

### 1.1 创建项目前需要先安装Maven，并设置好环境变量

* [Maven下载](http://download.csdn.net/download/lewky_liu/10000144)
* 设置环境变量
	* 新建变量`MAVEN_HOME`，值为Maven的目录`X:\XXX\apache-maven-XXX`
	* 将`%MAVEN_HOME%\bin`添加到`Path`变量下
* 运行CMD，输入`mvn -v`后可以看到Maven的版本信息等则表示安装成功

### 1.2 创建Maven项目有两种方式，如下

**第一种创建方式：使用命令行手动创建**

	mvn archetype:generate -DgroupId=com.lewis.seckill -DartifactId=seckill -Dpackage=com.lewis.seckill -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-webapp

在视频中使用的是`archetype:create`，该方法已被废弃，请使用`archetype:generate`来创建。命令行执行后会创建一个`maven-archetype-webapp`骨架的Maven项目，其中`groupId`是项目组织唯一的标识符，实际对应JAVA的包的结构；`artifactId`是项目的唯一的标识符，实际对应项目的名称；`package`一般是`groupId`+`artifactId`，是自动生成的，可以修改

**第二种创建方式：借助IDE工具的Maven插件来创建项目**

>Eclipse安装Maven插件

* 不知道怎么Maven插件的请参考[该博文](http://blog.csdn.net/wode_dream/article/details/38052639)，推荐使用link方式手工安装的方式
	* 如果是手工安装Maven插件的，可能会缺少pom.xml 图形化编辑工具，请另外添加进去，具体情况请参考[该博文](http://www.micmiu.com/software/build/eclipse-m2e-plugins/)
	* 已经安装了Maven插件的请走下一个步骤
* `File`→`New`→`Other...`→`Maven Project`→`Next`,进入如下界面

 ![Maven1](https://github.com/lewky/MarkdownImages/blob/master/resource/CSDN/Maven1.jpg?raw=true)

* 点击`Next`，选择要构建的骨架`maven-archetype-webapp`，如下图

 ![Maven2](https://github.com/lewky/MarkdownImages/blob/master/resource/CSDN/Maven2.jpg?raw=true)

* 点击`Next`，填写`groupId=com.lewis.seckill`，`DartifactId=seckill`，`package=com.lewis.seckill`（根据实际情况填写），然后`Finish`

>如果是第一次使用Eclipse的Maven插件来创建Maven项目的可能会遇到一些问题，可以参考[该博文](http://blog.csdn.net/lewky_liu/article/details/78138381)

### 1.3 修改pom.xml文件

当创建完Maven项目后会在根目录下有一个pom.xml文件，Maven项目通过pom.xml进行项目依赖的管理，如果没有该xml文件，Eclipse不会将该项目当作一个Maven项目

>添加项目需要的jar包依赖

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<groupId>com.lewis</groupId>
		<artifactId>seckill</artifactId>
		<packaging>war</packaging>
		<version>0.0.1-SNAPSHOT</version>
		<name>seckill Maven Webapp</name>
		<url>http://maven.apache.org</url>
		<dependencies>
			<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<version>4.11</version>
				<scope>test</scope>
			</dependency>
			<!--补全项目依赖 -->
			<!--1.日志 java日志有:slf4j,log4j,logback,commons-logging
				slf4j,commons-logging:是规范/接口 日志实现:log4j,logback
				使用:slf4j+logback -->
			<dependency>
				<groupId>org.slf4j</groupId>
				<artifactId>slf4j-api</artifactId>
				<version>1.7.12</version>
			</dependency>
			<dependency>
				<groupId>ch.qos.logback</groupId>
				<artifactId>logback-core</artifactId>
				<version>1.1.1</version>
			</dependency>
			<!--实现slf4j接口并整合 -->
			<dependency>
				<groupId>ch.qos.logback</groupId>
				<artifactId>logback-classic</artifactId>
				<version>1.1.1</version>
			</dependency>
	
			<!--2.数据库相关依赖 -->
			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>5.1.35</version>
				<scope>runtime</scope>
			</dependency>
			<dependency>
				<groupId>c3p0</groupId>
				<artifactId>c3p0</artifactId>
				<version>0.9.1.1</version>
			</dependency>
	
			<!--3.dao框架:MyBatis依赖 -->
			<dependency>
				<groupId>org.mybatis</groupId>
				<artifactId>mybatis</artifactId>
				<version>3.3.0</version>
			</dependency>
			<!--mybatis自身实现的spring整合依赖 -->
			<dependency>
				<groupId>org.mybatis</groupId>
				<artifactId>mybatis-spring</artifactId>
				<version>1.2.3</version>
			</dependency>
	
			<!--4.Servlet web相关依赖 -->
			<dependency>
				<groupId>taglibs</groupId>
				<artifactId>standard</artifactId>
				<version>1.1.2</version>
			</dependency>
			<dependency>
				<groupId>jstl</groupId>
				<artifactId>jstl</artifactId>
				<version>1.2</version>
			</dependency>
			<dependency>
				<groupId>com.fasterxml.jackson.core</groupId>
				<artifactId>jackson-databind</artifactId>
				<version>2.5.4</version>
			</dependency>
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>javax.servlet-api</artifactId>
				<version>3.1.0</version>
			</dependency>
	
			<!--5:spring依赖 -->
			<!--1)spring核心依赖 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-core</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-beans</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<!--2)spring dao层依赖 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-jdbc</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-tx</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<!--3)springweb相关依赖 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-web</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-webmvc</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
			<!--4)spring test相关依赖 -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-test</artifactId>
				<version>4.1.7.RELEASE</version>
			</dependency>
	
			<!--添加redis依赖 -->
			<dependency>
				<groupId>redis.clients</groupId>
				<artifactId>jedis</artifactId>
				<version>2.7.3</version>
			</dependency>
	
			<!--prostuff序列化依赖 -->
			<dependency>
				<groupId>com.dyuproject.protostuff</groupId>
				<artifactId>protostuff-core</artifactId>
				<version>1.0.8</version>
			</dependency>
			<dependency>
				<groupId>com.dyuproject.protostuff</groupId>
				<artifactId>protostuff-runtime</artifactId>
				<version>1.0.8</version>
			</dependency>
		</dependencies>
		<build>
			<finalName>seckill</finalName>
			<resources>
				<!--打包时包含源代码包下的资源文件
					默认情况下只会打包src/main/java下的源代码 -->
				<resource>
					<directory>src/main/java</directory>
					<includes>
						<include>**/*.xml</include>
					</includes>
					<filtering>false</filtering>
				</resource>
				<resource>
					<directory>src/main/resources</directory>
				</resource>
			</resources>
		</build>
	</project>

>关于maven依赖的简化写法

教学视频中老师写了很多的依赖，但其实这里面有一些是可以省略不写的，因为有些包会自动依赖其它的包（Maven的传递性依赖）。这里面可以省略的依赖有：spring-core;spring-beans（上面这两个spring-context会自动依赖）;spring-context，spring-jdbc（mybatis-spring会依赖）；spring-web（spring-webmvc会依赖）；logback-core（logback-classic会依赖）

>有想要了解Maven的依赖范围与传递性依赖的请参考[该博文](http://blog.csdn.net/lewky_liu/article/details/78145211)

## 2. 秒杀业务分析

### 2.1 业务分析

>秒杀业务的核心是对库存的处理，其业务流程如下图

 ![1.png](https://github.com/lewky/MarkdownImages/blob/master/resource/seckill/1.png?raw=true)

> 用户针对库存业务分析

当用户执行秒杀成功时，应该发生以下两个操作：

* 减库存
* 记录购买明细

这两个操作属于一个完整事务，通过事务来实现数据落地
>为什么需要事务？

* 减库存却没有记录购买明细，会导致商品少卖
* 记录购买明细却没有减库存，会导致商品超卖

在实际中，以上都是很严重的事故，会给商家或买家带来损失，这是不能被允许的。一旦发生这种事故，事故责任很自然的就会去找设计实现业务的程序员

>如何实现数据落地？

有**MySQL与NoSQL**两种数据落地的方案

* MySQL属于关系型数据库，而MySQL内置的事务机制来可以准确的帮我们完成减库存和记录购买明细的过程。MySQL有多种存储引擎，但只有InnoDB存储引擎支持事务。InnoDB支持行级锁和表级锁，默认使用行级锁
* NoSQL属于非关系型数据库，近些年来在数据存储方面承担了很大的职责，但是对于事务的支持做的并不是很好，更多追求的是性能、高复用、分布式。

事务机制依然是目前最可靠的数据落地方案。

> 数据落地与不数据落地

* **落地数据**：就是被持久化的数据，这种数据一般放在硬盘或是其他的持久化存储设备里，例如：图片、系统日志、在页面上显示的数据以及保存在关系数据库里的数据等等，落地数据一定会有一个固定的载体，他们不会瞬时消失的。
* **不落地数据**：一般指存储在内存或者是网络传输里的数据，这些数据是瞬时，使用完毕就会消失，例如：我们在浏览器发送给服务器的请求；从数据库读取出来的一直到页面展示前的数据等等。
* “不落地”传输能够满足用户在性能上的要求。

### 2.2 使用MySQL实现秒杀的难点分析

>难点问题：如何高效地处理竞争？

当一个用户在执行秒杀某件商品时，其他也想要秒杀该商品的用户就只能等待，直到上一个用户提交或回滚了事务，他才能够得到该商品的锁执行秒杀操作。这里就涉及到了锁的竞争。

 ![2.jpg](https://github.com/lewky/MarkdownImages/blob/master/resource/seckill/2.jpg?raw=true)

对于MySQL来说，竞争反应到背后的技术是就是事务+行级锁：

start transaction（开启事务）→ update库存数量 → insert购买明细 → commit（提交事务）

在秒杀系统中，在同一时刻会有很多用户在秒杀同一件商品，那么如何高效低处理这些竞争？如何高效地提交事务？这些将在[Java高并发秒杀API(四)之高并发优化](http://blog.csdn.net/lewky_liu/article/details/78166080)进行分析总结。

>实现哪些秒杀功能？

下面先以天猫的秒杀库存系统为例，如下图

 ![3.jpg](https://github.com/lewky/MarkdownImages/blob/master/resource/seckill/3.jpg?raw=true)

可以看到，天猫的秒杀库存系统是很复杂的，需要很多工程师共同开发。在这里，我们只实现秒杀相关的功能

* 秒杀接口暴露
* 执行秒杀
* 相关查询

>为什么要进行秒杀接口暴露的操作？

现实中有的用户回通过浏览器插件提前知道秒杀接口，填入参数和地址来实现自动秒杀，这对于其他用户来说是不公平的，我们也不希望看到这种情况

## 3. DAO层设计

### 3.1 创建数据库

源码里有个sql文件夹，可以给出了sql语句；也可以选择自己手写。数据库一共就两个表：秒杀库存表、秒杀成功明细表。

	-- 数据库初始化脚本
	
	-- 创建数据库
	CREATE DATABASE seckill;
	-- 使用数据库
	use seckill;
	CREATE TABLE seckill(
	  `seckill_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '商品库存ID',
	  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
	  `number` int NOT NULL COMMENT '库存数量',
	  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	  `start_time` TIMESTAMP  NOT NULL COMMENT '秒杀开始时间',
	  `end_time`   TIMESTAMP   NOT NULL COMMENT '秒杀结束时间',
	  PRIMARY KEY (seckill_id),
	  key idx_start_time(start_time),
	  key idx_end_time(end_time),
	  key idx_create_time(create_time)
	)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';
	
	-- 初始化数据
	INSERT into seckill(name,number,start_time,end_time)
	VALUES
	  ('1000元秒杀iphone6',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	  ('800元秒杀ipad',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	  ('6600元秒杀mac book pro',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
	  ('7000元秒杀iMac',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');
	
	-- 秒杀成功明细表
	-- 用户登录认证相关信息(简化为手机号)
	CREATE TABLE success_killed(
	  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
	  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
	  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
	  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
	  PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
	  KEY idx_create_time(create_time)
	)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

>秒杀成功明细表为何使用联合主键

之所以使用联合主键，是为了能够过滤重复插入，可以通过`insert ignore into`语句来避免用户重复秒杀同一件商品。这样当有重复记录就会忽略，语句执行后返回数字0。
	
>可能存在的问题

安装视频里的建表过程，可能会出现建表失败的情况。原因是当你给一个timestamp设置为on update current_timestamp的时候，其他的timestamp字段需要显式设定default值。

但是如果你有两个timestamp字段，但是只把第一个设定为current_timestamp而第二个没有设定默认值，MySQL也能成功建表,但是反过来就不行。这是mysql5.5版本对timestamp的处理。

为了解决这个问题，将create_time放到start_time和end_time的前面，还有的mysql版本需要将三个时间戳都设置默认值。

### 3.2 创建数据表对应的实体类

>在`src/main/java`包下创建com.lewis.entity包，接着建立`Seckill`实体类


	public class Seckill {
	    private Long seckillId;
	
	    private String name;
	
	    private Integer number;
	
	    private Date createTime;
	
	    private Date startTime;
	
	    private Date endTime;
	
	    public Long getSeckillId() {
	        return seckillId;
	    }
	
	    public void setSeckillId(Long seckillId) {
	        this.seckillId = seckillId;
	    }
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }
	
	    public Integer getNumber() {
	        return number;
	    }
	
	    public void setNumber(Integer number) {
	        this.number = number;
	    }
	
	    public Date getCreateTime() {
	        return createTime;
	    }
	
	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }
	
	    public Date getStartTime() {
	        return startTime;
	    }
	
	    public void setStartTime(Date startTime) {
	        this.startTime = startTime;
	    }
	
	    public Date getEndTime() {
	        return endTime;
	    }
	
	    public void setEndTime(Date endTime) {
	        this.endTime = endTime;
	    }
	
		@Override
		public String toString() {
			return "Seckill [seckillId=" + seckillId + ", name=" + name + ", number=" + number + ", createTime=" + createTime + ", startTime="
					+ startTime + ", endTime=" + endTime + "]";
		}
	}

>在com.lewis.entity包下，接着建立`SuccessKilled`实体类
	
	public class SuccessKilled {
		private Byte state;
	
		private Date createTime;
	
		private Long seckillId;
	
		private Long userPhone;
	
		// 多对一,因为一件商品在库存中有很多数量，对应的购买明细也有很多。
		private Seckill seckill;
	
		public Seckill getSeckill() {
			return seckill;
		}
	
		public void setSeckill(Seckill seckill) {
			this.seckill = seckill;
		}
	
		public Long getSeckillId() {
			return seckillId;
		}
	
		public void setSeckillId(Long seckillId) {
			this.seckillId = seckillId;
		}
	
		public Long getUserPhone() {
			return userPhone;
		}
	
		public void setUserPhone(Long userPhone) {
			this.userPhone = userPhone;
		}
	
		public Byte getState() {
			return state;
		}
	
		public void setState(Byte state) {
			this.state = state;
		}
	
		public Date getCreateTime() {
			return createTime;
		}
	
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	
		@Override
		public String toString() {
			return "SuccessKilled [state=" + state + ", createTime=" + createTime + ", seckillId=" + seckillId
					+ ", userPhone=" + userPhone + "]";
		}
	
	}

### 3.3 创建实体类对应的DAO层接口（也就是Mapper接口，DAO针对的是具体实体来操作的“实体的增删改查”）

>在`src/main/java`下建立`com.lewis.dao`包，在包下建立`SeckillDao`接口

	public interface SeckillDao {
	
		/**
		 * 减库存
		 * 
		 * @param seckillId
		 * @param killTime
		 * @return 更新的记录行数，如果返回值<1则表示更新失败
		 */
		int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
		/**
		 * 根据id查询秒杀商品
		 * 
		 * @param seckillId
		 * @return
		 */
		Seckill queryById(long seckillId);
	
		/**
		 * 根据偏移量查询秒杀商品列表
		 * 
		 * @param offset
		 * @param limit
		 * @return
		 */
		List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	}

>在`com.lewis.dao`包下建立`SuccessKilledDao`接口
	
	public interface SuccessKilledDao {
	
		/**
		 * 插入购买明细，可过滤重复
		 * 
		 * @param seckillId
		 * @param userphone
		 * @return 插入的行数，如果返回值<1则表示插入失败
		 */
		int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	
		/**
		 * 根据id查询SuccessKilled并携带秒杀商品对象实体
		 * 
		 * @param seckillId
		 * @return
		 */
		SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	}

>为什么有的方法形参前有@Param，有的却没有？

从上面的代码可以发现，当方法的形参在两个及两个以上时，需要在参数前加上@Param，如果不加上该注解会在之后的测试运行时报错。这是Sun提供的默认编译器（javac）在编译后的Class文件中会丢失参数的实际名称，方法中的形参会变成无意义的arg0、arg1等，在只有一个参数时就无所谓，但当参数在两个和两个以上时，传入方法的参数就会找不到对应的形参。因为Java形参的问题，所以在多个基本类型参数时需要用@Param注解区分开来。

### 3.4 基于MyBatis实现DAO接口

>MyBatis怎么用？SQL写在哪里？

Mybatis有两种提供SQL的方式：XML提供SQL、注解提供SQL（注解是java5.0之后提供的一个新特性）。

对于实际的使用中建议使用XML文件的方式提供SQL。如果通过注解的方式提供SQL，由于注解本身还是java源码，这对于修改和调整SQL其实是非常不方便的，一样需要重新编译类，当我们写复杂的SQL尤其拼接逻辑时，注解处理起来就会非常繁琐。而XML提供了很多的SQL拼接和处理逻辑的标签，可以非常方便的帮我们去做封装。 

>如何去实现DAO接口？

Mapper自动实现DAO（也就是DAO只需要设计接口，不需要去写实现类，MyBatis知道我们的参数、返回类型是什么，同时也有SQL文件，它可以自动帮我们生成接口的实现类来帮我们执行参数的封装，执行SQL，把我们的返回结果集封装成我们想要的类型） 。

第二种是通过API编程方式实现DAO接口（MyBatis通过给我们提供了非常多的API，跟其他的ORM和JDBC很像）

在实际开发中建议使用Mapper自动实现DAO，这样可以直接只关注SQL如何编写，如何去设计DAO接口，帮我们节省了很多的维护程序，所有的实现都是MyBatis自动完成。

>创建一个目录存放Mybatis的SQL映射

按照Maven的规范，SQL映射文件应该放在`src/main/resources`包下，在该包下建立`mapper`目录，用来存放映射DAO接口的XML文件。这样Maven在编译时就会自动将`src/main/resources`下的这些配置文件编译进来。

我们也可以按照原本的习惯，在`src/main/java`下建立`com.lewis.mapper`包，将这些SQL映射存放到这里。由于Maven默认不会编译`src/main/java`下除源码以外的文件，所以需要在pom.xml中进行额外的配置。

	<build>
		<finalName>seckill</finalName>
		<resources>
			<!--打包时包含源代码包下的资源文件，默认情况下只会打包src/main/java下的源代码 -->
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>**/*.xml</include>
				</includes>
				<filtering>false</filtering>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
			</resource>
		</resources>
	</build>

在本项目中，我是采用的第二种方式存放Mybatis的SQL映射。（只是将映射DAO的mapper文件放在java包下，其他的关于Spring、MyBatis等的配置文件还是放在resources包下）

>在`src/main/resources`目录下配置mybatis-config.xml（配置MyBatis的全局属性）

打开MyBatis的[官方文档](http://www.mybatis.org/mybatis-3/zh/index.html)（MyBatis的官方文档做的非常友好，提供了非常多版本的国际化支持），选择`
入门`，找到MyBatis全局配置，里面有XML的规范（XML的标签约束dtd文件），拷入到项目的MyBatis全局配置文件中，开始配置MyBatis，如下：
	
	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE configuration
	        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-config.dtd">
	<configuration>
		<!--配置全局属性 -->
		<settings>
			<!--使用jdbc的getGeneratekeys获取自增主键值，默认是false
				当inert一条记录时我们是不插入id的，id是通过自增去赋值的
				当插入完后想得到该插入记录的id时可以调用jdbc的getGeneratekeys -->
			<setting name="useGeneratedKeys" value="true" />
			
			<!--使用列别名替换列名 默认值为true（可以不用写出来，这里写出来只是为了讲解该配置的作用）
				select name as title(实体中的属性名是title) form table; 
				开启后mybatis会自动帮我们把表中name的值赋到对应实体的title属性中 -->
			<setting name="useColumnLabel" value="true" />
	
			<!--开启驼峰命名转换Table:create_time到 Entity(createTime) -->
			<setting name="mapUnderscoreToCamelCase" value="true" />
		</settings>
	
	</configuration>

>在`src/main/java`目录下的`com.lewis.mapper`包里创建SeckillDao.xml

	<!DOCTYPE mapper
	        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<!-- namespace:指定为哪个接口提供配置 --> 
	<mapper namespace="com.lewis.dao.SeckillDao">
		<!--目的:为dao接口方法提供sql语句配置， 即针对dao接口中的方法编写我们的sql语句 -->
	
		<!-- int reduceNumber(long seckillId, Date killTime);-->
		<!-- 这里id必须和对应的DAO接口的方法名一样 -->
		<update id="reduceNumber">
			UPDATE seckill
			SET number = number-1
			WHERE seckill_id=#{seckillId}
			AND start_time <![CDATA[ <= ]]>
			#{killTime}
			AND end_time >= #{killTime}
			AND number > 0;
		</update>
	
		 <!-- parameterType:使用到的参数类型
			正常情况java表示一个类型的包名+类名，这直接写类名，因为后面有一个配置可以简化写包名的过程 -->
		<select id="queryById" resultType="Seckill" parameterType="long">
			<!-- 可以通过别名的方式列明到java名的转换，如果开启了驼峰命名法就可以不用这么写了 
	       		 select seckill_id as seckillId
	        -->
			SELECT seckill_id,name,number,create_time,start_time,end_time
			FROM seckill
			WHERE seckill_id=#{seckillId}
		</select>
	
		<select id="queryAll" resultType="Seckill">
			SELECT *
			FROM seckill
			ORDER BY create_time DESC
			limit #{offset},#{limit}
		</select>
	
	</mapper>

>在`src/main/java`目录下的`com.lewis.mapper`包里创建SuccessKilledDao.xml
	
	<!DOCTYPE mapper
	        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
	        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
	<mapper namespace="com.lewis.dao.SuccessKilledDao">
	
	    <insert id="insertSuccessKilled">
	        <!--当出现主键冲突时(即重复秒杀时)，会报错;不想让程序报错，加入ignore-->
	        INSERT ignore INTO success_killed(seckill_id,user_phone,state)
	        VALUES (#{seckillId},#{userPhone},0)
	    </insert>
	
	    <select id="queryByIdWithSeckill" resultType="SuccessKilled">
	
	        <!--根据seckillId查询SuccessKilled对象，并携带Seckill对象-->
	        <!--如何告诉mybatis把结果映射到SuccessKill属性同时映射到Seckill属性-->
	        <!--可以自由控制SQL语句-->
	
	        SELECT
	            sk.seckill_id,
	            sk.user_phone,
	            sk.create_time,
	            sk.state,
	            s.seckill_id "seckill.seckill_id",
	            s.name "seckill.name",
	            s.number "seckill.number",
	            s.start_time "seckill.start_time",
	            s.end_time "seckill.end_time",
	            s.create_time "seckill.create_time"
	        FROM success_killed sk
	        INNER JOIN seckill s ON sk.seckill_id=s.seckill_id
	        WHERE sk.seckill_id=#{seckillId} and sk.user_phone=#{userPhone}
	    </select>
	
	</mapper>

注：上面的s.seckill_id “seckill.seckill_id”表示s.seckill_id这一列的数据是Success_killed实体类里的seckill属性里的seckill_id属性，是一个级联的过程，使用的就是别名只是忽略了as关键字，别名要加上双引号。

>为什么要用`<![CDATA[]]>`把`<=`给包起来

CDATA指的是不应由 XML 解析器进行解析的文本数据，在XML元素中，`<`和`&`是非法的：

* `<`会产生错误，因为解析器会把该字符解释为新元素的开始。
* `&`也会产生错误，因为解析器会把该字符解释为字符实体的开始。（字符实体：比如`&nbsp;`表示一个空格）

所以在这里我们需要使用`<![CDATA[ <= ]]>`来告诉XML`<=`不是XML的语言。

### 3.5 整合Spring和MyBatis

在`resources`目录下创建一个新的目录`spring`(存放所有Spring相关的配置)

>在resources包下创建jdbc.properties，用于配置数据库的连接信息

	driver=com.mysql.jdbc.Driver
	url=jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=utf-8
	jdbc.username=root
	password=123

>在`resources/spring`目录下创建Spring关于DAO层的配置文件spring-dao.xml

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xmlns:context="http://www.springframework.org/schema/context"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	        http://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	
	    <!--配置整合mybatis过程
	    1.配置数据库相关参数-->
	    <context:property-placeholder location="classpath:jdbc.properties"/>
	
	    <!--2.数据库连接池-->
	    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	        <!--配置连接池属性-->
	        <property name="driverClass" value="${driver}" />
	
	        <!-- 基本属性 url、user、password -->
	        <property name="jdbcUrl" value="${url}" />
	        <property name="user" value="${jdbc.username}" />
	        <property name="password" value="${password}" />
	
	        <!--c3p0私有属性-->
	        <property name="maxPoolSize" value="30"/>
	        <property name="minPoolSize" value="10"/>
	        <!--关闭连接后不自动commit-->
	        <property name="autoCommitOnClose" value="false"/>
	
	        <!--获取连接超时时间-->
	        <property name="checkoutTimeout" value="1000"/>
	        <!--当获取连接失败重试次数-->
	        <property name="acquireRetryAttempts" value="2"/>
	    </bean>
	
	    <!--约定大于配置-->
	    <!--３.配置SqlSessionFactory对象-->
	    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	        <!--往下才是mybatis和spring真正整合的配置-->
	        <!--注入数据库连接池-->
	        <property name="dataSource" ref="dataSource"/>
	        <!--配置mybatis全局配置文件:mybatis-config.xml-->
	        <property name="configLocation" value="classpath:mybatis-config.xml"/>
	        <!--扫描entity包,使用别名,多个用;隔开-->
	        <property name="typeAliasesPackage" value="com.lewis.entity"/>
	        <!--扫描sql配置文件:mapper需要的xml文件-->
	        <property name="mapperLocations" value="classpath:com/lewis/mapper/*.xml"/>
	    </bean>
	
	    <!--４:配置扫描Dao接口包,动态实现DAO接口,注入到spring容器-->
	    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	        <!--注入SqlSessionFactory-->
	        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
	        <!-- 给出需要扫描的Dao接口-->
	        <property name="basePackage" value="com.lewis.dao"/>
	    </bean>
	
	    <!--redisDao-->
	   <!--  <bean id="redisDao" class="com.lewis.dao.cache.RedisDao">
	        <constructor-arg index="0" value="localhost"/>
	        <constructor-arg index="1" value="6379"/>
	    </bean> -->
	</beans>

>关于数据库连接池的配置可能出现的问题

在jdbc.properties里使用的是`jdbc.username`，而不是`username`或者`name`，这是因为后两个属性名可能会与全局变量冲突，导致连接的数据库用户名变成了电脑的用户名，所以使用了`jdbc.username`。

>相关链接

关于Spring的XML配置文件的头部文件的说明可以参考[这篇文章](http://blog.csdn.net/lewky_liu/article/details/78157747)

### 3.6 DAO层单元测试

有不知道Eclipse如何直接进行生成快速的测试单元的，可以看看[这篇文章](http://blog.csdn.net/jj_nan/article/details/64134781)

使用Eclipse工具直接生成测试单元，这些测试代码按照Maven规范放到`src/test/java`包下。在生成的测试代码里测试我们的方法，测试的具体代码如下：

>`SeckillDaoTest.java`

	package com.lewis.dao;
	
	import static org.junit.Assert.*;
	
	import java.util.Date;
	import java.util.List;
	
	import javax.annotation.Resource;
	
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
	
	import com.lewis.entity.Seckill;
	
	/**
	 * 配置Spring和Junit整合,junit启动时加载springIOC容器 spring-test,junit
	 */
	@RunWith(SpringJUnit4ClassRunner.class)
	// 告诉junit spring的配置文件
	@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
	public class SeckillDaoTest {
	
		// 注入Dao实现类依赖
		@Resource
		private SeckillDao seckillDao;
	
		@Test
		public void testQueryById() {
	
			long seckillId = 1000;
			Seckill seckill = seckillDao.queryById(seckillId);
			System.out.println(seckill.getName());
			System.out.println(seckill);
		}
	
		@Test
		public void testQueryAll() {
	
			List<Seckill> seckills = seckillDao.queryAll(0, 100);
			for (Seckill seckill : seckills) {
				System.out.println(seckill);
			}
		}
	
		@Test
		public void testReduceNumber() {
	
			long seckillId = 1000;
			Date date = new Date();
			int updateCount = seckillDao.reduceNumber(seckillId, date);
			System.out.println(updateCount);
		}
	
	}

>测试说明

先左键单击要测试的那个方法名，再右键点击选择`Debug As`可以单独对该方法进行单元测试。三个方法都测试通过，但是对于最后一个方法会发现数据库中该商品数量并没有减少，这是因为我们设置了秒杀时间，当前时间不满足秒杀时间，所以不会秒杀成功减少数量。

如果之前没有在DAO接口的多参数方法里在形参前加上@Param注解，那么在这里进行单元测试时，MyBatis会报绑定参数失败的错误，因为无法找到参数。这是因为Java没有保存行参的记录，Java在运行的时候会把`queryAll(int offset,int limit)`中的参数变成这样`queryAll(int arg0,int arg1)`，导致MyBatis无法识别这两个参数。

>`SuccessKilledDaoTest.java`

	package com.lewis.dao;
	
	import static org.junit.Assert.*;
	
	import javax.annotation.Resource;
	
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.test.context.ContextConfiguration;
	import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
	
	import com.lewis.entity.SuccessKilled;
	
	@RunWith(SpringJUnit4ClassRunner.class)
	// 告诉junit spring的配置文件
	@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
	public class SuccessKilledDaoTest {
	
		@Resource
		private SuccessKilledDao successKilledDao;
	
		@Test
		public void testInsertSuccessKilled() {
	
			long seckillId = 1000L;
			long userPhone = 13476191877L;
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			System.out.println("insertCount=" + insertCount);
		}
	
		@Test
		public void testQueryByIdWithSeckill() {
	
			long seckillId = 1000L;
			long userPhone = 13476191877L;
			SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
			System.out.println(successKilled);
			System.out.println(successKilled.getSeckill());
		}
	
	}

>测试说明

测试方法同上，测试结果通过，另外由于我们使用了联合主键，在insert时使用了ignore关键字，所以对于`testInsertSuccessKilled()`重复插入同一条数据是无效的，会被过滤掉，确保了一个用户不能重复秒杀同一件商品。

>本节结语

至此，关于Java高并发秒杀API的DAO层的开发与测试已经完成，接下来进行Service层的开发、测试，详情可以参考[Java高并发秒杀API(二)之Service层](http://blog.csdn.net/lewky_liu/article/details/78162149)。
