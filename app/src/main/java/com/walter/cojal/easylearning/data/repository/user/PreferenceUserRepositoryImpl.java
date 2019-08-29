package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.utility.Constant;
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
        savePreferences.saveUser(Constant.KEY_USER, user);
    }

    @Override
    public User getUser() {
        return savePreferences.getUser(Constant.KEY_USER);
    }

    @Override
    public void deleteUser() {
        if (savePreferences.existsPreference(Constant.KEY_USER)) {
            savePreferences.removePreference(Constant.KEY_USER);
        }
    }
}
