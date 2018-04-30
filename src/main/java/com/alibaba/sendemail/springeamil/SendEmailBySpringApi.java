package com.alibaba.sendemail.springeamil;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


public class SendEmailBySpringApi {
    /**
     *
     * @param toEmails 所有收件人
     * @param suject 邮件主题
     * @param text 邮件内容
     * @return
     * @throws Exception
     */
        public static String sendEmailBySpringApi(final String FromEmail, final String Identify,String[] toEmails,String suject,String text) throws Exception{
            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
            String msg="";
            String emails="";
            // 企业邮箱时使用  senderImpl.setHost("smtp.qiye.163.com");
            senderImpl.setHost("smtp.163.com");  // 个人邮箱

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(toEmails);
            mailMessage.setFrom(FromEmail);
            mailMessage.setSubject(suject);
            mailMessage.setText(text);

            senderImpl.setUsername(FromEmail);
            senderImpl.setPassword(Identify);

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.timeout", "25000");
            senderImpl.setJavaMailProperties(prop);

            senderImpl.send(mailMessage);
            for (String toEmail : toEmails) {
               emails = emails+","+toEmail;
            }
            emails = emails.substring(emails.indexOf(",")+1,emails.length());
            msg="成功发送内容:"+text+"群发到邮箱"+emails;
            return msg;
        }

    /**
     *
     * @param toEmail 收件人
     * @param suject 邮件主题
     * @param text 邮件内容
     * @return
     * @throws Exception
     */
    public static String sendEmailBySpringApi(final String FromEmail, final String Identify,String toEmail,String suject,String text) throws Exception{
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        String msg="";

        // 企业邮箱时使用  senderImpl.setHost("smtp.qiye.163.com");
        senderImpl.setHost("smtp.163.com");  // 个人邮箱


        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setFrom(FromEmail);
        mailMessage.setSubject(suject);
        mailMessage.setText(text);

        senderImpl.setUsername(FromEmail);
        senderImpl.setPassword(Identify);

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);

        senderImpl.send(mailMessage);
        msg="成功发送内容:"+text+"到邮箱"+toEmail;
        return msg;
    }

        public static void main(String[] args) throws Exception{
            String FromEmail  = "15101516445@163.com"; //发件人邮箱
            String Identify = "15101516445zh";
            String[] array = new String[] {"1020886351@qq.com","3367413791@qq.com"};
            String information = sendEmailBySpringApi(FromEmail,Identify,array, "这是群发", "简易发送邮件jar包1.0.0.RELEASE");
            System.out.println(information);
        }
}
