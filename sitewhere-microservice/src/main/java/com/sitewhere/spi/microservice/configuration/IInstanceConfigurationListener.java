/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.microservice.configuration;

import io.sitewhere.k8s.crd.instance.SiteWhereInstance;

/**
 * Listens for changes in instance configuration.
 */
public interface IInstanceConfigurationListener {

    /**
     * Called when instance configuration is added.
     * 
     * @param instance
     */
    void onInstanceAdded(SiteWhereInstance instance);

    /**
     * Called when instance configuration is updated.
     * 
     * @param instance
     * @param specUpdates
     * @param statusUpdates
     */
    void onInstanceUpdated(SiteWhereInstance instance, IInstanceSpecUpdates specUpdates,
	    IInstanceStatusUpdates statusUpdates);

    /**
     * Called when instance configuration is deleted.
     * 
     * @param instance
     */
    void onInstanceDeleted(SiteWhereInstance instance);
}