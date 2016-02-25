package com.iontrading.frc_e2e.keywords;

import com.iontrading.robotframework.keywords2.FunctionRepository;

public class E2E_VMQ {
	private static E2E_GatewayTrades gwyTradeRef = new E2E_GatewayTrades();
	
	public void vmqLogin(String gatewaySource, String userName, String password,Object[] fvlist) throws Exception {
		gwyTradeRef.performVCMILogin(gatewaySource, userName, password, fvlist);
	}

	public void vmqLogout(String gatewaySource, String password) throws Exception {
		gwyTradeRef.performVCMILogout(gatewaySource, password);
	}
	
	public void setUserInVMQ(String gatewaySource, String password) throws Exception {
		gwyTradeRef.performVCMILogout(gatewaySource, password);
	}
	
	
}
