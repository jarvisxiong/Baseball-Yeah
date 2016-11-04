/**  
 * Copyright (c) 2016, 指端科技.
 */


    
package com.rofour.baseball.controller.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rofour.baseball.common.HttprRequestUtils;
import com.rofour.baseball.common.ShortUrl;
import com.rofour.baseball.controller.base.BaseController;
import com.rofour.baseball.controller.model.ResultInfo;

/**
 * @ClassName: ShortUrlController
 * @Description: 短地址转换
 * @author lpp
 * @date 2016年8月29日 上午9:56:47 
 */
@Controller
@RequestMapping(value="/manage/url")
public class ShortUrlController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/getshorturl")
	@ResponseBody
	public String getShortUrl(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入getShortUrl");
		String longurl = StringUtils.trim(request.getParameter("longurl"));
		if(StringUtils.isBlank(longurl)){
			return null;
		}
		String source = ShortUrl.getSource();
		logger.info("source:{}",source);
		String urls = "";
        try {
            urls = HttprRequestUtils.sendGet(ShortUrl.SINA_SHORTEN_URL, "source="+source+"&url_long="+longurl);
            logger.info("获取新浪短地址传回urls:{}",urls);
            JSONArray jsonArray = JSONObject.parseArray(urls);
            if(jsonArray != null){
            	JSONObject jsonObject = jsonArray.getJSONObject(0);
            	if(jsonObject.get("url_short") != null){
            		String shorturl = (String) jsonObject.get("url_short");
            		logger.info("获取短地址url_short:{}",shorturl);
            		return shorturl;
            	}
            }
        } catch (Exception ex) {
            logger.error("从新浪微博获取短地址出错:{}", ex);
        }
        return null;
	}
	
}
