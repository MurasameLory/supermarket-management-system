package com.daoimpl;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.Contact;
import com.dao.ContactDao;

public class ContactDaoImpl extends GenericDaoImpl<Contact, Integer> implements ContactDao{
	private static final long serialVersionUID = 1L;
	
	public ContactDaoImpl() throws RemoteException {
		super();
	}

	@Override
	protected Object[] getObjects(Contact t) {
		return new Object[] {t.getId(),t.getHid(),t.getContact(),t.getOfficePhone(),t.getFax(),t.getEmail(),t.getFaddress()};
	}

	@Override
	protected Contact getObject(ResultSet rs) throws SQLException {
		Contact t = new Contact();
		t.setId(rs.getInt("id"));
		t.setHid(rs.getInt("hid"));
		t.setContact(rs.getString("contact"));
		t.setOfficePhone(rs.getString("officePhone"));
		t.setFax(rs.getString("fax"));
		t.setEmail(rs.getString("email"));
		t.setFaddress(rs.getString("faddress"));
		return t;
	}

	@Override
	protected String getInsertSQL() {
		return "insert into tb_contact(hid,contact,officePhone,fax,email,faddress) values(?,?,?,?,?,?)";
	}

	@Override
	protected String getListSQL() {
		return "select * from tb_contact";
	}

	@Override
	protected String getSelectByIdSQL(Integer id) {
		return "select * from tb_contact where id="+id;
	}

	@Override
	protected String getSearchSQL(Contact t) {
		StringBuffer sql = new StringBuffer("select * from tb_contact where 1=1");
		if(t.getContact()!=null && !"".equals(t.getContact())) {
			sql.append(" and contact='"+t.getContact()+"'");
		}
		if(t.getHid()!=0) {
			sql.append(" and hid="+t.getHid());
		}
		return sql.toString();
	}

	@Override
	protected String getUpdateSQL() {
		return "update tb_contact set hid=?,contact=?,officePhone=?,fax=?,email=?,faddress=? where id=?";
	}

	@Override
	protected String getDeleteSQL(Integer id) {
		return "delete from tb_contact where hid="+id;
	}

}
