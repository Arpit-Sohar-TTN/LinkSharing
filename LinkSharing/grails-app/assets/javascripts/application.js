// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require bootstrap.min
//= require linkSharing
//= require navbar
//= require navbar2
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}
function ratePost(id) {
    var resourceId = id
var score = $("#score option:selected").text()
    $.ajax({
        url:'/resource/ratePost',
        data:{
            resourceId:resourceId,
            score:score
        },
        success: function () {
            $("#msg").html("Resource Rated Successfully")
            $("#msg").addClass(' alert alert-info')
            $("#score").hide()
            $("#rate").hide()
            $("#firstRateMsg").hide()

        },
        error: function () {
            $("#msg").html("Resource rating failed, Please Try after some time")
            $("#msg").addClass(' alert alert-danger')
            $("#score").hide()
            $("#rate").hide()
        }

    })
}

function toggleIsRead(id) {
    var resourceId = id
    $.ajax({
        url:'/readingItem/toggleIsRead',
        data:{
            id:resourceId
        },
        success:function () {
            $("#inboxMsg"+id).hide()
        },
        error: function () {
            $("#inboxMsg"+id).hide()
        }
    })

}
function toggleIsSubscribe(id) {
    var resourceId = id
    $.ajax({
        url:'/user/toggleIsSubscribe',
        data:{
            id:resourceId
        },
        success:function (response) {
            $("#msg").html(response.success)
            $("#msg").addClass("alert alert-info")
            if( response.success == "Subscribed topic") {
                $("#subscribeInfo").html("Unsubscribe")

            }
            else {
                $("#subscribeInfo").html("Subscribe")
                $("#topic"+resourceId).hide()
            }

        },
        error: function () {
            $("#msg").html("Error in response")
        }
    })
}
function editTopic(id) {
    var topicName = $("#topicName"+id).val()
    var visibility = $("#visibility"+id+" option:selected").text()
    var seriousness = $("#seriousness"+id+" option:selected").text()
    console.log(visibility + seriousness)
    var resourceId = id
    $.ajax({
        url:'/user/editTopic',
        data:{
            id:id,
            topicName:topicName,
            visibility:visibility,
            seriousness:seriousness
        },
        success:function (response) {
            $("#msg").html(response.success)
            $("#msg").addClass("alert alert-info")
        },
        error: function () {
            $("#msg").html("Error in response")
        }
    })
}
function editTopicProp(id) {
    var topicId = id
    var seriousness = $("#seriousnessSubs"+id+" option:selected").val()
    console.log(seriousness)
    $.ajax({
        url:'/user/editTopicProp',
        data:{
            topicId:topicId,

            seriousness:seriousness
        },
        success:function (response) {
            $("#msg").html(response.success)
            $("#msg").addClass("alert alert-info")
        },
        error: function () {
            $("#msg").html("Error in response")
            $("#msg").addClass("alert alert-info")

        }
    })
}
function deleteTopic(id) {
    var topicId=id
    $.ajax({
        url:'/topic/delete',
        data:{
            id:topicId
        },
        success:function (response) {
            $("#msg").html(response.success)
            $("#msg").addClass("alert alert-info")
            $("#topic"+id).hide()
        },
        error: function () {
            $("#msg").html("Error in response")
            $("#msg").addClass("alert alert-info")

        }
    })
}

function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();

    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
   var userCO = {
       firstName: profile.getName().split(" ")[0],
       lastName: profile.getName().split(" ")[1],
       email: profile.getEmail(),
       userName: profile.getName().split(" ")[0],
       password: "123456"
   }
   console.log(userCO)
    $.ajax({
        url:'/login/loginWithGoogle',
        type: 'POST',
        data:userCO,
        success:function () {
            $("#msg").html("Wish to continue to application <a href='/login/index'>Home</a> or not" +
                " <a href='javascript: signOut()'>Logout</a>")
                console.log(34)
            $("#msg").addClass("alert alert-info")

            // location.href = "http://localhost:8080/user/index"
        },
        error: function () {
            $("#msg").html("Error in response")
            $("#msg").addClass("alert alert-info")

        }
    })
}
function signOut() {
    console.log(2)
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    $('#msg').html("")
    $('#msg').removeClass("alert alert-info")
}



