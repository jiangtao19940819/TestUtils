package com.baiwang.utils;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.File;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * @author jiangtao
 *
 */
public class Mail {
	private static Logger logger = Logger.getLogger(Mail.class.getName());
	private static String fromAddress;
	private static String password;
	private static InternetAddress[] toAddressArray;
	private static String OutputFileDir;
	//通过配置文件获得邮件发送者地址和收件人地址 InternetAddress[] toAddressArray
	static {
		try{
			Properties prop = new Properties();
			InputStreamReader is = new InputStreamReader(new FileInputStream("properties.properties"),"utf-8");
			prop.load(is);
			OutputFileDir = prop.getProperty("OutputFileDir");
			String toAddress = prop.getProperty("ToEmailAddress");
			String selfAddress = prop.getProperty("FromEmailAddress");
			String[] self = selfAddress.split(",");
		    fromAddress = self[0];
		    password = self[1];
			logger.info(fromAddress+" Send Mail To:"+toAddress);
			String[] address = toAddress.split(",");
			toAddressArray = new InternetAddress[address.length];
			for(int i=0;i<address.length;i++){
				toAddressArray[i] = new InternetAddress(address[i]);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param 邮件内容和附件地址
	 * @throws Exception
	 */
	public static void sendMail(String[] args) throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", "smtp.baiwang.com");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(prop);
		session.setDebug(true);
		Transport transport = session.getTransport();
		transport.connect(fromAddress,password);
		MimeMessage message = createMessage(session,args);
		transport.sendMessage(message,message.getAllRecipients());
		transport.close();
		
		
	}
	/**
	 * @param session
	 * @param args 附件文件数组
	 * @return 邮件message 
	 * @throws Exception
	 */
	private static MimeMessage createMessage(Session session,String[] args) throws Exception{
		MimeMessage message = new MimeMessage(session);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		message.setSubject(time+"测试报告");
		//设置邮件发送地址和收件地址
		message.setFrom(new InternetAddress(fromAddress));
		for(InternetAddress addr:toAddressArray){
			message.addRecipient(Message.RecipientType.TO, addr);
		}
		//设置邮件内容和附件
		MimeMultipart mm = new MimeMultipart();
		for(int i=0; i<args.length;i++){
			if(i==(args.length-1)){
				MimeBodyPart part = new MimeBodyPart();
				part.setContent(args[args.length-1],"text/html;charset=utf-8");
				mm.addBodyPart(part);
			}else{
				MimeBodyPart part = new MimeBodyPart();
				DataHandler dh = new DataHandler(new FileDataSource(OutputFileDir+File.separator+args[i]));
				part.setDataHandler(dh);
				part.setFileName(MimeUtility.encodeText((new File(args[i])).getName()));
				mm.addBodyPart(part);
			}
			
		}
		message.setContent(mm);
		return message;
	}
		
}
