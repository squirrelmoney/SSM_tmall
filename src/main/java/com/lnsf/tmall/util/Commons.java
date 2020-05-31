package com.lnsf.tmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Commons {
	public static final String pic ="http://localhost:8089/FileService";

	public static String ordercode(){
		Date date =new Date();
		String random = String.valueOf((Math.random() * 9 + 1) * 1000000);
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		String code1 = dateFormat.format(date);
		String code2 = random.substring(0, 4);
		String ordercode=code2+code1;
		return  ordercode;

	}
}



