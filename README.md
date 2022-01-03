# Main
## Main.java
驱动类。循环菜单项直至用户要求退出。
# Operator
## Manage.java
接口，提供增删查改基本操作。
## GradeManage.java
实现了接口的针对学生成绩管理的实现。
其中主要的数据结构是对象数组，其中对象是Tool.Student类。
包含对用户输入数据的合法性检验，以及每次添加新信息后自动排序。
# Tool
## Student.java
数据元单元Student：封装了学生姓名、学号、各科成绩等信息。
每一个Student实例看做一条info，多条info组成Student[]数组。
## Menu.java
菜单的创建。
用户在switch-case中选择将调用Manage方法。
在main类中run()即为循环创建Menu对象。
## IOfile.java
通过两次正则表达式切割，将文件转换成Student对象数组。


