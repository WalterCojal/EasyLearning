package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.utility.Constant;
import com.walter.cojal.easylearning.utility.SavePreferences;

import javax.inject.Inject;

public class PreferenceAssessorRepositoryImpl implements IPreferenceAssessorRepository {

    SavePreferences savePreferences;

    @Inject
    public PreferenceAssessorRepositoryImpl(SavePreferences savePreferences) {
        this.savePreferences = savePreferences;
    }

    @Override
    public void saveAsesor(Assessor assessor) {
        savePreferences.saveAsesor(Constant.KEY_ASESOR, assessor);
    }

    @Override
    public Assessor getAsesor() {
        return savePreferences.getAsesor(Constant.KEY_ASESOR);
    }

    @Override
    public void deleteAsesor() {
        if (savePreferences.existsPreference(Constant.KEY_ASESOR)) {
            savePreferences.removePreference(Constant.KEY_ASESOR);
        }
    }
}
