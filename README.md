# 手写Spring

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
- 事件监听-ApplicationEvent&ApplicationListener
- Environment抽象
- 注解驱动
- AOP

## 二、文档规划

| 内容                                                         | 快速进入 | 进度 |
| :----------------------------------------------------------- | :------- | :--- |
| IoC容器概览-BeanFactory与ApplicationContext                  |          |      |
| Bean的元信息介绍-BeanDefinition                              |          |      |
| Bean生命周期之资源加载阶段-XmlBeanDefinitionReader           |          |      |
| Bean生命周期之资源解析阶段-DefaultBeanDefinitionDocumentReader |          |      |
| Bean生命周期之BeanDefiniton注册阶段-BeanDefinitionRegistry   |          |      |
| Bean生命周期之BeanDefinition合并阶段-AbstractBeanFactory#getMergedBeanDefinition |          |      |
|                                                              |          |      |
|                                                              |          |      |
|                                                              |          |      |



