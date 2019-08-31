package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.utility.Constants;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceUserRepositoryImpl implements IPreferenceUserRepository {

    SavePreferences savePreferences;

    @Inject
    public PreferenceUserRepositoryImpl(SavePreferences savePreferences) {
        this.savePreferences = savePreferences;
    }

    @Override
    public void saveUser(User user) {
        savePreferences.saveUser(Constants.KEY_USER, user);
    }

    @Override
    public User getUser() {
        return savePreferences.getUser(Constants.KEY_USER);
    }

    @Override
    public void deleteUser() {
        if (savePreferences.existsPreference(Constants.KEY_USER)) {
            savePreferences.removePreference(Constants.KEY_USER);
        }
    }
}
