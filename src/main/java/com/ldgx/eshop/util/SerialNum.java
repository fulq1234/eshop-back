package com.ldgx.eshop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class SerialNum {
	private static String count = "000";
	private static String dateValue = "20131115";

	/**
	 * 产生流水号
	 */
	public synchronized static String getMoveOrderNo() {
		long cm = System.currentTimeMillis();//当前秒数
		//取一个4位随机数
		String four = SerialNum.random(4);
		return cm + four ;
	}

	/**
	 * 取随机数
	 * @param n:取几位随机数
	 * @return
	 */
	public static String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while(true) {
                int k = ran.nextInt(10);
                if( (bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char)(k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			//System.out.println(getMoveOrderNo());
			System.out.println(SerialNum.getMoveOrderNo());
		}
		
		
	}
}
