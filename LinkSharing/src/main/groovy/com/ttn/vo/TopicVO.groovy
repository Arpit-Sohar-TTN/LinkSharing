package com.ttn.vo

import com.sun.org.apache.xpath.internal.operations.Bool
import groovy.transform.ToString
import linksharing.User
import linksharing.Visibility

/**
 * Created by arpit on 8/5/17.
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
