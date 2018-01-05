$(function() {



   $("#button1").click(function(){
       alert("request begin");

       $.ajax({
           type: "post",
           url: "user/register.action",
           dataType: "text",
           data: {suiusrnam: "李瑞锋", suimobile: "15900000000"},
           cache: false,
           async: false,
           success: function(data) {
               console.log("suc");
           },
           error: function(data) {
               console.log("fail");
           }
       });

       alert("request end");

   });




});

