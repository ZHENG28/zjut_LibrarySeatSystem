<%@ page import="entity.User" %>
<%@ page import="entity.Desk" %>
<%@ page import="entity.Student" %>
<%@ page pageEncoding="utf-8" %>
<%
    User user = (User) session.getAttribute("user");
    Desk desk = ((Student) user).desk;
%>
<!DOCTYPE html>
<head>
    <title>图书馆订座系统</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">

    <script type="text/javascript" src="/Library/main/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="/Library/main/js/mapctrl.js"></script>
    <script type="text/javascript" src="/Library/main/js/enter.js"></script>
    <script type="text/javascript" src="/Library/main/js/jquery.seat-charts.js"></script>
    <script type="text/javascript" src="/Library/main/js/seat-create.js"></script>


    <link rel="stylesheet" type="text/css" href="/Library/assets/css/layui.css">
    <link rel="stylesheet" type="text/css" href="/Library/assets/css/admin.css">

    <link rel="stylesheet" type="text/css" href="/Library/main/css.css">
    <link rel="stylesheet" type="text/css" href="/Library/main/style.css">
    <link rel="stylesheet" type="text/css" href="/Library/main/jquery.seat-charts.css">

    <script>
        let campusAbbr, floors, seatStr = "";
        $(function () {
            $("#campus li").click(function () {
                let campus = $(this).text();
                campusAbbr = "朝晖" == campus ? "ZH" : ("屏峰" == campus ? "PF" : "MGS");
            });
            $("#floor li").click(function () {
                let floortext = $(this).text();
                // 待优化
                floors = "一楼" == floortext ? 1 : ("二楼" == floortext ? 2 : ("三楼" == floortext ? 3 : 4));
                $.ajax({
                    url: "/Library/seat/find",
                    type: "POST",
                    data: {
                        "sno": "${user.getSno()}",
                        "campus": campusAbbr,
                        "floor": floors
                    },
                    dataType: 'JSON',
                    success: function (response) {
                        layoutStr = response.seatLayout;
                        mapArr = layoutStr.split(',');
                        seatLayout();
                        let seat_id = seatId(mapArr, response.selected);
                        sc.status(seat_id, 'selected');
                    }
                });
            });
        });

        function seatId(arr, deskno) {
            let count, line = 1, num = 0;
            for (let i = 0; i < mapArr.length; i++) {
                // 获取位置在第line行，第num个（并非其布局id，需要算上空格）
                // 每行默认座位个数（减去单引号）
                count = mapArr[i].length - 2;
                let str = mapArr[i];
                for (let j = 0; j < str.length; j++) {
                    // 减去空位的个数
                    if (str[j] == '_') {
                        count--;
                    }
                }
                if (deskno - count > 0) {
                    line++;
                    deskno -= count;
                } else {
                    num = deskno;
                    break;
                }
            }
            let str = mapArr[line - 1];
            // 去掉头尾的单引号
            for (let j = 1; j < str.length - 1; j++) {
                if (str[j] != '_') {
                    num--;
                    if (num == 0) {
                        return line + '_' + j;
                    }
                }
            }
        }
    </script>
</head>
<body>
<header class="layui-header">
    <h3 class="hd" id="up">图书馆订座系统</h3>
    <h4 style="float: left;text-shadow: 3px 3px 3px rgba(0, 0, 0, .29);">浙江工业大学图书馆</h4>
    <%--    导航栏--%>
    <div id="hddiv">
        <ul>
            <img id="sheadSculpture" src="/Library/main/img/Hime.png" height="25px">
            <span class="name">${user.getSname()}∨</span>
<%--            <img src="${user.getIDadress()}"><br>--%>
            <li style="top: 33px" class="identity_sign">个人信息</li>
            <li style="top: 59px">
                <a class="dl">更改密码</a>
            </li>
            <li style="top: 85px">
                <a onclick="show_confirm()">退出登录</a>
                <script>
                    function show_confirm() {
                        let answer = confirm("您确定要退出吗？");
                        if (answer == true) {
                            self.location = "/Library/home/homePage.html";
                        }
                    }
                </script>
            </li>
        </ul>
    </div>
    <!--        <div id="language">切换语言</div>-->
