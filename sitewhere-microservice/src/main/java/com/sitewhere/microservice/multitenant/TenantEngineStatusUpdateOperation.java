/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.microservice.multitenant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine;
import com.sitewhere.spi.microservice.multitenant.ITenantEngineStatusUpdateOperation;

import io.sitewhere.k8s.crd.common.BootstrapState;
import io.sitewhere.k8s.crd.instance.SiteWhereInstance;
import io.sitewhere.k8s.crd.tenant.engine.SiteWhereTenantEngine;
import io.sitewhere.k8s.crd.tenant.engine.SiteWhereTenantEngineStatus;

/**
 * Base class for operations which update the Kubernetes
 * {@link SiteWhereInstance} resource status.
 */
public abstract class TenantEngineStatusUpdateOperation implements ITenantEngineStatusUpdateOperation {

    /** Static logger instance */
    private static Logger LOGGER = LoggerFactory.getLogger(TenantEngineStatusUpdateOperation.class);

    /*
     * @see
     * com.sitewhere.spi.microservice.multitenant.ITenantEngineStatusUpdateOperation
     * #execute(com.sitewhere.spi.microservice.multitenant.
     * IMicroserviceTenantEngine)
     */
    @Override
    public SiteWhereTenantEngine execute(IMicroserviceTenantEngine<?> engine) throws SiteWhereException {
	while (true) {
	    try {
		SiteWhereTenantEngine current = engine.loadTenantEngineResource();
		if (current.getStatus() == null) {
		    current.setStatus(createDefaultStatus());
		}
		update(current.getStatus());
		return engine.updateTenantEngineStatus(current);
	    } catch (SiteWhereException e) {
		LOGGER.info("Unable to update tenant engine status. Will retry.", e);
	    } catch (Throwable t) {
		throw new SiteWhereException("Tenant engine status update failed.", t);
	    }
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
		throw new SiteWhereException(
			"Failed to modify instance. Interrupted while waiting after concurrent update.");
	    }
	}
    }

    /**
     * Create the default starting status for a tenant engine.
     * 
     * @return
     */
    protected SiteWhereTenantEngineStatus createDefaultStatus() {
	SiteWhereTenantEngineStatus status = new SiteWhereTenantEngineStatus();
	status.setBootstrapState(BootstrapState.NotBootstrapped);
	return status;
    }
}
