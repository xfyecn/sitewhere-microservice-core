/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.microservice.kafka;

import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.RecordMetadata;

import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.lifecycle.ILifecycleComponent;

/**
 * Component that produces messages that are sent to a Kafka topic.
 */
public interface IMicroserviceKafkaProducer<K, P> extends ILifecycleComponent {

    /**
     * Get key serializer class.
     * 
     * @return
     */
    public Class<?> getKeySerializer();

    /**
     * Get value serializer class.
     * 
     * @return
     */
    public Class<?> getValueSerializer();

    /**
     * Get name of Kafka topic which will receive the messages.
     * 
     * @return
     * @throws SiteWhereException
     */
    String getTargetTopicName() throws SiteWhereException;

    /**
     * Send a message to the topic.
     * 
     * @param key
     * @param message
     * @return
     * @throws SiteWhereException
     */
    Future<RecordMetadata> send(K key, P message) throws SiteWhereException;
}