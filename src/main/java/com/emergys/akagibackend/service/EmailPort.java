package com.emergys.akagibackend.service;

import com.emergys.akagibackend.mail.MailEmployee;

public interface EmailPort {

    public Integer sendInvitation(MailEmployee employee);
    public Integer sendYesMail(MailEmployee employee);
    public Integer sendNoMail(MailEmployee employee);

}
