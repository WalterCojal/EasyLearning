package com.walter.cojal.easylearning.di.scope;

import javax.inject.Qualifier;

public class Qualifiers {

    @Qualifier
    public @interface UiThread {}

    @Qualifier
    public @interface ExecutorThread{}

}
