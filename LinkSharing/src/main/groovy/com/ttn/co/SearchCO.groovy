package com.ttn.co

import grails.validation.Validateable


/**
 * Created by arpit on 7/5/17.
 */
class SearchCO implements Validateable{
    String q
    Integer max
    Integer offset
    String order
    String sort
}
