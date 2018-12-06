// js Document

// Project:        SOTTO - Blog and Portfolio HTML Template.
// Version:        1.0
// Last change:    16/05/2018.
// Designed:       heloshape (http://heloshape.com/)
// Developed:      heloshape (http://heloshape.com/)


(function ($) {
    "use strict";

    $(document).ready(function () {
        // -------------------- Navigation Scroll
        $(window).scroll(function () {
            var sticky = $('.theme-main-header'),
                scroll = $(window).scrollTop(), stickyBackHome = $('#blogdetail-backhome'),stickyBackHomeA = $('#backhome-a');
            if (scroll >= 100) {
                sticky.addClass('fixed');
                stickyBackHome.removeClass("fixed");
                stickyBackHomeA.css("display","none");
            } else {
                stickyBackHome.addClass("fixed");
                sticky.removeClass('fixed');
                stickyBackHomeA.css("display","block");
            }
        });


        // -------------------- Remove Placeholder When Focus Or Click
        $("input,textarea").each(function () {
            $(this).data('holder', $(this).attr('placeholder'));
            $(this).on('focusin', function () {
                $(this).attr('placeholder', '');
            });
            $(this).on('focusout', function () {
                $(this).attr('placeholder', $(this).data('holder'));
            });
        });

        // -------------------- From Bottom to Top Button
        //Check to see if the window is top if not then display button
        $(window).on('scroll', function () {
            if ($(this).scrollTop() > 200) {
                $('.scroll-top').fadeIn();
            } else {
                $('.scroll-top').fadeOut();
            }
        });
        //Click event to scroll to top
        $('.scroll-top').on('click', function () {
            $('html, body').animate({scrollTop: 0}, 1500);
            return false;
        });


        // ---------------------------- Greeting Message
        var thehours = new Date().getHours();
        var themessage;
        var morning = ('Good morning');
        var afternoon = ('Good afternoon');
        var evening = ('Good evening');

        if (thehours >= 0 && thehours < 12) {
            themessage = morning;

        } else if (thehours >= 12 && thehours < 17) {
            themessage = afternoon;

        } else if (thehours >= 17 && thehours < 24) {
            themessage = evening;
        }
        $('.greeting').append(themessage);


        // --------------------------------- Search Box
        var search = $("#search-button"),
            mainSearch = $("#searchWrapper"),
            close = $("#close-button");
        if (search.length) {
            search.on('click', function () {
                mainSearch.addClass('show-box');
                $(".main-page-wrapper").addClass('blury-bg')
            });
            close.on('click', function () {
                mainSearch.removeClass('show-box');
                $(".main-page-wrapper").removeClass('blury-bg')
            });
        }


        // ---------------------------- Style Switcher
        var switcher = $('.switch-menu');
        if (switcher.length) {
            $('.switch-btn button').on('click', function () {
                $('.switcher').toggleClass('switcher-show')
            });

            $('#styleOptions').styleSwitcher({
                hasPreview: true,
                fullPath: 'css/',
                cookie: {
                    expires: 40000,
                    isManagingLoad: true
                }
            });
        }
        ;


        // ------------------------ Breaking News Ticker
        var tickerWrapper = $(".easyTicker");
        if (tickerWrapper.length) {
            tickerWrapper.easyTicker({
                direction: 'up',
                easing: 'easeInOutCirc',
                speed: 'slow',
                interval: 3000,
                height: 'auto',
                visible: 1,
                mousePause: 1,
            });
        }


        // ------------------------------ Theme Menu 
        var menu = $("#mega-menu-holder");
        if (menu.length) {
            menu.slimmenu({
                resizeWidth: '767',
                animSpeed: 'medium',
                indentChildren: true,
            });
        }


        // ------------------------------ Video Blog
        var embed = $(".embed-video");
        if (embed.length) {
            embed.fitVids();
        }

        // --------------------------- Theme Main Banner Slider One

        if ($("#main-slider-one").length) {
            var masterslider = new MasterSlider();

            masterslider.setup("main-slider-one", {
                width: 1400,
                height: 500,
                minHeight: 0,
                space: 0,
                start: 1,
                grabCursor: true,
                swipe: false,
                mouse: true,
                keyboard: true,
                layout: "fullwidth",
                wheel: false,
                autoplay: true,
                instantStartLayers: true,
                loop: true,
                shuffle: false,
                preload: 0,
                heightLimit: true,
                autoHeight: false,
                smoothHeight: true,
                endPause: false,
                overPause: false,
                fillMode: "fill",
                centerControls: true,
                startOnAppear: false,
                layersMode: "center",
                autofillTarget: "",
                hideLayers: false,
                fullscreenMargin: 0,
                speed: 10,
                dir: "h",
                parallaxMode: 'swipe',
                view: "fade"
            });
        }


        // ------------------------------- Home Page Two Slider
        var bannerSlider = $(".main-banner-slider-two");
        if (bannerSlider.length) {
            bannerSlider.owlCarousel({
                loop: true,
                nav: true,
                navText: ["<i class='flaticon-left-arrow'></i>", "<i class='flaticon-right-arrow'></i>"],
                dots: false,
                autoplay: true,
                autoplayTimeout: 4000,
                autoplayHoverPause: true,
                smartSpeed: 1100,
                lazyLoad: true,
                items: 1
            })
        }


        // ------------------------------- Home Page Three Slider
        var bannerSlider = $(".main-banner-slider-three");
        if (bannerSlider.length) {
            bannerSlider.owlCarousel({
                loop: true,
                nav: false,
                dots: false,
                autoplay: true,
                autoplayTimeout: 4000,
                autoplayHoverPause: true,
                smartSpeed: 1100,
                lazyLoad: true,
                centerItem: true,
                center: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    576: {
                        items: 2
                    },
                    992: {
                        items: 3
                    },
                    1300: {
                        items: 4
                    }
                }
            })
        }


        // ------------------------------- Related Blog Slider
        var tSlider = $(".related-blog-slider");
        if (tSlider.length) {
            tSlider.owlCarousel({
                loop: true,
                nav: false,
                dots: false,
                autoplay: true,
                autoplayTimeout: 4000,
                autoplayHoverPause: true,
                smartSpeed: 1100,
                lazyLoad: true,
                responsive: {
                    0: {
                        items: 1
                    },
                    500: {
                        items: 2
                    },
                    992: {
                        items: 3
                    }
                },
            })
        }


        // --------------------------  Blog Details Image Slider
        var rpSlider = $(".blog-image-slider");
        if (rpSlider.length) {
            rpSlider.owlCarousel({
                loop: true,
                nav: true,
                navText: ["<i class='flaticon-left-arrow'></i>", "<i class='flaticon-right-arrow'></i>"],
                dots: false,
                autoplay: true,
                autoplayTimeout: 4000,
                autoplayHoverPause: true,
                smartSpeed: 1100,
                lazyLoad: true,
                items: 1
            })
        }


        // ------------------------------------- Fancybox
        var fancy = $(".fancybox");
        if (fancy.length) {
            fancy.fancybox({
                arrows: true,
                animationEffect: "zoom-in-out",
                transitionEffect: "zoom-in-out",
            });
        }


        // --------------------------------- Contact Form Validation
        if ($('.form-validation').length) {
            $('.form-validation').validate({ // initialize the plugin
                rules: {
                    name: {
                        required: true
                    },
                    email: {
                        required: true
                    },
                    message: {
                        required: true
                    }
                },
                submitHandler: function (form) {
                    $(form).ajaxSubmit({
                        success: function () {
                            $('.form-validation :input').attr('disabled', 'disabled');
                            $('.form-validation').fadeTo("slow", 1, function () {
                                $(this).find(':input').attr('disabled', 'disabled');
                                $(this).find('label').css('cursor', 'default');
                                $('#alert-success').fadeIn();
                            });
                        },
                        error: function (jqHXR) {
                            var res = JSON.parse(jqHXR.responseText);
                            $('.form-validation').fadeTo("slow", 1, function () {
                                $('#alert-error').find("p").html(res.message)
                                $('#alert-error').fadeIn();
                            });
                        }
                    });
                }
            });
        }

        $(".comment-emoji").each(function () {
            $(this).on('click',function(){
                var textarea = $(this).parent().parent().parent().find('textarea');
                var str = textarea.val() + $(this).html();
                textarea.val(str);
            });
        });


        // ---------------------------------- Validation Alert
        var closeButton = $(".closeAlert");
        if (closeButton.length) {
            closeButton.on('click', function () {
                $('.form-validation :input').removeAttr('disabled');
                $('.form-validation :button').removeAttr('disabled');
                $(".alert-wrapper").fadeOut();
                var idVal = $(this).parent().attr('id');
                if (idVal == 'success'){
                    if($("#blogdetail").length){
                        window.location.reload();
                    }else{
                        window.location.href='/';
                    }
                }
            });
            closeButton.on('click', function () {
                $('.form-validation :input').removeAttr('disabled');
                $('.form-validation :button').removeAttr('disabled');
                $(".alert-wrapper").fadeOut();
                // window.location.href='/';
            })
        }


        // --------------------------------- Comment Form Validation
        // if ($('.comment-form-validation').length) {
        //     $('.comment-form-validation').validate({ // initialize the plugin
        //         debug:true,
        //         rules: {
        //             commenter: {
        //                 required: true
        //             },
        //             commenterEmail: {
        //                 required: true
        //             },
        //             comment: {
        //                 required: true
        //             }
        //         },
        //         submitHandler: function (form) {
        //             $(form).ajaxSubmit({
        //                 success: function () {
        //                     $('.comment-form-validation :input').attr('disabled', 'disabled');
        //                     $('.comment-form-validation').fadeTo("slow", 1, function () {
        //                         $(this).find(':input').attr('disabled', 'disabled');
        //                         $(this).find('label').css('cursor', 'default');
        //                         $('#alert-success').fadeIn();
        //                     });
        //                 },
        //                 error: function (jqHXR) {
        //                     var res = JSON.parse(jqHXR.responseText);
        //                     $('.comment-form-validation').fadeTo("slow", 1, function () {
        //                         $('#alert-error').find("p").html(res.message)
        //                         $('#alert-error').fadeIn();
        //                     });
        //                 }
        //             });
        //         }
        //     });
        // }


        // ---------------------------------- Validation Alert
        var closeButton = $(".closeAlert");
        if (closeButton.length) {
            closeButton.on('click', function () {
                $('.comment-form-validation :input').removeAttr('disabled');
                $('.comment-form-validation :button').removeAttr('disabled');
                $(".alert-wrapper").fadeOut();
                var idVal = $(this).parent().attr('id');
                if (idVal == 'success'){
                    if($("#blogdetail").length){
                        window.location.reload();
                    }else{
                        window.location.href='/';
                    }

                }
            });
            closeButton.on('click', function () {
                $('.comment-form-validation :input').removeAttr('disabled');
                $('.comment-form-validation :button').removeAttr('disabled');
                $(".alert-wrapper").fadeOut();
            })
        }
        $('input[name=commenter]').val(getCookie('commenter'));
        $('input[name=commenterEmail]').val(getCookie('commenterEmail'));

        $(".reply").click(function () {
            $(this).parent().find(".comment-form").toggle(500, function () {
            })
        });

    });

    $(window).load(function () { // makes sure the whole site is loaded

        // -------------------- Site Preloader
        $('#loader').fadeOut(); // will first fade out the loading animation
        $('#loader-wrapper').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
        $('body').delay(350).css({'overflow': 'visible'});

        if ($(".blog-masonry").length) {
            $('.blog-masonry').masonry({
                // set itemSelector so .grid-sizer is not used in layout
                itemSelector: '.grid-item',
                // use element for option
                columnWidth: '.grid-sizer',
                percentPosition: true
            })
        }
        ;
    })

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
})(jQuery)

