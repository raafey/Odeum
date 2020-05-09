<?php
    session_start();
?>

<!DOCTYPE html>
<html>

<script>
        setTimeout(function() {
            window.location.href="UserHome.php";
        }, 5000);
</script>

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

            .logo {
                width: 100%;
                height: 100px;
                background-color: white;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

			.logo img {
				display: block;
			    margin-left: auto;
		    	margin-right: auto;
                float: right;
    			width: 250px;
			}

            .welcome {
                position: relative;
                width: 50%;
                height: 450px;
                top: 20px;
                margin: auto;
                background-color: rgb(242, 230, 230);
                text-align: center;
                color: rgb(64, 66, 65);
                border-radius: 50px;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .welcome img {
                width: 30%;
                margin: auto;
            }

            .welcome h1 {
                font-size: 40px;
                width: 100%;
                font-style: italic;

                text-shadow: 5px 5px 16px rgba(0,0,0,0.2);
                z-index: 2;
            }

            .load {
                position: relative;
                top: 20px;
                left: 42.5%;
                border: 16px solid rgb(242, 230, 230);
                border-radius: 50%;
                border-top: 12px solid rgb(115, 43, 44);
                border-bottom: 12px solid rgb(115, 43, 44);
                width: 100px;
                height: 100px;
                -webkit-animation: spin 2s linear infinite;
                animation: spin 2s linear infinite;
            }

            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }

		</style>
</head>

<body>
    <div class = 'logo'>
        <a href="LoginPage.php"><img src="Images/Odeum.png"/></a>
    </div>

    <div class = 'welcome'>
            <br><h1>WELCOME TO</h1>
            <img src="Images/Odeum.png"/>
            <?php
                $UName = $_SESSION["username"];
                echo "<h1 style='color: rgb(115, 43, 44)'>$UName</h1>";
            ?>
            <div class="load"></div>  
    </div>

</body>
</html>