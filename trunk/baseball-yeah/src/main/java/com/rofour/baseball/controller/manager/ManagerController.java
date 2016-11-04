package com.rofour.baseball.controller.manager;

import com.rofour.baseball.controller.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ManagerController
 * @Description: 管理中心页面跳转控制层
 * @author ZhangLei
 * @date 2016年5月5日 上午10:25:32
 *
 */

@Controller
@RequestMapping(value = "/manage")
public class ManagerController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/gotoEmployeeManager")
	public ModelAndView gotoEmployeeManager(HttpServletRequest request) {
		logger.info("进入系统用户页面跳转");
		if (request.getSession().getAttribute("user") != null) {
			logger.info("可以跳转系统用户页面");
			return new ModelAndView("/manager/employeeManager/employeeManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoExpressManager")
	public ModelAndView gotoExpressManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/expressManager/expressManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoPeoplePowerManager")
	public ModelAndView gotoPeoplePowerManagert(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/peoplePowerManager/peoplePowerManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoSchoolManager")
	public ModelAndView gotoSchoolManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/schoolManager/schoolManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoStoreInfoManager")
	public ModelAndView gotoStoreInfoManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/storeInfoManager/storeInfoManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoDeptManager")
	public ModelAndView gotoDeptManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/deptManager/deptManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoSysNoticeManager")
	public ModelAndView gotoSysNoticeManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/sysNoticeManager/allSysNotice");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoFeedBackManager")
	public ModelAndView gotoFeedBackManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/feedBackManager/allFeedBack");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoFocusPicManager")
	public ModelAndView gotoFocusPicManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/focusPicManager/allFocusPic");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoMsgTemplateManager")
	public ModelAndView gotoMsgTemplateManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/msgTemplateManager/allMsgTemplate");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoexpressCompanyManager")
	public ModelAndView gotoexpressCompanyManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/expressCompanyManager/expressCompanyManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoProvinceManager")
	public ModelAndView gotoProvinceManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/provinceManager/allProvince");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoUserLogManager")
	public ModelAndView gotoUserLogManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/userManageLog/indexUserManage");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoOfferPriceManager")
	public ModelAndView gotoOfferPriceManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/offerPriceManager/offerPriceManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoCompanyOfferPriceManager")
	public ModelAndView gotoCompanyOfferPriceManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/companyOfferPriceManager/companyOfferPriceManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoQuotaManager")
	public ModelAndView gotoQuotaManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/acctQuotaManager/allQuota");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
	@RequestMapping(value = "/gotoWaybillProblem")
	public ModelAndView gotoWaybillProblem(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/waybill/waybillProblem/waybillProblemType");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	@RequestMapping(value = "/gotoGoodsType")
	public ModelAndView gotoGoodsType(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/goodsTypeManager/goodsTypeManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	@RequestMapping(value = "/gotoPickupAddressManager")
	public ModelAndView gotoCollectAddressManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/pickupAddressManager/allPickupAddress");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoMonitorManager")
	public ModelAndView gotoMonitorManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/monitorManager/allMonitor");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}

	@RequestMapping(value = "/gotoBusinessChlManager")
	public ModelAndView gotoBusinessChlManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/businessChlManager/businessChlManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
	@RequestMapping(value = "/gotoCityOrderConfManager")
	public ModelAndView gotoCityOrderConfManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/cityOrderConfManager/cityOrderConfManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
	@RequestMapping(value = "/gotoCommisionOrderConfManager")
	public ModelAndView gotoCommisionOrderConfManager(HttpServletRequest request) {
		if (request.getSession().getAttribute("user") != null) {
			return new ModelAndView("/manager/commisionOrderConfManager/commisionOrderConfManager");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
}