function likeIt(blogId, likes, obj) {
    $.ajax({
        url: "/blog/likeIt",
        data: {
            id: blogId
        },
        type: "post",
        dataType: "text",
        success: function (data) {
            $('#alert-success').fadeIn(1000, function () {
                var afterLikes = likes + 1;
                obj.outerHTML = '<a href="javascript:;" onclick="javascript:likeIt(' + blogId + ',' + afterLikes + ',this)"><i class="icon flaticon-like-heart">Likes (' + afterLikes + ')</i></a>';
                $('#alert-success').fadeOut(500);
            });
        }
    });

}

function tagIt(tag){
    $.ajax({
        url: "/blog/tagIt",
        data: {
            tag: tag
        },
        type: "post",
        dataType: "text",
        success: function (data) {
            $('#blog-outline').html('');
            var jsonData = JSON.parse(data); //jsonData是该下路下的所有区间（json格式）
            var totalHtml = '';
            for (var i = 0; i < jsonData.length; i++) {
                var d = jsonData[i];
                var html;
                if(d.done ==1){
                    var html = '<div class="single-blog-post">\n' +
                        '    <div class="image-box"></div>\n' +
                        '    <div class="post-meta-box bg-box">\n' +
                        '        <ul class="author-meta clearfix">\n' +
                        '            <li class="tag"><a href="#">'+ d.tags +'</a></li>\n' +
                        '            <li class="date"><a href="#">'+ d.createTime +'</a>\n' +
                        '            </li>\n' +
                        '        </ul>\n' +
                        '        <h4 class="title"><a href="/blog/' + d.id + '/b">'+d.title+'</a></h4>\n' +
                        '        <ul class="share-meta clearfix">\n' +
                        '        <li><i class="icon flaticon-comment"> 评论 ('+ d.comments +')</i></li>'+
                        '            <li><a href="javascript:;" onclick="javascript:likeIt('+ d.id +',' + d.likes + ',this)"><i class="icon flaticon-like-heart"> 赞 ('+ d.likes +')</i></a></li>\n' +
                        '        </ul>\n' +
                        '    </div> \n' +
                        '</div>';
                }else{
                    html = '<div class="single-blog-post">\n' +
                        '    <div class="image-box"></div>\n' +
                        '    <div class="post-meta-box bg-box">\n' +
                        '        <ul class="author-meta clearfix">\n' +
                        '            <li class="tag"><a href="#">'+ d.tags +'</a></li>\n' +
                        '            <li class="date"><a href="#">'+ d.createTime +'</a>\n' +
                        '            </li>\n' +
                        '<li ><a><i class="fas fa-spinner fa-spin"></i> </a></li>\n' +
                        '        </ul>\n' +
                        '        <h4 class="title"><a href="/blog/' + d.id + '/b">'+d.title+'</a></h4>\n' +
                        '        <ul class="share-meta clearfix">\n' +
                        '        <li><i class="icon flaticon-comment"> 评论 ('+ d.comments +')</i></li>'+
                        '            <li><a href="javascript:;" onclick="javascript:likeIt('+ d.id +',' + d.likes + ',this)"><i class="icon flaticon-like-heart"> 赞 ('+ d.likes +')</i></a></li>\n' +
                        '        </ul>\n' +
                        '    </div> \n' +
                        '</div>';
                }

                totalHtml += html;
            }
            $('#blog-outline').html(totalHtml);

        }
    });
}

