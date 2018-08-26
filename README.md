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
