# 项目中的要点解释
当你来到这个项目时，你一定已经完成了FirstSpringBootDemo<https://github.com/dandan3029/FirstSpringBootDemo>
该项目是对FirstSpringBootDemo的重构，将其重构为了多模块的项目。
重构工作包含以下两个步骤：
1. 创建子模块工程(<module>)
2. 子模块依赖管理(<dependencyManagement>)

子模块工程包含以下三个部分：
模型层：model           User
持久层：persistence     UserRepository
表示层：web             其他
其中，模型层位于最底层，持久层位于中间层，表示层位于最高层。
表示层依赖持久层，持久层依赖模型层。
根据以上的依赖关系，我们可以通过在各模块的pom文件中添加<dependencies></dependencies>来完成其对应关系。