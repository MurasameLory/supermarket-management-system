package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Duty;
import com.dao.DutyDao;

public class DutyDaoImpl extends GenericDaoImpl<Duty, Integer> implements DutyDao {

	private static final long serialVersionUID = 1L;

	public DutyDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Duty t) {
		return new Object[] {t.getId(),t.getHeadshipName()};
	}

	@Override
	protected Duty getObject(ResultSet rs) throws SQLException {
		Duty t = new Duty();
		t.setId(rs.getInt("id"));
		t.setHeadshipName(rs.getString("headshipName"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_duty(headshipName) values(?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_duty";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_duty where id="+id;
	}

	@Override
	protected String getSearchSQL(Duty t) {
		StringBuffer sql = new StringBuffer("select * from tb_duty where 1=1");
		if(t.getHeadshipName()!=null && !"".equals(t.getHeadshipName())) {
			sql.append(" and headshipName like '%"+t.getHeadshipName()+"%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_duty set headshipName=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_duty where id="+id;
	}

}