function wechat(){
    $(".wechat-block").slideToggle();

}

function setCookie(name,value,time) {
    var strsec = getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec*1);
    document.cookie = name + "="+ escape (value) +";expires=" + exp.toGMTString()+ ";path=/;domain="+document.domain;
}

function getCookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function getsec(str) {
    var str1=str.substring(1,str.length)*1;
    var str2=str.substring(0,1);
    if (str2=="s"){
        return str1*1000;
    }else if (str2=="h"){
        return str1*60*60*1000;
    }else if (str2=="d"){
        return str1*24*60*60*1000;
    }
}

function sendComment(obj, isComment) {
    var form = new FormData(obj.parentNode);
    var checkResult = true;
    $(obj.parentNode).find("[required='true']").each(function () {
        if ($(this).val().trim() == '') {
            $(this).focus();
            checkResult = false;
            return;
        }
        var arrtName = $(this).attr('name');
        if (arrtName == 'comment' && $(this).val().trim().length > 500) {
            $(this).focus();
            checkResult = false;
            return;
        }
    });
    if (isComment) {
        form.set('replyTo', '');
    }
    if (checkResult) {
        setCookie('commenter',form.get('commenter'),'d30');
        setCookie('commenterEmail',form.get('commenterEmail'),'d30');
        $.ajax({
            url: "/comment/sendComment",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            success: function (data) {
                var commentDiv = $(obj).parent().parent().parent();
                if (!isComment) {
                    var appendHtml =
                        '<h6><div class="head-div"><img class="head-img" src="'+ data.data +'"></div>' +
                        '<div><a>' + form.get('commenter') + '</a><b class="date" >|'+ new Date().Format("yyyy-MM-dd HH:mm") +'</b></div></h6>\n' +
                        '   <p><strong>@' + form.get('replyTo') + '</strong> ' + form.get('comment') + '</p>\n' +
                        '   <button class="reply">回复</button>\n' +
                        '   <div class="comment-form">\n' +
                        '   <form method="post" class="comment-form-validation" autocomplete="off">\n' +
                        '       <input type="text" required="true" placeholder="Name*" name="commenter" value="'+ form.get('commenter') +'">\n' +
                        '       <input type="text" placeholder="Email" name="commenterEmail" value="'+ form.get('commenterEmail') +'" >\n' +
                        '       <input type="text" hidden name="blogId" value="' + form.get('blogId') + '">\n' +
                        '       <input type="text" hidden name="replyTo" th:value="' + form.get('replyTo') + '">\n' +
                        '       <input type="text" hidden name="parentId" th:value="' + form.get('parentId') + '">\n' +
                        '       <p><textarea  required="true" placeholder="500字以内评论" name="comment"></textarea></p>\n' +
                        '       <div class="comment-bar">\n' +
                        '          <ul>\n' +
                        '              <li class="comment-emoji">😂</li>\n' +
                        '              <li class="comment-emoji">😫</li>\n' +
                        '              <li class="comment-emoji">😭</li>\n' +
                        '              <li class="comment-emoji">🤣</li>\n' +
                        '              <li class="comment-emoji">😅</li>\n' +
                        '              <li class="comment-emoji">😣</li>\n' +
                        '              <li class="comment-emoji">😍</li>\n' +
                        '              <li class="comment-emoji">🤗</li>\n' +
                        '              <li class="comment-emoji">😜</li>\n' +
                        '              <li class="comment-emoji">👍</li>\n' +
                        '              <li class="comment-emoji">👎</li>\n' +
                        '          </ul>\n' +
                        '       </div>' +
                        '       <button type="button"  class="theme-button-one" onclick="javascript:sendComment(this,false)"   style="margin-bottom: 20px; margin-left: 10px">提交</button>\n' +
                        '   </form>\n' +
                        ' </div>';
                    var finalAppendHtml = '<div class="reply-comment">' + appendHtml + '</div>';
                    $(commentDiv).parent().append($(finalAppendHtml));
                } else {
                    var ht =
                        '  <div class="single-comment" >\n' +
                        '    <div class="comment">\n' +
                        '    <h6><div class="head-div"><img class="head-img" src="'+ data.data +'"></div><div><a>' + form.get('commenter') + '</a><b class="date" >|'+ new Date().Format("yyyy-MM-dd HH:mm") + '</b></div></h6>\n' +
                        '    <p >' + form.get('comment') + '</p>\n' +
                        '    <button class="reply">回复</button>\n' +
                        '    <div class="comment-form">\n' +
                        '        <form method="post" class="comment-form-validation" autocomplete="off">\n' +
                        '            <input type="text" required="true" placeholder="Name*" name="commenter" value="'+ form.get('commenter') +'" >\n' +
                        '            <input type="text" placeholder="Email" name="commenterEmail" value="'+ form.get('commenterEmail') +'">\n' +
                        '            <p><textarea required="true" placeholder="500字以内评论" name="comment"></textarea></p>\n' +
                        '            <input type="text" hidden name="blogId" value="' + form.get('blogId') + '">\n' +
                        '            <input type="text" hidden name="replyTo" value="' + form.get('commenter') + '">\n' +
                        '            <input type="text" hidden name="parentId" vaue="0">\n' +
                        '            <div class="comment-bar">\n' +
                        '               <ul>\n' +
                        '                   <li class="comment-emoji">😂</li>\n' +
                        '                   <li class="comment-emoji">😫</li>\n' +
                        '                   <li class="comment-emoji">😭</li>\n' +
                        '                   <li class="comment-emoji">🤣</li>\n' +
                        '                   <li class="comment-emoji">😅</li>\n' +
                        '                   <li class="comment-emoji">😣</li>\n' +
                        '                   <li class="comment-emoji">😍</li>\n' +
                        '                   <li class="comment-emoji">🤗</li>\n' +
                        '                   <li class="comment-emoji">😜</li>\n' +
                        '                   <li class="comment-emoji">👍</li>\n' +
                        '                   <li class="comment-emoji">👎</li>\n' +
                        '               </ul>\n' +
                        '            </div>' +
                        '            <button type="button"  class="theme-button-one" onclick="javascript:sendComment(this,false)"    style="margin-bottom: 20px; margin-left: 10px">提交</button>\n' +
                        '        </form>\n' +
                        '    </div>\n' +
                        '  </div>';
                    var finalHt = '<div class="comment-meta">' + ht + '</div>';
                    $('.comment-area').append($(finalHt));
                }
                if (!isComment) {
                    $(obj).parent().parent().toggle(500, function () {
                    });
                    obj.parentNode.reset();
                } else {
                    $('.comment-bottom-area').find("textarea").val('');
                }
                if ($(".reply").length > 0) {
                    $(".reply").each(function () {
                        if (!$._data(this, 'events')) {
                            $(this).on('click', function () {
                                $(this).parent().find(".comment-form").toggle(500, function () {
                                })
                            });
                        }
                    });
                }
                $(".comment-emoji").each(function () {
                    if (!$._data(this, 'events')) {
                        $(this).on('click', function () {
                            var textarea = $(this).parent().parent().parent().find('textarea');
                            var str = textarea.val() + $(this).html();
                            textarea.val(str);
                        });
                    }
                });
            },
            error: function (data) {
                var res = JSON.parse(data.responseText);
                $('.comment-form-validation').fadeTo("slow", 1, function () {
                    $('#alert-error').find("p").html(res.message)
                    $('#alert-error').fadeIn();
                });

            }
        });
    }
}

