<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .img-dialog .modal-dialog {
        width: 800px;
    }
    *{ margin:0; padding:0; list-style:none;}
    img{ border:0;}

    .ban{ width:500px; height:600px; position:relative; overflow:hidden;margin:40px auto 0 auto;}
    .ban2{ width:500px; height:500px; position:relative; overflow:hidden;}
    .ban2 ul{ position:absolute; left:0; top:0;}
    .ban2 ul li{ width:500px; height:500px;}
    .prev{ float:left; cursor:pointer;}
    .num{ height:82px;overflow:hidden; width:430px; position:relative;float:left;}
    .min_pic{ padding-top:10px; width:500px;}
    .num ul{ position:absolute; left:0; top:0;}
    .num ul li{ width:80px; height:80px; margin-right:5px; padding:1px;}
    .num ul li.on{ border:1px solid red; padding:0;}
    .prev_btn1{ width:16px; text-align:center; height:18px; margin-top:40px; margin-right:20px; cursor:pointer; float:left;}
    .next_btn1{  width:16px; text-align:center; height:18px; margin-top:40px;cursor:pointer;float:right;}
    .prev1{ position:absolute; top:220px; left:20px; width:28px; height:51px;z-index:9;cursor:pointer;}
    .next1{ position:absolute; top:220px; right:20px; width:28px; height:51px;z-index:9;cursor:pointer;}
    .mhc{ background:#000; width:100%;opacity:0.5;-moz-opacity:0.5;filter:alpha(Opacity=50); position:absolute; left:0; top:0; display:none;}
    .pop_up{ width:500px; height:500px; padding:10px; background:#fff; position:fixed; -position:absolute; left:50%; top:50%; margin-left:-255px; margin-top:-255px; display:none; z-index:99;}
    .pop_up_xx{ width:40px; height:40px; position:absolute; top:-40px; right:0; cursor:pointer;}
    .pop_up2{ width:500px; height:500px; position:relative; overflow:hidden;}
    .pop_up2{ width:500px; height:500px; position:relative; overflow:hidden; float:left;}
    .pop_up2 ul{ position:absolute; left:0; top:0;}
    .pop_up2 ul li{ width:500px; height:500px; float:left;}
</style>
    <div class="modal fade" id="pictureModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel"><label style="width: 200px" id="lableUserName"></label>
                        <label
                                id="lableVerifyStatus"></label></h4>
                </div>
                <div class="modal-body">
                    <div class="ban" id="demo1">
                        <div class="ban2" id="ban_pic1">
                            <div class="prev1" id="prev1"><img src="/js/slide/js/images/index_tab_l.png" width="28" height="51"  alt=""/></div>
                            <div class="next1" id="next1"><img src="/js/slide/js/images/index_tab_r.png" width="28" height="51"  alt=""/></div>
                            <ul id="img_big">

                            </ul>
                        </div>
                        <div class="min_pic">
                            <div class="prev_btn1" id="prev_btn1"><img src="/js/slide/js/images/feel3.png" width="9" height="18"  alt=""/></div>
                            <div class="num clearfix" id="ban_num1">
                                <ul id="img_small">

                                </ul>
                            </div>
                            <div class="next_btn1" id="next_btn1"><img src="/js/slide/js/images/feel4.png" width="9" height="18"  alt=""/></div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/slide/js/pic.js"></script>
