## Spring Cloud
### 简介
1. Spring Cloud是基于Spring Boot的一整套实现微服务的框架。提供了微服务开发所需的配置管理、服务发现、断路器、智能路由、微代理、控制总线、全局锁、决策竞选、分布式会话和集群状态管理组件。
2. [官网](https://projects.spring.io/spring-cloud/)
3. dubbo和Spring Cloud对比

    ![dubbo和Spring Cloud对比](images/dubbo-springcloud.png)

### Eureka注册中心
1. 创建项目，选择依赖

    ![创建项目](images/eureka-01.png)
2. 修改pom.xml（springboot和springcloud版本号）
3. 修改核心配置文件

    ![配置文件](images/eureka-02.png)
4. 程序入口启动类添加注解

    ![注解](images/eureka-03.png)
5. 启动访问

    ![启动访问](images/eureka-04.png)

### Provider创建
1. 创建Provider，添加依赖

    ![创建provider](images/provider-01.png)
2. 修改pom.xml（springboot和springcloud版本号）
3. 修改application.properties

    ![application.properties](images/provider-02.png)
4. 在应用启动类中添加注解

    ![注解](images/provider-03.png)
5. 启动provider，刷新注册中心页面，可查看注册的服务

    ![启动](images/provider-04.png)

### Consumer创建-Ribbon方式
Spring cloud的服务消费者Consumer有两种服务调用方式，一种是ribbon+restTemplate，另一种是feign。
ribbon是一个负载均衡客户端。Feign默认集成了ribbon。

1. 基于ribbon+restTemplate的服务调用架构示意图

    ![架构图](images/consumer-ribbon-01.png)
2. 创建项目、添加依赖

    ![创建项目](images/consumer-ribbon-02.png)
3. 修改pom.xml（springboot和springcloud版本号）
4. 配置文件application.properties

    ![application.properties](images/consumer-ribbon-03.png)
5. 在springboot启动类中添加注解、创建restTemplate

    ![启动](images/consumer-ribbon-04.png)
6. 创建service、controller
    编写HelloService.java(在service实现类中完成服务调用)

    ![创建service](images/consumer-ribbon-05.png)

    ![创建controller](images/consumer-ribbon-06.png)

### Consumer创建-Feign方式
该方式是对RestTemplate方式的封装（无需创建该对象，并通过该对象调用服务），直接在Consumer的Service接口中通过Feign注解完成服务的调用。
简而言之：
* Feign 采用的是基于接口的注解
* Feign 整合了ribbon
1. 创建项目、添加依赖

    ![创建项目](images/consumer-feign-01.png)
2. 修改pom.xml（springboot和springcloud版本号）
3. 核心配置文件application.properties

    ![application.properties](images/consumer-feign-02.png)
4. 在应用启动类中添加注解

    ![注解](images/consumer-feign-03.png)
5. 编写service接口和controller代码

    ![service](images/consumer-feign-04.png)
6. 运行测试
7. 与ribbon+rest对比
    * 依赖不同
    * 注解不同
    * 加载对象
        1. ribbon需要加载RestTemplate
        2. feign无需手动加载

### 断路器-Hystrix
如果单个服务出现问题，由于服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。
为了解决这个问题，业界提出了断路器模型。
当对特定的服务的调用的不可用达到一个阈值（Hystrix 是5秒20次） 断路器将会被打开。断路打开后，可以避免连锁故障，fallback方法可以直接返回一个固定值。    

![hystrix](images/hystrix-01.png)

#### ribbon+RestTemplate方式中使用断路器
1. 创建项目、添加依赖

    ![创建项目](images/hystrix-ribbon-01.png)
2. 修改pom.xml（springboot和springcloud版本号）
3. 编写核心配置文件，同ribbon项目

    ![配置文件](images/hystrix-ribbon-02.png)
4. 启动类添加注解

    ![注解](images/hystrix-ribbon-03.png)
5. HelloService.java
    ![service](images/hystrix-ribbon-04.png)    

#### feign中使用断路器
1. 手动添加hystrix的依赖（在mvnRepository找即可）
    
    ![hystrix依赖](images/hystrix-feign-01.png)
2. 核心配置文件，开启Feign自带的断路器

    ![配置文件](images/hystrix-feign-02.png)
3. 添加service实现、修改service接口
    * 添加service实现

        ![service实现](images/hystrix-feign-03.png)
    
    * 修改service接口

        ![service接口](images/hystrix-feign-04.png)

