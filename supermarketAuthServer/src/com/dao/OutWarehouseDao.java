package com.dao;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.OutWarehouse;
import com.common.util.TokenManager;
import com.common.util.TokenUnvalidException;
import com.supplier.bean.Supplier;

public interface OutWarehouseDao extends GenericDao<OutWarehouse, Integer> {

	
	
}
