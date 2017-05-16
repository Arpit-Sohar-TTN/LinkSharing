package com.ttn.vo

import linksharing.LinkResource
import linksharing.Resource
import linksharing.Topic
import linksharing.User

/**
 * Created by mayank on 10/5/17.
 */

class ResourceVO {
	Long id
	String description
	User createdBy
	Topic topic
	String path
	int rateCount
	Boolean isRead

	@Override
	String toString() {
		return "ResourceVO{" +
				"id=" + id +
				", description='" + description + '\'' +
				", createdBy=" + createdBy +
				", topic=" + topic +
				", path='" + path + '\'' +
				", rateCount=" + rateCount +
				'}'
	}

	boolean check() {
		Resource resource = Resource.get(id)
		/*if (resource instanceof LinkResource) {
			return true
		} else {
			return false
		}*/
		return (resource instanceof LinkResource) ? true : false
	}
}
