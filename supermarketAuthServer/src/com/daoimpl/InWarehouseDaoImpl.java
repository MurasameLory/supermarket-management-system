package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.InWarehouse;
import com.dao.InWarehouseDao;

public class InWarehouseDaoImpl extends GenericDaoImpl<InWarehouse, Integer> implements InWarehouseDao{

	private static final long serialVersionUID = 1L;

	public InWarehouseDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(InWarehouse t) {
		return new Object[] {t.getId(),t.getOid(),t.getDid(),t.getWareName(),t.getJoinTime()
				,t.getWeight(),t.getRemark()};
	}

	@Override
	protected InWarehouse getObject(ResultSet rs) throws SQLException {
		InWarehouse t = new InWarehouse();
		t.setId(rs.getInt("id"));
		t.setOid(rs.getString("oid"));
		t.setDid(rs.getInt("did"));
		t.setWareName(rs.getString("wareName"));
		t.setJoinTime(rs.getString("joinTime"));
		t.setWeight(rs.getFloat("weight"));
		t.setRemark(rs.getString("remark"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_inwarehouse(oid,did,wareName,joinTime,weight,remark) "
				+ "values(?,?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_inwarehouse";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_inwarehouse where id="+id;
	}

	@Override
	protected String getSearchSQL(InWarehouse t) {
		StringBuffer sql = new StringBuffer("select * from tb_inwarehouse where 1=1");
		if(t.getOid()!=null && !"".equals(t.getOid())) {
			sql.append(" and oid like '%"+t.getOid()+"%'");
		}
		if(t.getWareName()!=null && !"".equals(t.getWareName())) {
			sql.append(" and wareName like '%"+t.getWareName()+"%'");
		}
		if(t.getJoinTime()!=null && !"".equals(t.getJoinTime())) {
			sql.append(" and joinTime like '%"+t.getJoinTime()+"%'");	
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_inwarehouse set oid=?,did=?,wareName=?,joinTime=?,weight=?,remark=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_inwarehouse where id="+id;
	}

}
