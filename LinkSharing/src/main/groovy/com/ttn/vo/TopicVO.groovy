package com.ttn.vo

import groovy.transform.ToString
import linksharing.User
import linksharing.Visibility

/**
 * Created by mayank on 8/5/17.
 */
@ToString
class TopicVO {
	Long id
	String name
	Visibility visibility
	int count
	int noOfSubscribedUsers
	User createdBy
	Boolean isLoggedInUserSubscribed
}
