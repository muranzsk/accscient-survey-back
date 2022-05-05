package com.emergys.akagibackend.model;

import com.emergys.akagibackend.mail.MailConstants;

import javax.persistence.*;

@Entity
@Table(name = "company_config")
public class CompanyConfig {

    @Id
    @Column(name = "id_config")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConfig;

    @Column(name = "color_top")
    private String colorTop;

    @Column(name = "color_bot")
    private String colorBot;

    @Column(name = "contact")
    private String contact;

    @Column(name = "mail_host")
    private String mailHost;

    @Column(name = "mail_port")
    private String mailPort;

    @Column(name = "username")
    private String username;

    @Column(name = "code")
    private String code;

    @OneToOne()
    @JoinColumn(name = "id_company")
    private Company company;

    public Integer getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public String getColorTop() {
        return colorTop;
    }

    public void setColorTop(String colorTop) {
        this.colorTop = colorTop;
    }

    public String getColorBot() {
        return colorBot;
    }

    public void setColorBot(String colorBot) {
        this.colorBot = colorBot;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getMailPort() {
        return mailPort;
    }

    public void setMailPort(String mailPort) {
        this.mailPort = mailPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getLogoBase64() {
        switch(this.company.getId()) {
            case 1 :
                return MailConstants.A2U_LOGO;
            case 2 :
                return MailConstants.APPRIDAT_LOGO;
            case 3 :
                return MailConstants.EMERGYS_LOGO;
            case 4 :
                return MailConstants.MISSION_LOGO;
            case 5 :
                return MailConstants.NORWIN_LOGO;
            case 6 :
                return MailConstants.PDS_LOGO;
            case 7 :
                return MailConstants.PITSOL_LOGO;
            case 8 :
                return MailConstants.BITB_LOGO;
            case 10 :
                return MailConstants.OVAL_LOGO;
            case 11 :
                return MailConstants.NRS_LOGO;
            default :
                return MailConstants.ACCSCIENT_LOGO;
        }
    }

    @Override
    public String toString() {
        return "CompanyConfig{" +
                "idConfig=" + idConfig +
                ", colorTop='" + colorTop + '\'' +
                ", colorBot='" + colorBot + '\'' +
                ", contact='" + contact + '\'' +
                ", mailHost='" + mailHost + '\'' +
                ", mailPort='" + mailPort + '\'' +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", company=" + company +
                '}';
    }
}
