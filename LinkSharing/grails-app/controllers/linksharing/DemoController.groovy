package linksharing

import org.springframework.context.MessageSource

class DemoController {
	static defaultAction = "demo"
	MessageSource messageSource

	def index() {
		String message = messageSource.getMessage("default.show.label", ["text"] as Object[], Locale.getDefault())
		message += " " + messageSource.getMessage("demo.test.defaultMessage", ["henry", "base"] as Object[], Locale.getDefault())
		message += " " + messagesource.getMessage("hello.japan", null, Locale.JAPANESE)
		render "${controllerName}/${actionName},message"
	}
}
