package com.mx.mxdao;

import java.lang.reflect.Field;

public class MxDaoData {

	/**
	 * 更细所有参数
	 * 
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean updataAll(String keycolumn) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length;// 属性个数
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("update " + ins.getSimpleName() + " set ");
		Object[] args = new Object[size + 1];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			String key = f.getName();// key:得到属性名
			args[i] = f.get(this);
			sqlstr.append(key + "=?,");
		}
		sqlstr.deleteCharAt(sqlstr.length() - 1);
		Field f = ins.getDeclaredField(keycolumn);
		f.setAccessible(true); // 设置些属性是可以访问的
		args[size] = f.get(this);
		if (args[size] == null) {
			return false;
		}
		sqlstr.append(" where " + keycolumn + " = ? ");

		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * 更新哪些列
	 * 
	 * @param columns
	 *            更新列的名称
	 * @param keycolumn
	 *            唯一参数列名称
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean updata(String columns, String keycolumn) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = new Field[columnstr.length];
		for (int j = 0; j < columnstr.length; j++) {
			fileds[j] = ins.getDeclaredField(columnstr[j]);
		}
		int size = fileds.length;// 属性个数
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("update " + ins.getSimpleName() + " set ");
		Object[] args = new Object[size + 1];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			String key = f.getName();// key:得到属性名
			sqlstr.append(key + "=? ");
			args[i] = f.get(this);
		}
		Field f = ins.getDeclaredField(keycolumn);
		f.setAccessible(true); // 设置些属性是可以访问的
		sqlstr.append(" where " + keycolumn + " = ? ");
		args[size] = f.get(this);
		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * 插入一条记录
	 * 
	 * @param columns
	 *            插入值的列名称
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean insert(String columns) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = new Field[columnstr.length];
		for (int j = 0; j < columnstr.length; j++) {
			fileds[j] = ins.getDeclaredField(columnstr[j]);
		}
		int size = fileds.length;// 属性个数
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			args[i] = f.get(this);
			String key = f.getName();// key:得到属性名
			sqlstr.append(key + ",");
			valuename.append("?,");
		}
		sqlstr.deleteCharAt(sqlstr.length() - 1);
		sqlstr.append(")");
		valuename.deleteCharAt(valuename.length() - 1);
		valuename.append(")");
		sqlstr.append(valuename);
		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * 插入一条数据 不插入某些值
	 * 
	 * @param columns
	 *            不插入的列名
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean insertNoColumns(String columns) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length - columnstr.length;// 属性个数
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			String key = f.getName();// key:得到属性名
			if (columns.indexOf(key) >= 0) {
				continue;
			}
			args[i] = f.get(this);
			sqlstr.append(key + ",");
			valuename.append("?,");
		}
		sqlstr.deleteCharAt(sqlstr.length() - 1);
		sqlstr.append(")");
		valuename.deleteCharAt(valuename.length() - 1);
		valuename.append(")");
		sqlstr.append(valuename);
		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * 插入所有的值
	 * 
	 * @return 是否成功
	 * @throws Exception
	 */
	public boolean insert() throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length;// 属性个数
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			args[i] = f.get(this);
			String key = f.getName();// key:得到属性名
			sqlstr.append(key + ",");
			valuename.append("?,");

		}
		sqlstr.deleteCharAt(sqlstr.length() - 1);
		sqlstr.append(")");
		valuename.deleteCharAt(valuename.length() - 1);
		valuename.append(")");
		sqlstr.append(valuename);
		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param columns
	 *            更新列的名称
	 * @param keycolumn
	 *            唯一参数列名称
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String column) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field filed = ins.getDeclaredField(column);
		Object[] args = new Object[1];

		filed.setAccessible(true); // 设置些属性是可以访问的
		args[0] = filed.get(this);
		String sqlstr = "delete from " + ins.getSimpleName() + " where " + column + " = ?";
		boolean result = MxDaoManager.exceuteSql(sqlstr, args);
		return result;
	}

}
