package com.boc.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class Test {
	public static void main(String[] args) {
		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		// 取得应用(系统)类加载器
		URLClassLoader appClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader().getParent();
		System.out.println(appClassLoader);
		urls = appClassLoader.getURLs();
		for (URL url : urls) {
			System.out.println(url);
		}
	}
	
	public void test() {
		
	}

//	public static void main(String[] args)
//			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		// MyClassloaderByClassloader loader1 = new
		// MyClassloaderByClassloader("/Users/boc/Desktop/cs/a/", "loader1");
		// MyClassloaderByClassloader loader2 = new MyClassloaderByClassloader(null,
		// "/Users/boc/Desktop/cs/b/",
		// "loader2");
		// Class<?> clazz = loader2.loadClass("com.boc.Demo");
		// clazz.newInstance();
//	}
}
