package com.dao;

import java.rmi.RemoteException;
import java.util.List;

import com.bean.OutWarehouse;
import com.common.util.TokenUnvalidException;

public interface OutWarehouseDao extends GenericDao<OutWarehouse, Integer> {
	public List<OutWarehouse> selectOutWarehouse(String token) throws RemoteException, TokenUnvalidException;

}
