package com.walter.cojal.easylearning.data.repository.user;

import com.walter.cojal.easylearning.data.entities.User;

public interface IPreferenceUserRepository {

    void saveUser(User user);
    User getUser();
    void deleteUser();

}
