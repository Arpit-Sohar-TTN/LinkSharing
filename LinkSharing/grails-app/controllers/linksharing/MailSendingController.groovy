package linksharing

import org.apache.commons.lang.RandomStringUtils

class MailSendingController {
	String randomString
	def mailService

	def generator() {
		String charset = (('A'..'Z') + ('0'..'9')).join()
		Integer length = 9
		randomString = RandomStringUtils.random(length, charset.toCharArray())
	}

	def sendMail() {
		generator()
		User user = User.findByUserName(params.username)
		mailService.sendMail {
			multipart(1)

			to user.email
			//attach(new File('/home/mayank/Desktop/LinkSharing-LinkSharingV2/LinkSharing/grails-app/assets/images/forgetPassword.png'))
			subject "Forget Password"
			text "your PASSWORD is \n ${randomString} \nchange your PASSWORD in edit profile after logging in using this PASSWORD"
		}
		user.password = randomString
		user.save(flush: true)
		flash.message = 'A mail Send Successfully to your email-id. In case having problem finding e-mail, contact system admin. '
		redirect(controller: 'login', action: 'index')
	}

	def sendInvitation() {
		User user = session.user
		def fullname = "${user.firstName} ${user.lastName}"
		def sendAddress = params.email
		def topicId = params.topicvalue
		mailService.sendMail {
			// multipart(1)

			to sendAddress
			//attach(new File('/home/mayank/Desktop/LinkSharing-LinkSharingV2/LinkSharing/grails-app/assets/images/forgetPassword.png'))
			subject "Topic Invitation"
			text "You have been invited by ${fullname} to subscribe the topic plz visit following link \n http://localhost:8080/topic/showTopic/${topicId}"
		}
		flash.message = 'invitation has been send to your given email address'
		redirect(controller: 'user', action: 'index')
	}
}
