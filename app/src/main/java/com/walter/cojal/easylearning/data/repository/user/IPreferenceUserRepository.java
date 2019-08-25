package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.Entities.User;

public interface IPreferenceUserRepository {

    void saveUser(String key, User user);
    User getUser(String key);
    void deleteUser(String key);

}
