package com.ttn.co

import grails.validation.Validateable
import linksharing.User
import linksharing.Visibility


/**
 * Created by mayank on 5/5/17.
 */

class TopicCO implements Validateable {
	Long id
	String topicName
	User createdBy
	Date dateCreated
	Date lastUpdated
	Visibility visibility

	static constraints = {
		topicName blank: false
	}

	@Override
	String toString() {
		return "TopicCO{" +
				"topicName='" + topicName + '\'' +
				", createdBy=" + createdBy +
				", dateCreated=" + dateCreated +
				", lastUpdated=" + lastUpdated +
				", visibility=" + visibility +
				'}'
	}
}
