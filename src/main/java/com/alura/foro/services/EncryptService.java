package com.alura.foro.services;

public interface EncryptService {

	String encryptPassword(String password);
	
	boolean verifyPassword(String originalPassword, String hashPassword);
}
