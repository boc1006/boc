package com.boc.common.enums;

import java.util.Map;

import com.boc.common.utils.ResourceUtils;

public class PropertiesHelper {
	public final static Map<String, String> GLOBAL_APP = ResourceUtils.getResource("global").getMap();

	public final static String CURATOR_LOCK_ZK = GLOBAL_APP.get("curator.lock.zk");
	public final static String CURATOR_LOCK_SESSION_TIMEOUT = GLOBAL_APP.get("curator.lock.session.timeout");
	public final static String CURATOR_LOCK_BASESLEEPTIMEMS = GLOBAL_APP.get("curator.lock.baseSleepTimeMs");
	public final static String CURATOR_LOCK_MAXRETRIES = GLOBAL_APP.get("curator.lock.maxRetries");
}
