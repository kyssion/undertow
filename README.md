### magic-sso

#### 系统依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.magic</groupId>
    <artifactId>sso</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>io.undertow</groupId>
            <artifactId>undertow-core</artifactId>
            <version>2.0.15.Final</version>
        </dependency>
        <dependency>
            <groupId>io.undertow</groupId>
            <artifactId>undertow-servlet</artifactId>
            <version>2.0.15.Final</version>
        </dependency>
        <dependency>
            <groupId>io.undertow</groupId>
            <artifactId>undertow-websockets-jsr</artifactId>
            <version>2.0.15.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.jboss.logging/jboss-logging -->
        <dependency>
            <groupId>org.jboss.logging</groupId>
            <artifactId>jboss-logging</artifactId>
            <version>3.3.2.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.jboss.logging/jboss-logging-processor -->
        <dependency>
            <groupId>org.jboss.logging</groupId>
            <artifactId>jboss-logging-processor</artifactId>
            <version>2.1.0.Final</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>io.undertow</groupId>
            <artifactId>undertow-websockets-jsr</artifactId>
            <version>2.0.15.Final</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-slf4j-impl</artifactId>
            <version>2.11.1</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.12</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.13</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

> 从体系结构上来看主要依赖jboss 的 undertow ,alibaba 开源数据库连接池

#### 单点登入架构设计

#### 需求统计

1. 多用户登入的存储
2. 跨域登入

##### 1. 初次登入业务流程图

```plantuml
@startuml login
actor user
database cache 
database database 
user -> service_one : 发送请求信息
service_one -> service_one : cookie中是否有token
alt 存在token
    service_one -> redis : 查询是否存在userId 和 token 的记录
    redis --> service_one : 返回记录的查询结果
    alt 如果不存在记录
        service_one->user:重定向到sso并携带service url地址
        user->sso : 进行登入校验 并传递sso处理后要跳转的地址 url(1)
        alt 如果用户在sso登入过(存在sso cookie token)
            sso->cache : 使用cookie token值查询 登入token
            cache-->sso: 返回登入结果
            sso->service_one : 使用jsonp等技术写入登入 token值
            sso->user : 重定向到 url(1)
        else 用户没有在sso登入过(无 so cookie token)
            sso->user:进入登入页面
            user->sso: 输入登入信息
            sso->database:用户查询
            database-->sso:返回查询结果
            alt 无查询结果
                sso->user:账号或密码错误请重试
            else 存在查询结果
                activate sso
                sso->sso : 生成登入token
                sso->cache : 写入登入token
                sso->database : 写入登入token
                sso->sso : 生成sso cookie token
                sso->cache : 写入 soo cookie token和登入token的对应关系
                sso->user : 写入用户sso cookie 到前端
                sso->service_one : 使用jsonp等技术写入登入 token值
                sso->user : 重定向到重定向到 url(1)
                deactivate sso
            end
        end
    else 存在记录
        service_one->service_one:执行剩余业务逻辑
        service_one->user: 返回结果
    end

else 不存在token
    service_one->user:重定向到sso并携带service url地址
    user->sso : 进行登入校验 并传递sso处理后要跳转的地址 url(1)
    alt 如果用户在sso登入过(存在sso cookie token)
        sso->cache : 使用cookie token值查询 登入token
        cache-->sso: 返回登入结果
        sso->service_one : 使用jsonp等技术写入登入 token值
        sso->user : 重定向到 url(1)
    else 用户没有在sso登入过(无 so cookie token)
        sso->user:进入登入页面
        user->sso: 输入登入信息
        sso->database:用户查询
        database-->sso:返回查询结果
        alt 无查询结果
            sso->user:账号或密码错误请重试
        else 存在查询结果
            activate sso
            sso->sso : 生成登入token
            sso->cache : 写入登入token
            sso->database : 写入登入token
            sso->sso : 生成sso cookie token
            sso->cache : 写入 soo cookie token和登入token的对应关系
            sso->user : 写入用户sso cookie 到前端
            sso->service_one : 使用jsonp等技术写入登入 token值
            sso->user : 重定向到重定向到 url(1)
            deactivate sso
        end
    end
end
@enduml
```


