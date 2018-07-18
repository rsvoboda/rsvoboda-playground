package org.experiments.rsvoboda.system;

import javax.inject.Inject;

import javax.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.experiments.rsvoboda.config.Email;

public class SystemConfig {

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_system_inMaintenance")
    Provider<Boolean> inMaintenance;

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_email")
    private Provider<Email> email;


    public boolean isInMaintenance() {
        return inMaintenance.get();
    }

    public Email getEmail() {
        return email.get();
    }

}
