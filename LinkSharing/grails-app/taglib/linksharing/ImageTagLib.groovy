package linksharing

class ImageTagLib {
	static defaultEncodeAs = [taglib: 'text']
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	static namespace = "ls1"
	def userImage = { attrs, body ->
		//User user = User.get(attrs.userId)
		out << "<img class=\"img-rounded img-responsive center-block profileImage\" " +
				"src=\"${createLink(controller: 'user', action: 'image', params: [userId: attrs.userId])}\">"
	}
}
