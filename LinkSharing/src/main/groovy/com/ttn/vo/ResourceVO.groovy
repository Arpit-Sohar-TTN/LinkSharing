package com.ttn.vo

import linksharing.Topic
import linksharing.User

/**
 * Created by arpit on 10/5/17.
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
    public String toString() {
        return "ResourceVO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                ", path='" + path + '\'' +
                ", rateCount=" + rateCount +
                '}';
    }
}
