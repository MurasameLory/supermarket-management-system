package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Department;
import com.dao.DepartmentDao;

public class DepartmentDaoImpl extends GenericDaoImpl<Department, Integer> implements DepartmentDao{
	private static final long serialVersionUID = 1L;

	public DepartmentDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Department t) {
		return new Object[] {t.getId(), t.getdName(), t.getPrincipal(), t.getBewrite()};
	}

	@Override
	protected Department getObject(ResultSet rs) throws SQLException{
		Department t = new Department();
		t.setId(rs.getInt("id"));
		t.setdName(rs.getString("dName"));
		t.setPrincipal(rs.getString("principal"));
		t.setBewrite(rs.getString("bewrite"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_department(dName, principal, bewrite) values(?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_department";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_department where id="+id;
	}

	@Override
	protected String getSearchSQL(Department t) {
		StringBuffer sql = new StringBuffer("select * from tb_department where 1=1 ");
		if(t.getdName()!=null && !"".equals(t.getdName())){
			sql.append(" and dName like '%" + t.getdName() +"%'");
		}
		if(t.getPrincipal()!=null && !"".equals(t.getPrincipal())) {
			sql.append(" and principal like '%" + t.getPrincipal() +"%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_department set dName=?, principal=?, bewrite=? where id=? ";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_department where id="+id;
	}

}
