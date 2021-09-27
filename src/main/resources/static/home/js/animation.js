$(function (){
    let $close = $('.closebtn');
    let $trylogin = $('.login_sign');
    let $rule = $('.rule_sign');

    $close.click(function (){
        $('#entrance').hide('fast');
        $('#background').hide('fast');
        $('#rule').hide('fast');
    });

    $('.confirmbtn').click(function () {
        $('#entrance').hide('fast');
        $('#background').hide('fast');
        $('#rule').hide('fast');
    })

    $trylogin.click(function (){
        $('#entrance').show('fast');
        $('#background').show('fast');
    });

    $rule.click( function () {
        $('#background').show('fast');
        $('#rule').show('fast');
    })

    $('.enterbtn').click(function (){
        $('#entrance').show('fast');
        $('#background').show('fast');
    })
})