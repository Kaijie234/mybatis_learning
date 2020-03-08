## 前言

### 什么是框架

它是我们软件开发中的一套解决方案，不同的框架解决不同的问题。使用框架的好处：框架封装了很多的细节，使开发者可以使用极简的方式实现功能。大大提高开发效率。

### 三层架构

- 表现层：展示数据
- 业务层：处理业务需求
- 持久层：和数据库交互

![三层架构](/images/Mybatis学习/01三层架构.png)

### 持久层技术解决方案

1. JDBC技术：Connection、PrepareStatement、ResultSet
2. Spring的JdbcTemplate：Spring对jdbc的简单封装
3. Apache的DBUtils：和Spring的JdbcTemplate很像，也是对Jdbc的简单封装

以上这些都不是框架：JDBC是规范、Spring的JdbcTemplate和Apache和DBUtils都只是工具类(封装的还不够细致)。

JDBC存在的问题：

1. 数据库链接创建、释放频繁造成系统资源浪费从而影响系统性能，如果使用数据库链接池可解决此问题。
2. Sql 语句在代码中硬编码，造成代码不易维护，实际应用 sql 变化的可能较大，sql 变动需要改变 java
代码。
3. 使用 preparedStatement 向占有位符号传参数存在硬编码，因为 sql 语句的 where 条件不一定，可能
多也可能少，修改 sql 还要修改代码，系统不易维护。
4. 对结果集解析存在硬编码（查询列名），sql 变化导致解析代码变化，系统不易维护，如果能将数据库记
录封装成 pojo(Plain Ordinary Java Object,简单的Java对象，实际就是普通JavaBeans，是为了避免和EJB（Enterprise JavaBean）混淆所创造的简称。) 对象解析比较方便。

