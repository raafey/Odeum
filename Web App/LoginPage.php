<?php
    session_start();
	$_SESSION["invalid"] = false;
?>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Odeum</title>
    <link rel="icon" type="image/png" href="Images/Favicon.png">
    <style>
			body {
				font-family: Helvetica;
				font-size: 95%;
                min-width: 1200px;

                background-image: url("Images/Background2.jpeg");
                background-position: center;
                background-repeat: no-repeat;
                background-size: auto;
			}

			br {
				line-height: 200%;
			}

            * {
                box-sizing: border-box;
            }

            .loginbox {
                background-image: url("Images/LoginBox.png");
                background-repeat: no-repeat;
                background-size: contain;
                background-position: 100% 50%;
                position: relative;
                width: 100%;
                height: 500px;
                top: 20px;
                margin: auto;
                background-color: rgb(242, 230, 230);

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

			.loginbox form {
				width: 270px;
                position: absolute;
                top: 25%;
                right: 80px;
				padding: 20px;
				border-radius: 20px;
				/*background-color: rgb(237, 239, 242);*/
				/*box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 3;*/
			}

            .loginbox strong {
                color: rgb(112, 0, 6);
            }

            .signup a {
                position: absolute;
                top: 45px;
                right: 24.4%;
                color: rgb(112, 0, 6);
                background-color: rgb(242, 230, 230);
                text-decoration: none;
                font-weight: bold;
                padding: 10px;
                padding-top: 5px;
                padding-bottom: 5px;
                border: 2px solid rgb(112, 0, 6);
				border-radius: 4px;
                z-index: 3;
            }

            .signup a:hover {
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            }

			input {
				width: 98%;
				padding: 3px;
				border: 2px solid rgb(112, 0, 6);
				border-radius: 4px;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
			}

			.submitbutton {
				width: 50%;
				border: 2px solid rgb(112, 0, 6);
				border-radius: 4px;
				background-color: rgb(242, 230, 230);
				color: rgb(112, 0, 6);
				display: block;
			    margin-left: auto;
		    	margin-right: auto;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
			}

            .logo {
                width: 100%;
                height: 100px;
                background-color: white;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

			img {
				display: block;
			    margin-left: auto;
		    	margin-right: auto;
                float: right;
    			width: 250px;
			}

            .slideshow {
                z-index: 2;
                position: absolute;
                top: 20px;
                left: 20%;
                width: 400px;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            }

		</style>
</head>

<body>
    <div class = 'logo'>
        <a href="LoginPage.php"><img src="Images/Odeum.png"/></a>
    </div>
    <div class = 'signup'><a href = "NewAccount.php">SIGN UP</a></div>
    <div class = 'loginbox'>
        <form action="LoginSession.php" method="POST">
            <strong>Username</strong><br>
            <input type="text" name="username" placeholder="Enter your username" required/>
            <br><br>

            <strong>Password</strong><br>
            <input type="password" name="password" placeholder="Enter your password" required>
            <br><br>

            <input type="submit" value="Login" class="submitbutton">

            <?php
                if ($_SESSION["invalid"] == true)
                {
                    echo "<br><strong>Invalid Username / Password</strong>";
                }
            ?>
        </form>
    </div>
    <div class = 'slideshow'>
        <img class="mySlides" src="HomePosters/1.jpg" style="width: 100%">
        <img class="mySlides" src="HomePosters/2.jpg" style="width: 100%">
        <img class="mySlides" src="HomePosters/3.jpg" style="width: 100%">
        <img class="mySlides" src="HomePosters/4.jpg" style="width: 100%">
    </div>

    <script>
        var Ind = 0;
        carousel();

        function carousel()
        {
            var itr;
            var Image = document.getElementsByClassName("mySlides");
            for (itr = 0; itr < Image.length; itr++)
            {
                Image[itr].style.display = "none";  
            }
            Ind++;
            if (Ind > Image.length)
            {
                Ind = 1;
            }    
            Image[Ind-1].style.display = "block";  
            setTimeout(carousel, 5000);    
        }
    </script>
</body>
</html>

<?php
    $_SESSION["invalid"] = false;
?>