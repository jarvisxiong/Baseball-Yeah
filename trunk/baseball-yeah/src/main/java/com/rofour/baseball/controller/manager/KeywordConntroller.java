package com.rofour.baseball.controller.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rofour.baseball.common.AttachConstant;
import com.rofour.baseball.common.AxpStream;
import com.rofour.baseball.common.Constant;
import com.rofour.baseball.common.DefaultKeywordProvider;
import com.rofour.baseball.common.HttpClientUtils;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.DataGrid;
import com.rofour.baseball.controller.model.ResultInfo;
import com.rofour.baseball.controller.model.manager.KeywordInfo;
import com.rofour.baseball.dao.manager.bean.KeywordBean;
import com.rofour.baseball.dao.user.bean.UserManagerLoginBean;
import com.rofour.baseball.service.manager.KeywordService;

@Controller
@RequestMapping(value = "/manage/keyword")
public class KeywordConntroller extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Qualifier("keywordService")
	@Autowired
	private KeywordService keywordService;
	
	@RequestMapping(value = "/gotoKeyword")
	public ModelAndView gotoEmployeeManager(HttpServletRequest request) {
		logger.info("进入系统用户页面跳转");
		if (request.getSession().getAttribute("user") != null) {
			logger.info("可以跳转系统用户页面");
			return new ModelAndView("manager/keyword/keyword");
		} else {
			return new ModelAndView("/error/noPermission");
		}
	}
	
	@RequestMapping(value = "/query", method= RequestMethod.POST)
	public @ResponseBody DataGrid<KeywordInfo> getOfflineReport(
			HttpServletRequest request, @RequestBody KeywordInfo queryInfo) {
		if(null == queryInfo.getOffset()){
			queryInfo.setOffset(0);
		}
		if(null == queryInfo.getLimit()){
			queryInfo.setLimit(10);
		} 
		if(StringUtils.isBlank(queryInfo.getSort())) {
			queryInfo.setOrder("desc");
			queryInfo.setSort("createTime");
		} 
		List<KeywordInfo> list = keywordService.getList(queryInfo);
		Integer total = keywordService.getListCount(queryInfo);
		
		DataGrid<KeywordInfo> dataList = new DataGrid<KeywordInfo>();
		dataList.setRows(list);
		dataList.setTotal(total);

		return dataList;
	}
	
	 /**
     * 新增
     * @param request
     * @param response
     * @param info
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public ResultInfo addKeyword(HttpServletRequest request, HttpServletResponse response,@RequestBody KeywordBean bean) {
        logger.info("开始添加关键字信息[data:" + bean + "]");
        UserManagerLoginBean userBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
        try {
            if (userBean != null) {
            	Long userId = userBean.getUserManagerId();
            	bean.setUserId(userId);
            	if(keywordService.validateKeyword(bean) == true) {
            		return new ResultInfo(-1, "", "关键字已存在");
            	}else {
	            	keywordService.insertKeyword(bean);
	                DefaultKeywordProvider.increase();
	            	return new ResultInfo(0, "", "添加成功");
            	}
    		} else {
    			return new ResultInfo(-1, "", "请登录");
    		}
        } catch (Exception e) {
            return processException(e, logger);
        }
    }
    
    /**
     * 删除
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo deleteKeyword(HttpServletRequest request, HttpServletResponse response) {
        logger.info("开始删除关键字");
        String wordId = request.getParameter("wordId");
        if (wordId == null || !wordId.matches("^\\d+$")) {
            logger.error("传入参数错误");
            return new ResultInfo(-1, "111", getMessage("111"));
        }
        try {
        	KeywordBean bean = new KeywordBean();
        	bean.setWordId(wordId);
            keywordService.deleteByPrimaryKey(bean); 
            DefaultKeywordProvider.increase();
            return new ResultInfo(0, "", "删除成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return processException(e, logger);
        }
    }
    
//    /**
//     * 导入
//     * @param request
//     * @param response
//     * @param info
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/importKeyword", method = RequestMethod.POST,consumes = "application/json")
//    @ResponseBody
//    public ResultInfo importKeyword(HttpServletRequest request, HttpServletResponse response, @RequestBody KeywordBean bean ) { 
//        UserManagerLoginBean userBean = (UserManagerLoginBean) request.getSession().getAttribute("user");
////        String fileName = request.getParameter("fileName");
////        System.out.println("fileName:" +fileName);
//        System.out.println("fileName:" + bean.getFileName());
//        String fileName = bean.getFileName();
//        try {
//            if (userBean != null) {
//            	Long userId = userBean.getUserManagerId();
//            	bean.setUserId(userId);
////            	  RandomAccessFile accessFile = new RandomAccessFile(fileName, "r");
////                  while (null != (str = accessFile.readLine())) {// 每次读取一行
////                      System.out.println(str);
////                  } 
////                  accessFile.close();
////            	if(keywordService.validateKeyword(bean) == true) {
////            		return new ResultInfo(-1, "", "关键字已存在");
////            	}else {
////	            	keywordService.insertKeyword(bean);
//	            	return new ResultInfo(0, "", "导入完成");
////            	}
//    		} else {
//    			return new ResultInfo(-1, "", "请登录");
//    		}
//        } catch (Exception e) {
//            return processException(e, logger);
//        }
//    }
    
    /**
     * Upload files result info.
     *
     * @param request  the request
     * @param file     the file
     * @param response the response
     * @param model    the model
     * @return the result info
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultInfo uploadFiles(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file,
                                          HttpServletResponse response, Model model) {

        try {
            UserManagerLoginBean userManagerLoginBean = (UserManagerLoginBean) request.getSession().getAttribute("user"); 
            
            InputStreamReader reader = null;
//            if(file.getContentType().equals("text/plain")) {
//            	reader = new InputStreamReader(file.getInputStream(), "GB2312");  
//            } else if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
//            	reader = new InputStreamReader(file.getInputStream(), "GBK");  
//            } else{application/msword
//            	reader = new InputStreamReader(file.getInputStream(), "UTF-8");  
//            }
//            reader = new InputStreamReader(file.getInputStream(), "GBK");  
            if(file.getContentType().equals("text/plain")) {
            	reader = new InputStreamReader(file.getInputStream(), "GB2312");  
            } else {
            	return new ResultInfo(-1, "0", "文件格式不对，请导入TXT文件");
            }
			BufferedReader read  = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
			String s=null;  
			while((s=read.readLine())!=null) 
			{ 
				System.out.println(s);  
			} 
//            CommonsMultipartFile cf= (CommonsMultipartFile)file; //这个myfile是MultipartFile的
//            DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
//            File file2 = fi.getStoreLocation();
//            
//            String str = ""; 
//      	  	RandomAccessFile accessFile = new RandomAccessFile(file2, "r");
//      	  	while (null != (str = accessFile.readLine())) {// 每次读取一行
//	            System.out.println(str);
//	        } 
//	        accessFile.close();
//            String url = Constant.axpurl.get("resource_upload_serv");
//            System.out.println("url:" + url);
//            // 定义反序列化 数据格式
//            final TypeReference<ResultInfo<?>> TypeRef = new TypeReference<ResultInfo<?>>() {
//            };
//
//            ResultInfo<?> data = (ResultInfo<?>) HttpClientUtils.post(url, map, TypeRef);
//            System.out.println(data.getData());
//            // 返回参数赋值
//            if (data.getSuccess() < 0) {
//                return new ResultInfo(-1, "0", "调用AXP接口失败", "");
//            }
        	return new ResultInfo(0, "", "导入完成");
        } catch (Exception e) {
//            logger.error(e.getMessage());
        	e.printStackTrace();
        }
        return new ResultInfo(-1, "0", "调用AXP接口失败");
    }
    
    
    
}
