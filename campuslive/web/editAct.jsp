<%@ page import="Entity.User" %>
<%@ page import="Entity.Activity" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/18
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<style type="text/css">
    body{
        padding-top: 56px;
    }
    .container-fluid{
        width: 100%;
        height: auto;
    }

    .url {
        width: 250px;
        height: 30px;
        border: 1px solid #CCCCCC;
        border-radius: 4px;
    }

    span {
        color: #999999;
        cursor: pointer;
    }

    span:hover {
        color: orange
    }
</style>
<%
    HttpSession s = request.getSession();
    Activity activity = (Activity) s.getAttribute("act");
    User user = (User) s.getAttribute("user");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    if (user != null){
%>
<div class="container-fluid">
    <form action="updateActivity" method="post">
        <input type="hidden" name="actId" value="<%=activity.getActId() %>"/>
        <input type="hidden" name="userId" value="<%=user.getUserId() %>"/>
        <div class="col-lg-12" id="basicInfo">
            <div class="alert alert-info">
                <h2>基本信息</h2>
            </div>
            <div class="col-lg-5">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动名称</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="actName" placeholder="请输入活动名称" value="<%=activity.getActName()%>" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动地点</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="actLocation" placeholder="请输入活动地点" value="<%=activity.getActLocation()%>" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">人数上限</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="maxMember" placeholder="请输入最大人数" value="<%=activity.getMaxMember()%>" required/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报名开始时间</label>
                        <div class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                            <input class="form-control" size="16" type="text" value="" placeholder="请重新选择报名开始时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input1" value="" name="regStartTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报名结束时间</label>
                        <div class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input2">
                            <input class="form-control" size="16" type="text" value="" placeholder="请重新选择报名结束时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input2" value="" name="regEndTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动开始时间</label>
                        <div class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input3">
                            <input class="form-control" size="16" type="text" value="" placeholder="请重新选择活动开始时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input3" value="" name="actStartTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动结束时间</label>
                        <div class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input4">
                            <input class="form-control" size="16" type="text" value="" placeholder="请重新选择活动结束时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input4" value="" name="actEndTime"/><br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-12" id="inputDesc">
            <div class="alert alert-info">
                <h2>活动介绍</h2>
            </div>
            <div class="form-group">
                <textarea class="form-control" id="actDesc" name="actDesc" style="height: 400px">
                    <%=activity.getActDesc() %>
                </textarea>
            </div>
        </div>

        <input type="submit" class="btn btn-primary form-control" value="提交"/>
    </form>
</div>
<%
    }
%>

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
</script>

<script type="text/javascript">
    // 阻止输出log
    // wangEditor.config.printLog = false;

    var editor = new wangEditor('actDesc');

    // 上传图片
    editor.config.uploadImgUrl = '/upload';
    editor.config.uploadParams = {
        token: 'abcde',
        user: 'su'
    };
    editor.config.uploadHeaders = {
        'Accept' : 'text/x-json'
    }
    // editor.config.uploadImgFileName = 'myFileName';

    // 隐藏网络图片
    editor.config.hideLinkImg = true;

    // 表情显示项
    // editor.config.emotionsShow = 'value';
    editor.config.emotions = {
        'default': {
            title: '默认',
            data: './emotions.data'
        },
        'weibo': {
            title: '微博表情',
            data: [
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif',
                    value: '[草泥马]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif',
                    value: '[神马]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif',
                    value: '[浮云]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif',
                    value: '[给力]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif',
                    value: '[围观]'
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif',
                    value: '[威武]'
                }
            ]
        }
    };

    // 只粘贴纯文本
    // editor.config.pasteText = true;

    // 跨域上传
    // editor.config.uploadImgUrl = 'http://localhost:8012/upload';

    // 第三方上传
    // editor.config.customUpload = true;

    // 普通菜单配置
    // editor.config.menus = [
    //     'img',
    //     'insertcode',
    //     'eraser',
    //     'fullscreen'
    // ];
    // 只排除某几个菜单（兼容IE低版本，不支持ES5的浏览器），支持ES5的浏览器可直接用 [].map 方法
    // editor.config.menus = $.map(wangEditor.config.menus, function(item, key) {
    //     if (item === 'insertcode') {
    //         return null;
    //     }
    //     if (item === 'fullscreen') {
    //         return null;
    //     }
    //     return item;
    // });

    // onchange 事件
    // editor.onchange = function () {
    //     console.log(this.$txt.html());
    // };

    // 取消过滤js
    // editor.config.jsFilter = false;

    // 取消粘贴过来
    // editor.config.pasteFilter = false;

    // 设置 z-index
    // editor.config.zindex = 20000;

    // 语言
    // editor.config.lang = wangEditor.langs['en'];

    // 自定义菜单UI
    // editor.UI.menus.bold = {
    //     normal: '<button style="font-size:20px; margin-top:5px;">B</button>',
    //     selected: '.selected'
    // };
    // editor.UI.menus.italic = {
    //     normal: '<button style="font-size:20px; margin-top:5px;">I</button>',
    //     selected: '<button style="font-size:20px; margin-top:5px;"><i>I</i></button>'
    // };
    editor.create();
</script>

<jsp:include page="footer.jsp"/>
