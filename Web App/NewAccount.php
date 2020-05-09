<?php
    session_start();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Sign Up - Odeum</title>
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

            .signupbox {
                float: right;
                margin: auto;
                text-align: right;
                background-color: rgb(242, 230, 230);
                position: relative;
                width: 30%;
                min-width: 300px;
                height: 800px;
                /*min-width: 1000px;*/
                top: 20px;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

			.signupbox form {
                display: inline-block;
                text-align: left;
                width: 100%;
				padding: 20px;
				/*background-color: rgb(237, 239, 242);*/
				/*box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 3;*/
			}

            .signupbox strong {
                color: rgb(112, 0, 6);
            }

            .signupbox p {
                color: rgb(255, 0, 0);
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

            .createacc {
                z-index: 1;
                position: absolute;
                font-style: italic;
                font-size: 80px;
                line-height: 100%;
                left: 100px;
                color: rgb(112, 0, 6);

                text-shadow: 5px 5px 0px rgba(255,255,255,0.5);
            }

            .createacc #join {
                letter-spacing: 75px;
                color: rgb(66, 64, 65);
            }

            .createacc h2 {
                line-height: 10%;
                font-size: 35px;
                color: rgb(66, 64, 65);
                text-shadow: 2px 2px 0px rgba(255,255,255,0.4);
            }

		</style>
</head>

<body>
    <div class = 'logo'>
        <a href="LoginPage.php"><img src="Images/Odeum.png"/></a>
    </div>
    <div class ='createacc'>
        <h1 id='join'>JOIN</h1>
        <h1>ODEUM</h1>
        <h2>The Easiest Ticket Booking Around</h2>
    </div>
    <div class = 'signupbox'>
        <form action="SignUpConfirm.php" method="POST">
            <strong>Name</strong><br>
            <input type="text" name="name" placeholder="Enter your Name" required>
            <br><br>

            <strong>Username</strong><br>
            <input type="text" name="username" placeholder="Enter a Username" required/>
            <?php
                if ($_SESSION["UNameError"] == true)
                {
                    echo "<p>Username is already taken</p>";
                }
                else
                {
                    echo "<br><br>";
                }
            ?>

            <strong>Email Address</strong><br>
            <input type="text" name="email" placeholder="Enter you Email" required/>
            <br><br>

            <strong>Password</strong><br>
            <input type="password" name="password" placeholder="Choose a password" required>
            <br><br>

            <strong>Repeat Password</strong><br>
            <input type="password" name="Rpassword" placeholder="Retype your password" required>
            <?php
                if ($_SESSION["RPassError"] == true)
                {
                    echo "<br><p>Passwords do not match</p>";
                }
                else
                {
                    echo "<br><br>";
                }
            ?>

            <strong>Address</strong><br>
            <input type="text" name="address" placeholder="Enter your address" required/>
            <br><br>

            <strong>Contact Number</strong><br>
            <input type="text" name="contactno" placeholder="Enter your contact number" required/>
            <br><br>

            <strong>Date Of Birth</strong><br>
            <input type="date" name="dob" required/>
            <br><br>

            <input type="submit" value="Confirm Sign Up" class="submitbutton">
            <br><br>
        </form>
    </div>
    <br><br>
</body>
</html>

<?php
    $_SESSION["RPassError"] = false;
    $_SESSION["UNameError"] = false;
?>