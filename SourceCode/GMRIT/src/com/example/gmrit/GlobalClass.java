package com.example.gmrit;
import android.app.Application;

public class GlobalClass extends Application{
	public String UserName;
	public String Password;
	
	
	public String GetUsername()
	{
		return UserName;
	}
	
	public void Setusername(String _susername)
	{
		UserName=_susername;
		
	}
	
	public String GetPassword()
	{
		return Password;
	}
	
	public void Setpassword(String _spassword)
	{
		Password=_spassword;
		
	}
	
	
}
