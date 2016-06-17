package com.person.model;

/**
 * Hello world!
 *
 */

import java.text.MessageFormat;

public class Roles 
{
    private int role_id;
	private String role_type;
	
	public Roles(){}
	
	public Roles(String type){
		role_type=type;
	}
	public void setRole_id(int id){
		role_id = id;
	}
	public int getRole_id(){
		return role_id;
	}
	public void setRole_type(String type){
		role_type=type;
	}
	public String getRole_type(){
		return role_type;
	}
	
	public String toString(){
		return MessageFormat.format("{0} {1} ", this.role_id, this.role_type);
	}
}
