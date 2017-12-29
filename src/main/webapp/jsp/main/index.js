$(function() {



   $("#button1").click(function(){
       alert("hello world");

       $.ajax({
           type: "post",
           url: "index/input.action",
           dataType: "text",
           data: {id: 6, city: "南京"},
           cache: false,
           async: false,
           success: function(data) {
               console.log("suc");
           },
           error: function(data) {
               console.log("fail");
           }
       });

       alert("end");

   });




});

