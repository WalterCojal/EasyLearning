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

}
