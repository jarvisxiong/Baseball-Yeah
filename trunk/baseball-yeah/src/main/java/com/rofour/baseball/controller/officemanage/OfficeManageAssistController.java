package com.rofour.baseball.controller.officemanage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.UserModel;
import com.rofour.baseball.controller.model.office.OfficeAcctQueryInfo;
import com.rofour.baseball.controller.model.office.OfficeStoreQueryInfo;
import com.rofour.baseball.dao.officemanage.bean.UserAcctBean;
import com.rofour.baseball.service.officemanage.OfficeManageAssistSerivce;

@Controller
@RequestMapping(value="/office/assist")
public class OfficeManageAssistController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("officeManageAssistSerivce")
	private OfficeManageAssistSerivce officeManageAssistSerivce;
	
	/**
	 * 新增CEO页面
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAddCeo", method = RequestMethod.GET)
	public ModelAndView toAddCeo(HttpServletRequest request,UserModel user, Model model) throws Exception {
		if (request.getSession().getAttribute("user") != null) {			
			model.addAttribute("flag", 1);
			return new ModelAndView("officemanage/addCeo");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
	/**
	 * 新增COO页面
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toAddCoo", method = RequestMethod.GET)
	public ModelAndView toAddCoo(HttpServletRequest request,UserModel user, Model model) throws Exception {
		if (request.getSession().getAttribute("user") != null) {			
			model.addAttribute("flag", 0);			
			return new ModelAndView("officemanage/addCeo");
		}else {
			return new ModelAndView("/error/noPermission");
		}
	}
		
	@RequestMapping(value = "/query", method= RequestMethod.POST)
	public @ResponseBody DataGrid<OfficeAcctQueryInfo> getOfflineReport(
			HttpServletRequest request, @RequestBody OfficeAcctQueryInfo queryInfo) {
		if(null == queryInfo.getOffset()){
			queryInfo.setOffset(0);
		}
		if(null == queryInfo.getLimit()){
			queryInfo.setLimit(10);
		} 
		if(StringUtils.isBlank(queryInfo.getSort())) {
			queryInfo.setOrder("desc");
			queryInfo.setSort("pulicTime");
		}
//		if(!StringUtils.isBlank(queryInfo.getRealName())){
//			String temp = queryInfo.getRealName();
//			try {
//				queryInfo.setRealName(new String(temp.getBytes("ISO-8859-1"), "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				
//			}
//		}
		List<OfficeAcctQueryInfo> list = officeManageAssistSerivce.getAcctList(queryInfo);
		Integer total = officeManageAssistSerivce.getAcctCount(queryInfo);
		
		DataGrid<OfficeAcctQueryInfo> dataList = new DataGrid<OfficeAcctQueryInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	}

	/**
	 * 职务详情
	 * @param request
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
    @RequestMapping(value = "/partInfo")
    public UserAcctBean partInfo(HttpServletRequest request,HttpServletResponse response, UserAcctBean requestOrderInfo) {
        UserAcctBean acctBean = null;
        String userId = request.getParameter("userId");
        try {
        	acctBean = officeManageAssistSerivce.getPartInfo(userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), logger);
        }
        return acctBean;
    }
 	
	
//	@RequestMapping(value = "/partInfo", method = RequestMethod.GET)
//	public ModelAndView toWalletDrawMoney(HttpServletRequest request,UserModel user, Model model) throws Exception {
//		String userId = request.getParameter("userId");
//		if (request.getSession().getAttribute("user") != null) { 
//			model.addAttribute("part", officeManageAssistSerivce.getPartInfo(userId));
//			model.addAttribute("smallPieList", officeManageAssistSerivce.getSmallPieList(userId));
//			model.addAttribute("storeList", officeManageAssistSerivce.getMyStoreList(userId));
//			return new ModelAndView("officemanage/partInfo");
//		}else {
//			return new ModelAndView("/error/noPermission");
//		}
//	}
	 	@ResponseBody
	    @RequestMapping(value = "/smallPieList")
	    public List<UserAcctBean> smallPieList(HttpServletRequest request,HttpServletResponse response, UserAcctBean requestOrderInfo) {
	        List<UserAcctBean> list = null;
	        String userId = request.getParameter("userId");
	        System.out.println(userId);
	        try {
	            list = officeManageAssistSerivce.getSmallPieList(userId) ;
	            System.out.println("小派数量：" + list.size());
	        } catch (Exception e) {
	            logger.error(e.getMessage(), logger);
	        }
	        return list;
	    }
	 	
	 	@ResponseBody
	    @RequestMapping(value = "/storeList")
	    public List<UserAcctBean> storeList(HttpServletRequest request,HttpServletResponse response, UserAcctBean requestOrderInfo) {
	        List<UserAcctBean> list = null;
	        String userId = request.getParameter("userId");
	        try {
	            list = officeManageAssistSerivce.getMyStoreList(userId) ;
	        } catch (Exception e) {
	            logger.error(e.getMessage(), logger);
	        }
	        return list;
	    } 

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add")
	@ResponseBody
	public ResultInfo add(HttpServletRequest request) { 
		String userId = request.getParameter("userId");
		String flag = request.getParameter("flag");
		if(null == userId || null == flag){
		    return new ResultInfo(-1, "", "参数不能为空");
		}

		UserAcctBean acctBean = new UserAcctBean();
		acctBean.setUserId(userId);
		if(officeManageAssistSerivce.validateOfficeManageAudit(acctBean) == true) {
			return new ResultInfo(-1, "", "此用户已经待审核了");
		}

		Integer flagInt = Integer.valueOf(flag);
		Integer optType = null;
		if(flagInt == 0) {
			optType = 2;
		} else {
			optType = 1;
			if(officeManageAssistSerivce.validateCollegeCeo(acctBean) == true) {
				return new ResultInfo(-1, "", "该校区已经有CEO或者已申请CEO");
			}
		}
		
		if(officeManageAssistSerivce.validateOfficeRoleType(acctBean) == true) {
			return new ResultInfo(-1, "", "该成员已经是COO/CEO，解职之后才能重新申请");
		}
		
		acctBean.setOptType(optType);
		try {
			return officeManageAssistSerivce.insertOfficeManageAudit(acctBean) > 0 ? new ResultInfo(0, "", "新增成功")
					: new ResultInfo(-1, "", "操作失败");
		} catch (Exception e) {
			return new ResultInfo(-1, "", "操作失败，请联系系统管理员" + e.getMessage());
		} 
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addChooseStore")
	@ResponseBody
	public ResultInfo addChooseStore(HttpServletRequest request) { 
		String userId = request.getParameter("userId");  
		String stoExpId = request.getParameter("stoExpId");
		UserAcctBean acctBean = new UserAcctBean();
		acctBean.setUserId(userId);
		acctBean.setStoExpId(stoExpId);
		String[] array = stoExpId.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		acctBean.setStoIdList(list);
		try {
			return 
					officeManageAssistSerivce.chooseStore(acctBean) > 0 ? new ResultInfo(0, "", "新增成功"):
		new ResultInfo(-1, "", "操作失败");
		} catch (Exception e) {
			return new ResultInfo(-1, "", "操作失败，请联系系统管理员" + e.getMessage());
		} 
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/delChooseStore")
	@ResponseBody
	public ResultInfo delChooseStore(HttpServletRequest request) { 
		String userId = request.getParameter("userId");  
		String stoExpId = request.getParameter("stoExpId");
		UserAcctBean acctBean = new UserAcctBean();
		acctBean.setUserId(userId);
		acctBean.setStoExpId(stoExpId);
		String[] array = stoExpId.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		acctBean.setStoIdList(list);
		try {
			return 
					officeManageAssistSerivce.delStore(acctBean) > 0 ? new ResultInfo(0, "", "删除成功"):
		new ResultInfo(-1, "", "操作失败");
		} catch (Exception e) {
			return new ResultInfo(-1, "", "操作失败，请联系系统管理员" + e.getMessage());
		} 
	}
	
	
    @RequestMapping(value = "/chooseStore", method = RequestMethod.GET)
    public ModelAndView chooseStore(HttpServletRequest request, Model model ) {
    	String userId = request.getParameter("userId");
    	String flag = request.getParameter("flag");
    	if (request.getSession().getAttribute("user") != null) {
    		model.addAttribute("userId", userId);
    		model.addAttribute("flag", flag);
			return new ModelAndView("officemanage/chooseStore");
		}else {
			return new ModelAndView("/error/noPermission");
		}
    }  
	
	@RequestMapping(value = "/getCooStoreList", method= RequestMethod.POST)
	public @ResponseBody DataGrid<OfficeStoreQueryInfo> getCooStoreList(
			HttpServletRequest request, @RequestBody OfficeStoreQueryInfo queryInfo) {
		
		if(null == queryInfo.getOffset()){
			queryInfo.setOffset(0);
		}
		if(null == queryInfo.getLimit()){
			queryInfo.setLimit(10);
		} 
		if(StringUtils.isBlank(queryInfo.getSort())) {
			queryInfo.setOrder("desc");
			queryInfo.setSort("pulicTime");
		} 
		List<OfficeStoreQueryInfo> list = officeManageAssistSerivce.getCooStoreList(queryInfo);
		Integer total = officeManageAssistSerivce.getCooStoreCount(queryInfo); 
		DataGrid<OfficeStoreQueryInfo> dataList = new DataGrid<OfficeStoreQueryInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	} 
	@RequestMapping(value = "/getStoreList", method= RequestMethod.POST)
	public @ResponseBody DataGrid<OfficeStoreQueryInfo> getStoreList(
			HttpServletRequest request, @RequestBody OfficeStoreQueryInfo queryInfo) {
		if(null == queryInfo.getOffset()){
			queryInfo.setOffset(0);
		}
		if(null == queryInfo.getLimit()){
			queryInfo.setLimit(10);
		} 
		if(StringUtils.isBlank(queryInfo.getSort())) {
			queryInfo.setOrder("desc");
			queryInfo.setSort("pulicTime");
		} 
		List<OfficeStoreQueryInfo> list = officeManageAssistSerivce.getStoreList(queryInfo);
		Integer total = officeManageAssistSerivce.getAllStoreCount(queryInfo);
		
		DataGrid<OfficeStoreQueryInfo> dataList = new DataGrid<OfficeStoreQueryInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	} 
}
