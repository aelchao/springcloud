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

#### Hystrix Dashboard
断路器仪表盘:断路器状态监控界面
1. 添加两个依赖

    ![依赖](images/hystrix-dashboard-01.png)
2. 在启动类上添加注解

    ![注解](images/hystrix-dashboard-02.png)
3. 界面如下

    ![界面](images/hystrix-dashboard-03.png)

### 路由Zuul
Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/a转发到到a服务，/api/b转发到到b服务。zuul默认和Ribbon结合实现了负载均衡的功能。

#### 路由转发
1. 架构示意图

    ![架构示意图](images/zuul-01.png)
2. 创建项目，添加依赖

    ![依赖](images/zuul-02.png)
3. 添加注解

    ![注解](images/zuul-03.png)
4. 修改核心配置文件

    ![配置wenjian ](images/zuul-04.png)
5. 测试
    启动注册中心、provider、consumer和zuul项目，访问[http://localhost:8087/a/getinfo](http://localhost:8087/a/getinfo) 或 [http://localhost:8087/b/getinfo](http://localhost:8087/b/getinfo)

#### Zuul过滤器
1. 编写过滤器类继承ZuulFilter，并重写当中的方法。
    方法解释：
    
    ![zuul-filter](images/zuul-filter-01.png)
2. Debug启动Consumer项目并在controller方法上加断点，看是否请求相应的项目。
   访问路径：[http://localhost:8087/a/getinfo](http://localhost:8087/a/getinfo) 或 [http://localhost:8087/b/getinfo](http://localhost:8087/b/getinfo)

### 多注册中心
当成千上万个服务向注册中心注册的时候，它的负载是非常高的，这在生产环境上是不太合适的，如何将Eureka Server集群化？通过运行多个Eureka实例，使其更具有高可用性即可。

1. 核心配置文件
    1. application-peer1.properties
        ```text
        # 运行端口号
        server.port=8088
        
        # 指定注册中心实例名
        eureka.instance.hostname=localhost
        
        # 应用名称
        spring.application.name=springcloud-reg1
        
        #注册当前服务
        eureka.client.service-url.defaultZone=http://localhost:8089/eureka
    
        ```
    2. application-peer2.properties
        ```text
        # 运行端口号
        server.port=8089
        
        # 指定注册中心实例名
        eureka.instance.hostname=localhost
        
        # 应用名称
        spring.application.name=springcloud-reg2
        
        #注册当前服务
        eureka.client.service-url.defaultZone=http://localhost:8088/eureka
        ```
    3. application.properties
        ```text
        # 指定默认加载的配置文件
        spring.profiles.active=peer1
        ```
2. 指定注册中心

    在服务提供者、消费者、代理方等项目指定多个注册中心即可
    `eureka.client.service-url.defaultZone=http://localhost:8088/eureka,http://localhost:8089/eureka`
3. 打包运行
    1. 打包命令`mvn package`
    2. 运行
        ```shell
        java -jar xxxx.jar  --spring.profiles.active=peer1
        java -jar xxxx.jar  --spring.profiles.active=peer2
        ```

### 配置管理中心config
在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件。在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程SVN或Git仓库中。在spring cloud config 组件中，分两个角色，一是config server，二是config client。

1. 配置文件统一管理示意图
    
    ![示意图](images/config-01.png)
2. 创建服务端
    1. 创建项目，添加依赖

        ![创建项目](images/config-server-01.png)
    2. 修改pom文件（核对版本号），手动添加svnkit依赖（mvnRepository查找）
        ```xml
        <dependency>
            <groupId>org.tmatesoft.svnkit</groupId>
            <artifactId>svnkit</artifactId>
        </dependency>
        ```
    3. 在启动类上添加注解
        ```java
        package com.cylib;
        
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
        import org.springframework.cloud.config.server.EnableConfigServer;
        
        @SpringBootApplication
        @EnableDiscoveryClient
        @EnableConfigServer
        public class Springcloud10ConfigServerApplication {
        
            public static void main(String[] args) {
                SpringApplication.run(Springcloud10ConfigServerApplication.class, args);
            }
        }
        ```
    4. 修改核心配置文件application.properties
        ```text
        # 运行端口号
        server.port=8090
        
        # 应用名称
        spring.application.name=config-server
        
        # 注册中心路径
        eureka.client.service-url.defaultZone=http://localhost:8080/eureka
        
        # SVN配置信息
        # 指定连接版本库类型
        spring.profiles.active=subversion
        # 指定SVN服务器路径
        spring.cloud.config.server.svn.uri=svn://localhost/springcloud/
        spring.cloud.config.server.svn.username=aelchao
        spring.cloud.config.server.svn.password=aelchao
        # 指定默认访问的分支名称
        spring.cloud.config.server.svn.default-label=trunk
        ```
    5. SVN服务器新建springcloud库，在trunk下新建config-server-dev.properties，添加`star.username=zhangsan`
    6. 测试config-server端是否可用
    
        * 访问路径：[http://localhost:8086/config-server/dev](http://localhost:8086/config-server/dev)
        * 访问路径中config-server是应用名称，dev是配置文件名的一部分，底层自动拼接成要访问的文件名。
        * 返回信息如下，有配置文件中内容，则证明config-server端可用

            ![返回信息](images/config-server-02.png)
3. 创建客户端
    1. 创建项目，添加依赖（修改pom.xml）（由于测试使用到springmvc，需要添加web起步依赖）
        
        1. ![创建项目](images/config-client-01.png)
        
            ```xml
            <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            ```
        
        2. 其他依赖和服务端相同
        
    2. 重命名核心配置文件为bootstrap.properties
        ```text
        # 运行端口号
        server.port=8091
        
        # 应用名称
        spring.application.name=config-client
        
        # 注册中心路径
        eureka.client.service-url.defaultZone=http://localhost:8080/eureka
        
        # 服务端地址
        spring.cloud.config.uri=http://localhost:8090
        
        # 分支名称
        spring.cloud.config.label=trunk
        
        # 访问文件名称
        spring.cloud.config.name=config-client
        spring.cloud.config.profile=dev
        ```
    3. 在SVN，springcloud/trunk/新建config-config-dev.properties，添加`star.username=lisi`
    4. 启动类测试代码如下：
        ```java
        package com.cylib;
        
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        
        @SpringBootApplication
        @EnableDiscoveryClient
        @RestController
        public class Springcloud11ConfigClientApplication {
        
            @Value("${star.username}")
            private String username;
        
            @RequestMapping("getusername")
            public String getUsername() {
                return username;
            }
        
           public static void main(String[] args) {
              SpringApplication.run(Springcloud11ConfigClientApplication.class, args);
           }
        }
        ```
    5. 浏览器访问，结果如下：

        ![效果](images/config-client-02.png)
    6. 配置中心服务端和客户端都需要添加注册中心，否则客户端会启动失败
        * pom.xml
            ```xml
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            </dependency>
            ```
        * 启动类增加注解@EnableDiscoveryClient
