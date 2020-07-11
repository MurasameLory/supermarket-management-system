package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Purchase;
import com.dao.PurchaseDao;

public class PurchaseDaoImpl extends GenericDaoImpl<Purchase, Integer> implements PurchaseDao{

	private static final long serialVersionUID = 1L;

	public PurchaseDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Purchase t) {
		return new Object[] {t.getId(),t.getsName(),t.getOid(),t.getConsignmentDate(),t.getBaleName(),t.getMount(),t.getMoney()};
	}

	@Override
	protected Purchase getObject(ResultSet rs) throws SQLException {
		Purchase t = new Purchase();
		t.setId(rs.getInt("id"));
		t.setsName(rs.getString("sName"));
		t.setOid(rs.getString("oid"));
		t.setConsignmentDate(rs.getString("consignmentDate"));
		t.setBaleName(rs.getString("baleName"));
		t.setMount(rs.getString("mount"));
		t.setMoney(rs.getFloat("money"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_purchase(sName,oid,consignmentDate,baleName,mount,money) "
				+ "values(?,?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_purchase";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_purchase where id="+id;
	}

	@Override
	protected String getSearchSQL(Purchase t) {
		StringBuffer sql = new StringBuffer("select * from tb_purchase where 1=1");
		if(t.getsName()!=null && !"".equals(t.getsName())){
			sql.append(" and sName like '%"+t.getsName()+"%'");
		}
		if(t.getOid()!=null && !"".equals(t.getOid())) {
			sql.append(" and oid like '%"+t.getOid()+"%'");
		}
		if(t.getConsignmentDate()!=null && !"".equals(t.getConsignmentDate())) {
			sql.append(" and consignmentDate like '%"+t.getConsignmentDate()+"%'");
		}
		if(t.getBaleName()!=null && !"".equals(t.getBaleName())) {
			sql.append(" and baleName like '%"+t.getBaleName()+"%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_purchase set sName=?,oid=?,consignmentDate=?,baleName=?,mount=?,money=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_purchase where id="+id;
	}

}
