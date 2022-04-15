ARouterTool
===========

## **概述**
针对开源路由框架[ARouter](https://github.com/alibaba/ARouter)的功能扩展gradle插件。  
此插件是针对父project扩展，因此，将其配置到父project的build.gradle中，以作用于它的子module。主要功能如下：
+ 提供对ARouter产生的doc文档的收集能力
+ ~~（已移除）提供ARouter的自动依赖注入~~

## **使用**
### 1. **发布/打包插件**
打开AS右侧Gradle面板，依次展开ARouterTool/ARouterTool/Tasks
+ **方案一**：打包成jar包
  > 1. 展开build任务组，双击执行“jar”选项
  > 2. 生成的jar文件位于项目build/jars/ARouterTool.jar
+ **方案二**：发布到本地maven仓库
  > 1. 展开”build“任务组，双击执行“assemble”选项，然后再展开”upload“任务组，双击执行”uploadArchives“选项，可发布发到本地windows用户目录/.mavenCentral。
        如果还想打包库，执行“extension”任务组中的“uploadArchives”即可，zip包输出到目标module/build目录下。

### 2. **导入插件**
+ **jar包形式**
  ```
  dependencies {
     classpath files('插件jar的绝对路径')
  }
  ```
+ **依赖形式**  
  在目标工程根project的build.gradle中添加：
  ```
  buildScript {
      repositories {
          maven {       
            //本地maven仓库位于 用户目录\.mavenCentral        
            url uri("${System.getProperties().getProperty('user.home')}/.mavenCentral")
          }
      }
      dependencies {
          classpath 'com.qxtx.idea.gradle.plugin.aroutertool:ARouterTool:1.0.0'
      }
  }
  ```

### 3. **使插件工作**
1. **添加代码**  
  在父级module的build.gradle中，添加以下代码，即可作用于所有的子module（*将所有使用ARouter的组件库都放到一起，更容易统一管理*）  
   添加插件
    ```
   apply plugin: 'arouter-tool'
   ```
   启用和配置插件
   ```
   ARouterTool {
        docConfig {
            //启用doc收集功能，输出路径为module。默认为false，即功能不生效。     
            docEnable true                      
            //指定需要收集arouter-apiDoc的project，使用project path。默认为空，即未指定任何project，功能将不生效。
            //注意，如果指定的project包含子project，则也会包含这些子project在内；
            includes 'xxx', 'yyy'
            //可选。指定跳过的project，使用project path。如aaa，yyyy/bb等，不包括指定project的子project。默认为空。
            excludes 'aaa', ':yyyy:bb'            
        }           
   }
    ```
3. **执行sync、build**  
    执行完成后，如果ARouter产生api文档，ARouterTool插件将收集它们，保存到目标project的根目录/ARouterDocs中。  
    这将有利于路由信息的归档
4. **demo**  
demo将演示使用ARouter框架的时候，demo工程如何收集它自己以及依赖的其它module中arouter生成的api doc。  
插件配置位于demo/build.gradle中

