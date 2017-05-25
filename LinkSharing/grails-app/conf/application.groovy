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

baseURL = "localhost:8080/login/google"
oauth {
	providers {

		google {
			api = org.scribe.builder.api.GoogleApi20
			key = 'oauth_google_key'
			secret = 'oauth_google_secret'
			successUri = '/oauth/google/success'
			failureUri = '/oauth/google/error'
			callback = "${baseURL}/oauth/google/callback"
			scope = 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
		}
	}
}