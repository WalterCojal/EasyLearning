package com.walter.cojal.easylearning.data.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Asesor;

public interface IPreferenceAsesorRepository {

    void saveAsesor(Asesor asesor);
    Asesor getAsesor();
    void deleteAsesor();

}
