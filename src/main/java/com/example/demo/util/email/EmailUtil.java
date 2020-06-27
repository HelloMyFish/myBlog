package com.example.demo.util.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author gyf
 * @date 2020/6/26 18:46
 */
@Configuration
public class EmailUtil {
    @Value("${mail.smtp.host}")
    private  String HOST;
    @Value(value = "${mail.smtp.auth}")
    private  String AUTH;
    @Value(value = "${mail.smtp.port}")
    private  String PORT;
    @Value(value = "${mail.smtp.user}")
    private  String USER;
    @Value(value = "${mail.smtp.password}")
    private  String PASSWORD;

    public  void send(String title,String content) throws MessagingException {

        // 创建Properties 类用于记录邮箱的一些属性
        Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", AUTH);
        //此处填写SMTP服务器
        props.put("mail.smtp.host", HOST);
        //端口号，QQ邮箱端口587
        props.put("mail.smtp.port", PORT);
        // 此处填写，写信人的账号
        props.put("mail.user", USER);
        // 此处填写16位STMP口令
        props.put("mail.password", PASSWORD);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人的邮箱
        InternetAddress to = new InternetAddress("gengyufan.cn@gmail.com");
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(content, "text/html;charset=UTF-8");

        // 最后当然就是发送邮件啦
        Transport.send(message);
    }

}
