package com.pratik.dao;

public class OTM_DAOFactory {
	
	public static OTM_DAO getInstance(){
		return new OTM_DAOImpl();
	}

}
