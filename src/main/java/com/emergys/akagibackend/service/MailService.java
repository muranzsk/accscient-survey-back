package com.emergys.akagibackend.service;

import com.emergys.akagibackend.mail.MailConstants;
import com.emergys.akagibackend.mail.MailEmployee;
import com.emergys.akagibackend.model.CompanyConfig;
import com.emergys.akagibackend.repository.CompanyConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class MailService  implements EmailPort {

    @Autowired
    private CompanyConfigRepository repo;

    @Override
    public Integer sendInvitation(MailEmployee employee) {
        CompanyConfig params = repo.findByCompany(employee.getCompany());
        String subject = MailConstants.SUBJECT_INVITATION;
        String message = MailConstants.MAIL_INVITE_BODY;
        message = message.replace("{#INVITATION_LINK#}", employee.getFullName());

        return this.sendMail(employee, params, message, subject);
    }

    @Override
    public Integer sendYesMail(MailEmployee employee) {
        CompanyConfig params = repo.findByCompany(employee.getCompany());
        String subject = MailConstants.SUBJECT_RECORD;
        String message = MailConstants.MAIL_YES_BODY;

        return this.sendMail(employee, params, message, subject);
    }

    @Override
    public Integer sendNoMail(MailEmployee employee) {
        CompanyConfig params = repo.findByCompany(employee.getCompany());
        String subject = MailConstants.SUBJECT_RECORD;
        String message = MailConstants.MAIL_NO_BODY;

        return this.sendMail(employee, params, message, subject);
    }

    public Integer sendMail(MailEmployee employee, CompanyConfig params, String message, String subject) {

        Properties props = new Properties();
        props.put("mail.smtp.host", params.getMailHost());
        props.put("mail.smtp.port", params.getMailPort());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");

        String mailContent = message;
        mailContent = mailContent.replace("{#COMPANYCOLORTOP#}", params.getColorTop());
        mailContent = mailContent.replace("{#COMPANYLOGO#}", params.getLogoBase64());
        mailContent = mailContent.replace("{#FULLNAME#}", employee.getFullName());
        mailContent = mailContent.replace("{#COMPANYNAME#}", employee.getCompany().getCompanyName());
        mailContent = mailContent.replace("{#COMPANYCOLORBOT#}", params.getColorBot());
        mailContent = mailContent.replace("{#HREMAIL#}", params.getContact());

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(params.getUsername(), params.getCode());
                    }
                });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(params.getUsername()));
            msg.addRecipient(Message.RecipientType.BCC,
                    new InternetAddress(params.getUsername()));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(employee.getEmailAddress(), employee.getFullName()));
            msg.setSubject(subject);
            msg.setContent(mailContent, "text/html");
            Transport.send(msg);
            return 1;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
