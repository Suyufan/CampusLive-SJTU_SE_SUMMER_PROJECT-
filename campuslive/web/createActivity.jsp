<%@ page import="java.util.List" %>
<%@ page import="Entity.Tag" %>
<%@ page import="Entity.Team" %>
<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/11
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<link href="css/webuploader.css" rel="stylesheet"/>
<link href="css/createActivity.css" rel="stylesheet"/>
<script src="js/ajaxfileupload.js" type="text/javascript" charset="UTF-8"></script>
<script src="js/webuploader.js" type="text/javascript" charset="UTF-8"></script>
<%
    HttpSession s = request.getSession();
    List<Tag> tags = (List<Tag>) s.getAttribute("tags");
    List<Team> teams = (List<Team>) s.getAttribute("teams");
    User user = (User) s.getAttribute("user");
    if (user != null){
%>
<div class="container-fluid">
    <form action="saveActivity" method="post">
        <div class="col-lg-12" id="basicInfo">
            <div class="alert alert-info">
                <h2>基本信息</h2>
            </div>
            <div class="col-lg-5">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动名称</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="actName" placeholder="请输入活动名称" value="" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动地点</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="actLocation" placeholder="请输入活动地点" value="" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">人数上限</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="maxMember" placeholder="请输入最大人数" value="" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">主办团队</label>
                        <div class="col-sm-7">
                            <select class="form-control" name="groupName">
                                <option></option>
                                <%
                                    for (Team team : teams){
                                %>
                                <option><%=team.getTeamName() %></option>
                                <%--<input type="hidden" name="groupId" value="<%=team.getTeamId() %>">--%>
                                <%
                                    }
                                %>
                            </select>
                            <%--<input type="text" class="form-control" name="actName" placeholder="请输入主办团队（可为空）"/>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动标签</label>
                        <div class="col-sm-7">
                            <%
                                for (Tag tag : tags){
                            %>
                            <label class="checkbox-inline"><input type="checkbox" name="tags" value="<%=tag.getTagId() %>"><%=tag.getTagName() %></label>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报名开始时间</label>
                        <div id="time1" class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
                            <input class="form-control" size="16" type="text" value="" placeholder="请选择报名开始时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input1" value="" name="regStartTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">报名结束时间</label>
                        <div id="time2" class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input2">
                            <input class="form-control" size="16" type="text" value="" placeholder="请选择报名结束时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input2" value="" name="regEndTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动开始时间</label>
                        <div id="time3" class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input3">
                            <input class="form-control" size="16" type="text" value="" placeholder="请选择活动开始时间" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            <input type="hidden" class="form-control" id="dtp_input3" value="" name="actStartTime"/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">活动结束时间</label>
                        <div id="time4" class="input-group date form_datetime col-md-5" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input4">
                            <input class="form-control" size="16" type="text" value="" placeholder="请选择活动结束时间" required>
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
                    <p>请输入活动介绍......</p>
                </textarea>
            </div>
        </div>
        <div class="col-lg-12" id="post">
            <div class="alert alert-info">
                <h2>上传海报</h2>
            </div>
            <div id="uploader-demo">
                <!--用来存放item-->
                <div id="fileList" class="uploader-list"></div>
                <div id="filePicker">选择图片</div>
            </div>
            <input type="hidden" id="postPath" name="postPath" value=""/>
        </div>
        <input type="hidden" name="userId" value="<%=user.getUserId() %>"/>
        <input type="submit" class="btn btn-primary form-control submit" value="提交"/>
    </form>
</div>
<%
    }
    else {
%>
<div class="alert alert-danger">
    <h2>请先登录或注册</h2>
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

<script type="text/javascript">
    // 图片上传demo
    jQuery(function() {
        var $ = jQuery,
                $list = $('#fileList'),

        // 缩略图大小
                thumbnailWidth = 400 ,
                thumbnailHeight = 500 ,
        // Web Uploader实例
                uploader;
        // 初始化Web Uploader
        uploader = WebUploader.create({
            // 自动上传。
            auto: true,
            // swf文件路径
            swf:'js/Uploader.swf',
            // 文件接收服务端。
            server: 'http://localhost:8080/campus/uploadFile',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#filePicker',
            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 90,
            // 只允许选择文件，可选。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });


        // 当有文件添加进来的时候
        uploader.on( 'fileQueued', function( file ) {
            var $li = $(
                            '<div id="' + file.id + '" class="file-item thumbnail">' +
                            '<img>' +
                            '<div class="info">' + file.name + '</div>' +
                            '</div>'
                    ),
                    $img = $li.find('img');
            // $list为容器jQuery实例
            $list.append( $li );

            // 创建缩略图
            // 如果为非图片文件，可以不用调用此方法。
            // thumbnailWidth x thumbnailHeight 为 100 x 100
            uploader.makeThumb( file, function( error, src ) {
                if ( error ) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }

                $img.attr( 'src', src );
            }, thumbnailWidth, thumbnailHeight );
        });

        // 文件上传过程中创建进度条实时显示。
        uploader.on( 'uploadProgress', function( file, percentage ) {
            var $li = $( '#'+file.id ),
                    $percent = $li.find('.progress span');

            // 避免重复创建
            if ( !$percent.length ) {
                $percent = $('<p class="progress"><span></span></p>')
                        .appendTo( $li )
                        .find('span');
            }

            $percent.css( 'width', percentage * 100 + '%' );
        });

        // 文件上传成功，给item添加成功class, 用样式标记上传成功。
        //注意我写样式了啊response:服务端返回的数据
        uploader.on( 'uploadSuccess', function( file,response) {
            $( '#'+file.id ).addClass('upload-state-done');
            //console.info(response);
           /* $("#upInfo").html("<strong>"+response._raw+"</strong>");
            $("#preView").attr('src',response._raw);*/
            $("#postPath").attr('value',response._raw);
        });

        // 文件上传失败
        uploader.on( 'uploadError', function(file) {
            var $li = $( '#'+file.id ),
                    $error = $li.find('div.error');
            alert("failed ");

            // 避免重复创建
            if ( !$error.length ) {
                $error = $('<div class="error"></div>').appendTo( $li );
            }

            $error.text('上传失败!');
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on( 'uploadComplete', function( file ) {
            $( '#'+file.id ).find('.progress').remove();
        });
    });
</script>

<script type="text/javascript">

</script>


<jsp:include page="footer.jsp"/>
