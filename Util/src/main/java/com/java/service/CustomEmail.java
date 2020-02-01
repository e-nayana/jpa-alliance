package com.java.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class CustomEmail {

  private JavaMailSender javaMailSender;

  public CustomEmail(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendSimpleEmail(
    String subject ,
    String[] to ,
    String[] cc ,
    String[] bcc,
    String text
  ) throws Exception {

    SimpleMailMessage msg = new SimpleMailMessage();

    msg.setTo(to);

    if(cc != null && cc.length != 0){
      msg.setCc(cc);
    }

    if(bcc != null && bcc.length != 0){
      msg.setBcc(bcc);
    }

    msg.setSubject(subject);
    msg.setText(text);
    javaMailSender.send(msg);
  }


  public void sendHtmlEmail(
    String subject ,
    String[] to ,
    String[] cc ,
    String[] bcc,
    String html
  ) throws Exception {

    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(msg, true);

    helper.setTo(to);
    if(cc != null && cc.length != 0){
      helper.setCc(cc);
    }

    if(bcc != null && bcc.length != 0){
      helper.setBcc(bcc);
    }

    helper.setSubject(subject);
    helper.setText(html, true);

    javaMailSender.send(msg);

  }

}
