<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8" />
        <title>A Touch Optimized Gallery | Tutorialzine Freebie</title>
        
        <!-- Make the page take the full width of the device-->
        <meta name="viewport"  content="width=device-width, initial-scale=1.0, user-scalable=0, maximum-scale=1.0" />
         
        <!-- The stylesheet -->
        <link rel="stylesheet" href="assets/css/styles.css" />
        <link rel="stylesheet" href="assets/touchTouch/touchTouch.css" />
        
        <!-- Google Fonts -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Dancing+Script" />
        

	<link rel="stylesheet" href="css/bootstrap.css">
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<link rel="stylesheet" href="css/sheet.css">
	<link rel="stylesheet" href="css/ConnecteJoueur.css">
	<!--<script src="js/jquery-3.3.1.min.js"></script>-->

        
        <jsp:include page="/WEB-INF/header.jsp" />
</head>
 <body>

		<header>
			<h1>TouchTouch</h1>
			<h2>A gallery optimized for touch devices</h2>
		</header>
		
        <div class="thumbs" data-gallery="one">
	        <a href="http://farm8.staticflickr.com/7013/6754656011_3de2cc73a2_z.jpg" style="background-image:url(http://farm8.staticflickr.com/7013/6754656011_3de2cc73a2_m.jpg)" title="Lion Rock"></a>
	        <a href="http://farm8.staticflickr.com/7042/6895252645_45f5dfffaa_z.jpg" style="background-image:url(http://farm8.staticflickr.com/7042/6895252645_45f5dfffaa_m.jpg)" title="Holsten Gate"></a>
	        <a href="http://farm8.staticflickr.com/7183/6943277737_21b521659c_z.jpg" style="background-image:url(http://farm8.staticflickr.com/7183/6943277737_21b521659c_m.jpg)" title="Blue Hour"></a>
	        <a href="http://farm8.staticflickr.com/7047/7000951429_5eae078a62_z.jpg" style="background-image:url(http://farm8.staticflickr.com/7047/7000951429_5eae078a62_m.jpg)" title="Waikato Landscape"></a>
        </div>

        <div class="thumbs">
	        <a href="http://farm6.staticflickr.com/5346/7051537899_efc7a44830_z.jpg" data-gallery="two" style="background-image:url(http://farm6.staticflickr.com/5346/7051537899_efc7a44830_m.jpg)" title="Tauranga Bridge"></a>
	        <a href="http://farm8.staticflickr.com/7268/6951148260_440661b4d1_z.jpg" data-gallery="two" style="background-image:url(http://farm8.staticflickr.com/7268/6951148260_440661b4d1_m.jpg)" title="East Coast"></a>
	        <a href="http://farm8.staticflickr.com/7259/6930112984_5fcc076288_z.jpg" data-gallery="two" style="background-image:url(http://farm8.staticflickr.com/7259/6930112984_5fcc076288_m.jpg)" title="Cathedral Cove"></a>
	        <a href="http://farm8.staticflickr.com/7276/6886626710_047cd03acb_z.jpg" data-gallery="two" style="background-image:url(http://farm8.staticflickr.com/7276/6886626710_047cd03acb_m.jpg)" title="The Pond"></a>
	        <a href="http://farm8.staticflickr.com/7020/6683299491_f2b5f6aa8b_z.jpg" data-gallery="two" style="background-image:url(http://farm8.staticflickr.com/7020/6683299491_f2b5f6aa8b_m.jpg)" title="Good Night"></a>
        </div>

		<p id="credit">
			Photos: 
			<a href="http://www.flickr.com/photos/zanthia/6754656011/">Lion Rock</a>
			<a href="http://www.flickr.com/photos/zanthia/6895252645/">Holsten Gate</a>
			<a href="http://www.flickr.com/photos/zanthia/6943277737/">Blue Hour</a>
			<a href="http://www.flickr.com/photos/zanthia/7000951429/">Waikato Landscape</a>
			<a href="http://www.flickr.com/photos/zanthia/7051537899/">Tauranga Bridge</a>
			<a href="http://www.flickr.com/photos/zanthia/6951148260/">East Coast</a>
			<a href="http://www.flickr.com/photos/zanthia/6930112984/">Cathedral Cove</a>
			<a href="http://www.flickr.com/photos/zanthia/6886626710/">The Pond</a>
			<a href="http://www.flickr.com/photos/zanthia/6683299491/">Good Night</a>
		</p>
        <footer>
	        <h2><i>Freebie:</i> Touch Optimized Gallery</h2>
            <a class="tzine" href="http://tutorialzine.com/2012/04/mobile-touch-gallery/">Head on to <i>Tutorial<b>zine</b></i> to download this freebie</a>
        </footer>
        
        <!-- JavaScript includes - jQuery, turn.js and our own script.js -->
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script src="assets/touchTouch/touchTouch.jquery.js"></script>
		<script src="assets/js/script.js"></script>
		
		<!-- BSA AdPacks code. Please ignore and remove.--> 
        <script src="http://cdn.tutorialzine.com/misc/adPacks/v1.js" async></script>
        
    </body>
</html>