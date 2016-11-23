package test.com.sendmail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailActivity extends Activity {
	private Button send; 
	private EditText userid; 
	private EditText password; 
	private EditText from; 
	private EditText to; 
	private EditText subject; 
	private EditText body;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        send = (Button) findViewById(R.id.send); 
        userid = (EditText) findViewById(R.id.userid); 
        password = (EditText) findViewById(R.id.password); 
        from = (EditText) findViewById(R.id.from); 
        to = (EditText) findViewById(R.id.to); 
        subject = (EditText) findViewById(R.id.subject); 
        body = (EditText) findViewById(R.id.body);
        
        send.setText("Send Mail");
        userid.setText("lvqiang593594@163.com");
        password.setText("lvQIANG0812");
        from.setText("lvqiang593594@163.com");
        to.setText("lvqiang593594@163.com");
        
        subject.setText("fuck you");
        body.setText("aaaaabbbbaaaaa\nϵͳ");

        send.setOnClickListener(new View.OnClickListener() 
        {
            @Override 
            public void onClick(View v) 
            { 
                // TODO Auto-generated method stub                    
                try 
                { 
               	 final MailSenderInfo mailInfo = new MailSenderInfo();
                 mailInfo.setMailServerHost("smtp.163.com");
                 mailInfo.setMailServerPort("25");    
                 mailInfo.setValidate(true);    
                 mailInfo.setUserName(userid.getText().toString());  //?????????  
                 mailInfo.setPassword(password.getText().toString());//????????????    
                 mailInfo.setFromAddress(from.getText().toString());    
                 mailInfo.setToAddress(to.getText().toString());    
                 mailInfo.setSubject(subject.getText().toString());    
                 mailInfo.setContent(body.getText().toString());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SimpleMailSender sms = new SimpleMailSender();
                            sms.sendTextMail(mailInfo);//??????????
                        }
                    }).start();;
                 
                }
                catch (Exception e) { 
                    Log.e("SendMail", e.getMessage(), e); 
                }
            } 
        }); 
    }
}