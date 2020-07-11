package com.supplier.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;

public interface SupplierDao extends Remote {
	public void insertSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException;

	public List<Supplier> selectSupplier(String token) throws RemoteException, TokenUnvalidException;

	public Supplier selectSupplierByID(int id, String token) throws RemoteException, TokenUnvalidException;

	public List<Supplier> selectSupplierByAddress(String address, String token)
			throws RemoteException, TokenUnvalidException;

	public List<Supplier> selectSupplierByName(String name, String token) throws RemoteException, TokenUnvalidException;

	public List<Supplier> selectSupplierByNameAddress(String address, String name, String token)
			throws RemoteException, TokenUnvalidException;

	public void updateSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException;

	public void deleteSupplier(int id, String token) throws RemoteException, TokenUnvalidException;
}
