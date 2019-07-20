package com.mx.mxdao;

import java.util.Arrays;
import java.util.List;

public class MxDaoManager {
 
	private static MxDaoManagerUtil instance;

	/**
	 * ���ҷ��������Ķ���
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
	 * select���
	 * @param selectvalue  select��� ʾ�� " id,name "
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
	 * @param where where���  ʾ����" id = ? and name = ?"
	 * @param wherevalue   ����ֵ ʾ���� "1,2,3"
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
	 * �����������
	 * @param limit  ����ֵ ʾ���� " 6,10 "  ������ limit 6,10
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
	 * �������
	 * @param oderby  ������� ʾ�� " id desc"  ��ͬ�� orderby id desc
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
	 * ִ��sql���
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
	 * ���ҷ��������Ķ���
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
	 * ����������
	 * 
	 * @param ins    �������� "id=?,usernam=?"
	 * @param values ����"1,2,3,4"
	 * @return
	 * @throws Exception
	 */
	public static <T> boolean upDateByWhere(Class<T> ins, String update, String values) throws Exception {
		boolean reult = instance.upDateByWhere(ins, update, values); 
		return reult;
	}

	/**
	 * ������ɾ��
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
	 * ���ҷ��������Ķ���
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
