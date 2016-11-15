package com.example.accessurl1;

import java.io.InputStream;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView myImg;
	private Handler  myHandler;
	private Bitmap bitmap;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myImg = (ImageView) findViewById(R.id.myImg);
		myHandler=new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what==0x1122){
					myImg.setImageBitmap(bitmap);
				}
			}			
		};
		new Thread(){
			public void run(){
				try{
					URL url = new URL("http://www.baidu.com/" +
							"img/baidu_sylogo1.gif");
					//��ȡ�ٶ���ҳͼƬ
					InputStream is = url.openStream();
					bitmap = BitmapFactory.decodeStream(is);
					is.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
				myHandler.sendEmptyMessage(0x1122);				
			}
		}.start();
	}
}
