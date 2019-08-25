package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceUserRepositoryImpl implements IPreferenceUserRepository {

    SavePreferences savePreferences;

    @Inject
    public PreferenceUserRepositoryImpl(SavePreferences savePreferences) {
        this.savePreferences = savePreferences;
    }

    @Override
    public void saveUser(String key, User user) {
        savePreferences.saveUser(key, user);
    }

    @Override
    public User getUser(String key) {
        return savePreferences.getUser(key);
    }

    @Override
    public void deleteUser(String key) {
        if (savePreferences.existsPreference(key)) {
            savePreferences.removePreference(key);
        }
    }
}
