package Ezen.project.service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailServiceInter {
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    public String createKey();

    public String sendSimpleMessage(String to) throws Exception;
}
