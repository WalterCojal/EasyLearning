package com.walter.cojal.easylearning.data.repository.auth;

public interface IPreferenceAuthRepository {
    void saveToken(String token);
    void saveUserId(int userId);
    void logout();
}
