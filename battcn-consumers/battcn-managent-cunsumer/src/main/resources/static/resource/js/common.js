/**
 * 工具组件 对原有的工具进行封装，自定义某方法统一处理
 *
 * @Email: yafeng.tang@zoomy-media.com
 */
;
(function () {


    battcn = {};

    battcn.lockedFormat = (function (value, row, index) {
        return value == 1 ? '<i class="fa fa-check text-navy"></i>' : '<i class="fa fa-close text-danger"></i>';
    });

    battcn.ajax = (function (params) {
        var pp = {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.open({
                    type: 1,
                    title: "出错啦！",
                    area: ['30%', '30%'],
                    content: "<div id='layerError' style='color:red'>"
                    + XMLHttpRequest.responseText + "</div>"
                });
            }
        };
        $.extend(pp, params);
        $.ajax(pp);
    });

    battcn.ajaxJson = (function (options, callback) {
        var dataType = options.dataType === undefined ? 'json' : options.dataType;
        if (options.url === undefined) {
            layer.alert("请输入battcn.ajax的URL地址");
            return;
        }
        ;
        var url = options.url;
        if (url.indexOf("?") > -1) {
            url = url + "&_t=" + new Date();
        } else {
            url = url + "?_t=" + new Date();
        }
        ;
        $.ajax({
            type: 'POST',
            url: url,
            data: options.data,
            dataType: dataType,
            success: function (data) {
                console.info(data);
                if (data.messageId === 200) {
                    if (typeof callback === "function") {
                        callback();
                    };
                };
                battcn.toastrsAlert({
                    code: data.messageId === 200 ? 'success' : 'error',
                    msg: data.message
                });
            },
            error: function (xhr) {
                console.info(xhr.responseText);
                var result = eval("("+xhr.responseText+")");//转换为json对象
                layer.alert(result.message,{closeBtn : false});
            }
        });
    });

    battcn.ajaxSubmit = (function (form, params) {// form 表单ID. params ajax参数
        var pp = {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.open({
                    type: 1,
                    title: "出错啦！",
                    area: ['30%', '30%'],
                    content: "<div id='layerError' style='color:red'>"
                    + XMLHttpRequest.responseText + "</div>"
                });
            }
        };
        $.extend(pp, params);
        $(form).ajaxSubmit(pp);
    });


    battcn.showWindow = (function (options) {
        var title = options.title;
        var href = options.href === undefined ? null : options.href;
        var content = options.content;
        var width = options.width;
        var height = options.height;
        var type = options.type === undefined ? 1 : options.type;
        var buttons = [];
        if (buttons.length === 0) {
            buttons = null;
        }
        if (type === 1) {
            $.get(href, {}, function (str) {
                var index = layer.open({
                    type: type,
                    title: title,
                    shadeClose: false,
                    moveOut: true,
                    shade: 0.5,
                    maxmin: true,
                    area: [width, height],
                    content: str,
                    btn: ['提交', '取消'],
                    yes: options.okhandler,
                    chanel: options.cancelhandler
                });
                initUI();
            });
        }
        else {
            layer.open({
                type: 2,
                title: title,
                shadeClose: false,
                moveOut: true,
                shade: 0.5,
                maxmin: true,
                area: [width, height],
                content: href,
                btn: ['提交', '取消'],
                yes: options.okhandler,
                chanel: options.cancelhandler

            });

        }
    });

    /**
     * 增加命名空间功能
     *
     * 使用方法：battcn.ns('jQuery.bbb.ccc','jQuery.eee.fff');
     */
    battcn.ns = function () {
        var o = {}, d;
        for (var i = 0; i < arguments.length; i++) {
            d = arguments[i].split(".");
            o = window[d[0]] = window[d[0]] || {};
            for (var k = 0; k < d.slice(1).length; k++) {
                o = o[d[k + 1]] = o[d[k + 1]] || {};
            }
        }
        return o;
    };
    /**
     * 获得项目根路径
     *
     * 使用方法：battcn.bp();
     */
    battcn.bp = function () {
        var curWwwPath = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        var localhostPaht = curWwwPath.substring(0, pos);
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    };

    /**
     * IE6背景图片缓存
     */
    try {
        document.execCommand("BackgroundImageCache", false, true);
    } catch (e) {
    }

    /**
     * 生成UUID
     */
    battcn.UUID = function () {
        var s = [], itoh = '0123456789ABCDEF';
        for (var i = 0; i < 36; i++)
            s[i] = Math.floor(Math.random() * 0x10);
        s[14] = 4;
        s[19] = (s[19] & 0x3) | 0x8;
        for (var i = 0; i < 36; i++)
            s[i] = itoh[s[i]];
        s[8] = s[13] = s[18] = s[23] = '-';
        return s.join('');
    };

    battcn.reloadDiv = function (url) {
        var o = layer.load();
        $(".J_mainContent div.J_box:visible").html('<div class="loading">加载中,请稍等。。<div>');
        $(".J_mainContent div.J_box:visible").loadUrl(url, 'get', {}, function (data) {
            layer.close(o);
            $(".J_mainContent div.J_box:visible").find("div.loading").remove();
        });
    }


    battcn.init = function () {
        toastr.options = {
            "closeButton": true,
            "debug": true,
            "progressBar": true,
            "positionClass": "toast-top-center",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "1000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }

    }

    battcn.msg = function (msg) {
        layer.msg(msg);
    }

    battcn.toastrsAlert = function (obj) {
        battcn.init();
        if (obj.code === 'info') {
            toastr.info(obj.msg, '提示');
        } else if (obj.code === 'warning') {
            toastr.warning(obj.msg, '警告');
        } else if (obj.code === 'error') {
            toastr.error(obj.msg, '失败');
        } else if (obj.code === 'success') {
            toastr.success(obj.msg, '成功');
        }

    }

//关闭弹出窗口
    battcn.closeWindow = function () {
        layer.closeAll();
    }
//关闭弹出子窗口
    battcn.closeNestWindow = function (index) {
        layer.close(index)
    }

    battcn.uuid = function () {
        var len = 32;// 32长度
        var radix = 16;// 16进制
        var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
        var uuid = [], i;
        radix = radix || chars.length;
        if (len) {
            for (i = 0; i < len; i++)
                uuid[i] = chars[0 | Math.random() * radix];
        } else {
            var r;
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
            uuid[14] = '4';
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random() * 16;
                    uuid[i] = chars[(i === 19) ? (r & 0x3) | 0x8 : r];
                }
            }
        }
        return uuid.join('');
    }

    battcn.confirm = function confirm(dowork) {
        layer.confirm('是否继续操作?', {icon: 3, title: '提示'}, function (index) {
            dowork();
            layer.close(index);
        });
    }

    /**
     * 显示AJAX开始时的提示信息
     */
    battcn.showLoadingDiv = function () {
        $('#_AJAXLOADINGDIV_').show();
    };

    /**
     * AJAX结束时隐藏提示信息
     */
    battcn.hideLoadingDiv = function () {
        $('#_AJAXLOADINGDIV_').fadeOut(500);
    };

    $.ajaxSetup({
        cache: false,
        timeout: 50000
    });


    CommnUtil = {
        /**
         * ajax同步请求 返回一个html内容 dataType=html. 默认为html格式 如果想返回json.
         * CommnUtil.ajax(url, data,"json")
         *
         */
        ajax: function (url, data, dataType) {
            if (!CommnUtil.notNull(dataType)) {
                dataType = "html";
            }
            var html = '没有结果!';
            // 所以AJAX都必须使用battcn.ajax..这里经过再次封装,统一处理..同时继承ajax所有属性
            if (url.indexOf("?") > -1) {
                url = url + "&_t=" + new Date();
            } else {
                url = url + "?_t=" + new Date();
            }
            battcn.ajax({
                type: "post",
                data: data,
                url: url,
                dataType: dataType,// 这里的dataType就是返回回来的数据格式了html,xml,json
                async: false,
                cache: false,// 设置是否缓存，默认设置成为true，当需要每次刷新都需要执行数据库操作的话，需要设置成为false
                success: function (data) {
                    html = data;
                }
            });
            return html;
        },
        /**
         * 返回一个cookie = key = value 内容
         * CommnUtil.getCookie(cookieName)
         *
         */
        getCookie: function (cookieName) {
            var cookieContent = '';
            var cookieAry = document.cookie.split(";");//得到Cookie数组
            for (var i = 0; i < cookieAry.length; i++) {
                var temp = cookieAry[i].split("=");
                if ($.trim(temp[0]) == cookieName) {
                    cookieContent = unescape(temp[1]);
                }
            }
            return cookieContent;
        },


        /**
         * 判断某对象不为空..返回true 否则 false
         */
        notNull: function (obj) {
            if (obj === null) {
                return false;
            } else if (obj === undefined) {
                return false;
            } else if (obj === "undefined") {
                return false;
            } else if (obj === "") {
                return false;
            } else if (obj === "[]") {
                return false;
            } else if (obj === "{}") {
                return false;
            } else {
                return true;
            }

        },

        /**
         * 判断某对象不为空..返回obj 否则 ""
         */
        notEmpty: function (obj) {
            if (obj === null) {
                return "";
            } else if (obj === undefined) {
                return "";
            } else if (obj === "undefined") {
                return "";
            } else if (obj === "") {
                return "";
            } else if (obj === "[]") {
                return "";
            } else if (obj === "{}") {
                return "";
            } else {
                return obj;
            }

        },
        loadingImg: function () {
            var html = '<div class="alert alert-warning" id="loadImg">'
                + '<button type="button" class="close" data-dismiss="alert">'
                + '<i class="ace-icon fa fa-times"></i></button><div style="text-align:center">'
                + '<img src="http://static.battcn.com/back/img/loading.gif"/><div>'
                + '</div>';
            return html;
        },
        /**
         * html标签转义
         */
        htmlspecialchars: function (str) {
            var s = "";
            if (str.length == 0)
                return "";
            for (var i = 0; i < str.length; i++) {
                switch (str.substr(i, 1)) {
                    case "<":
                        s += "&lt;";
                        break;
                    case ">":
                        s += "&gt;";
                        break;
                    case "&":
                        s += "&amp;";
                        break;
                    case " ":
                        if (str.substr(i + 1, 1) == " ") {
                            s += " &nbsp;";
                            i++;
                        } else
                            s += " ";
                        break;
                    case "\"":
                        s += "&quot;";
                        break;
                    case "\n":
                        s += "";
                        break;
                    default:
                        s += str.substr(i, 1);
                        break;
                }
            }
        },
        /**
         * in_array判断一个值是否在数组中
         */
        in_array: function (array, string) {
            for (var s = 0; s < array.length; s++) {
                thisEntry = array[s].toString();
                if (thisEntry == string) {
                    return true;
                }
            }
            return false;
        }
    };
})
();
// 表单json格式化方法……不使用&拼接
(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        $(array).each(
            function () {
                if (serializeObj[this.name]) {
                    if ($.isArray(serializeObj[this.name])) {
                        serializeObj[this.name].push(this.value);
                    } else {
                        serializeObj[this.name] = [
                            serializeObj[this.name], this.value];
                    }
                } else {
                    serializeObj[this.name] = this.value;
                }
            });
        return serializeObj;
    };


    /**
     * 对Date的扩展
     *
     * 将 Date 转化为指定格式的String
     *
     * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
     *
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     *
     * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
     *
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
     *
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
     *
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
     *
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
     */
    Date.prototype.pattern = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, /* 月份 */
            "d+": this.getDate(), /* 日 */
            "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, /* 小时 */
            "H+": this.getHours(), /* 小时 */
            "m+": this.getMinutes(), /* 分 */
            "s+": this.getSeconds(), /* 秒 */
            "q+": Math.floor((this.getMonth() + 3) / 3), /* 季度 */
            /* 毫秒 */
            "S": this.getMilliseconds()
        };
        var week = {
            "0": "\u65e5",
            "1": "\u4e00",
            "2": "\u4e8c",
            "3": "\u4e09",
            "4": "\u56db",
            "5": "\u4e94",
            "6": "\u516d"
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        if (/(E+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };

})(jQuery);

$(function () {

    // 图片预览
    $("a.imagePreview").each(function () {
        $this = $(this);
        $this.qtip({
            content: '<img src="' + $this.attr("href") + '?timestamp=' + (new Date()).valueOf() + '" />',
            style: {
                width: "auto",
                padding: "1px"
            }
        });
    })
});

