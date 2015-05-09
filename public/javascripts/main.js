"use strict";

var closeSideBar = undefined;
var toggleSideBar = undefined;
var scrollToAnch = undefined
var collapseMenuBar = undefined
var formBehavior = undefined

//function added to JQuery.fn to enable custom scrolling
$(document).ready(function() {
	var $pageWrapper = $("#pageWrapper");
	var $doc = $(document);
	var $menuBar = $("#menuBar");
	var $win = $(window);
	var $body=$('body');

	function switchClass(targetId,class1,class2) {
		targetId.removeClass(class2);
		targetId.addClass(class1);
	}

	switchClass($menuBar,"toggleColorTop","toggleColorNop");

	;(function($) {
		"use strict";
		var instance;
		var swipeDeltaX0 = 0;
		var swipeDeltaY0 = 0;
		var swipeDeltaX1 = 0;
		var swipeDeltaY1 = 0;
		var currBox = 0;
		var lastBox = 0;
		var lastTarget="";
		var prueba = "esta es una prueba";
		function DiscreteScroll($container) {
			this.$container = $container;	
		}
		DiscreteScroll.prototype.scrollToBox = function() {
			var $currBox=$(document.getElementById("box"+currBox.toString()));
			var $currinnBox=$(document.getElementById("innbox"+currBox.toString()));
			var $previnnBox=$(document.getElementById("innbox"+lastBox.toString()));
			$body.animate({scrollTop: $currBox.offset().top},800,'easeInOutQuint',function() {
				$currinnBox.addClass("zoomBlock");
				$previnnBox.removeClass("zoomBlock");
			});
		}
		DiscreteScroll.prototype.enable = function() {
			this.$container.on(
				"mousewheel.discretescroll DOMMouseScroll.discretescroll touchmove.discretescroll", this.handleScroll
			);
			this.$container.on(
				"touchstart.discretescroll", this.handleTStart
			);
		};
		DiscreteScroll.prototype.handleTStart = function(e) {
			swipeDeltaY0 = e.originalEvent.touches[0].screenY;
		}
		DiscreteScroll.prototype.handleScroll = function(e) {
			e.preventDefault();
			if (lastTarget !== e.originalEvent.target) { 
				lastTarget = e.originalEvent.target;
				swipeDeltaY1 = e.type === "touchmove" ? (e.originalEvent.touches[0].screenY - swipeDeltaY0) : e.originalEvent.wheelDelta;

				if (swipeDeltaY1 > 0) { //scrolling upward
					if (currBox > 0) {
						lastBox = currBox;
						currBox--;
						instance.scrollToBox();
						if (currBox === 0) switchClass($menuBar,"toggleColorTop","toggleColorNop");
					}
					else {
						lastTarget = "";
					}
				}
				else {  //scrolling downward
					if (currBox < 3) { //here should go the total number of preview blocks
						lastBox = currBox;
						currBox++;
						instance.scrollToBox();//scrollToBox(e);
						switchClass($menuBar,"toggleColorNop","toggleColorTop");
					}
					else {
						lastTarget = "";
					}
				}

			}
		};
		DiscreteScroll.prototype.disable = function() {
			this.$container.off(".discretescroll");
		};
		$.fn.discrete_scroll = function(option) {
			if (!instance) {
				console.log("creando instancia");
				instance = new DiscreteScroll(this);
				console.log("instancia creada");
			}
			if (instance && typeof option === "undefined") {
				console.log("habilitando custom scroll");
				instance.enable();
				console.log("custom scroll habilitado");
			}
			if (instance && option === "enable") {
				instance.disable();
			}
		};
	})(jQuery);
	
	toggleSideBar = function(btnSideBar,formIn,formOut) {
	//function toggleSideBar(btnSideBar,formIn,formOut) {
		var $formIn = $(formIn);
		var $formOut = $(formOut);
		$doc.on("click",btnSideBar,function (e) {
			e.preventDefault();
			$pageWrapper.addClass("toggled");
			$formOut.fadeOut("slow");
			$formIn.fadeIn("slow");
		}); 
	}
	
	closeSideBar = function(btnClose,form1,form2) {
		var $form1 = $(form1);
		var $form2 = $(form2);
		$doc.on("click",btnClose, function(e) { 
			e.preventDefault(); 
			$pageWrapper.toggleClass("toggled"); 
			$form1.fadeOut("slow");
			$form2.fadeOut("slow");
		});
	}
	
	scrollToAnch = function(e) {
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
	
	
	formBehavior = function() {
		$doc.on("keypress", ".tabOnEnter" , function(e) {
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
	
	
	collapseMenuBar = function(menuBarWrapper,listWrapper) {
		var $listWrapper = $(listWrapper);
		$doc.on('click', function(event) {
			if (!$(event.target).closest(menuBarWrapper).length) {
				$listWrapper.collapse('hide');
			}
		});
	}
});

