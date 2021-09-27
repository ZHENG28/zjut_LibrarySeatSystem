$(function() {
    playauto();//playauto()函数控制弹出框居中显示

    function playauto() {
        var _width=$(window).width();//获取浏览器窗口宽度
        var _height=$(window).height();//获取高度
        $(" #ChangePassword").css({left:_width/2-300,top:_height/2-175});//使弹出框居中
    }
    
    //动态改变浏览器窗口时执行
    $(window).resize(function () {
        playauto();//浏览器窗口大小改变，则重新执行playauto()函数，使弹出框居中  
    } );
    $(".dl").click(function () {//点击超链接按钮显示bg、login样式
        playauto();//再次点击登陆按钮时，弹出框仍然处于居中位置
        $("#ChangePassword").show("fast");
    })
    $(".close").click(function () {//点击弹出框上的X按钮隐藏bg、login样式，即关闭弹出框
        $("#bg").hide();
        $("#ChangePassword").hide("fast");
        $("p input").val("");//清除输入
        $("#entrance").hide("fast");
        $("#background").hide("fast");
    });
    $("#ChangePassword").mousedown(function(e){
        var x=e.clientX;//鼠标按下的X坐标
        var y=e.clientY;//鼠标按下的Y坐标
        var $left=$("#ChangePassword").offset().left;//登陆框距离左边位置
        var $top=$("#ChangePassword").offset().top;//登陆框距离顶部位置
        var l=x-$left;//点击的坐标点距离弹出框左边的距离
        var t=y-$top;//点击的坐标点距离弹出框上边的距离
        $(document).mousemove(function (e) {
            var nx=e.clientX;
            var ny=e.clientY;
            var n_left=nx-l;//动态得到弹出框距离浏览器左边距离
            var n_top=ny-t;//动态得到弹出框距离浏览器上边距离
            $("#ChangePassword").css({left: n_left,top:n_top});
        });
        $(document).mouseup(function () {
            $(document).unbind("mousemove");//解除鼠标移动事件
            $(document).unbind("mouseup");
        })
    })
    $(".identity_sign").click(function (){
        $("#entrance").show("fast");
        $("#background").show("fast");
    });
    $(".submitbtn").click (function (){
        $("#entrance").hide("fast");
        $("#background").hide("fast");
    });
})