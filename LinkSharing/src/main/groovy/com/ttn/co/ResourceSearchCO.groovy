package com.ttn.co

import grails.validation.Validateable
import linksharing.Resource
import linksharing.Visibility

/**
 * Created by mayank on 7/5/17.
 */
class ResourceSearchCO extends SearchCO implements Validateable {
	Long topicId
	Visibility visibility


	static namedQueries = {
		search { ResourceSearchCO co, Resource resource ->
			if (co.topicId == resource.topic.id) {
				eq('topicId', topicId)
			}

		}
	}


	@Override
	String toString() {
		return "ResourceSearchCO{" +
				"topicId=" + topicId +
				'}'
	}
}