function searchfor(obj){
    var searchWord = $(obj).parent().find("input").val();
    if (searchWord == ''){
        $(obj).parent().find("input").focus();
        return;
    }
    $.ajax({
        url: "/s/blogs",
        data: {
            word: searchWord
        },
        type: "post",
        dataType: "text",
        success: function (data) {
            $('#blog-outline').html('');
            var jsonData = JSON.parse(data); //jsonData是该下路下的所有区间（json格式）
            var code = jsonData.code;
            if(code == 1){
                jsonData = jsonData.data;
                var totalHtml = '';
                for (var i = 0; i < jsonData.length; i++) {
                    var d = jsonData[i];
                    var html;
                    if(d.done == 1){
                        html = '<div class="single-blog-post">\n' +
                            '    <div class="image-box"></div>\n' +
                            '    <div class="post-meta-box bg-box">\n' +
                            '        <ul class="author-meta clearfix">\n' +
                            '            <li class="tag"><a href="#">'+ d.tags +'</a></li>\n' +
                            '            <li class="date"><a href="#">'+ d.createTime +'</a>\n' +
                            '            </li>\n' +
                            '<li ><a><i class="fas fa-spinner fa-spin"></i> </a></li>\n' +
                            '        </ul>\n' +
                            '        <h4 class="title"><a href="/blog/' + d.id + '/b">'+d.title+'</a></h4>\n' +
                            '        <ul class="share-meta clearfix">\n' +
                            '        <li><i class="icon flaticon-comment"> 评论 ('+ d.comments +')</i></li>'+
                            '            <li><a href="javascript:;" onclick="javascript:likeIt('+ d.id +',' + d.likes + ',this)"><i class="icon flaticon-like-heart"> 赞 ('+ d.likes +')</i></a></li>\n' +
                            '        </ul>\n' +
                            '    </div> \n' +
                            '</div>';
                    }else{
                        html = '<div class="single-blog-post">\n' +
                            '    <div class="image-box"></div>\n' +
                            '    <div class="post-meta-box bg-box">\n' +
                            '        <ul class="author-meta clearfix">\n' +
                            '            <li class="tag"><a href="#">'+ d.tags +'</a></li>\n' +
                            '            <li class="date"><a href="#">'+ d.createTime +'</a>\n' +
                            '            </li>\n' +
                            '        </ul>\n' +
                            '        <h4 class="title"><a href="/blog/' + d.id + '/b">'+d.title+'</a></h4>\n' +
                            '        <ul class="share-meta clearfix">\n' +
                            '        <li><i class="icon flaticon-comment"> 评论 ('+ d.comments +')</i></li>'+
                            '            <li><a href="javascript:;" onclick="javascript:likeIt('+ d.id +',' + d.likes + ',this)"><i class="icon flaticon-like-heart"> 赞 ('+ d.likes +')</i></a></li>\n' +
                            '        </ul>\n' +
                            '    </div> \n' +
                            '</div>';
                    }

                    totalHtml += html;
                }
                $('#blog-outline').html(totalHtml);
            }else{
                $('#alert-error').find("p").html(jsonData.message)
                $('#alert-error').fadeIn();
            }
        },
        error: function (jqHXR) {

        }
    });

}