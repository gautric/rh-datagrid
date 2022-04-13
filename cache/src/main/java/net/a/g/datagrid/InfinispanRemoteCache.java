package net.a.g.datagrid;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;
import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

public class InfinispanRemoteCache {

	public static void main(String[] args) throws Exception {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		Properties p = new Properties();
		try (Reader r = new FileReader("./src/main/resources/hotrod-client.properties")) {
			p.load(r);
			builder.withProperties(p);
		}
		
		RemoteCacheManager cacheManager = new RemoteCacheManager(builder.build());
		System.out.println("cacheManager OK");

		RemoteCache<String, String> cache = cacheManager.getCache(p.getProperty("infinispan.client.cache_name"));
		System.out.println("Cache OK");

		String key = UUID.randomUUID().toString();

		cache.put(key, "arigatoo " + UUID.randomUUID().toString());
		System.out.println("Put OK");

		// Retrieve the value and print it out
		System.out.printf("%s = %s\n", key, cache.get(key));
		// Stop the cache manager and release all resources
		cacheManager.stop();
		System.exit(0);
	}

}
