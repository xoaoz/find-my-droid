package alan.smspop;
import javax.activation.DataHandler;   
import javax.activation.DataSource;   
import javax.mail.Message;   
import javax.mail.PasswordAuthentication;   
import javax.mail.Session;   
import javax.mail.Transport;   
import javax.mail.internet.InternetAddress;   
import javax.mail.internet.MimeMessage;   

import android.util.Log;

import java.io.ByteArrayInputStream;   
import java.io.IOException;   
import java.io.InputStream;   
import java.io.OutputStream;   
import java.security.Security;   
import java.util.HashMap;
import java.util.Properties;   


class MyAuthenticator extends javax.mail.Authenticator {
	private String strUser;
	private String strPwd;
	public MyAuthenticator(String user, String password) {
			this.strUser = user;
			this.strPwd = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(strUser, strPwd);
	}
}

class MyException extends Exception {
	public MyException(String msg){
	super(msg);
	}
}

public class GmailSender extends javax.mail.Authenticator {   
    private String mailhost ;   
    private String user;   
    private String password;   
    private Session session;   
    private String port;
    private HashMap<String, String> mailhostmap = new HashMap<String, String>();

    public GmailSender(String user, String password) throws MyException {   
        this.user = user;   
        this.password = password;   
        
        mailhostmap.put("gmail.com", "smtp.gmail.com");
        mailhostmap.put("qq.com", "smtp.qq.com");
        mailhostmap.put("163.com", "smtp.163.com");
        mailhostmap.put("hotmail.com", "smtp.live.com");
        
        int idx =  user.indexOf("@");
        String domain = user.substring(idx+1, user.length());
        Log.i("smschecker",domain);
        mailhost = mailhostmap.get(domain);
        if(domain.equals("163.com"))port ="25";
        else port="465";
        
        if(mailhost==null)
        	throw new MyException("can't find the domain");
        
        Properties props = new Properties();   
        props.setProperty("mail.transport.protocol", "smtp");   
        props.setProperty("mail.host", mailhost);   
        props.put("mail.smtp.auth", "true");   
        props.put("mail.smtp.port", port);   
        props.put("mail.smtp.socketFactory.port", port);   
        if(!domain.equals("163.com"))
        	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.put("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.quitwait", "false");   
       // props.setProperty("mail.smtp.starttls.enable"       , "true");

       // session = Session.getDefaultInstance(props, this);   
        MyAuthenticator myauth = new MyAuthenticator(user, password);
        session = Session.getDefaultInstance(props,myauth);   
    }   

    

    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {   
        try{
        MimeMessage message = new MimeMessage(session);   
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));   
        message.setSender(new InternetAddress(sender));   
        message.setSubject(subject);   
        message.setDataHandler(handler);   
        if (recipients.indexOf(',') > 0)   
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));   
        else  
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));   
        Transport.send(message);   
        }catch(Exception e){
        }
    }   

    public class ByteArrayDataSource implements DataSource {   
        private byte[] data;   
        private String type;   

        public ByteArrayDataSource(byte[] data, String type) {   
            super();   
            this.data = data;   
            this.type = type;   
        }   
        public ByteArrayDataSource(byte[] data) {   
            super();   
            this.data = data;   
        }   
        public void setType(String type) {   
            this.type = type;   
        }   
        public String getContentType() {   
            if (type == null)   
                return "application/octet-stream";   
            else  
                return type;   
        }   
        public InputStream getInputStream() throws IOException {   
            return new ByteArrayInputStream(data);   
        }   
        public String getName() {   
            return "ByteArrayDataSource";   
        }   
        public OutputStream getOutputStream() throws IOException {   
            throw new IOException("Not Supported");   
        }   
    }
    
   
}  