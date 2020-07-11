package com.supplier.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.util.DataConnection;
import com.common.util.TokenManager;
import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;

public class SupplierDaoImpl extends UnicastRemoteObject implements SupplierDao {

	private static final long serialVersionUID = 1L;
	
	DataConnection dc = new DataConnection();
	Connection conn = null;

	public SupplierDaoImpl() throws RemoteException {
		super();
	}

	@Override
	public void insertSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		conn = dc.getConnection();
		if(conn==null) return;
		try {
			PreparedStatement ps = conn.prepareStatement("insert into tb_supplier(cName,address,linkman,linkPhone,faxes,postNum,bankNum,netAddress,emailAddress,remark) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, supplier.getcName());
			ps.setString(2, supplier.getAddress());
			ps.setString(3, supplier.getLinkman());
			ps.setString(4, supplier.getLinkPhone());
			ps.setString(5, supplier.getFaxes());
			ps.setString(6, supplier.getPostNum());
			ps.setString(7, supplier.getBankNum());
			ps.setString(8, supplier.getNetAddress());
			ps.setString(9, supplier.getEmailAddress());
			ps.setString(10, supplier.getRemark());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Supplier> selectSupplier(String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = dc.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_supplier");
			while(rs.next()){
				Supplier sp = new Supplier();
				sp.setId(rs.getInt(1));
				sp.setcName(rs.getString("cName"));
				sp.setAddress(rs.getString("address"));
				sp.setLinkman(rs.getString("linkman"));
				sp.setLinkPhone(rs.getString("linkPhone"));
				sp.setFaxes(rs.getString("faxes"));
				sp.setPostNum(rs.getString("postNum"));
				sp.setBankNum(rs.getString("bankNum"));
				sp.setNetAddress(rs.getString("netAddress"));
				sp.setEmailAddress(rs.getString("emailAddress"));
				sp.setRemark(rs.getString("remark"));
				list.add(sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Supplier selectSupplierByID(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		Supplier sp = new Supplier();
		conn = dc.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_supplier where id=" + id);
			while(rs.next()){
				sp.setId(rs.getInt(1));
				sp.setcName(rs.getString("cName"));
				sp.setAddress(rs.getString("address"));
				sp.setLinkman(rs.getString("linkman"));
				sp.setLinkPhone(rs.getString("linkPhone"));
				sp.setFaxes(rs.getString("faxes"));
				sp.setPostNum(rs.getString("postNum"));
				sp.setBankNum(rs.getString("bankNum"));
				sp.setNetAddress(rs.getString("netAddress"));
				sp.setEmailAddress(rs.getString("emailAddress"));
				sp.setRemark(rs.getString("remark"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sp;
	}

	@Override
	public List<Supplier> selectSupplierByAddress(String address, String token)
			throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = dc.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_supplier where address like '%" + address + "%'");
			while(rs.next()){
				Supplier sp = new Supplier();
				sp.setId(rs.getInt(1));
				sp.setcName(rs.getString("cName"));
				sp.setAddress(rs.getString("address"));
				sp.setLinkman(rs.getString("linkman"));
				sp.setLinkPhone(rs.getString("linkPhone"));
				sp.setFaxes(rs.getString("faxes"));
				sp.setPostNum(rs.getString("postNum"));
				sp.setBankNum(rs.getString("bankNum"));
				sp.setNetAddress(rs.getString("netAddress"));
				sp.setEmailAddress(rs.getString("emailAddress"));
				sp.setRemark(rs.getString("remark"));
				list.add(sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Supplier> selectSupplierByName(String name, String token)
			throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = dc.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_supplier where cName like '%" + name + "%'");
			while(rs.next()){
				Supplier sp = new Supplier();
				sp.setId(rs.getInt(1));
				sp.setcName(rs.getString("cName"));
				sp.setAddress(rs.getString("address"));
				sp.setLinkman(rs.getString("linkman"));
				sp.setLinkPhone(rs.getString("linkPhone"));
				sp.setFaxes(rs.getString("faxes"));
				sp.setPostNum(rs.getString("postNum"));
				sp.setBankNum(rs.getString("bankNum"));
				sp.setNetAddress(rs.getString("netAddress"));
				sp.setEmailAddress(rs.getString("emailAddress"));
				sp.setRemark(rs.getString("remark"));
				list.add(sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Supplier> selectSupplierByNameAddress(String address, String name, String token)
			throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = dc.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_supplier where address like '%" + address + "%' and cName like '%"+name+"%'");
			while(rs.next()){
				Supplier sp = new Supplier();
				sp.setId(rs.getInt(1));
				sp.setcName(rs.getString("cName"));
				sp.setAddress(rs.getString("address"));
				sp.setLinkman(rs.getString("linkman"));
				sp.setLinkPhone(rs.getString("linkPhone"));
				sp.setFaxes(rs.getString("faxes"));
				sp.setPostNum(rs.getString("postNum"));
				sp.setBankNum(rs.getString("bankNum"));
				sp.setNetAddress(rs.getString("netAddress"));
				sp.setEmailAddress(rs.getString("emailAddress"));
				sp.setRemark(rs.getString("remark"));
				list.add(sp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		conn = dc.getConnection();
		try {
			String sql="update tb_supplier set cName=?, address=?, linkman=?, linkPhone=?, faxes=?,"
					+ "postNum=?, bankNum=?, netAddress=?, emailAddress=?, remark=? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, supplier.getcName());
			ps.setString(2, supplier.getAddress());
			ps.setString(3, supplier.getLinkman());
			ps.setString(4, supplier.getLinkPhone());
			ps.setString(5, supplier.getFaxes());
			ps.setString(6, supplier.getPostNum());
			ps.setString(7, supplier.getBankNum());
			ps.setString(8, supplier.getNetAddress());
			ps.setString(9, supplier.getEmailAddress());
			ps.setString(10, supplier.getRemark());
			ps.setInt(11, supplier.getId());
			ps.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteSupplier(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		conn = dc.getConnection();
		String sql = "delete from tb_supplier where id="+id;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
