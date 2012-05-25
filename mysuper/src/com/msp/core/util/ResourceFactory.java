package com.msp.core.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResourceFactory {

	private String topAppkey;
	private String topSandboxUrl;
	private String topSandboxAppSecret;
	private static ResourceFactory res;

	private ResourceFactory() {
		final String propName = "com.msp.core.util.tao-interface";

		ResourceBundle rb = ResourceBundle.getBundle(propName, Locale.getDefault());

		topAppkey = rb.getString("tao_appkey");
		topSandboxUrl = rb.getString("tao_sanbox_url");
		topSandboxAppSecret = rb.getString("tao_sandbox_appsecret");

		ResourceBundle.clearCache();

	}

	public static ResourceFactory getRes() {
		if (res == null) {
			res = new ResourceFactory();
		}
		return res;
	}

	public String getTopAppkey() {
		return topAppkey;
	}

	public String getTopSandboxUrl() {
		return topSandboxUrl;
	}

	public String getTopSandboxAppSecret() {
		return topSandboxAppSecret;
	}

	public static void main(String[] arg) {
		ResourceFactory rf = ResourceFactory.getRes();

		System.out.println("appKey=" + rf.getTopAppkey());
		System.out.println("sandboxAppSecret=" + rf.getTopSandboxAppSecret());
		System.out.println("url=" + rf.getTopSandboxUrl());
		
	}
}
