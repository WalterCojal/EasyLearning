package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Assessor;

public interface IPreferenceAssessorRepository {

    void saveAsesor(Assessor assessor);
    Assessor getAsesor();
    void deleteAsesor();

}
