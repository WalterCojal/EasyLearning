package com.walter.cojal.easylearning.data.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Asesor;

public interface IPreferenceAsesorRepository {

    void saveAsesor(String key, Asesor asesor);
    Asesor getAsesor(String key);
    void deleteAsesor(String key);

}
