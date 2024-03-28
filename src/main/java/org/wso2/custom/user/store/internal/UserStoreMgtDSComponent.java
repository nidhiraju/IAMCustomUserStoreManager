package org.wso2.custom.user.store.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.custom.user.store.CustomUserStoreManager;

/**
 * This class implements the OSGI Service component for Custom User Store Manager.
 */
/*
 * @Component( name = "sample.custom.user.store.manager.dscomponent.mine",
 * immediate = true )
 */

public class UserStoreMgtDSComponent {

    private static Log log = LogFactory.getLog(UserStoreMgtDSComponent.class);
    private static RealmService realmService;
    @Activate
    protected void activate(ComponentContext ctxt) {

    	CustomUserStoreManager customUserStoreManager = new CustomUserStoreManager();
        ctxt.getBundleContext().registerService(UserStoreManager.class.getName(),customUserStoreManager, null);
        log.info("CustomUserStoreManager bundle activated successfully..");
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        if (log.isDebugEnabled()) {
            log.debug("Custom User Store Manager is deactivated ");
        }
    }

	/*
	 * @Reference( name = "RealmService", service =
	 * org.wso2.carbon.user.core.service.RealmService.class, cardinality =
	 * ReferenceCardinality.MANDATORY, policy = ReferencePolicy.DYNAMIC, unbind =
	 * "unsetRealmService")
	 */

    protected void setRealmService(RealmService rlmService) {
    	realmService = rlmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        realmService = null;
    }
}