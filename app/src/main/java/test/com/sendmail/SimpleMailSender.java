package test.com.sendmail;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;   
import javax.mail.Address;    
import javax.mail.BodyPart;    
import javax.mail.Message;    
import javax.mail.MessagingException;    
import javax.mail.Multipart;    
import javax.mail.Session;    
import javax.mail.Transport;    
import javax.mail.internet.InternetAddress;    
import javax.mail.internet.MimeBodyPart;    
import javax.mail.internet.MimeMessage;    
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * ????????????????????????????   
 */    
public class SimpleMailSender  
{    
	/**   
	 * @param mailInfo
	 */    
	public boolean sendTextMail(MailSenderInfo mailInfo) 
	{
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();   
		if (mailInfo.isValidate()) 
		{    
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}   
		Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
		try 
		{

			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = null;
			try {
				from = new InternetAddress(mailInfo.getFromAddress(),"吕强","utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			mailMessage.setFrom(from);//设置发件人邮箱
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO,to);//设置收件人邮箱地址
			mailMessage.setSentDate(new Date());

			String mailContent = mailInfo.getContent();
			try {
				mailMessage.setSubject(MimeUtility.encodeText("中文邮件标题","utf-8", "B"));//生成邮件标题
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			mailMessage.setContent(mailContent, "text/plain;charset=utf-8"); //生成邮件正文
//			mailMessage.setText(mailContent);

			Transport.send(mailMessage);
			return true;    
		}
		catch (MessagingException ex) 
		{    
			ex.printStackTrace();    
		}    
		return false;    
	}    

	/**   
	 * @param mailInfo ?????????????
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo)
	{    
		// ????????????????    
		MyAuthenticator authenticator = null;   
		Properties pro = mailInfo.getProperties();   
		//?????????????????????????????     
		if (mailInfo.isValidate()) 
		{    
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
		}    
		// ???????????????????????????????????????session    
		Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
		try {    
			// ????session?????????????    
			Message mailMessage = new MimeMessage(sendMailSession);    
			// ???????????????    
			Address from = new InternetAddress(mailInfo.getFromAddress());    
			// ?????????????????    
			mailMessage.setFrom(from);    
			// ?????????????????????????????????    
			Address to = new InternetAddress(mailInfo.getToAddress());    
			// Message.RecipientType.TO??????????????????TO    
			mailMessage.setRecipient(Message.RecipientType.TO,to);    
			// ????????????????    
			mailMessage.setSubject(mailInfo.getSubject());    
			// ??????????????????    
			mailMessage.setSentDate(new Date());    
			// MiniMultipart?????????????????MimeBodyPart????????    
			Multipart mainPart = new MimeMultipart();    
			// ???????????HTML?????MimeBodyPart    
			BodyPart html = new MimeBodyPart();    
			// ????HTML????    
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
			mainPart.addBodyPart(html);    
			// ??MiniMultipart????????????????    
			mailMessage.setContent(mainPart);    
			// ???????    
			Transport.send(mailMessage);    
			return true;    
		} catch (MessagingException ex) {    
			ex.printStackTrace();    
		}    
		return false;    
	}    

}
