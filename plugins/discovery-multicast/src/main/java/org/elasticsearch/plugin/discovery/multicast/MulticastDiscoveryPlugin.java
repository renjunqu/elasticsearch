/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.plugin.discovery.multicast;

import org.elasticsearch.common.logging.DeprecationLogger;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.DiscoveryModule;
import org.elasticsearch.plugins.Plugin;

public class MulticastDiscoveryPlugin extends Plugin {

    private static ESLogger logger = ESLoggerFactory.getLogger("discovery");
    private static DeprecationLogger deprecationLogger = new DeprecationLogger(logger);

    private final Settings settings;

    public MulticastDiscoveryPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override
    public String name() {
        return "discovery-multicast";
    }

    @Override
    public String description() {
        return "Multicast Discovery Plugin";
    }

    public void onModule(DiscoveryModule module) {
        deprecationLogger.deprecated("[discovery-multicast] plugin will be removed in the next CrateDB 1.1 version. " +
            "Use unicast or any cloud discovery plugin.");
        if (settings.getAsBoolean("discovery.zen.ping.multicast.enabled", false)) {
            module.addZenPing(MulticastZenPing.class);
        }
    }
}
