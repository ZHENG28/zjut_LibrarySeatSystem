这里是图书馆座位预约系统。

项目启动说明：（本项目采用前后端不分离的方法）
1. 电脑系统的环境变量配置：java（本项目采用的JDK版本为11，电脑上的JDK版本号高于11即可）、mysql
2. 后端端口号为8080（启动项目时，请确保端口号8080未被占用）
3. 配置好src/main/resources/application.properties文件中的部分属性：
	1) spring.datasource.url（务必加上后缀?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8）
	2) spring.datasource.username
	3) spring.datasource.password