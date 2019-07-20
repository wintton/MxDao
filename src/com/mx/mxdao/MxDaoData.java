package com.mx.mxdao;

import java.lang.reflect.Field;

public class MxDaoData {

	/**
	 * ��ϸ���в���
	 * 
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean updataAll(String keycolumn) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length;// ���Ը���
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("update " + ins.getSimpleName() + " set ");
		Object[] args = new Object[size + 1];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			String key = f.getName();// key:�õ�������
			args[i] = f.get(this);
			sqlstr.append(key + "=?,");
		}
		sqlstr.deleteCharAt(sqlstr.length() - 1);
		Field f = ins.getDeclaredField(keycolumn);
		f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
		args[size] = f.get(this);
		if (args[size] == null) {
			return false;
		}
		sqlstr.append(" where " + keycolumn + " = ? ");

		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * ������Щ��
	 * 
	 * @param columns
	 *            �����е�����
	 * @param keycolumn
	 *            Ψһ����������
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean updata(String columns, String keycolumn) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = new Field[columnstr.length];
		for (int j = 0; j < columnstr.length; j++) {
			fileds[j] = ins.getDeclaredField(columnstr[j]);
		}
		int size = fileds.length;// ���Ը���
		StringBuffer sqlstr = new StringBuffer();
		sqlstr.append("update " + ins.getSimpleName() + " set ");
		Object[] args = new Object[size + 1];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			String key = f.getName();// key:�õ�������
			sqlstr.append(key + "=? ");
			args[i] = f.get(this);
		}
		Field f = ins.getDeclaredField(keycolumn);
		f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
		sqlstr.append(" where " + keycolumn + " = ? ");
		args[size] = f.get(this);
		boolean result = MxDaoManager.exceuteSql(sqlstr.toString(), args);
		return result;
	}

	/**
	 * ����һ����¼
	 * 
	 * @param columns
	 *            ����ֵ��������
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean insert(String columns) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = new Field[columnstr.length];
		for (int j = 0; j < columnstr.length; j++) {
			fileds[j] = ins.getDeclaredField(columnstr[j]);
		}
		int size = fileds.length;// ���Ը���
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			args[i] = f.get(this);
			String key = f.getName();// key:�õ�������
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
	 * ����һ������ ������ĳЩֵ
	 * 
	 * @param columns
	 *            �����������
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean insertNoColumns(String columns) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		String[] columnstr = columns.split(",");
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length - columnstr.length;// ���Ը���
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			String key = f.getName();// key:�õ�������
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
	 * �������е�ֵ
	 * 
	 * @return �Ƿ�ɹ�
	 * @throws Exception
	 */
	public boolean insert() throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field[] fileds = ins.getDeclaredFields();
		int size = fileds.length;// ���Ը���
		StringBuffer sqlstr = new StringBuffer();
		StringBuffer valuename = new StringBuffer(" values (");
		sqlstr.append("insert into " + ins.getSimpleName() + " (");
		Object[] args = new Object[size];
		for (int i = 0; i < fileds.length; i++) {
			Field f = fileds[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			args[i] = f.get(this);
			String key = f.getName();// key:�õ�������
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
	 * ɾ��
	 * 
	 * @param columns
	 *            �����е�����
	 * @param keycolumn
	 *            Ψһ����������
	 * @return
	 * @throws Exception
	 */
	public boolean delete(String column) throws Exception {
		Class<? extends MxDaoData> ins = this.getClass();
		Field filed = ins.getDeclaredField(column);
		Object[] args = new Object[1];

		filed.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
		args[0] = filed.get(this);
		String sqlstr = "delete from " + ins.getSimpleName() + " where " + column + " = ?";
		boolean result = MxDaoManager.exceuteSql(sqlstr, args);
		return result;
	}

}
