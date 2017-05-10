package com.ttn.co

import grails.validation.Validateable
import linksharing.User
import linksharing.Visibility

import javax.validation.Valid

/**
 * Created by arpit on 5/5/17.
 */

class TopicCO implements Validateable{

    String topicName
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static constraints = {
        topicName blank: false
    }

    @Override
    public String toString() {
        return "TopicCO{" +
                "topicName='" + topicName + '\'' +
                ", createdBy=" + createdBy +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", visibility=" + visibility +
                '}';
    }
}
