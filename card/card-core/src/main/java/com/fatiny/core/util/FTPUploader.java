package com.fatiny.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import lombok.extern.slf4j.Slf4j;

/**
 * ftp上传工具
 */
@Slf4j
public class FTPUploader {
	
	/**
	 * ftp上传文件, ssl加密
	 * 
	 * @param server     服务器IP或域名
	 * @param username   ftp用户名
	 * @param password   ftp用户密码
	 * @param workingDir 远程工作目录
	 */
	public static void uploadSSL(String server, String username, String password, String workingDir, String uploadFilePath) {
		final FTPSClient ftp = new FTPSClient();
        ftp.setControlKeepAliveTimeout(300);
        ftp.setControlKeepAliveReplyTimeout(300);
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));
		try {
			ftp.connect(server);
			log.info("Connected to " + server + " on " + ftp.getDefaultPort());
			
			// After connection attempt, you should check the reply code to verify
			// success.
			int reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.error("FTP server refused connection.");
				return;
			}
			
			if (!ftp.login(username, password)) {
				ftp.logout();
				return;
			}
			
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			// Use passive mode as default because most of us are
			// behind firewalls these days.
			ftp.enterLocalPassiveMode();
			
			if (!ftp.changeWorkingDirectory(workingDir)) {
				ftp.makeDirectory(workingDir);
			}
			
			File uploadFile = new File(uploadFilePath);
			if (!ftp.storeFile(uploadFile.getName(), new FileInputStream(uploadFile))) {
				log.info("上传文件失败");
			}
			
			log.info("成功上传文件:{}", uploadFile.getName());
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	
	public static void main(String[] args) {
//		String server = "183.60.126.138";
//		String username = "shoot";
//		String password = "cdnftpgood1688";
//		String workingDir = "/res/server";
	}
	
}
