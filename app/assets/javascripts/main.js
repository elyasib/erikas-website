var closeSideBar;
var toggleSideBar;
var scrollToAnch;
var scrollToAnch1;
var collapseMenuBar;
var formBehavior;
var $secondBox=$(document.getElementById("box2"));

$(document).ready(function() {
	var $pageWrapper = $("#pageWrapper");
	var $doc = $(document);
	var $menuBar = $("#menuBar");
	var $body=$('body');
	var currBox = 0;
	var lastBox = 0;
	var lastTarget="";
	var scrollFinished=true;
	var $htmlBody=$("html,body");
	var $Box1=$(document.getElementById("box1"));
	var $innBox1=$(document.getElementById("innbox1"));

	function switchClass(targetId,class1,class2) {
		targetId.removeClass(class2);
		targetId.addClass(class1);
	}

	switchClass($menuBar,"toggleColorTop","toggleColorNop");

	//function added to JQuery.fn to enable custom scrolling
	;(function($) {
		"use strict";
		var instance;
		var swipeDeltaY0 = 0;
		var swipeDeltaY1 = 0;
		function DiscreteScroll($container) {
			this.$container = $container;	
		}
		DiscreteScroll.prototype.scrollToBox = function() {
			var $currBox=$(document.getElementById("box"+currBox.toString()));
			console.log("started");
			var $currinnBox=$(document.getElementById("innbox"+currBox.toString()));
			var $previnnBox=$(document.getElementById("innbox"+lastBox.toString()));
			$htmlBody.animate({scrollTop: $currBox.offset().top},800,'easeInOutQuart',function() {
				$currinnBox.addClass("zoomBlock").removeClass("willScroll");
				$previnnBox.removeClass("zoomBlock");
				if (currBox === 0) {
					switchClass($menuBar,"toggleColorTop","toggleColorNop");
				}
				else {
					switchClass($menuBar,"toggleColorNop","toggleColorTop");
				}
				setTimeout(function(){scrollFinished=true;},400);
			});
		};	
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
		};
		DiscreteScroll.prototype.handleScroll = function(e) {
			e.preventDefault();
			if (lastTarget !== e.originalEvent.target && scrollFinished === true) { 
				scrollFinished = false;
				lastTarget = e.originalEvent.target;
				swipeDeltaY1 = e.type === "touchmove" ? (e.originalEvent.touches[0].screenY - swipeDeltaY0) : e.originalEvent.wheelDelta;

				if (swipeDeltaY1 > 0) { //scrolling upward
					if (currBox > 0) {
						lastBox = currBox;
						currBox--;
						console.log("preparing");
						$(document.getElementById("innbox"+currBox.toString())).addClass("willScroll");
						console.log("scheduling");
						setTimeout(function(){instance.scrollToBox();},50);
					}
					else {
						lastTarget = "";
						scrollFinished = true;
					}
				}
				else {  //scrolling downward
					if (currBox < 3) { //here should go the total number of preview blocks
						lastBox = currBox;
						currBox++;
						console.log("preparing");
						$(document.getElementById("innbox"+currBox.toString())).addClass("willScroll");
						console.log("scheduling");
						setTimeout(function(){instance.scrollToBox();},50);
					}
					else {
						lastTarget = "";
						scrollFinished = true;
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
		var $formIn = $(formIn);
		var $formOut = $(formOut);
		$doc.on("click",btnSideBar,function (e) {
			e.preventDefault();
			$pageWrapper.addClass("toggled");
			$formOut.fadeOut("slow");
			$formIn.fadeIn("slow");
		}); 
	};
	
	closeSideBar = function(btnClose,form1,form2) {
		var $form1 = $(form1);
		var $form2 = $(form2);
		$doc.on("click",btnClose, function(e) { 
			e.preventDefault(); 
			$pageWrapper.toggleClass("toggled"); 
			$form1.fadeOut("slow");
			$form2.fadeOut("slow");
		});
	};
	
	scrollToAnch1 = function(scrollBtn) {
		scrollBtn.on("click",function(){
			currBox=1;
			lastBox=0;
			lastTarget="";
			$body.animate({scrollTop: $Box1.offset().top},800,'easeInOutQuart',function() {
				$innBox1.addClass("zoomBlock");
				switchClass($menuBar,"toggleColorNop","toggleColorTop");
			});
		});
	};
	
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
	};
	
	
	collapseMenuBar = function(menuBarWrapper,listWrapper) {
		var $listWrapper = $(listWrapper);
		$doc.on('click', function(event) {
			if (!$(event.target).closest(menuBarWrapper).length) {
				$listWrapper.collapse('hide');
			}
		});
	};
});

