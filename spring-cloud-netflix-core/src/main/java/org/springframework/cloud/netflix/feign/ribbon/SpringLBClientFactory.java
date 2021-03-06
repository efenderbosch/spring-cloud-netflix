/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.feign.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import feign.ribbon.LBClient;
import feign.ribbon.LBClientFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;

/**
 * @author Spencer Gibb
 */
public class SpringLBClientFactory implements LBClientFactory {

	private final SpringClientFactory factory;

	public SpringLBClientFactory(SpringClientFactory factory) {
		this.factory = factory;
	}

	@Override
	public LBClient create(String clientName) {
		IClientConfig config = factory.getClientConfig(clientName);
		ILoadBalancer lb = factory.getLoadBalancer(clientName);
		return LBClient.create(lb, config);
	}
}
