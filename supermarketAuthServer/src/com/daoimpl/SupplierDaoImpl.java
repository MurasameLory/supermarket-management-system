package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Supplier;
import com.dao.SupplierDao;

public class SupplierDaoImpl extends GenericDaoImpl<Supplier, Integer> implements SupplierDao{

	private static final long serialVersionUID = 1L;

	protected SupplierDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Supplier t) {
		return new Object[] {t.getId(),t.getcName(),t.getAddress(),t.getLinkman(),t.getLinkPhone(),t.getFaxes(),t.getPostNum(),t.getBankNum(),t.getNetAddress(),t.getEmailAddress(),t.getRemark()};
	}

	@Override
	protected Supplier getObject(ResultSet rs) throws SQLException {
		Supplier t = new Supplier();
		t.setcName(rs.getString("cName"));
		t.setAddress(rs.getString("address"));
		t.setLinkman(rs.getString("linkman"));
		t.setLinkPhone(rs.getString("linkPhone"));
		t.setFaxes(rs.getString("faxes"));
		t.setPostNum(rs.getString("postNum"));
		t.setBankNum(rs.getString("bankNum"));
		t.setNetAddress(rs.getString("netAddress"));
		t.setRemark(rs.getString("remark"));		
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_supplier(cName,address,linkman,linkPhone,faxes,postNum,bankNum,netAddress,emailAddress,remark) values(?,?,?,?,?,?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_supplier";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_supplier where id="+id;
	}

	@Override
	protected String getSearchSQL(Supplier t) {
		StringBuffer sql = new StringBuffer("select * from tb_supplier where 1=1");
		if(t.getcName()!=null && !"".equals(t.getcName())) {
			sql.append(" and cName like '%"+t.getcName()+"%'");
		}
		if(t.getAddress()!=null && !"".equals(t.getAddress())) {
			sql.append(" and address like '%"+t.getAddress()+"%'");
		}
		return sql.toString();		
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_supplier set cName=?,address=?,linkman=?,linkPhone=?,faxes=?,postNum=?,bankNum=?,netAddress=?,emailAddress=?,remark=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_supplier where id="+id;
	}

}
