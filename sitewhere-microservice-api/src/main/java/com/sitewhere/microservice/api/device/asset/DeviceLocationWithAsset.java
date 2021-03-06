/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.microservice.api.device.asset;

import java.math.BigDecimal;

import com.sitewhere.microservice.api.asset.IAssetManagement;
import com.sitewhere.rest.model.device.event.DeviceLocation;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.device.event.IDeviceLocation;

/**
 * Wraps a {@link DeviceLocation} so that information about the asset associated
 * with its assignment is available.
 */
public class DeviceLocationWithAsset extends DeviceEventWithAsset implements IDeviceLocation {

    /** Serial version UID */
    private static final long serialVersionUID = -8449689938042640635L;

    public DeviceLocationWithAsset(IDeviceLocation wrapped, IAssetManagement assetManagement)
	    throws SiteWhereException {
	super(wrapped, assetManagement);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sitewhere.spi.device.IDeviceLocation#getLatitude()
     */
    @Override
    public BigDecimal getLatitude() {
	return ((IDeviceLocation) getWrapped()).getLatitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sitewhere.spi.device.IDeviceLocation#getLongitude()
     */
    @Override
    public BigDecimal getLongitude() {
	return ((IDeviceLocation) getWrapped()).getLongitude();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sitewhere.spi.device.IDeviceLocation#getElevation()
     */
    @Override
    public BigDecimal getElevation() {
	return ((IDeviceLocation) getWrapped()).getElevation();
    }
}