/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.microservice.kubernetes;

import java.util.Arrays;

import com.sitewhere.rest.model.microservice.MicroserviceSummary;
import com.sitewhere.rest.model.tenant.Tenant;

import io.sitewhere.k8s.crd.ResourceLabels;
import io.sitewhere.k8s.crd.microservice.SiteWhereMicroservice;
import io.sitewhere.k8s.crd.tenant.SiteWhereTenant;

/**
 * Conversions between k8s resources and SiteWhere API objects.
 */
public class K8sModelConverter {

    /**
     * Convert k8s microservice resource to SiteWhere API.
     * 
     * @param microservice
     * @return
     */
    public static MicroserviceSummary convert(SiteWhereMicroservice microservice) {
	MicroserviceSummary summary = new MicroserviceSummary();
	summary.setId(microservice.getMetadata().getName());
	summary.setName(microservice.getSpec().getName());
	summary.setDescription(microservice.getSpec().getDescription());
	summary.setFunctionalArea(
		microservice.getMetadata().getLabels().get(ResourceLabels.LABEL_SITEWHERE_FUNCTIONAL_AREA));
	summary.setIcon(microservice.getSpec().getIcon());
	summary.setMultitenant(microservice.getSpec().isMultitenant());
	summary.setDockerImageTag(microservice.getSpec().getPodSpec().getImageTag());
	summary.setDebugEnabled(microservice.getSpec().getDebug().isEnabled());
	summary.setDebugJdwpPort(microservice.getSpec().getDebug().getJdwpPort());
	summary.setDebugJmxPort(microservice.getSpec().getDebug().getJmxPort());
	return summary;
    }

    /**
     * Convert k8s tenant resource to SiteWhere API.
     * 
     * @param tenant
     * @return
     */
    public static Tenant convert(SiteWhereTenant tenant) {
	Tenant api = new Tenant();
	api.setToken(tenant.getMetadata().getName());
	api.setName(tenant.getSpec().getName());
	api.setAuthenticationToken(tenant.getSpec().getAuthenticationToken());
	api.setAuthorizedUserIds(Arrays.asList(tenant.getSpec().getAuthorizedUserIds()));
	api.setBackgroundColor(tenant.getSpec().getBranding().getBackgroundColor());
	api.setForegroundColor(tenant.getSpec().getBranding().getForegroundColor());
	api.setBorderColor(tenant.getSpec().getBranding().getBorderColor());
	api.setIcon(tenant.getSpec().getBranding().getIcon());
	api.setImageUrl(tenant.getSpec().getBranding().getImageUrl());
	api.setConfigurationTemplateId(tenant.getSpec().getConfigurationTemplate());
	api.setDatasetTemplateId(tenant.getSpec().getDatasetTemplate());
	api.setMetadata(tenant.getSpec().getMetadata());
	return api;
    }
}
