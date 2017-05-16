package com.ttn.vo
/**
 * Created by mayank on 10/5/17.
 */
class UserVO {
	Long id
	String userName
	String firstName
	String lastName
	int noOfSubscriptions
	int noOfCreatedTopics
	List<TopicVO> topicSubscriptionList
	List<ResourceVO> resourceVOList
	List<TopicVO> createdTopics

}
