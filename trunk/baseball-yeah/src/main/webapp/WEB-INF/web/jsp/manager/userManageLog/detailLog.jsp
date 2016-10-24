<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="auditForm" method="post" class="form-horizontal" action="">
    <div class="modal fade " id="detailModal" role="dialog"
         aria-labelledby="myModalLabel" >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">日志明细</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <div class="col-lg-12">
                            <table id="detail_distinction">
                            </table>
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
</form>
<input type='hidden' id='userId'>