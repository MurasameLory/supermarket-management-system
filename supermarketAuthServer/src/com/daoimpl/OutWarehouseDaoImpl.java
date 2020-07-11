package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.OutWarehouse;
import com.common.util.DataConnection;
import com.common.util.TokenManager;
import com.common.util.TokenUnvalidException;
import com.dao.OutWarehouseDao;
import com.supplier.bean.Supplier;

public class OutWarehouseDaoImpl extends GenericDaoImpl<OutWarehouse, Integer> implements OutWarehouseDao {

	private static final long serialVersionUID = 1L;
	Connection conn = null;
	DataConnection dc = new DataConnection();
	public OutWarehouseDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(OutWarehouse t) {
		return new Object[] {t.getId(),t.getDid(),t.getWareName(),t.getOutDate(),t.getWeight(),t.getRemark()};
	}

	@Override
	protected OutWarehouse getObject(ResultSet rs) throws SQLException {
		OutWarehouse t = new OutWarehouse();
		t.setId(rs.getInt("id"));
		t.setDid(rs.getInt("did"));
		t.setWareName(rs.getString("wareName"));
		t.setOutDate(rs.getString("outDate"));
		t.setWeight(rs.getFloat("weight"));
		t.setRemark(rs.getString("remark"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_outwarehouse(did,wareName,outDate,weight,remark) values(?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_outwarehouse";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_outwarehouse where id="+id;
	}

	@Override
	protected String getSearchSQL(OutWarehouse t) {
		StringBuffer sql = new StringBuffer("select * from tb_outwarehouse where 1=1");
		if(t.getWareName()!=null && !"".equals(t.getWareName())) {
			sql.append(" and wareName like '%"+t.getWareName()+"%'");
		}
		if(t.getDid()!=0) {
			sql.append(" and did="+t.getDid());
		}
		if(t.getOutDate()!=null && !"".equals(t.getOutDate())) {
			sql.append(" and outDate like '%"+t.getOutDate()+"%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_outwarehouse set did=?,warename=?,outDate=?,weight=?,remark=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_outwarehouse where id="+id;
	}
	

}
