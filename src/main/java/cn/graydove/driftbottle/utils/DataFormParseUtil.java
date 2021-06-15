package cn.graydove.driftbottle.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.graydove.driftbottle.core.bean.Bottle;
import cn.graydove.driftbottle.core.bean.DataTrans;
import cn.graydove.driftbottle.core.bean.User;

/**
 * ������json��ʽ���д���
 * @author HUIHUI
 *
 */
@SuppressWarnings("all")
public class DataFormParseUtil {
	
	private DataFormParseUtil() {
		
	}
	
	/**
	 * �����õ�������
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
			System.out.println("messageת��ʧ��");
		}
		return dt;
	}
	
	/**
	 * ���ݸ�ʽ����Ϊ������׼��
	 * @param obj Ҫ���������
	 * @param service ����ķ���
	 * @return
	 */
	public static String format(Object obj,String service) {
		Gson gson = new Gson();
		DataTrans dt = new DataTrans();
		Object temp = null;
		Class clazz = null;
		
		//���÷�������
		dt.setService(service);
		
		//������������
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
		
		//�������ݵ���
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
		//������������
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
