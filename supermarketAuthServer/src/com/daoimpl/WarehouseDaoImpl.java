package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Warehouse;
import com.dao.WarehouseDao;

public class WarehouseDaoImpl extends GenericDaoImpl<Warehouse, Integer> implements WarehouseDao{

	private static final long serialVersionUID = 1L;

	public WarehouseDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Warehouse t) {
		return new Object[] {t.getId(),t.getName(),t.getRemark()};
	}

	@Override
	protected Warehouse getObject(ResultSet rs) throws SQLException {
		Warehouse t = new Warehouse();
		t.setId(rs.getInt("id"));
		t.setName(rs.getString("name"));
		t.setRemark(rs.getString("remark"));		
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_warehouse(name,remark) values(?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_warehouse";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_warehouse where id="+id;
	}

	@Override
	protected String getSearchSQL(Warehouse t) {
		StringBuffer sql = new StringBuffer("select * from tb_warehouse where 1=1");
		if(t.getId()!=0) {
			sql.append(" and id="+t.getId());
		}
		if(t.getName()!=null && !"".equals(t.getName())) {
			sql.append(" and name like '%"+t.getName()+ "%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_warehouse set name=?, remark=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_warehouse where id="+id;
	}

}
