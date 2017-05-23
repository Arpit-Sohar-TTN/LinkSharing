grails {
	mail {
		host = "smtp.gmail.com"
		port = 465
		username = "arpit.sohar10@gmail.com"
		password = "123@Arpit"
		props = ["mail.smtp.auth":"true",
		         "mail.smtp.socketFactory.port":"465",
		         "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
		         "mail.smtp.socketFactory.fallback":"false"]
	}
}


grails.mail.default.from = "server@yourhost.com"
grails.mail.disabled = false
