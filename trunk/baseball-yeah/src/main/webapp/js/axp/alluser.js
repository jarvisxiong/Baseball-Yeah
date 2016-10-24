$(document).ready(
		function() {

			$('#reportrange').daterangepicker(
					{
						startDate : moment().subtract('days', 29),
						endDate : moment(),
						minDate : '01/01/2012',
						maxDate : '12/31/2014',
						dateLimit : {
							days : 60
						},
						showDropdowns : true,
						showWeekNumbers : true,
						timePicker : false,
						timePickerIncrement : 1,
						timePicker12Hour : true,
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract('days', 1),
									moment().subtract('days', 1) ],
							'Last 7 Days' : [ moment().subtract('days', 6),
									moment() ],
							'Last 30 Days' : [ moment().subtract('days', 29),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract('month', 1).startOf(
											'month'),
									moment().subtract('month', 1)
											.endOf('month') ]
						},
						opens : 'left',
						buttonClasses : [ 'btn btn-default' ],
						applyClass : 'btn-small btn-primary',
						cancelClass : 'btn-small',
						format : 'MM/DD/YYYY',
						separator : ' to ',
						locale : {
							applyLabel : 'Submit',
							fromLabel : 'From',
							toLabel : 'To',
							customRangeLabel : 'Custom Range',
							daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We', 'Th', 'Fr',
									'Sa' ],
							monthNames : [ 'January', 'February', 'March',
									'April', 'May', 'June', 'July', 'August',
									'September', 'October', 'November',
									'December' ],
							firstDay : 1
						}
					},
					function(start, end) {
						console.log("Callback has been called!");
						$('#reportrange span').html(
								start.format('MMMM D, YYYY') + ' - '
										+ end.format('MMMM D, YYYY'));
					});
			// Set the initial state of the picker label
			$('#reportrange span').html(
					moment().subtract('days', 29).format('MMMM D, YYYY')
							+ ' - ' + moment().format('MMMM D, YYYY'));
			$('#userTable').DataTable({
				
				'bProcessing' : true,
				'bStateSave' : true
			});
		});

function cancelUser(userId) {
	sweetAlert({
		  title: "注销用户",
		  text: "您确定要注销此用户吗？",
		  type: "warning",
		  showCancelButton: true,
		  animation:'slide-from-top',
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "注销!",
		  cancelButtonText: "取消",
		  closeOnConfirm: false
		}, function(){
			$.post("cancelUser", {
				"userId" : userId
			}, function(data, status) {
				if (status == "success") {
					var obj = JSON.parse(data);
					if (obj.success == 0) {
						// alert("注销成功");
						window.location.reload();
					}
				} else {
					$.growlUI('提示', '数据加载失败');
				}
			});
		});
}

function reloadDataTable() {
	showModalDialog('<c:url value="gotoEditUser"/>', "",
			"dialogWidth=735px;dialogHeight:600px");
	window.location.reload();
	// $('#userTable').DataTable().ajax.reload();
}

function verify(userId, state) {
	$('#form-add').css('display','block');
	BootstrapDialog.show({
       message: $('#form-add'),
       size:'size-wide',
        closable:false,
        autodestroy:true
    });
	
	
	/*$.post("verify", {
		"userId" : userId,
		"verifyStatus" : state
	}, function(data, status) {
		if (status == "success") {
			var obj = JSON.parse(data);
			if (obj.success == 0) {
				// alert("注销成功");
				window.location.reload();
			}
		} else {
			alert("数据加载失败");
			window.location.reload();
		}
	});*/
}

/*
 function gotoEditUser(url,param){ post(url,param); }
 */

function setSearchPage() {
	var val = $(this).attr('title');
}