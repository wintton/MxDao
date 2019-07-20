package com.mx.mxdao;

import java.util.ArrayList;
import java.util.List;

public class Ins extends MxDaoData {
	private int id;
	private String data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String toString() {
		return id + ":" + data;
	}

	public static void main(String[] args) {
		Ins ins = new Ins();
		ins.setData("da");
		ins.setId(2222);
		Ins ins3 = new Ins();
		ins3.setData("daa");
		ins3.setId(22223333);
		List<Ins> ins2 = new ArrayList<>();
		ins2.add(ins);
		ins2.add(ins3);
		try {

			MxDaoManager.Select("id").find(Ins.class);
			MxDaoManager.Where("id=?", "1").find(Ins.class);
			MxDaoManager.limit("10").find(Ins.class);
			MxDaoManager.orderby("id").find(Ins.class);
			MxDaoManager.Select("id").Where("id=?", "1").limit("10").orderby("id").find(Ins.class);

			MxDaoManager.findByColumn(Ins.class, "id", "1");
			MxDaoManager.upDateByWhere(Ins.class, "id,name", "1,222");
			MxDaoManager.Where("id=?", "1").upDateByWhere(Ins.class, "id,name", "1,222");
			MxDaoManager.deleteByWhere(Ins.class, "id", "1");

			System.out.println(MxJsonUtil.getJsonIns(0, "√Ë ˆ", ins));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
