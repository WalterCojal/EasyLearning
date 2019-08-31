package com.walter.cojal.easylearning.presentation.main._profile.presenter;

import com.walter.cojal.easylearning.data.entities.Result;
import com.walter.cojal.easylearning.data.entities.User;
import com.walter.cojal.easylearning.domain.main_interactor.profile_interactor.IProfileInteractor;
import com.walter.cojal.easylearning.presentation.IBaseView;
import com.walter.cojal.easylearning.presentation.main._profile.IProfileContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MultipartBody;

public class ProfilePresenter implements IProfileContract.IPresenter {

    IProfileContract.IView view;
    IProfileInteractor interactor;
    Disposable disposable;
    User user;

    @Inject
    public ProfilePresenter(IProfileInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(IBaseView view) {
        this.view = (IProfileContract.IView) view;
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
    public void getAssessorData() {
        view.showProgress();
        user = interactor.getUser();
        interactor.getAssessorData(user.getId())
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Result result) {
                        if (isViewAttached()) {
                            view.hideProgress();
                            view.fillUserData(user);
                            if (result.isSuccess()) {
                                view.fillAssessorData(result.getAssessor());
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

    @Override
    public void updateUserData(String name, String lastName, String email, String phone, int age, String birthDate) {
        if (!view.validate()) return;
        view.showProgress();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAge(age);
        user.setBirthDate(birthDate);
        interactor.updateUserData(user)
                .subscribe(new Observer<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Result result) {
                        if (isViewAttached()){
                            view.hideProgress();
                            if (result.isSuccess()) {
                                interactor.saveUser(user);
                            } else {
                                user = interactor.getUser();
                                view.fillUserData(user);
                            }
                            view.setMainEdition(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            view.hideProgress();
                            view.fillUserData(user);
                            view.showError(e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void updateUserImage(MultipartBody.Part image) {
        view.showProgress();
        interactor.updateUserImage(image, user.getId()).subscribe(new Observer<Result>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Result result) {
                if (isViewAttached()) {
                    view.hideProgress();
                    if (result.isSuccess()) {
                        user = result.getUser();
                        view.fillImage(user.getImage());
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
