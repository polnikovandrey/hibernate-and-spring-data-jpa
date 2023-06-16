package com.mcfly.creditcard.services;

public interface EncryptionService {

    String encrypt(String text);

    String decrypt(String encrypted);

}
