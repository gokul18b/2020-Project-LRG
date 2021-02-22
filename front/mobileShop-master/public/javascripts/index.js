/**
 * Created by gokul on 21-02-2018.
 */
$(document).ready(function () {

   


    $("#login").click(function () {
        var mobile = $("#mobile").val().trim();
        var password = $("#password").val().trim();


        if (mobile.length == 0 || password.length == 0) {
            alert('Should enter username and password')
        } else {
            if (mobile == "admin" && password == "admin") {
                window.location.href = "/adminhome";
                $("#mobile").val("")
                $("#password").val("")
            } else {
                $.ajax({
                    type: "POST",
                    url: "/api/signin",
                    data: {
                        mobile: mobile,
                        password: password
                    },
                    success: function (result) {
                        if (result.length == 0) {
                            alert('Invalid username and password')
                        } else {
                            window.location.href = "/userhome";
                            $("#mobile").val("")
                            $("#password").val("")
                        }
                    }
                });
            }

        }

    });

});

