/**  
* @Title: ListUtils.java
* @package com.rofour.baseball.tools
* @Project: axp-tools
* @Description: (用一句话描述该文件做什么)
* @author wjj
* @date 2016年4月2日 下午10:38:14 
* @version V1.0  
* ──────────────────────────────────
* Copyright (c) 2016, 指端科技.
*/

package com.rofour.baseball.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ListUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author wjj
 * @date 2016年4月2日 下午10:38:14
 *
 */

public class ListUtils {
	/**
	 * 分組依据接口，用于集合分组时，获取分組依据
	 */
	public interface GroupBy<T> {
		T groupby(Object obj);
	}

	/**
	 * 
	 * 分组
	 */
	public static final <T extends Comparable<T>, D> Map<T, List<D>> group(Collection<D> colls, GroupBy<T> gb) {
		if (colls == null || colls.isEmpty()) {
			System.out.println("分组集合不能为空!");
			return null;
		}
		if (gb == null) {
			System.out.println("分组依据接口不能为Null!");
			return null;
		}
		Iterator<D> iter = colls.iterator();
		Map<T, List<D>> map = new HashMap<T, List<D>>();
		while (iter.hasNext()) {
			D d = iter.next();
			T t = gb.groupby(d);
			if (map.containsKey(t)) {
				map.get(t).add(d);
			} else {
				List<D> list = new ArrayList<D>();
				list.add(d);
				map.put(t, list);
			}
		}
		return map;
	}
	
	/** 
     * 深层拷贝 - 需要类继承序列化接口 
     * @param <T> 
     * @param obj 
     * @return 
     * @throws Exception 
     */  
//    @SuppressWarnings("unchecked")  
//    public static <T> T copyImplSerializable(T obj) throws Exception {  
//        ByteArrayOutputStream baos = null;  
//        ObjectOutputStream oos = null;  
//          
//        ByteArrayInputStream bais = null;  
//        ObjectInputStream ois = null;  
//          
//        Object o = null;  
//        //如果子类没有继承该接口，这一步会报错  
//        try {  
//            baos = new ByteArrayOutputStream();  
//            oos = new ObjectOutputStream(baos);  
//            oos.writeObject(obj);  
//            bais = new ByteArrayInputStream(baos.toByteArray());  
//            ois = new ObjectInputStream(bais);  
//  
//            o = ois.readObject();  
//            return (T) o;  
//        } catch (Exception e) {  
//            throw new Exception("对象中包含没有继承序列化的对象");  
//        } finally{  
//            try {  
//                baos.close();  
//                oos.close();  
//                bais.close();  
//                ois.close();  
//            } catch (Exception e2) {  
//                //这里报错不需要处理  
//            }  
//        }  
//    }  
	
	
}
