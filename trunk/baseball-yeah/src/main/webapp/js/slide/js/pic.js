
// var jq = $.noConflict();
//ban_qh
$.fn.banqh = function(can){
	can = $.extend({
					box:null,//�ܿ��
					pic:null,//��ͼ���
					pnum:null,//Сͼ���
					prev_btn:null,//Сͼ���ͷ
					next_btn:null,//Сͼ�Ҽ�ͷ
					prev:null,//��ͼ���ͷ
					next:null,//��ͼ�Ҽ�ͷ
					pop_prev:null,//���������ͷ
					pop_next:null,//�������Ҽ�ͷ
					autoplay:false,//�Ƿ��Զ�����
					interTime:5000,//ͼƬ�Զ��л����
					delayTime:800,//�л�һ��ͼƬʱ��
					pop_delayTime:800,//�������л�һ��ͼƬʱ��
					order:0,//��ǰ��ʾ��ͼƬ����0��ʼ��
					picdire:true,//��ͼ��������trueˮƽ���������
					mindire:true,//Сͼ��������trueˮƽ���������
					min_picnum:null,//Сͼ��ʾ����
					pop_up:false,//��ͼ�Ƿ��е�����
					pop_div:null,//��������
					pop_pic:null,//������ͼƬ���
					pop_xx:null,//�رյ�����ť
					mhc:null//���Ҳ�
				}, can || {});
	var picnum = $(can.pic).find('ul li').length;
	var picw = $(can.pic).find('ul li').outerWidth(true);
	var pich = $(can.pic).find('ul li').outerHeight(true);
	var poppicw = $(can.pop_pic).find('ul li').outerWidth(true);
	var picminnum = $(can.pnum).find('ul li').length;
	var picpopnum = $(can.pop_pic).find('ul li').length;
	var picminw = $(can.pnum).find('ul li').outerWidth(true);
	var picminh = $(can.pnum).find('ul li').outerHeight(true);
	var pictime;
	var tpqhnum=0;
	var xtqhnum=0;
	var popnum=0;
	$(can.pic).find('ul').width(picnum*picw).height(picnum*pich);
	$(can.pnum).find('ul').width(picminnum*picminw).height(picminnum*picminh);
	$(can.pop_pic).find('ul').width(picpopnum*poppicw);
	
//���Сͼ�л���ͼ
	$(can.pnum).find('li').click(function () {
        tpqhnum = xtqhnum = $(can.pnum).find('li').index(this);
        show(tpqhnum);
		minshow(xtqhnum);
    }).eq(can.order).trigger("click");
//��ͼ������
if(can.pop_up==true){
	$(can.pic).find('ul li').click(function(){
		$(can.mhc).height($(document).height()).show();
		$(can.pop_div).show();
		popnum = $(this).index();
		var gdjl_w=-popnum*poppicw;
		$(can.pop_pic).find('ul').css('left',gdjl_w);
		popshow(popnum);
		})
	$(can.pop_xx).click(function(){
		$(can.mhc).hide();
		$(can.pop_div).hide();
	})
}

	if(can.autoplay==true){
//�Զ�����
		pictime = setInterval(function(){
			show(tpqhnum);
			minshow(tpqhnum)
			tpqhnum++;
			xtqhnum++;
			if(tpqhnum==picnum){tpqhnum=0};	
			if(xtqhnum==picminnum){xtqhnum=0};
					
		},can.interTime);	
		
//��꾭��ֹͣ����
		$(can.box).hover(function(){
			clearInterval(pictime);
		},function(){
			pictime = setInterval(function(){
				show(tpqhnum);
				minshow(tpqhnum)
				tpqhnum++;
				xtqhnum++;
				if(tpqhnum==picnum){tpqhnum=0};	
				if(xtqhnum==picminnum){xtqhnum=0};		
				},can.interTime);			
			});
	}
//Сͼ�����л�			
	$(can.prev_btn).click(function(){
		if(tpqhnum==0){tpqhnum=picnum};
		if(xtqhnum==0){xtqhnum=picnum};
		xtqhnum--;
		tpqhnum--;
		show(tpqhnum);
		minshow(xtqhnum);	
		})
	$(can.next_btn).click(function(){
		if(tpqhnum==picnum-1){tpqhnum=-1};
		if(xtqhnum==picminnum-1){xtqhnum=-1};
		xtqhnum++;
		minshow(xtqhnum)
		tpqhnum++;
		show(tpqhnum);
		})	
//��ͼ�����л�	
	$(can.prev).click(function(){
		if(tpqhnum==0){tpqhnum=picnum};
		if(xtqhnum==0){xtqhnum=picnum};
		xtqhnum--;
		tpqhnum--;
		show(tpqhnum);
		minshow(xtqhnum);	
		})
	$(can.next).click(function(){
		if(tpqhnum==picnum-1){tpqhnum=-1};
		if(xtqhnum==picminnum-1){xtqhnum=-1};
		xtqhnum++;
		minshow(xtqhnum)
		tpqhnum++;
		show(tpqhnum);
		})
//������ͼƬ�����л�	
	$(can.pop_prev).click(function(){
		if(popnum==0){popnum=picnum};
		popnum--;
		popshow(popnum);
		})
	$(can.pop_next).click(function(){
		if(popnum==picnum-1){popnum=-1};
		popnum++;
		popshow(popnum);
		})			
//Сͼ�л�����
	function minshow(xtqhnum){
		var mingdjl_num =xtqhnum-can.min_picnum+2
		var mingdjl_w=-mingdjl_num*picminw;
		var mingdjl_h=-mingdjl_num*picminh;
		
		if(can.mindire==true){
			$(can.pnum).find('ul li').css('float','left');
			if(picminnum>can.min_picnum){
				if(xtqhnum<3){mingdjl_w=0;}
				if(xtqhnum==picminnum-1){mingdjl_w=-(mingdjl_num-1)*picminw;}
				$(can.pnum).find('ul').stop().animate({'left':mingdjl_w},can.delayTime);
				}
				
		}else{
			$(can.pnum).find('ul li').css('float','none');
			if(picminnum>can.min_picnum){
				if(xtqhnum<3){mingdjl_h=0;}
				if(xtqhnum==picminnum-1){mingdjl_h=-(mingdjl_num-1)*picminh;}
				$(can.pnum).find('ul').stop().animate({'top':mingdjl_h},can.delayTime);
				}
			}
		
	}
//��ͼ�л�����
		function show(tpqhnum){
			var gdjl_w=-tpqhnum*picw;
			var gdjl_h=-tpqhnum*pich;
			if(can.picdire==true){
				$(can.pic).find('ul li').css('float','left');
				$(can.pic).find('ul').stop().animate({'left':gdjl_w},can.delayTime);
				}else{
				$(can.pic).find('ul').stop().animate({'top':gdjl_h},can.delayTime);
			}//����
			//$(can.pic).find('ul li').eq(tpqhnum).fadeIn(can.delayTime).siblings('li').fadeOut(can.delayTime);//���뵭��
			$(can.pnum).find('li').eq(tpqhnum).addClass("on").siblings(this).removeClass("on");
		};
//������ͼƬ�л�����
		function popshow(popnum){
			var gdjl_w=-popnum*poppicw;
			$(can.pop_pic).find('ul').stop().animate({'left':gdjl_w},can.pop_delayTime);
			//$(can.pop_pic).find('ul li').eq(tpqhnum).fadeIn(can.pop_delayTime).siblings('li').fadeOut(can.pop_delayTime);//���뵭��
		};					
				
}