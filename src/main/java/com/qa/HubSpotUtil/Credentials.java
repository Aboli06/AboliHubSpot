package com.qa.HubSpotUtil;

public class Credentials {
	
	String appUserName;
	String appPassword;
	
	public Credentials(String appUserName , String appPassword ) {
		this.appPassword=appPassword;
		this.appUserName = appUserName;
		
	}
	
	public String getappUserName() {
		return appUserName;
	}
	
	public void setappUserName(String appUserName) {
		this.appUserName= appUserName;
		
	}
	
	public String getappPassword() {
		return appPassword;
	}

	
	public void setappPassword(String appPassword ) {
		this.appPassword = appPassword;
		
		
	}
}
