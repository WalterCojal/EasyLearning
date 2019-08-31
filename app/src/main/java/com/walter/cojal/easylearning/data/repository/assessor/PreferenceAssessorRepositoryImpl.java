package com.walter.cojal.easylearning.data.repository.assessor;

import com.walter.cojal.easylearning.data.entities.Assessor;
import com.walter.cojal.easylearning.utility.Constants;
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
        savePreferences.saveAsesor(Constants.KEY_ASESOR, assessor);
    }

    @Override
    public Assessor getAsesor() {
        return savePreferences.getAsesor(Constants.KEY_ASESOR);
    }

    @Override
    public void deleteAsesor() {
        if (savePreferences.existsPreference(Constants.KEY_ASESOR)) {
            savePreferences.removePreference(Constants.KEY_ASESOR);
        }
    }
}
