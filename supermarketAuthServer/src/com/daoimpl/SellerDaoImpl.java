package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Seller;
import com.dao.SellerDao;

public class SellerDaoImpl extends GenericDaoImpl<Seller, Integer> implements SellerDao {

	private static final long serialVersionUID = 1L;

	public SellerDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Seller t) {
		return new Object[] {t.getId(),t.getSellName(),t.getAddress(),t.getLinkman(),t.getLinkPhone(),t.getFaxNum(),t.getPostNum(),t.getBankNum(),t.getNetAddress(),t.getEmailAddress(),t.getRemark()};
	}

	@Override
	protected Seller getObject(ResultSet rs) throws SQLException {
		Seller t = new Seller();
		t.setId(rs.getInt("id"));
		t.setSellName(rs.getString("sellName"));
		t.setAddress(rs.getString("address"));
		t.setLinkman(rs.getString("linkman"));
		t.setLinkPhone(rs.getString("linkphone"));
		t.setFaxNum(rs.getString("faxNum"));
		t.setPostNum(rs.getString("postNum"));
		t.setBankNum(rs.getString("bankNum"));
		t.setNetAddress(rs.getString("netAddress"));
		t.setEmailAddress(rs.getString("emailAddress"));
		t.setRemark(rs.getString("remark"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_seller(sellName,address,linkman,linkphone,faxNum,postNum,bankNum,netAddress,emailAddress,remark) values(?,?,?,?,?,?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_seller";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_seller where id="+id;
	}

	@Override
	protected String getSearchSQL(Seller t) {
		StringBuffer sql =new StringBuffer("select * from tb_seller where 1=1 ");
		if(t.getSellName()!=null && !"".equals(t.getSellName())) {
			sql.append(" and sellName like '%" + t.getSellName() + "%'");
		}
		if(t.getAddress()!=null && !"".equals(t.getAddress())) {
			sql.append(" and address like '%" + t.getAddress() + "%'");
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_seller set sellName=?, address=?, linkman=?, linkphone=?, faxNum=?, postNum=?, bankNum=?, netAddress=?, emailAddress=?, remark=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_seller where id=" +id;
	}

}
