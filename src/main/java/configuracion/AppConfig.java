package configuracion;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class AppConfig implements InitializingBean {
	
	private Integer cantidadMaximaDePrendasUsuarioGratuito;
	private String cityList_openWeather_fileLocation;
	
//	mail sender
	private String mailSender_gmailAccount;
	private String mailSender_gmailPassword;
	private String mailSender_smtpServerHost;
	private String mailSender_smtpServerPort;
	private String mailSender_smtpSslTrust;
	private boolean mailSender_smtpAuth;
	private String mailSender_smtpStartTlsEnable;
	
//	WhatsApp sender
	private String whatsAppSender_accountSid;
	private String whatsAppSender_authToken;
	private String whatsAppSender_number;
	
	private static AppConfig INSTANCE;
	
	
	public AppConfig() {
		INSTANCE = this;
	}
	
	public Integer getCantidadMaximaDePrendasUsuarioGratuito() {
		return cantidadMaximaDePrendasUsuarioGratuito;
	}

	public void setCantidadMaximaDePrendasUsuarioGratuito(Integer cantidadMaximaDePrendasUsuarioGratuito) {
		this.cantidadMaximaDePrendasUsuarioGratuito = cantidadMaximaDePrendasUsuarioGratuito;
	}

	public static AppConfig getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(AppConfig iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public String getCityList_openWeather_fileLocation() {
		return cityList_openWeather_fileLocation;
	}

	public void setCityList_openWeather_fileLocation(String cityList_openWeather_fileLocation) {
		this.cityList_openWeather_fileLocation = cityList_openWeather_fileLocation;
	}

	public String getMailSender_gmailAccount() {
		return mailSender_gmailAccount;
	}

	public void setMailSender_gmailAccount(String mailSender_gmailAccount) {
		this.mailSender_gmailAccount = mailSender_gmailAccount;
	}

	public String getMailSender_gmailPassword() {
		return mailSender_gmailPassword;
	}

	public void setMailSender_gmailPassword(String mailSender_gmailPassword) {
		this.mailSender_gmailPassword = mailSender_gmailPassword;
	}

	public String getMailSender_smtpServerHost() {
		return mailSender_smtpServerHost;
	}

	public void setMailSender_smtpServerHost(String mailSender_smtpServerHost) {
		this.mailSender_smtpServerHost = mailSender_smtpServerHost;
	}

	public String getMailSender_smtpServerPort() {
		return mailSender_smtpServerPort;
	}

	public void setMailSender_smtpServerPort(String mailSender_smtpServerPort) {
		this.mailSender_smtpServerPort = mailSender_smtpServerPort;
	}

	public String getMailSender_smtpSslTrust() {
		return mailSender_smtpSslTrust;
	}

	public void setMailSender_smtpSslTrust(String mailSender_smtpSslTrust) {
		this.mailSender_smtpSslTrust = mailSender_smtpSslTrust;
	}

	public boolean getMailSender_smtpAuth() {
		return mailSender_smtpAuth;
	}

	public void setMailSender_smtpAuth(boolean mailSender_smtpAuth) {
		this.mailSender_smtpAuth = mailSender_smtpAuth;
	}

	public String getMailSender_smtpStartTlsEnable() {
		return mailSender_smtpStartTlsEnable;
	}

	public void setMailSender_smtpStartTlsEnable(String mailSender_smtpStartTlsEnable) {
		this.mailSender_smtpStartTlsEnable = mailSender_smtpStartTlsEnable;
	}

	public String getWhatsAppSender_accountSid() {
		return whatsAppSender_accountSid;
	}

	public void setWhatsAppSender_accountSid(String whatsAppSender_accountSid) {
		this.whatsAppSender_accountSid = whatsAppSender_accountSid;
	}

	public String getWhatsAppSender_authToken() {
		return whatsAppSender_authToken;
	}

	public void setWhatsAppSender_authToken(String whatsAppSender_authToken) {
		this.whatsAppSender_authToken = whatsAppSender_authToken;
	}

	public String getWhatsAppSender_number() {
		return whatsAppSender_number;
	}

	public void setWhatsAppSender_number(String whatsAppSender_number) {
		this.whatsAppSender_number = whatsAppSender_number;
	}

	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

}
