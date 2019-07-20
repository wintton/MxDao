package com.mx.mxdao;

import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MxDaoManagerUtil extends MxDbInfo{
	 
	private  String selectvalue = "*";
	private  String wherevalue = "";
	private  String limitvalue = "";
	private  String groupvalue = "";
	private  String ordervalue = "";
	private  List<String> value = new ArrayList<>(); 
	
	 

	public String getSelectvalue() {
		return selectvalue;
	}

	public void setSelectvalue(String selectvalue) {
		this.selectvalue = selectvalue;
	}

	public String getWherevalue() {
		return wherevalue;
	}

	public void setWherevalue(String wherevalue) {
		this.wherevalue = wherevalue;
	}

	public String getLimitvalue() {
		return limitvalue;
	}

	public void setLimitvalue(String limitvalue) {
		this.limitvalue = limitvalue;
	}

	public String getGroupvalue() {
		return groupvalue;
	}

	public void setGroupvalue(String groupvalue) {
		this.groupvalue = groupvalue;
	}

	public String getOrdervalue() {
		return ordervalue;
	}

	public void setOrdervalue(String ordervalue) {
		this.ordervalue = ordervalue;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}
	
	public MxDaoManagerUtil() throws ClassNotFoundException {
		// TODO Auto-generated constructor stub
		Class.forName(driver); // 加载驱动
	}
	
	/**
	 * select语句
	 * @param selectvalue  select语句 示例 " id,name "
	 * @return
	 */
	public  MxDaoManagerUtil Select(String selectvalue) { 
		this.setSelectvalue(selectvalue);
		return this;
	}
	
	/**
	 * @param where where语句  示例：" id = ? and name = ?"
	 * @param wherevalue   条件值 示例： "1,2,3"
	 * @return
	 */
	public  MxDaoManagerUtil Where(String where, String wherevalue) {
		String[] values = wherevalue.split(",");
		List<String> value = Arrays.asList(values); 
		this.setWherevalue(where);
		this.setValue(value);
		return this;
	}
	
	/**
	 * 限制条数语句
	 * @param limit  限制值 示例： " 6,10 "  类似于 limit 6,10
	 * @return
	 */
	public  MxDaoManagerUtil limit(String limit) { 
		this.setLimitvalue(limit);
		return this;
	}
	
	/**
	 * 排序语句
	 * @param oderby  排序语句 示例 " id desc"  等同于 orderby id desc
	 * @return
	 */
	public  MxDaoManagerUtil orderby(String oderby) { 
		this.setOrdervalue(oderby);
		return this;
	}

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public  <T> List<T> find(Class<T> ins) throws Exception {
//		测试代码		
//		String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase();
//		if (wherevalue.length() != 0) {
//			sql += " where " + wherevalue;
//		}
//		if (limitvalue.length() != 0) {
//			sql += " limit " + limitvalue;
//		}
//		if (ordervalue.length() != 0) {
//			sql += " orderby " + ordervalue;
//		}
//		System.out.println("sql: "  + sql);
//		JSONArray jsonObject = new JSONArray();
//		if (value.size() != 0) {
//			for (int j = 0; j < value.size(); j++) {
//				jsonObject.put(value.get(j));
//			}
//		}
//		System.out.println("values: "  + jsonObject.toString());
//		reset();
//		return null;
		 
		List<T> list = new ArrayList<>();
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null;
		if (!conn.isClosed()) {
			String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase();
			if (wherevalue.length() != 0) {
				sql += " where " + wherevalue;
			}
			if (limitvalue.length() != 0) {
				sql += " limit " + limitvalue;
			}
			if (ordervalue.length() != 0) {
				sql += " orderby " + ordervalue;
			}
			pe = conn.prepareStatement(sql);
			if (value.size() != 0) {
				for (int j = 0; j < value.size(); j++) {
					pe.setObject(j + 1, value.get(j));
				}
			}
			ResultSet rSet = pe.executeQuery();
			Field[] fileds = null;
			if (selectvalue.indexOf("*") >= 0) {
				fileds = ins.getFields();
			} else {
				String[] args = selectvalue.split(",");
				fileds = new Field[args.length];
				for (int i = 0; i < args.length; i++) {
					fileds[i] = ins.getDeclaredField(args[i]);
				}
			}

			while (rSet.next()) {
				T s = ins.newInstance();
				for (Field field : fileds) {
					String type = field.getType().getSimpleName();
					if (supportTypeStr.indexOf(type) >= 0) {
						// 字符类型
						Object value = rSet.getObject(field.getName());
						field.set(s, value); 
					}
				}
				list.add(s);
			}
			rSet.close();
		}
		pe.close();
		conn.close();
		reset();
		return list;
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public  boolean exceuteSql(String sql, Object[] args) throws Exception {
		
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null;
		pe = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pe.setObject(i + 1, args[i]);
		}
		int result = pe.executeUpdate();
		pe.close();
		conn.close();
		reset();
		return result != 0;
	}

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public  <T> List<T> findByColumn(Class<T> ins, String column, String value) throws Exception {
//		测试代码
//		String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
//  		System.out.println("sql: "  + sql); 
//		System.out.println("values: "  + value);
//		reset();
//		return null;
		 
		List<T> list = new ArrayList<>();
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null;
		if (!conn.isClosed()) {
			String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
			pe = conn.prepareStatement(sql);
			pe.setObject(1, value);
			ResultSet rSet = pe.executeQuery();
			Field[] fileds = null;
			if (selectvalue.indexOf("*") >= 0) {
				fileds = ins.getFields();
			} else {
				String[] args = selectvalue.split(",");
				fileds = new Field[args.length];
				for (int i = 0; i < args.length; i++) {
					fileds[i] = ins.getDeclaredField(args[i]);
				}
			}
			while (rSet.next()) {
				T s = ins.newInstance();
				for (Field field : fileds) {
					String type = field.getType().getSimpleName();
					if (supportTypeStr.indexOf(type) >= 0) {
						// 字符类型
						Object values = rSet.getObject(field.getName());
						field.set(s, values);
					}
				}
				list.add(s);
			}
			rSet.close();
		}
		pe.close();
		conn.close();
		reset();
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
	public <T> boolean upDateByWhere(Class<T> ins, String update, String values) throws Exception {
//		测试代码
//		StringBuffer sql =  new StringBuffer("update " + ins.getSimpleName().toLowerCase() + " set ");
//		String[] updates = update.split(",");
//		for(String updatecolumn : updates) {
//			sql.append(updatecolumn + "=?,");
//		}
//		sql.deleteCharAt(sql.length() - 1);
//		if (wherevalue.length() != 0) {
//			sql.append(" where " + wherevalue);
//		}
//		System.out.println("sql: "  + sql.toString()); 
//		
//		String[] valus = values.split(",");
//		JSONArray jsonObject = new JSONArray();
//		if (valus.length != 0) {
//			for (int j = 0; j < valus.length; j++) {
//				jsonObject.put(valus[j]);
//			}
//		} 
//		for(int j = 0; j < value.size(); j++) {
//			jsonObject.put(value.get(j));
//		}
//		System.out.println("values: "  + jsonObject.toString()); 
//		reset();
//		return false;
		 
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null; 
		int reult = 0;
		if (!conn.isClosed()) {
			StringBuffer sql =  new StringBuffer("update " + ins.getSimpleName().toLowerCase() + " set ");
			String[] updates = update.split(",");
			for(String updatecolumn : updates) {
				sql.append(updatecolumn + "=?,");
			}
			sql.deleteCharAt(sql.length() - 1);
			if (wherevalue.length() != 0) {
				sql.append(" where " + wherevalue);
			}
			String[] valus = values.split(","); 
			pe = conn.prepareStatement(sql.toString());
			int i = 0;
			for (i = 0; i < valus.length; i++) {
				pe.setObject(i + 1, valus[i]);
			}
			for(int j = i; j < i + value.size(); j++) {
				pe.setObject(j + 1, value.get(j - i));
			}
			reult = pe.executeUpdate();

		}
		pe.close();
		conn.close();
		reset();
		return reult != 0;
	}

	/**
	 * 按条件删除
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public  <T> boolean deleteByWhere(Class<T> ins, String column, String value) throws Exception {
//		测试代码		
//		String sql = "delete from " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
//		System.out.println("sql: "  + sql); 
//		System.out.println("values: "  + value);
//		reset();
//		return false;
		 
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null; 
		int reult = 0;
		if (!conn.isClosed()) {
			String sql = "delete " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
			pe = conn.prepareStatement(sql);
			pe.setObject(1, value);
			reult = pe.executeUpdate();

		}
		pe.close();
		conn.close();
		reset();
		return reult != 0;
	}

	/**
	 * 查找符合条件的对象
	 * 
	 * @param ins
	 * @return
	 * @throws Exception
	 */
	public  <T> T findInsByColumn(Class<T> ins, String column, String value) throws Exception {
//		测试代码	
//		String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
//		System.out.println("sql: "  + sql); 
//		System.out.println("values: "  + value);
//		reset();
//		return null;
		 
		conn = DriverManager.getConnection(sqlurl, user, password);
		PreparedStatement pe = null;
		T s = ins.newInstance();
		if (!conn.isClosed()) {
			String sql = "select " + selectvalue + " from " + ins.getSimpleName().toLowerCase() + " where " + column + "=?";
			pe = conn.prepareStatement(sql);
			pe.setObject(1, value);
			ResultSet rSet = pe.executeQuery();
			Field[] fileds = null;
			if (selectvalue.indexOf("*") >= 0) {
				fileds = ins.getFields();
			} else {
				String[] args = selectvalue.split(",");
				fileds = new Field[args.length];
				for (int i = 0; i < args.length; i++) {
					fileds[i] = ins.getDeclaredField(args[i]);
				}
			}

			if (rSet.next()) {

				for (Field field : fileds) {
					String type = field.getType().getSimpleName();
					if (supportTypeStr.indexOf(type) >= 0) {
						// 字符类型
						Object values = rSet.getObject(field.getName());
						field.set(s,values);
					}
				}

			}
			rSet.close();
		}
		pe.close();
		conn.close();
		reset();
		return s;
	}

	/**
	 * 重置所有参数
	 */
	private  void reset() {
		selectvalue = "*";
		wherevalue = "";
		limitvalue = "";
		groupvalue = "";
		ordervalue = "";
		value = new ArrayList<>();
	}

}