</header>
<div id="wrapper">
<%--    <aside class="sidebar">--%>
    <div id="aside" class="custom-admin layui-side layui-input-inline layui-nav layui-side-scroll">
        <div>
        <div id="leftnav" class="">
            <div id="headSculpture" title="放大">
            </div>
            <%--            个人信息栏--%>
            <div class="PersonalInfo">
                <ul>
                    <li><span class="fixedtip">学 号 ：</span><span>${user.getSno()}</span></li>
                    <li><span class="fixedtip">姓 名 ：</span><span>${user.getSname()}</span></li>
                    <li><span class="fixedtip">占座情况：</span><span
                            id="occupyseat"
                            style="word-break: break-all"><%=desk == null ? "" : desk.getDeskInfo()%></span>
                    </li>
                    <li><span class="fixedtip">当前状态：</span><span
                            id="status">${user.getState()}</span>
                    </li>
                    <li><span class="fixedtip">违规次数：</span><span>${user.getViolatetime()}</span></li>
                </ul>
            </div>
        </div>
        <div id="switch">
            <ul id="nav">
                <%--                    选中场馆--%>
                <li class="layer1" id="first" style="display: list-item">
                    <span>当前选中场馆：</span>
                    <div style="float:right;display: block">
                        <%--                            <ul id="menu" class="menu">--%>
                        <ul id="campus" class="menu">
                            <li id="zhaohui">朝晖</li>
                            <li id="pingfeng" style="top: 41px">屏峰</li>
                            <li id="deqing" style="top: 72px">德清</li>
                        </ul>
                    </div>
                </li>
                <%--    选中楼层--%>
                <li id="second" style="color:deepskyblue;">
                    <span>当前选中楼层：</span>
                    <div style="float:right;display: block">
                        <ul id="floor" class="menu">
                            <li id="1F">一楼</li>
                            <li id="2F" style="top: 77px">二楼</li>
                            <li id="3F" style="top: 108px">三楼</li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        </div>
        <%--        <script>--%>
        <%--            // 选择完楼层后直接加载数据库内容--%>
        <%--            let first;--%>
        <%--            $("#floor li").click(function () {--%>
        <%--                alert(floors);--%>
        <%--                $.ajax({--%>
        <%--                    url: "/Library/seat/find",--%>
        <%--                    type: "POST",--%>
        <%--                    data: {--%>
        <%--                        "campus": campusAbbr,--%>
        <%--                        "floor": floors--%>
        <%--                    },--%>
        <%--                    dataType: 'JSON',--%>
        <%--                    success: function (response) {--%>
        <%--                        first = 'e_ff_e';--%>
        <%--                    }--%>
        <%--                });--%>
        <%--            });--%>
        <%--        </script>--%>
    </div>
<%--    </aside>--%>
    <div id="haoleia" class="layui-input-inline">
        <%--        提示--%>
        <span>
                <button id="sidebto"></button>
                <span id="info"> 选楼： </span>
                <button id="xys">删除空白</button>
            </span>
        <br>

        <div id="outmap">
            <img id="eventmap" src="/Library/main/img/house.png">
            <div id="seat-map1" class="seat-map">
                <div class="front-indicator">北</div>
                <div class="booking-details">
                    <h3>已选中的座位 (<span id="counter">0</span>):</h3>
                    <ul id="selected-seats" name="book"></ul>
                    <!-- <p>总价: <b>$<span id="total">0</span></b></p> -->
                    <p>
                        <button class="checkout-button" onclick="doBook()">确定</button>
                    </p>
                    <p id="msg" style="font-size: 15px;color: red"></p>
                    <div id="legend"></div>
                    <script>
                        function doBook() {
                            if (document.getElementsByName("book")[0].innerHTML == "") {
                                alert("请选择座位");
                                return;
                            }
                            let deskId = document.getElementsByName("deskId")[0].value;
                            $.ajax({
                                url: "/Library/seat/book",
                                type: "GET",
                                data: {
                                    "sno": "${user.getSno()}",
                                    "campus": campusAbbr,
                                    "floor": floors,
                                    "deskno": deskId
                                },
                                dataType: "JSON",
                                success: function (response) {
                                    $("#msg").text("恭喜你，选座成功");
                                    // 选座成功后的操作：
                                    // 更新网页上的显示
                                    $(response).each(function (index, elem) {
                                        // 个人信息的修改——占座桌号，占座状态
                                        $("#status").text(response.userState);
                                        $("#occupyseat").text(response.deskInfo);
                                    });
                                }
                            });
                        }
                    </script>
                </div>
            </div>
        </div>

        <%--                <div id="seat-map2" class="seat-map">--%>
        <%--                    <div class="front-indicator">北</div>--%>
        <%--                    <div class="booking-details">--%>
        <%--                        <h3>已选中的座位 (<span id="counter">0</span>):</h3>--%>
        <%--                        <ul id="selected-seats"></ul>--%>
        <%--                        <!-- <p>总价: <b>$<span id="total">0</span></b></p> -->--%>
        <%--                        <p>--%>
        <%--                            <button class="checkout-button">确定</button>--%>
        <%--                        </p>--%>
        <%--                        <div id="legend"></div>--%>
        <%--                    </div>--%>
        <%--                </div>--%>

        <%--        <div id="seat-map3" class="seat-map">--%>
        <%--            <div class="front-indicator">北</div>--%>
        <%--            <div class="booking-details">--%>
        <%--                <h3>已选中的座位 (<span id="counter">0</span>):</h3>--%>
        <%--                <ul id="selected-seats"></ul>--%>
        <%--                <!-- <p>总价: <b>$<span id="total">0</span></b></p> -->--%>
        <%--                <p>--%>
        <%--                    <button class="checkout-button">确定</button>--%>
        <%--                </p>--%>
        <%--                <div id="legend"></div>--%>
        <%--            </div>--%>
        <%--        </div>--%>

    </div>

