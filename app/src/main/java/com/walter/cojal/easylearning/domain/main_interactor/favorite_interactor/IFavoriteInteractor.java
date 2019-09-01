package com.walter.cojal.easylearning.domain.main_interactor.favorite_interactor;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;

import io.reactivex.Observable;

public interface IFavoriteInteractor {

    User getUser();
    Observable<Result> getFavorites(int userId);
    Observable<Result> removeFavorite(int userId, int assessorId);

}
