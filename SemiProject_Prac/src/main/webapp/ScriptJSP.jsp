<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous">
  </script>
  
  <script>
    $(function(){
    	let count =2;
    	$(window).scroll(function(){
    		if(window.innerHeight+ window.scrollY>= document.body.offsetHeight) {
    		 $.ajax({
                 type     : 'get',
                 url      : 'sendlist.do',
                 data	  : {"count": count++},
                 datetype :  'JSON',
                 success : function(text){
 	      			let list = $.parseJSON(text);
 	      			for(let index = 0; index<list.length; index++){
 	      				console.log(list[index].title)
 	      				let card = $("<div class=card>");
 	      				let card_body= $("<div class=card-body>");
 	      				let title = $("<h5 class=card-title>");
 	      				let text2 = $("<p class=card-text>");
 	      				
 	      				title.text(list[index].contents);
 	      				text2.text(list[index].write);
 	      				
 	      				card_body.append(title);
 	      				card_body.append(text2);
 	      				
 	      				card.append(card_body);
 	      				
 	      				$("#d_body").append(card);			
 	      			}
                 },
                 error : function(){
                     alert("error");
                 }
             })
    	    }})
    })
    </script>