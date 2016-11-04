/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rofour.baseball.common.CommonConsistEnum;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.SelectModel;

/**
 * @ClassName: CommonSelectDataController
 * @Description: 公共下拉列表常量
 * @author Administrator
 * @date 2016年10月27日 上午11:24:17 
 */
@Controller
@RequestMapping(value = "/manage/commonSelectData")
public class CommonSelectDataController  extends BaseController {

	/**
	*
	* @Description: 用于下拉列表
	* @return List<SelectModel>
	*/
	@ResponseBody
	@RequestMapping(value = "/getOrderType", method = RequestMethod.GET)
	public List<SelectModel> getSelectList() {
		List<SelectModel> sellist = new ArrayList<>();
		SelectModel selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.COMMON_EMPTY.getCode());
		selectModel.setText(CommonConsistEnum.COMMON_EMPTY.getDesc());
		sellist.add(selectModel);
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.TYPE_PACKET.getCode());
		selectModel.setText(CommonConsistEnum.TYPE_PACKET.getDesc());
		sellist.add(selectModel);
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.TYPE_AGENT.getCode());
		selectModel.setText(CommonConsistEnum.TYPE_AGENT.getDesc());
		sellist.add(selectModel);
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.TYPE_SEND.getCode());
		selectModel.setText(CommonConsistEnum.TYPE_SEND.getDesc());
		sellist.add(selectModel);
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.TYPE_ERRANDS.getCode());
		selectModel.setText(CommonConsistEnum.TYPE_ERRANDS.getDesc());
		sellist.add(selectModel);
		return sellist;
	}


	/**
	*
	* @Description: 用于状态的下拉列表
	* @return List<SelectModel>
	*/
	@ResponseBody
	@RequestMapping(value = "/getStateList", method = RequestMethod.GET)
	public List<SelectModel> getStateList() {
		List<SelectModel> sellist = new ArrayList<>();
		SelectModel selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.COMMON_EMPTY.getCode());
		selectModel.setText(CommonConsistEnum.COMMON_EMPTY.getDesc());
		sellist.add(selectModel);

		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.STATE_DISALBE.getCode());
		selectModel.setText(CommonConsistEnum.STATE_DISALBE.getDesc());
		sellist.add(selectModel);

		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.STATE_ENALBE.getCode());
		selectModel.setText(CommonConsistEnum.STATE_ENALBE.getDesc());
		sellist.add(selectModel);
		return sellist;
	}

	/**
	*
	* @Description: 初始化角色的下拉列表
	* @return List<SelectModel>
	*/
	@ResponseBody
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	public List<SelectModel> getRoleList() {
		List<SelectModel> sellist = new ArrayList<>();
		SelectModel selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.COMMON_EMPTY.getCode());
		selectModel.setText(CommonConsistEnum.COMMON_EMPTY.getDesc());
		sellist.add(selectModel);
		
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.ROLE_STORE.getCode());
		selectModel.setText(CommonConsistEnum.ROLE_STORE.getDesc());
		sellist.add(selectModel);
		
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.ROLE_CEO.getCode());
		selectModel.setText(CommonConsistEnum.ROLE_CEO.getDesc());
		sellist.add(selectModel);
		
		selectModel = new SelectModel();
		selectModel.setId(CommonConsistEnum.ROLE_COO.getCode());
		selectModel.setText(CommonConsistEnum.ROLE_COO.getDesc());
		sellist.add(selectModel);
		return sellist;
	}
	
}
