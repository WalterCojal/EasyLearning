package com.walter.cojal.easylearning.presentation.main._favotire;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.presentation.IBasePresenter;
import com.walter.cojal.easylearning.presentation.IBaseView;

import java.util.ArrayList;

public interface IFavoriteContract {

    interface IView extends IBaseView {
        void showItems(ArrayList<Assessor> items);
        void showNoItems();
        void updateItemDeleted(int position);
    }

    interface IPresenter extends IBasePresenter {
        void getFavorites();
        void deleteItem(int assessorId);
    }

}
