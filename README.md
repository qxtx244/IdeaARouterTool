ARouterTool
===========

## **概述**
gradle插件。针对开源路由框架[ARouter](https://github.com/alibaba/ARouter)的辅助方案，提供一些便捷功能。主要功能如下：
+ 收集ARouter为各module生成的api文档
+ ~~（已移除）ARouter的自动依赖注入~~

## **使用**

### **1.插件依赖**
在目标工程根或module的build.gradle顶部中添加：
```
buildScript {
   repositories {
       mavenCentral()  //添加mavenCentral 
   } 
   dependencies {
       classpath 'io.github.qxtx244.build:IdeaARouterTool:1.0.1'    //添加到classpath
   }
}
```

### **2.使插件工作**
1. **添加代码**  
   在父级module的build.gradle中，添加以下代码（*将所有使用ARouter的组件库都放到一起，更容易统一管理*）  
   添加插件
   ```
   apply plugin: 'arouter-tool'
   ```
   启用和配置插件
   ```
   ARouterTool {
        docConfig {                    //doc相关功能配置
            docEnable true             //启用doc收集功能，默认为false       
            includes 'xxx', 'yyy'      //指定一个或多个project path，对子project也有效。默认为空
            excludes 'aaa', ':yyyy:bb' //可选。指定跳过的project，使用project path。如aaa，yyyy/bb等，不包括project的子project。默认为空       
        }           
   }
    ```
2. **执行sync和build**  
    执行完成后，如果ARouter产生api文档，ARouterTool插件将收集它们，保存到目标project的根目录/ARouterDocs中。  
    这将有利于路由信息的归档

## **demo**
demo工程将演示在使用ARouter时，ARouterTool插件如何收集ARouter为各个module生成的api文档。

