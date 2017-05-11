package com.ttn.vo

import linksharing.Subscription
import linksharing.Topic

/**
 * Created by arpit on 10/5/17.
 */
class UserVO {
    int noOfSubscriptions
    int noOfCreatedTopics
    List<TopicVO> topicSubscriptionList
    List<ResourceVO> resourceVOList

}
