
var jq = $.noConflict();
//ban_qh
jq.fn.banqh = function(can){
	can = jq.extend({
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
	var picnum = jq(can.pic).find('ul li').length;
	var picw = jq(can.pic).find('ul li').outerWidth(true);
	var pich = jq(can.pic).find('ul li').outerHeight(true);
	var poppicw = jq(can.pop_pic).find('ul li').outerWidth(true);
	var picminnum = jq(can.pnum).find('ul li').length;
	var picpopnum = jq(can.pop_pic).find('ul li').length;
	var picminw = jq(can.pnum).find('ul li').outerWidth(true);
	var picminh = jq(can.pnum).find('ul li').outerHeight(true);
	var pictime;
	var tpqhnum=0;
	var xtqhnum=0;
	var popnum=0;
	jq(can.pic).find('ul').width(picnum*picw).height(picnum*pich);
	jq(can.pnum).find('ul').width(picminnum*picminw).height(picminnum*picminh);
	jq(can.pop_pic).find('ul').width(picpopnum*poppicw);
	
//���Сͼ�л���ͼ
	    jq(can.pnum).find('li').click(function () {
        tpqhnum = xtqhnum = jq(can.pnum).find('li').index(this);
        show(tpqhnum);
		minshow(xtqhnum);
    }).eq(can.order).trigger("click");
//��ͼ������
if(can.pop_up==true){
	jq(can.pic).find('ul li').click(function(){
		jq(can.mhc).height(jq(document).height()).show();
		jq(can.pop_div).show();
		popnum = jq(this).index();
		var gdjl_w=-popnum*poppicw;
		jq(can.pop_pic).find('ul').css('left',gdjl_w);
		popshow(popnum);
		})
	jq(can.pop_xx).click(function(){
		jq(can.mhc).hide();
		jq(can.pop_div).hide();
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
		jq(can.box).hover(function(){
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
	jq(can.prev_btn).click(function(){
		if(tpqhnum==0){tpqhnum=picnum};
		if(xtqhnum==0){xtqhnum=picnum};
		xtqhnum--;
		tpqhnum--;
		show(tpqhnum);
		minshow(xtqhnum);	
		})
	jq(can.next_btn).click(function(){
		if(tpqhnum==picnum-1){tpqhnum=-1};
		if(xtqhnum==picminnum-1){xtqhnum=-1};
		xtqhnum++;
		minshow(xtqhnum)
		tpqhnum++;
		show(tpqhnum);
		})	
//��ͼ�����л�	
	jq(can.prev).click(function(){
		if(tpqhnum==0){tpqhnum=picnum};
		if(xtqhnum==0){xtqhnum=picnum};
		xtqhnum--;
		tpqhnum--;
		show(tpqhnum);
		minshow(xtqhnum);	
		})
	jq(can.next).click(function(){
		if(tpqhnum==picnum-1){tpqhnum=-1};
		if(xtqhnum==picminnum-1){xtqhnum=-1};
		xtqhnum++;
		minshow(xtqhnum)
		tpqhnum++;
		show(tpqhnum);
		})
//������ͼƬ�����л�	
	jq(can.pop_prev).click(function(){
		if(popnum==0){popnum=picnum};
		popnum--;
		popshow(popnum);
		})
	jq(can.pop_next).click(function(){
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
			jq(can.pnum).find('ul li').css('float','left');
			if(picminnum>can.min_picnum){
				if(xtqhnum<3){mingdjl_w=0;}
				if(xtqhnum==picminnum-1){mingdjl_w=-(mingdjl_num-1)*picminw;}
				jq(can.pnum).find('ul').stop().animate({'left':mingdjl_w},can.delayTime);
				}
				
		}else{
			jq(can.pnum).find('ul li').css('float','none');
			if(picminnum>can.min_picnum){
				if(xtqhnum<3){mingdjl_h=0;}
				if(xtqhnum==picminnum-1){mingdjl_h=-(mingdjl_num-1)*picminh;}
				jq(can.pnum).find('ul').stop().animate({'top':mingdjl_h},can.delayTime);
				}
			}
		
	}
//��ͼ�л�����
		function show(tpqhnum){
			var gdjl_w=-tpqhnum*picw;
			var gdjl_h=-tpqhnum*pich;
			if(can.picdire==true){
				jq(can.pic).find('ul li').css('float','left');
				jq(can.pic).find('ul').stop().animate({'left':gdjl_w},can.delayTime);
				}else{
			jq(can.pic).find('ul').stop().animate({'top':gdjl_h},can.delayTime);
			}//����
			//jq(can.pic).find('ul li').eq(tpqhnum).fadeIn(can.delayTime).siblings('li').fadeOut(can.delayTime);//���뵭��
			jq(can.pnum).find('li').eq(tpqhnum).addClass("on").siblings(this).removeClass("on");
		};
//������ͼƬ�л�����
		function popshow(popnum){
			var gdjl_w=-popnum*poppicw;
				jq(can.pop_pic).find('ul').stop().animate({'left':gdjl_w},can.pop_delayTime);
			//jq(can.pop_pic).find('ul li').eq(tpqhnum).fadeIn(can.pop_delayTime).siblings('li').fadeOut(can.pop_delayTime);//���뵭��
		};					
				
}