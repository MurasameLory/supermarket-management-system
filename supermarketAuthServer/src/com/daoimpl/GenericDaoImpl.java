package com.daoimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.util.DBUtil;
import com.common.util.TokenManager;
import com.common.util.TokenUnvalidException;
import com.dao.GenericDao;

public abstract class GenericDaoImpl<T,P> extends UnicastRemoteObject implements GenericDao<T, P> {
	private static final long serialVersionUID = 1L;

	public GenericDaoImpl() throws RemoteException {
		super();
	}

	@Override
	public void add(T t, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		try {
			Connection conn = DBUtil.getConnection();
			Object[] objects = getObjects(t);
			PreparedStatement ps = conn.prepareStatement(getInsertSQL());
			for(int i=1;i<objects.length;i++) {
				ps.setObject(i, objects[i]);
			}
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object insert(T t, String token) throws RemoteException, TokenUnvalidException{
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		Object id = null;
		try {
			Connection conn = DBUtil.getConnection();
			Object[] objects = getObjects(t);
			PreparedStatement ps = conn.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
			for(int i=1;i<objects.length;i++) {
				ps.setObject(i, objects[i]);
			}
			ps.executeUpdate();
			ResultSet rs= ps.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getObject(1);
			}
			ps.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<T> list(String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<T> list = new ArrayList<T>();
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getListSQL());
			while(rs.next()){
				T obj = getObject(rs);
				list.add(obj);
			}
			rs.close();
			stmt.close();
			conn.close();		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public T get(P id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		T obj = null;
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getSelectByIdSQL(id));
			while(rs.next()){
				obj = getObject(rs);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<T> search(T t, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		List<T> list = new ArrayList<T>();
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getSearchSQL(t));
			while(rs.next()){
				T obj = getObject(rs);
				list.add(obj);
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void update(T t, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(getUpdateSQL());
			Object[] objects = getObjects(t);
			int i;
			for(i=1; i<objects.length; i++) {
				ps.setObject(i, objects[i]);
			}
			ps.setObject(i, objects[0]);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(P id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.instance.verify(token)) {
			throw new TokenUnvalidException();
		}
		try {
			Connection conn = DBUtil.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(getDeleteSQL(id));
			stmt.close();
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract Object[] getObjects(T t);
	protected abstract T getObject(ResultSet rs) throws SQLException;
	protected abstract String getInsertSQL();
	protected abstract String getListSQL();
	protected abstract String getSelectByIdSQL(P id);
	protected abstract String getSearchSQL(T t);
	protected abstract String getUpdateSQL();
	protected abstract String getDeleteSQL(P id);	
}
