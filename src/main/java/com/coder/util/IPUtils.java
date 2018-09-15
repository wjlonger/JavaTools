/**
 * IP������
 */
package com.coder.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class IPUtils {

	public static String strfullip(String ip){
		StringBuffer buff = new StringBuffer();
		String strzero = "000";
		int ilen = 0;
		if(StringUtils.isNullOrEmpty(ip)){
			String[] arrip = ip.split("\\.");
			if(arrip.length == 4){
				for(int i = 0; i < 4; i++){
					if (i==0){
						ilen = arrip[i].length();
						if(ilen < 3){
							buff.append(strzero.substring(0,3-ilen)).append(arrip[i]);
						}else{
							buff.append(arrip[i]);
						}
					}else{
						ilen = arrip[i].length();
						if(ilen < 3){
							buff.append(".").append(strzero.substring(0,3-ilen)).append(arrip[i]);
						}else{
							buff.append(".").append(arrip[i]);
						}
					}
				}
			}
		}
		return buff.toString();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			if (StringUtils.isNullOrEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isNullOrEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isNullOrEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1")) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) {
				// xxx.xxx.xxx.xxx 长度为 15
				if (ipAddress.contains(",")) {
					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
				}
			}
		} catch (Exception e) {
			ipAddress = StringUtils.EMPTY;
		}
		return ipAddress;
	}
}
