<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="worksigninModules">
<style>
    #tree {
        float: left;
        width: 400px;
        display: inline;
        height:730px;
        line-height:730px;
        overflow:auto;
        overflow-x:hidden;
    }

    #table {
        width: 1200px;
        height: auto;
        float: right;
        display: inline;
        margin-right: 20px;
        margin-top: 10px;
    }


</style>
<div class="row" id="tree">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <div class="main-box-body clearfix">
                <div class="table-responsive" id="mainTree">
                    <div class="panel-body" style="padding-bottom: 0px;"></div>

                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row" id="table">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="a-clear-search" id="clearSearch">
                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                </a>
                <a class="accordion-toggle a-search" data-toggle="collapse"
                   data-parent="#accordion" href="#"> 查询
                </a>
            </h4>
        </div>
        <div style="margin-top: 20px; " class="panel-collapse collapse in">
            <div class="panel-body">
                <div class="form-group col-xs-6">
                    <label for="cityId" class="control-label col-xs-2">城市</label>
                    <div class="col-xs-10">
                        <select id='cityId' name="payType"
                                class="form-control" style="width: 240px">
                        </select>
                    </div>
                </div>
                <div class="form-group col-xs-6">
                    <label for="collegeId" class="control-label col-xs-2">学校校区</label>
                    <div class="col-xs-10">
                        <select id='collegeId' name="collegeId"
                                class="form-control" style="width: 240px">
                        </select>
                    </div>
                </div>

                <div class="form-group col-xs-6">
                    <label class="control-label col-xs-2">日期</label>
                    <div class="col-xs-4">
                        <div class="input-group date" id="workDatePicker">
                            <input type="text" class="form-control" name="workDate" id="workDate" placeholder="日期"/>
                            <span class="input-group-addon">
                    <span class="glyphicon-calendar glyphicon"></span>
                </span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-xs-6">
                    <div class="col-xs-4">
                        <button type="button" id="btn_query_signin" class="btn btn-default">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="margin-top: 20px;">
        <div id="provinceWorkSigninTable" class="table"></div>
        <div id="cityWorkSigninTable" class="table"></div>
        <div id="collegeWorkSigninTable" class="table"></div>
        <div id="puserWorkSigninTable" class="table"></div>
    </div>


</div>


