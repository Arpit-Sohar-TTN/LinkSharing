package linksharing

import com.ttn.co.SearchCO

class User {

	String userName
	String firstName
	String lastName
	String email
	String password
	Byte[] photo //c1
	boolean isAdmin = false
	boolean isActive = true
	Date dateCreated
	Date lastUpdated
	String confirmPassword
	static hasMany = [topics: Topic, subscriptions: Subscription, readingItems: ReadingItem, resources: Resource]

	static transients = ['fullName', 'confirmPassword']

	String getFullName() {
		return "${firstName}  ${lastName}"
	}
	static mapping = {
		sort id: 'desc'
		topics lazy: false
		photo(sqlType: 'longBlob')
	}

	static constraints = {
		firstName blank: false, nullable: false
		lastName blank: false, nullable: false
		userName blank: false, nullable: false, unique: true
		email email: true, nullable: false, unique: true, blank: false
		password nullable: false, blank: false, size: 5..15
		confirmPassword nullable: true, size: 5..15, validator: { confirmPassword, user ->
			if (!user.id && confirmPassword && !confirmPassword.equalsIgnoreCase(user.password))
				return false
		}
		isAdmin nullable: true
		isActive nullable: true
		photo nullable: true, blank: true


	}


	List<ReadingItem> getUnReadResources(SearchCO searchCO) {
//        List<ReadingItem> unreadItems =[]

		if (searchCO.q) {
			List<ReadingItem> unreadItems = ReadingItem.createCriteria().list {
				eq('user', this)

				resource {
					ilike('description', "%${searchCO.q}%")
				}

				eq('isRead', false)
				max searchCO.max.toString()
				order('lastUpdated', searchCO.order)
			}

			return unreadItems
		}

	}

	@Override
	 String toString() {
		return userName
	}


	static List getSubscribedTopic(User user) {
		List list = createCriteria().list {
			projections {
				createAlias('subscriptions', 's')
				property('s.topic')
			}
			eq('s.user', user)
		}
		return list
	}
}