</div>
<div id="top">
    <a href="#up">回到顶部</a>
</div>

<div id="bg"></div>
<%--修改密码--%>
<div id="ChangePassword">
    <div id="title">&nbsp;&nbsp;&nbsp;&nbsp;更改密码
        <a class="close" href="javascript:">X</a>
    </div>
    <form action="/Library/user/modifyPwd" method="post" name="modifyPwdForm" target=""><!--将action中的内容删去就会刷新页面-->
        <input type="text" name="sno" value="${user.getSno()}" hidden>
        <p>原密码: <input type="password" class="psw" name="oldPwd" id="oldPwd" maxlength="16"></p>
        <p>新密码: <input type="password" class="psw" name="newPwd" id="newPwd" maxlength="16"></p>
        <p><input type="button" class="sub" id="confirmBtn" onclick="doModify()" value="确认"></p>
    </form>
    <script>
        // 验证是否为空
        function isNotEmpty(elem, label) {
            if (elem.val() == "") {
                alert(label + "不能为空，请填写");
                elem.focus();
                return false;
            }
            return true;
        }

        // 验证旧密码是否与新密码相同
        function isNotSame() {
            let oldPwd = $("#oldPwd").val();
            let newPwd = $("#newPwd").val();
            if (oldPwd == newPwd) {
                alert("原密码与新密码一致，请重新填写");
                newPwd.empty();
                newPwd.focus();
                return false;
            }
            return true;
        }

        function validData() {
            let oldPwd = $("#oldPwd");
            let newPwd = $("#newPwd");
            return isNotEmpty(oldPwd, "原密码") && isNotEmpty(newPwd, "新密码") && isNotSame();
        }

        function doModify() {
            let ok = validData();
            if (ok) {
                document.forms["modifyPwdForm"].submit();
            }
        }
    </script>
</div>
<!--个人信息-->
<div class="background" id="background"></div>
<div class="entrance" id="entrance">
    <div class="identity_back">
        <p class="identity_title">个人信息</p>
        <p class="close">X</p>
        <hr>
    </div>
    <form action="/Library/user/identity" method="get" class="identity_bottom">
        <center><img src="/Library/main/img/%E8%8C%89%E8%8E%89.jpg" id="identity_img"></center>
        <label>学号：<input type="text" name="sno" value="${user.getSno()}" readonly></label><br>
        <label>姓名：<input type="text" name="name" value="${user.getSname()}" readonly></label><br>
        <label>身份：<input type="text" name="identity"
                         value="${user.getIdentity()==1?"学生":"管理员"}" readonly></label><br>
        <label>校区：<select class="choice" name="campus">
            <option value="ZH"  ${"ZH".equals(user.getCampus())?"selected":""}>朝晖</option>
            <option value="PF"  ${"PF".equals(user.getCampus())?"selected":""}>屏峰</option>
            <option value="MGS" ${"MGS".equals(user.getCampus())?"selected":""}>莫干山</option>
        </select></label><br>
        <label>性别：<select class="choice" name="gender">
            <option value="0" ${user.getGender()==0?"selected":""}>男</option>
            <option value="1" ${user.getGender()==1?"selected":""}>女</option>
        </select></label><br>
        <label>生日：<input type="date" name="birthday" value="${user.getBirthday()}"></label>
        <input type="submit" value="保存" class="submitbtn">
        <input type="button" value="关闭" class="submitbtn">
    </form>
</div>
<footer>
    <hr>
    <p>&copy;xys</p>
    <span style="width: 180px" id="datetime">
    </span>
</footer>
</body>