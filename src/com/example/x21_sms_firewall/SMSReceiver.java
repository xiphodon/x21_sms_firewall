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
		//�õ����ŵ���Ϣ
		//������Ϣ��װ��intent��
		Bundle bundle = intent.getExtras();
		//��pdus��Э�����ݵ�Ԫ��Ϊ����ȡ��һ��object���飬�����е�ÿһ��Ԫ�ض���һ������
		Object[] objects = (Object[]) bundle.get("pdus");
		
		//�õ��㲥�е����еĶ���
		for (Object object : objects) {
			//ͨ��pdu���������
			SmsMessage sms = SmsMessage.createFromPdu((byte[])object);
			if(sms.getOriginatingAddress().equals("110")){
				//��ֹ�����㲥�����߽��������㲥       4.4�������������ض��Ų�������
				abortBroadcast();
				
//				System.out.println(sms.getDisplayMessageBody());
			}
		}
	}

}
