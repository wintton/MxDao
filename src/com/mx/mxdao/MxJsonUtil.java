package com.mx.mxdao;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MxJsonUtil {

	public static JSONObject getJson(int code, String desc) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		jsonObject.put("desc", desc);
		return jsonObject;
	}
	
	/**
	 * 输出泛型list
	 * @param code
	 * @param desc
	 * @param datalist
	 * @return
	 * @throws JSONException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <T> JSONObject getJsonList(int code, String desc, List<T> datalist)
			throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		jsonObject.put("desc", desc);
		JSONArray array = new JSONArray();
		if (datalist.size() <= 0) {
			jsonObject.put("list", "null");
			return jsonObject;
		}
		Field[] fileds = datalist.get(0).getClass().getDeclaredFields();
		for (T t : datalist) {
			JSONObject eachjson = new JSONObject();
			for (Field f : fileds) {
				f.setAccessible(true);
				eachjson.put(f.getName(), f.get(t));
			}
			array.put(eachjson);
		}
		jsonObject.put("list", array);
		return jsonObject;
	}
	
	/**
	 * 输出泛型单个实例
	 * @param code
	 * @param desc
	 * @param data
	 * @return
	 * @throws JSONException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static <E> JSONObject getJsonIns(int code, String desc, E data)
			throws JSONException, IllegalArgumentException, IllegalAccessException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		jsonObject.put("desc", desc);

		Field[] fileds = data.getClass().getDeclaredFields();

		JSONObject eachjson = new JSONObject();
		for (Field f : fileds) {
			f.setAccessible(true);
			eachjson.put(f.getName(), f.get(data));
		}

		jsonObject.put("data", eachjson);
		return jsonObject;
	}
}
