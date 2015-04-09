var toggleSideBar = function(btnSideBar,formIn,formOut) {
	$(document).on("click",btnSideBar,function (e) {
		e.preventDefault();
		$("#pageWrapper").addClass("toggled");
		$(formOut).fadeOut("slow");
		$(formIn).fadeIn("slow");
	}); 
}

var closeSideBar = function(btnClose,form1,form2) {
	$(document).on("click",btnClose, function(e) { 
		e.preventDefault(); 
		$("#pageWrapper").toggleClass("toggled"); 
		$(form1).fadeOut("slow");
		$(form2).fadeOut("slow");
	});
}

var scrollToAnch = function(e) {
                $('a[href*=#]:not([href=#])').click(function(e) {
                       if( $(e.target).is('a') ) {
                        	$(e.target).parent().parent().parent().collapse('hide');
                        } 
			if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') 
                            || location.hostname == this.hostname) {

                            var target = $(this.hash);
                            target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
                               if (target.length) {
                                 $('html,body').animate({
                                     scrollTop: target.offset().top
                                }, 1000);
                                return false;
                            }
                        }
                }); 
}

var scrollColor = function(menuBar,colorTop,colorScroll) {
	$(window).scroll(
		function() {
		if ($(this).scrollTop() > 20) {
			console.log("bot");
			$("#menuBar").removeClass("toggleColorTop");
			console.log($("#menuBar")[0].classList);
			$("#menuBar").addClass("toggleColorNop");
			console.log($("#menuBar")[0].classList);
		}
		else {
			console.log("top");
			$("#menuBar").removeClass("toggleColorNop");
			console.log($("#menuBar")[0].classList);
			$("#menuBar").addClass("toggleColorTop");
			console.log($("#menuBar")[0].classList);
		}
	});
}

var formBehavior = function(menuBar,colorTop,colorScroll) {
	$(document).on("keypress", ".tabOnEnter" , function(e) {
		if( e.keyCode ==  13 && !$(e.target).hasClass("submitForm") ) {
			e.preventDefault ();
			var nextElement = $('input[tabindex="' + (this.tabIndex+1)  + '"]');
			if(nextElement.length )
				nextElement.focus();
			else
				$('input[tabindex="1"]').focus();
		}
        });
}


var collapseMenuBar = function(menuBarWrapper,listWrapper) {
	$(document).on('click', function(event) {
		if (!$(event.target).closest(menuBarWrapper).length) {
			$(listWrapper).collapse('hide');
		}
	});
}
