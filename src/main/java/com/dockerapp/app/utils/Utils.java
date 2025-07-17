package com.dockerapp.app.utils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.net.ServerSocket;
import java.util.Random;

public class Utils {
	
	
	

	public static String getClientIp() {
	    ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    if (attrs == null) return "UNKNOWN";

	    HttpServletRequest request = attrs.getRequest();
	    String ip = request.getHeader("X-Forwarded-For");
	    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    } else {
	        ip = ip.split(",")[0];
	    }
	    return ip;
	}

	
	
	  public static int getAvailablePort() {
	        int port;
	        do {
	            port = new Random().nextInt(10000) + 30000;
	        } while (!isPortAvailable(port));
	        return port;
	    }

	    private static boolean isPortAvailable(int port) {
	        try (ServerSocket socket = new ServerSocket(port)) {
	            socket.setReuseAddress(true);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	
	

}
