package com.boc.common.utils;

import java.math.BigInteger;

/**
 * 权限计算帮助类
 * @author user
 *
 */
public class RightsHelper {
	/**
	 * 利用BigInteger对权限进行2的权的和计算
	 * @param rights int型权限编码数组
	 * @return 2的权的和
	 */
	public static BigInteger sumRights(int[] rights){
		BigInteger num = new BigInteger("0");
		for(int i=0; i<rights.length; i++){
			num = num.setBit(rights[i]);
		}
		return num;
	}
	/**
	 * 利用BigInteger对权限进行2的权的和计算
	 * @param rights String型权限编码数组
	 * @return 2的权的和
	 */
	public static BigInteger sumRights(String[] rights){
		BigInteger num = new BigInteger("0");
		for(int i=0; i<rights.length; i++){
			num = num.setBit(Integer.parseInt(rights[i]));
		}
		return num;
	}
	
	public static void main(String[] args) {
		BigInteger arights = BigInteger.ZERO;
		System.out.println(RightsHelper.testRights(arights, "0"));
		int s[]=new int[]{};
		BigInteger bi1 = sumRights(s);
		System.out.println(bi1);
//		arights.or(bi1);
		System.out.println(testRights(bi1, 0));
//		System.out.println(bi1+"\t"+bi1.toString().length());
//		int s2[]=new int[40];
//		for(int i = 0; i < 40 ;i ++) {
//			s2[i] = i+1;
//		}
//		BigInteger bi2 = sumRights(s2);
//		System.out.println(bi2+"\t"+bi2.toString().length());
//		BigInteger bi3 = bi1.or(bi2);
//		for(int i = 0 ; i < s.length ; i ++) {
//			System.out.println(bi3.testBit(s[i]));
//		}
//		for(int i = 0 ; i < s2.length ; i ++) {
//			System.out.println(bi3.testBit(s2[i]));
//		}
		System.out.println("--------------");
		int s3[] = new int []{5,3,6,7,8,9,230,398,78,772,76,87};
		BigInteger bi = sumRights(s3);
		System.out.println(bi.toString().length());
//		for(int i = 0 ; i < s3.length ; i ++) {
//			System.out.println(bi3.testBit(s3[i]));
//		}
	}
	
	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(BigInteger sum,int targetRights){
		return sum.testBit(targetRights);
	}
	
	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(String sum,int targetRights){
		if(Tools.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum),targetRights);
	}
	
	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(String sum,String targetRights){
		if(Tools.isEmpty(sum))
			return false;
		return testRights(new BigInteger(sum),targetRights);
	}
	
	/**
	 * 测试是否具有指定编码的权限
	 * @param sum
	 * @param targetRights
	 * @return
	 */
	public static boolean testRights(BigInteger sum,String targetRights){
		return testRights(sum,Integer.parseInt(targetRights));
	}
}
