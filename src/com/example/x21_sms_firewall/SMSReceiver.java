 package com.example.x21_sms_firewall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//拿到短信的信息
		//短信信息封装在intent中
		Bundle bundle = intent.getExtras();
		//以pdus（协议数据单元）为键，取出一个object数组，数组中的每一个元素都是一条短信
		Object[] objects = (Object[]) bundle.get("pdus");
		
		//拿到广播中的所有的短信
		for (Object object : objects) {
			//通过pdu来构造短信
			SmsMessage sms = SmsMessage.createFromPdu((byte[])object);
			if(sms.getOriginatingAddress().equals("110")){
				//阻止其他广播接受者接收这条广播       4.4后这样做，拦截短信不起作用
				abortBroadcast();
				
//				System.out.println(sms.getDisplayMessageBody());
			}
		}
	}

}
