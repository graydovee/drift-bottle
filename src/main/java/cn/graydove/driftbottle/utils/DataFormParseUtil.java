package cn.graydove.driftbottle.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.core.bean.User;

/**
 * 数据以json格式进行传输
 * @author HUIHUI
 *
 */
@SuppressWarnings("all")
public class DataFormParseUtil {
	
	private DataFormParseUtil() {
		
	}
	
	/**
	 * 解析得到的数据
	 * @param info
	 * @return
	 */
	public static DataTrans parse(String info) {
		Gson gson = new Gson();
		DataTrans dt = gson.fromJson(info, DataTrans.class);
		try {
			if("element".equalsIgnoreCase(dt.getType())) {
				String msgStr = dt.getMessage().toString();
				Object obj = gson.fromJson(msgStr, Class.forName(dt.getClazz()));
				dt.setMessage(obj);
			}else if("list".equalsIgnoreCase(dt.getType())){
				String msgStr = dt.getMessage().toString();
				if(dt.getClazz().equals(Bottle.class.getName())) {
					List<Bottle> obj = gson.fromJson(msgStr, new TypeToken<List<Bottle>>() {  
			        }.getType());
					dt.setMessage(obj);
				}else if(dt.getClazz().equals(User.class.getName())){
					List<User> obj = gson.fromJson(msgStr, new TypeToken<List<User>>() {  
			        }.getType());
					dt.setMessage(obj);
				}
				
			}
		} catch (Exception e) {
			System.out.println("message转化失败");
		}
		return dt;
	}
	
	/**
	 * 数据格式化，为传输做准备
	 * @param obj 要传输的数据
	 * @param service 请求的服务
	 * @return
	 */
	public static String format(Object obj,String service) {
		Gson gson = new Gson();
		DataTrans dt = new DataTrans();
		Object temp = null;
		Class clazz = null;
		
		//设置服务类型
		dt.setService(service);
		
		//设置数据类型
		if(obj instanceof List) {
			dt.setType("list");
			List<Object> list = (List<Object>)obj;
			if(list != null&&!list.isEmpty()) {
				temp = list.get(0);
			}
		}else {
			temp = obj;
			dt.setType("element");
		}
		
		//设置数据的类
		if(temp instanceof Bottle) {
			clazz = Bottle.class;
		}else if(temp instanceof User){
			clazz = User.class;
		}else if(temp instanceof Integer){
			clazz = Integer.class;
		}else{
			clazz = Object.class;
		}
		dt.setClazz(clazz.getName());
		//设置数据内容
		dt.setMessage(gson.toJson(obj));
		
		return gson.toJson(dt);
	}
	
	public static <T> T Bean(DataTrans dt){
		Class clazz = null;
		try {
			if("element".equalsIgnoreCase(dt.getType())) {
				clazz = Class.forName(dt.getClazz());
				if(clazz.isInstance(dt.getMessage())) {
					return (T) clazz.cast(dt.getMessage());
				}
			}else if("list".equalsIgnoreCase(dt.getType())){
				return (T)dt.getMessage();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
