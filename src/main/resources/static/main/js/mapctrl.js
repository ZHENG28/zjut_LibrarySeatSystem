//现在进行到选座的哪1步。0选馆，1选层，2选桌
var state = 0;
//场馆，楼层，区域，座位
var house = "";
var floor = "";
var area = "";
var site = "";
//地图src
var mapsrc = "";
//jQuery入口函数
$(function (ev) {
    //按钮
    var $next = $("#next")[0];
    var $prev = $("#prev")[0];
    var $print = $("#print")[0];

    //select
    var $fir = $("#fir");
    var $sec = $("#sec");

    //li
    var $second = $("li#second");
    var $third = $("li#third");

    var $map = $("#eventmap");
    var $info = $("span#info");

    var sidebarwidth = 0;

    var $xyshu = $('#xys');
    let $smap = $('#seat-map');
    $xyshu.click(function(){
        $('.seatCharts-space').remove();
    });
    
    var $selectseat = $("#seat-map");
    $selectseat.hide();
    
    
    $('#hddiv ul').click(function (){
        $('header li').animate({
            height:'toggle'
        });
    } )
    
   // 侧边栏弹出
   $('#sidebto').click(function () {
        $('#aside').toggle('slow');
        if(sidebarwidth == 1) $(this).css('transform', 'rotate(0deg)');
        else $(this).css('transform', 'rotate(450deg)');
        sidebarwidth = 1-sidebarwidth;
   })
    
    $('#first').click(function () {
        
        $('#first li').toggle('middle');
    })
    
    $('#second').click(function () {
        $('#second li').toggle('middle');
    })
    
    $('#first li').click(function () {
        if(state == 0 ) {
            $map.fadeTo("fast", 0);
            $map[0].src = mapsrc;
            $map.fadeTo("middle", 1);
        }
        $map.show("middle");
        
        $('.seat-map').hide('middle');
        $('#first span').html("当前选中场馆:" + this.innerHTML );
        $('#second span').html("当前选中楼层:");
        $('#first').css('color','black');
        $('#second').css('color','deepskyblue');
        house = this.id ;
        $second.css('display', 'list-item');
        $third.css('display','none');
        $info.text(" 选择您要去楼层 ");
        mapsrc = "img/" + house + ".jpg";
        setTimeout(function(){
            $map[0].src = mapsrc;
            $selectseat.hide();
        },300)
        
        state=1;
    })
    
    $('#second li').click(function () {
        state=2;
        $('#second span').html("当前选中楼层:" + this.innerHTML );
        $('#second').css('color','black');
        $('#third').css('color','deepskyblue');
        floor = this.id ;
        $info.text(" 请选座");
        // mapsrc = "img/" + house + "-" + floor + ".png";
            $map.hide("middle");
        setTimeout(function(){
            $map[0].src = mapsrc;
            $selectseat.show();
        },300);
        
    })

    let $smp1 = $('#1F'),
        $smp2 = $('#2F'),
        $smp3 = $('#3F');

    $smp1.click(function (){
        $('.seat-map').hide();
        $('#seat-map1').show('middle');
    })

    $smp2.click(function (){
        $('.seat-map').hide();
        $('#seat-map2').show('middle');
    })

    $smp3.click(function (){
        $('.seat-map').hide();
        $('#seat-map3').show('middle');
    })
   
    setInterval(function(){
        $('#datetime').html(new Date().toLocaleString());
    }, 1000);
})