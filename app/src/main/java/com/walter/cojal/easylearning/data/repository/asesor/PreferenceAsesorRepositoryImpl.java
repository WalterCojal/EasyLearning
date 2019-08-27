package com.walter.cojal.easylearning.data.repository.asesor;

import com.walter.cojal.easylearning.data.Entities.Asesor;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceAsesorRepositoryImpl implements IPreferenceAsesorRepository {

    @Inject
    SavePreferences savePreferences;

    @Override
    public void saveAsesor(Asesor asesor) {
        savePreferences.saveAsesor(Constant.KEY_ASESOR, asesor);
    }

    @Override
    public Asesor getAsesor() {
        return savePreferences.getAsesor(Constant.KEY_ASESOR);
    }

    @Override
    public void deleteAsesor() {
        if (savePreferences.existsPreference(Constant.KEY_ASESOR)) {
            savePreferences.removePreference(Constant.KEY_ASESOR);
        }
    }
}
