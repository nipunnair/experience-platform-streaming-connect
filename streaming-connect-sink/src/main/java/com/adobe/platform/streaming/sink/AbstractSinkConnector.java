/*
 *  ADOBE CONFIDENTIAL
 *  __________________
 *
 *  Copyright 2019 Adobe
 *  All Rights Reserved.
 *
 *  NOTICE:  All information contained herein is, and remains
 *  the property of Adobe and its suppliers,
 *  if any.  The intellectual and technical concepts contained
 *  herein are proprietary to Adobe and its
 *  suppliers and are protected by trade secret or copyright law.
 *  Dissemination of this information or reproduction of this material
 *  is strictly forbidden unless prior written permission is obtained
 *  from Adobe.
 */

package com.adobe.platform.streaming.sink;

import com.google.common.collect.ImmutableMap;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Adobe Inc.
 */
public abstract class AbstractSinkConnector extends SinkConnector {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractSinkConnector.class);

  private static Map<String, String> connectorProps;

  @Override
  public String version() {
    return AppInfoParser.getVersion();
  }

  @Override
  public void start(Map<String, String> props) {
    connectorProps = props;
    LOG.info("Started Sink Connector with props {}", connectorProps);
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    List<Map<String, String>> configs = new ArrayList<>();
    for (int i = 0; i < maxTasks; i++) {
      configs.add(new ImmutableMap.Builder<String, String>().putAll(connectorProps).build());
    }

    return configs;
  }

  @Override
  public void stop() {
    LOG.info("Stopped Sink Connector");
  }

  @Override
  public ConfigDef config() {
    return new ConfigDef();
  }

}