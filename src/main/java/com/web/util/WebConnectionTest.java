package com.web.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebConnectionTest {

	public static void main(String[] args) throws Exception {

		BufferedReader in = null;
		
		System.out.println("start");
		
		
		//for(int i = 0;i <ss.length ;i++) {
			String ip ="127.0.0.1";
			System.out.println(ip);
			try { 
				
				URL obj = new URL(ip); 
				// 호출할 url 
				HttpURLConnection con = (HttpURLConnection)obj.openConnection(); con.setRequestMethod("GET");
				
				con.setConnectTimeout(1500);//타임아웃시간

				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//				System.out.println(ip + " : O");
				String line; 
				while((line = in.readLine()) != null) { 
					// response를 차례대로 출력 
					System.out.println(line); 
				} 
			}catch(ConnectException e) {
				System.out.println(ip + " : X");
			//	continue;
			}catch(Exception e) { 
				//e.printStackTrace(); 
			} finally { 
				if(in != null) 
					try { in.close(); 
					} catch(Exception e) {
						e.printStackTrace(); 
					} 
				}
				
			}

		//}
}