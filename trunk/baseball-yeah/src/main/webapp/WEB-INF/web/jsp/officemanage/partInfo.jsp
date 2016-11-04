<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="detailForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="detailModal" role="dialog"
         aria-labelledby="myModalLabel" style="z-index:1041;">
        <div class="modal-dialog" role="document" style="width: 1100px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">职务详情</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="detailMoblile" class="control-label col-xs-1" style=" margin-top: 0;">登录账号</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailMoblile" readonly></div>
                        <label for="detailOrderId" class="col-lg-1 control-label" style=" margin-top: 0;">职务</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailOrderId" readonly></div>
                    </div> 

                    <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="detailRealName" class="control-label col-xs-1" style=" margin-top: 0;">真实姓名</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailRealName" readonly>
                      
                        </div> 
                        <label for="detailSex" class="col-lg-1 control-label" style=" margin-top: 0;">性别</label>
                        <div class="col-lg-3">  <input type="text" class="form-control" id="detailAbc" readonly></div>
                    </div>

                    <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="detailState" class="control-label col-xs-1" style=" margin-top: 0;">账号状态</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailState" readonly></div>
                    </div> 
                   
			   <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
			    
			   </div>
			   <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;padding-left: 10px;">
			   下属小派：
			    <table id="detailTable" class="table"></table>
			   </div>
                   
   
			   <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
			    
			   </div>
			   <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;padding-left: 10px;padding-top: 30px;">
			   负责商户：
			   </div>
                    <table id="storeTable" class="table"></table>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div> 
<%-- <jsp:include page="./../crowdsource/smallPieDetail.jsp"></jsp:include> --%>
</form>
