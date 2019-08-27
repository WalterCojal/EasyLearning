package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.Entities.User;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceUserRepositoryImpl implements IPreferenceUserRepository {

    @Inject
    SavePreferences savePreferences;

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
