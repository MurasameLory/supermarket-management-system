package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.BasicMessage;
import com.dao.BasicMessageDao;

public class BasicMessageDaoImpl extends GenericDaoImpl<BasicMessage,Integer> implements BasicMessageDao {
	private static final long serialVersionUID = 1L;

	public BasicMessageDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(BasicMessage t) {
		return new Object[] {t.getId(),t.getName(),t.getAge(),t.getSex(),t.getDept(),t.getHeadship()};
	}

	@Override
	protected BasicMessage getObject(ResultSet rs) throws SQLException {
		BasicMessage t = new BasicMessage();
		t.setId(rs.getInt("id"));
		t.setName(rs.getString("name"));
		t.setAge(rs.getInt("age"));
		t.setSex(rs.getString("sex"));
		t.setDept(rs.getInt("dept"));
		t.setHeadship(rs.getInt("headship"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_basicmessage(name,age,sex,dept,headship) values(?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_basicmessage";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_basicmessage where id="+id;
	}

	@Override
	protected String getSearchSQL(BasicMessage t) {
		StringBuffer sql = new StringBuffer("select * from tb_basicmessage where 1=1");
		if(t.getId()!=0) {
			sql.append(" and id="+t.getId());
		}
		if(t.getName()!=null && !"".equals(t.getName())) {
			sql.append(" and name like '%"+t.getName()+"%'");
		}
		if(t.getDept()!=0) {
			sql.append(" and dept="+t.getDept());
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_basicmessage set name=?,age=?,sex=?,dept=?,headship=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_basicmessage where id="+id;
	}

}
