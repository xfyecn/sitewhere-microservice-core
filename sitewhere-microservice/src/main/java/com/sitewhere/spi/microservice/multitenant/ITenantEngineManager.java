/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.microservice.multitenant;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.configuration.ITenantEngineConfigurationListener;
import com.sitewhere.spi.microservice.lifecycle.ITenantEngineLifecycleComponent;

/**
 * Manages tenant engines for a multitenant microservice.
 */
public interface ITenantEngineManager<T extends IMicroserviceTenantEngine<?>>
	extends ITenantEngineConfigurationListener, ITenantEngineLifecycleComponent {

    /**
     * Get tenant engine corresponding to the given id.
     * 
     * @param token
     * @return
     * @throws SiteWhereException
     */
    T getTenantEngineByToken(String token) throws SiteWhereException;

    /**
     * Make sure the given tenant engine exists and is started.
     * 
     * @param token
     * @return
     * @throws TenantEngineNotAvailableException
     */
    T assureTenantEngineAvailable(String token) throws TenantEngineNotAvailableException;

    /**
     * Shuts down and restarts the given tenant engine. Note that restart happens
     * asynchronously based on available tenant operation threads.
     * 
     * @param token
     */
    void restartTenantEngine(String token);

    /**
     * Restart all tenant engines. Note that restarts happen asynchronously based on
     * available tenant operation threads. If all threads are used, this method will
     * block.
     * 
     * @throws SiteWhereException
     */
    void restartAllTenantEngines() throws SiteWhereException;

    /**
     * Shuts down and removes a tenant engine.
     * 
     * @param token
     * @throws SiteWhereException
     */
    void removeTenantEngine(String token) throws SiteWhereException;

    /**
     * Remove all tenant engines for the microservice.
     * 
     * @throws SiteWhereException
     */
    void removeAllTenantEngines() throws SiteWhereException;
}
