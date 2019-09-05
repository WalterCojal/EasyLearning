package com.walter.cojal.easylearning.data.repository.auth;

import com.walter.cojal.easylearning.utility.Constants;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceAuthRepositoryImpl implements IPreferenceAuthRepository {

    SavePreferences savePreferences;

    @Inject
    public PreferenceAuthRepositoryImpl(SavePreferences savePreferences) {
        this.savePreferences = savePreferences;
    }

    @Override
    public void saveToken(String token) {
        savePreferences.saveString(Constants.API_TOKEN, token);
    }

    @Override
    public void saveUserId(int userId) {
        savePreferences.saveString(Constants.USER_ID, String.valueOf(userId));
    }

    @Override
    public void logout() {
        savePreferences.clearPreferences();
    }
}
