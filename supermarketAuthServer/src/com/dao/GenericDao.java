package com.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.common.util.TokenUnvalidException;

public interface GenericDao<T, P> extends Remote {
	public void add(T t, String token) throws RemoteException, TokenUnvalidException;
	
	public Object insert(T t, String token) throws RemoteException, TokenUnvalidException;

	public List<T> list(String token) throws RemoteException, TokenUnvalidException;

	public T get(P id, String token) throws RemoteException, TokenUnvalidException;

	public List<T> search(T t, String token) throws RemoteException, TokenUnvalidException;

	public void update(T t, String token) throws RemoteException, TokenUnvalidException;

	public void delete(P id, String token) throws RemoteException, TokenUnvalidException;
}