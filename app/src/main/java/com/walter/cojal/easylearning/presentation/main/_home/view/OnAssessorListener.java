package com.walter.cojal.easylearning.presentation.main._home.view;

import com.walter.cojal.easylearning.data.entities.Assessor;

public interface OnAssessorListener {

    void itemViewClick(Assessor assessor);
    void itemFavClick(int assessorId, int position);

}
