/*
$('.dropdown-menu.price-dropdown select').click(function (e) {
    e.stopPropagation();
});*/
function checkPasswordMatch() {

    var password = $("#pass").val();
    var confirmPassword = $("#confPass").val();
    console.log(password +" "+confirmPassword)
    if (!(password == confirmPassword)){
        console.log(password +" "+confirmPassword)
        $("#pass").cleanData;
        $("#confPass").cleanData;
        $("#validatorMessage").show()
        $("#validatorMessage").html("<b>Password Mismatch</b>");

        $("#subBtn").prop('disabled',true);
    }else {
        $("#validatorMessage").hide()
        $("#subBtn").prop('disabled',false);
    }
}