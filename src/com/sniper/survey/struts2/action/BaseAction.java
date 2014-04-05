package com.sniper.survey.struts2.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	private static final long serialVersionUID = 1L;

	public T model;

	public BaseAction() {
		// 得到泛型话的超类，
		Type type = this.getClass().getGenericSuperclass();
		// 如果type集成与ParameterizedType,以为内ParameterizedType可以带有参数，可以从他里面获取参数
		Type[] args = null;
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 获取参数列表
			args = parameterizedType.getActualTypeArguments();
			if (args != null && args.length > 0) {
				// 获取地一个参数
				Type arg = args[0];
				if (arg instanceof Class) {
					Class clazz =  (Class<T>) arg;
					try {
						model = (T) clazz.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 以上简单写法
		// ParameterizedType Type = (ParameterizedType)
		// this.getClass().getGenericSuperclass();;
		// clazz = (Class<T>) Type.getActualTypeArguments()[0];
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public T getModel() {
		return model;
	}
	
	// 定义基本的CURD操作
	
}
