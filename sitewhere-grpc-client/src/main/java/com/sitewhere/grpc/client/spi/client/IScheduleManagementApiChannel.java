/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.grpc.client.spi.client;

import com.sitewhere.grpc.client.MultitenantGrpcChannel;
import com.sitewhere.grpc.client.spi.multitenant.IMultitenantApiChannel;
import com.sitewhere.microservice.api.schedule.IScheduleManagement;

/**
 * Provides an {@link IMultitenantApiChannel} that supplies the
 * {@link IScheduleManagement}. API.
 */
public interface IScheduleManagementApiChannel<T extends MultitenantGrpcChannel<?, ?>>
	extends IScheduleManagement, IMultitenantApiChannel<T> {
}