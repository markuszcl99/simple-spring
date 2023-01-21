# 手写Spring

![spring-bg](01-assets/spring-bg.jpg)

## 一、前言

2022年，是学习Spring的一年，但一年下来，我从Spring中学到了什么呢？设计理念？IoC核心容器？AOP编程？我真正理解到了吗？各模块是怎么运作的，实现原理又是如何？基于此，《手写Spring》的想法就突入脑海，于是新建仓库来完成手撸Spring框架的工作。

Spring由2002年10月发布第一个，由一个带有易于配置和使用的控制反转（IoC）容器的小型内核组成，到如今发展出Spring Boot、Spring Cloud等各种产品。Spring社区庞大而复杂，完全手写是极其的不现实，本项目旨在完成Spring IoC核心容器功能、AOP Bean代理即可。

项目主要实现点如下：
- 底层IoC容器-BeanFactory
- 应用程序上下文-ApplicationContext
- 资源加载
- Bean生命周期
- Bean作用域
- 应用生命周期
- 类型转换
- 事件监听
- Environment抽象
- 注解驱动
- AOP

## 二、文档规划

> 在文档方面的规划是:
>
> - 基于《[Spring Framework Documentation](https://docs.spring.io/spring-framework/docs/5.2.19.RELEASE/spring-framework-reference/index.html)》《[小马哥讲 Spring 核心编程思想](https://time.geekbang.org/course/intro/100042601?tab=catalog)》《[小马哥讲 Spring AOP 编程思想](https://time.geekbang.org/course/intro/100066301?tab=catalog)》《[Spring源码深度解析（第2版）](https://book.douban.com/subject/30452948/)》资料，梳理出一套自己对于Spring框架核心实现原理的总结（如果有描述不对的地方，还请批评指教）
> - 手撸Spring核心源码时，实现了哪些内容，如何设计的，总结出一版思考文档

### 1、原理总结专栏

- [ ] [【spring源码分析】SpringFramework简介](https://github.com/markuszcl99/simple-spring/blob/master/02-doc/SpringFramework简介.md)
- [ ] 【spring源码分析】Spring IoC容器概览
- [ ] 【spring源码分析】Spring IoC容器生命周期(一)之refresh主流程
- [ ] 【spring源码分析】Spring IoC容器生命周期(二)之start流程
- [ ] 【spring源码分析】Spring IoC容器生命周期(三)之close流程
- [ ] 【spring源码分析】Spring IoC容器生命周期(二)之stop流程
- [ ] 【spring源码分析】Spring Bean概览(一)之Bean基础
- [ ] 【spring源码分析】Spring Bean概览(二)之Bean作用域
- [ ] 【spring源码分析】Spring Bean生命周期(一)之加载BeanDefinition
- [ ] 【spring源码分析】Spring Bean生命周期(二)之解析BeanDefinition
- [ ] 【spring源码分析】Spring Bean生命周期(三)之注册BeanDefinition
- [ ] 【spring源码分析】Spring Bean生命周期(四)之合并BeanDefinition
- [ ] 【spring源码分析】Spring Bean生命周期(五)之Bean实例创建
- [ ] 【spring源码分析】Spring Bean生命周期(六)之Bean属性填充
- [ ] 【spring源码分析】Spring Bean生命周期(七)之Bean功能拓展
- [ ] 【Spring源码分析】Spring Bean生命周期(八)之Bean循环依赖处理
- [ ] 【spring源码分析】Spring IoC容器之基于Java注解的容器配置
- [ ] 【spring源码分析】Spring IoC容器之泛型处理
- [ ] 【spring源码分析】Spring IoC容器之事件处理
- [ ] 【spring源码分析】Spring IoC容器之Environment抽象
- [ ] 【spring源码分析】Spring IoC容器之数据绑定
- [ ] 【spring源码分析】Spring IoC容器之数据校验
- [ ] 【spring源码分析】Spring IoC容器之SpEL表达式

### 2、手撸源码专栏

