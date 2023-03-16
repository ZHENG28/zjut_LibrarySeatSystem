# 图书馆座位预约系统（Library Seat System）
### 1 项目简介
- 功能：
    - 管理员端：登录地址（或点击用户端首页右上角的“进入后台”）：http://localhost:8080/adminLogin
        1. 可在主页查看今日系统访问量以及当前在馆人数（朝晖、屏峰、莫干山）【待完善】
        2. 管理座位信息与用户信息，如查看座位信息，新建区域座位表，查看用户选座记录等
    - 用户端：登录地址：http://localhost:8080/
        1. 通过展示在页面上的各校区图书馆的3D模型，进行可视化选座；或者是根据系统推荐，进行选座
        2. 可查看当前选座情况与违规次数，以及修改当前选座状态，同时可扫描二维码进行签到签退
        3. 可查看场馆内所有座位的信息与本人历史选座记录
        4. 可查询收录在图书馆中的所有书籍，以及留言反馈
- 所用技术：
	- 前端：HTML+CSS+JavaScript、layui、ajax、three.js
	- 后端：Spring+SpringMVC+Mybatis框架、生成二维码（com.google.zxing）
- 项目启动命令：
    1. 使用项目下的res/db/create.sql文件初始化数据库
    2. 修改配置文件application.properties中的各项参数：`spring.datasource.url`和`spring.datasource.password`
### 2 页面展示
#### 1 管理员端
1. 登陆页面：
    ![admin-login](/res/img/admin_login.jpg)
2. 主页：
    ![admin-home](/res/img/admin_homePage.jpg)
3. 查看座位信息：
    ![admin-seatInfo](/res/img/admin_seatInfo.jpg)
4. 新增座位表：
    ![admin-insertSeat](/res/img/admin_insertSeat.jpg)
5. 用户信息管理：
    ![admin-userInfo](/res/img/admin_userInfo.jpg)
6. 用户选座记录：
    ![admin-userHistory](/res/img/admin_userHistory.jpg)
#### 2 用户端（教师与学生页面相同，均只有选座功能）
1. 登陆页面：
    ![user-login](/res/img/index.jpg)
2. 主页：
    ![user-homePage](/res/img/user_homePage.jpg)
3. 选座-选择校区：
    ![user-selectSeat-campus](/res/img/user_selectSeat_campus.jpg)
4. 选座-选择楼层：
    ![user-selectSeat-floor](/res/img/user_selectSeat_floor.jpg)
5. 查看当前选座情况：
    ![user-seatInfo](/res/img/user_seatInfo.jpg)
6. 留言反馈：
    ![user-leaveMsg](/res/img/user_leaveMsg.jpg)
