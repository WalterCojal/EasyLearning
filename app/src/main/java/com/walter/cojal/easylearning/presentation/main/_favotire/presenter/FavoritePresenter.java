package com.walter.cojal.easylearning.presentation.main._favotire.presenter;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.main_interactor.favorite_interactor.IFavoriteInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.main._favotire.IFavoriteContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FavoritePresenter implements IFavoriteContract.IPresenter {

    IFavoriteContract.IView view;
    Disposable disposable;
    IFavoriteInteractor interactor;
    private User user;

    @Inject
    public FavoritePresenter(IFavoriteInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (IFavoriteContract.IView) view;
    }

    @Override
    public void detachView() {
        view = null;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void getFavorites() {
        view.showProgress();
        user = interactor.getUser();
        interactor.getFavorites(user.getId()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.showItems(result.getAssessors());
                    } else {
                        view.showNoItems();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showNoItems();
                    view.showError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void deleteItem(int assessorId, final int position) {
        view.showProgress();
        interactor.removeFavorite(user.getId(), assessorId).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        view.updateItemDeleted(position);
                    } else {
                        view.showError(result.getMessage());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                if (isViewAttached()) {
                    view.hideProgress();
                    view.showError(e.getLocalizedMessage());
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
