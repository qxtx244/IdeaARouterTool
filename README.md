IdeaARouterTool
===========

## **概述**
针对开源路由框架[ARouter](https://github.com/alibaba/ARouter)的辅助性gradle插件，提供一些便捷功能。主要功能如下：
+ 对ARouter产生的api doc的收集
+ ~~（已移除）ARouter的自动依赖注入~~

## **使用**

### **· 导入插件**
  1. 打开AS右侧Gradle面板，依次展开IdeaARouterTool/ARouterTool/Tasks；
  2. 展开build任务组，双击执行“jar”选项，jar文件输出目录：ARouterTool/jars
  3. 在目标工程根或module的build.gradle顶部中添加：
     ```
     buildScript {
        dependencies {
            classpath files('插件jar的路径') //依赖插件
        }
     }
     ```

### **· 使插件工作**
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
demo工程将演示使用ARouter框架的时候，demo工程如何收集它自己以及依赖的其它module中arouter生成的api doc。  
插件配置在IdeaARouterTool/demo/build.gradle

