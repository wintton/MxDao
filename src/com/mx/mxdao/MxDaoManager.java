package com.mx.mxdao;

import java.util.Arrays;
import java.util.List;

public class MxDaoManager {
 
	private static MxDaoManagerUtil instance;

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> find(Class<T> ins) throws Exception {
		List<T> list = instance.find(ins); 
		return list;
	}
	
	/**
	 * select语句
	 * @param selectvalue  select语句 示例 " id,name "
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static MxDaoManagerUtil Select(String selectvalue) throws ClassNotFoundException {
		if (instance == null) {
			instance = new MxDaoManagerUtil();
		}
		instance.setSelectvalue(selectvalue);
		return instance;
	}
	
	/**
	 * @param where where语句  示例：" id = ? and name = ?"
	 * @param wherevalue   条件值 示例： "1,2,3"
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static MxDaoManagerUtil Where(String where, String wherevalue) throws ClassNotFoundException {
		String[] values = wherevalue.split(",");
		List<String> value = Arrays.asList(values);
		if (instance == null) {
			instance = new MxDaoManagerUtil();
		}
		instance.setWherevalue(where);
		instance.setValue(value);
		return instance;
	}
	
	/**
	 * 限制条数语句
	 * @param limit  限制值 示例： " 6,10 "  类似于 limit 6,10
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static MxDaoManagerUtil limit(String limit) throws ClassNotFoundException {
		if (instance == null) {
			instance = new MxDaoManagerUtil();
		}
		instance.setLimitvalue(limit);
		return instance;
	}
	
	/**
	 * 排序语句
	 * @param oderby  排序语句 示例 " id desc"  等同于 orderby id desc
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static MxDaoManagerUtil orderby(String oderby) throws ClassNotFoundException {
		if (instance == null) {
			instance = new MxDaoManagerUtil();
		}
		instance.setOrdervalue(oderby);
		return instance;
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public static boolean exceuteSql(String sql, Object[] args) throws Exception {
		boolean result = instance.exceuteSql(sql, args);
		return result;
	}

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> findByColumn(Class<T> ins, String column, String value) throws Exception {
		List<T> list = instance.findByColumn(ins, column, value); 
		return list;
	}

	/**
	 * 按条件更新
	 * 
	 * @param ins    更新数据 "id=?,usernam=?"
	 * @param values 数据"1,2,3,4"
	 * @return
	 * @throws Exception
	 */
	public static <T> boolean upDateByWhere(Class<T> ins, String update, String values) throws Exception {
		boolean reult = instance.upDateByWhere(ins, update, values); 
		return reult;
	}

	/**
	 * 按条件删除
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static <T> boolean deleteByWhere(Class<T> ins, String column, String value) throws Exception {
		boolean reult = instance.deleteByWhere(ins, column, value); 
		return reult;
	}

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public static <T> T findInsByColumn(Class<T> ins, String column, String value) throws Exception {
		T s = instance.findInsByColumn(ins, column, value); 
		return s;
	}

	 

}
