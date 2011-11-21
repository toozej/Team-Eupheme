package sms.messenging;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{	

		//---get the SMS message passed in---
		Bundle bundle = intent.getExtras();        
		SmsMessage[] msgs = null;
		String str = "";            
		if (bundle != null)
		{
			//retrieve the SMS message received
			Object[] pdus = null;
			try
			{
				pdus = (Object[]) bundle.get("pdus");
			} catch (Exception e){
				//error checking, getting or setting pdus failed
				//let user know something went wrong
			}
			msgs = new SmsMessage[pdus.length];            
			for (int i=0; i<msgs.length; i++){
				msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
				str += "SMS from " + msgs[i].getOriginatingAddress();                     
				str += " :";
				str += msgs[i].getMessageBody().toString();
				str += "\n";        
			}
			//display the new SMS message
			Toast.makeText(context, str, Toast.LENGTH_LONG).show();
		}    
	}
}