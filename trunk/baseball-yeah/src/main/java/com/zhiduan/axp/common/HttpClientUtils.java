package com.zhiduan.axp.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;

/**
* @ClassName: HttpClientUtils
* @Description: HttpClientUtils
* @author wdx
* @date 2016年4月10日 下午9:45:33 
*
*/
    
public class HttpClientUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * @Description: post方式请求axpApi
	 * @param url
	 * @param paraMap
	 * @param type
	 * @return Object
	 * @throws IOException 
	 */
	    
	public static Object post(String url, Object paraMap,TypeReference<?> type) throws IOException{
		
		logger.debug("Http Post Url:"+url);

		//序列化成 json
		String data=JsonUtils.translateToJson(paraMap);
		//准备发送的参数
		Map<String,String> paramMap = new HashMap<String,String>();

		paramMap.put("timestamp", System.currentTimeMillis()+"");
		paramMap.put("data", data);
		paramMap.put("appid", Constant.appid);
		paramMap.put("format", Constant.format);
		paramMap.put("version", Constant.version);
		
	    //生成签名
		String sign=SignUtils.getMd5Sign(paramMap,Constant.appkey);
		paramMap.put("sign", sign);
		
		//http post 参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<Map.Entry<String,String>> $it = paramMap.entrySet().iterator(); 
	    while($it .hasNext()){
	    	Map.Entry<String,String>  entry=$it.next();
	        params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
	    }
	    
		logger.debug("Http Post Para:"+paramMap);
		
	    //http post 请求
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
		CloseableHttpResponse response = httpclient.execute(httpPost);

		int status=response.getStatusLine().getStatusCode();
		
		logger.debug("Http Post Status:"+status);
		
		if(status!=200)
			return null;

		try {
			//返回结果 格式转换
			String result = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
			logger.debug("Http Post Result:"+result);
			return JsonUtils.readValueByType(result, type);
		} finally {
		    response.close();
		}
	}
	
//public static Object httpPost(String url, Object paraMap,TypeReference<?> type) throws JsonGenerationException, JsonMappingException, IOException {
//		
//		HttpPost httppost = new HttpPost(url);
//		
//
//
//
//		//序列化成 json
//		String data=JsonUtils.translateToJson(paraMap);
//
//		
//		
//		//准备发送的参数
//		Map<String,String> paramMap = new HashMap<String,String>();
//		
//	    //生成签名
//		String sign=SignUtils.getMd5Sign(paramMap,Constant.appkey);
//		paramMap.put("sign", sign);
//		paramMap.put("timestamp", System.currentTimeMillis()+"");
//		paramMap.put("data", data);
//		paramMap.put("appid", Constant.appid);
//		paramMap.put("format", Constant.format);
//		paramMap.put("version", Constant.version);
//		
//		
//
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		Iterator<Map.Entry<String,String>> $it = paramMap.entrySet().iterator(); 
//	    while($it .hasNext()){
//	    	Map.Entry<String,String>  entry=$it.next();
//	        params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
//	    }
//		httppost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
//		HttpResponse response = HttpClients.createDefault().execute(httppost);
//		//返回结果
//		if (response.getStatusLine().getStatusCode() == 200) {
//			String result = EntityUtils.toString(response.getEntity());
//			
//			return JsonUtils.readValueByType(result, type);
//		}
//
//	
//		return null;
//	}
}



