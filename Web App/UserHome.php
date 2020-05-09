<?php
	include_once './DBConnect.php';
?>

<?php
    session_start();
	if ($_SESSION["access"] != "allowed") {
		header("Location: LoginPage.php");
	}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Odeum - Home</title>
    <link rel="icon" type="image/png" href="Images/Favicon.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
			body {
				font-family: Helvetica;
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

			.logout a:hover {
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.logout a {
    position: absolute;
    top: 45px;
    right: 86.6%;
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

		</style>
</head>

<body>
	.
	<div class = 'logo'>
        <a href="UserHome.php"><img src="Images/Odeum.png"/></a>
		<div class = 'logout'><a href = "LogoutSession.php">Logout</a></div>
    </div>

   <br>
	<nav class="navbar navbar-default" style="top:20px;  bottom:20px;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Odeum</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="UserHome.php">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Events <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li>
						<form action="./EventNameSearch.php" method="get">
							<div class="form-group">
								<label for="name">Search by name:</label>
								<input type="text" id="name" name="name">
							</div>
						</form>
					</li>
					<li>
						<form action="./EventGenreSearch.php" method="get">
							<div class="form-group">
								<label for="genre">Search by genre:</label>
								<input type="text" id="genre" name="genre">
							</div>
						</form>
					</li>
					<li>
						<form action="./EventRatingSearch.php" method="get">
							<div class="form-group">
								<label for="rating">Search by rating:</label>
								<input type="text" id="rating" name="rating">
							</div>
						</form>
					</li>
					<li>
						<form action="./EventLocSearch.php" method="get">
							<div class="form-group">
								<label for="loc">Search by location:</label>
								<input type="text" id="loc" name="loc">
							</div>
						</form>
					</li>
					<li>
						<form action="./EventDateSearch.php" method="get">
							<div class="form-group">
								<label for="date">Search by date:</label>
								<input type="date" id="date" name="date">
								<input type="submit" value="search date">
							</div>
						</form>
					</li>
				</ul>
				</li>
				<li>
					
				</li>
				<li class="active"><a href="ViewTickets.php">View Tickets</a></li>
				<li class="active"><a href="ViewQuizzes.php">Trivia</a></li>
			</ul>
		</div>
	</nav>
	<br>

	<div class='table'>
		<table class="table table-hover">
			<thead>
				<tr><th><p><h3 style="color: #660000"><b>Recommended Events:</b></h3></p></th></tr>
			</thead>
			<tbody>
			
				<?php 
				$uname = $_SESSION["username"];
				$genreSql = "SELECT genre FROM odeum.event WHERE eventId in (SELECT eventId FROM odeum.ticket WHERE seatId in (SELECT seatId FROM odeum.seating_plan WHERE userId=(SELECT userId FROM odeum.user WHERE username='$uname'))) GROUP BY genre ORDER BY COUNT(*) DESC LIMIT 1";
				$genreResult = $conn->query($genreSql);
				$genre = $genreResult->fetch_assoc()["genre"];

				$eventSql = "SELECT * FROM event WHERE genre='$genre'";
				$result = $conn->query($eventSql);

				if ($result->num_rows > 0) {
				echo ".";
				?>

				<tr>
				<?php
					while($row = $result->fetch_assoc()) { ?>
						
						<td>
							<div style="text-align: center;">
								<div style="display: inline-block; width: 80%;"><img src="<?php echo $row["imageURL"]?>"></div>
							</div>
						</td>
	
					<?php
					}?>
				</tr>
				<?php	
				} else {
					echo "0 results";
				}
				$genreResult = $conn->query($genreSql);
				$genre = $genreResult->fetch_assoc()["genre"];
				$eventSql = "SELECT * FROM event WHERE genre='$genre'";
				$result = $conn->query($eventSql);

				if ($result->num_rows > 0) {
				echo ".";
				?>

				<tr>
				<?php
					while($row = $result->fetch_assoc()) { ?>
						
							<td>
								<div style="text-align: center;">
									<div style="display: inline-block;"><h4><?php echo $row["name"]?></h4></div>
								</div>
							</td>
					<?php
					}?>
				</tr>
				<?php	
				} else {
					echo "0 results";
				}
				?>

			</tbody>
		</table>

		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					
					<p><h3 style="color: white;">Stay up to date on events with the latest news and articles</h3></p><br>
							
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<a target="_blank" href="https://www.animatedtimes.com/avengers-endgame-pre-book-spi-cinemas-tickets-go-on-sale-in-some-international-markets/">
						<img src="https://www.animatedtimes.com/wp-content/uploads/2019/03/Avengers-Endgame-MCU-Rewatch.jpg" style="border: 6px solid #1a1a1a; padding: 5px; width: 100%;">
					</a>
					<table class="table" style="background-color: #f2f2f2">
						<thead>
							<tr>
								<th style="color: black;">Avengers: Endgame: Tickets Go On Sale In Some International Markets</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="col-sm-3">
					<a target="_blank" href="https://wdwnt.com/2019/04/funtime-with-toy-story-4-coming-to-tokyo-disney-resort-june-14th-september-1st/">
						<img src="https://wdwnt.com/wp-content/uploads/2019/04/funtime-with-toy-story-4-1-820x556.jpg" style="border: 6px solid #1a1a1a; padding: 5px; width: 100%;">
					</a>
					<table class="table" style="background-color: #f2f2f2">
						<thead>
							<tr>
								<th style="color: black;">“Funtime with Toy Story 4” Coming to Tokyo Disney Resort June 14th – September 1st</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="col-sm-3">
					<a target="_blank" href="https://www.theverge.com/2019/4/3/18294009/captain-marvel-cinematic-universe-billion-dollar-box-office">
						<img src="https://cdn.vox-cdn.com/thumbor/18rBj3X1uPOEJTTVI-bQ93G04ms=/0x0:5760x3840/920x613/filters:focal(2687x781:3607x1701):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/63345365/CaptainMarvel5c805147a2d03.0.jpg" style="border: 6px solid #1a1a1a; padding: 5px; width: 100%;">
					</a>
					<table class="table" style="background-color: #f2f2f2">
						<thead>
							<tr>
								<th style="color: black;">Captain Marvel is the latest Marvel movie to cross the billion-dollar mark</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="col-sm-3">
					<a target="_blank" href="https://www.theverge.com/2018/11/28/18115201/spider-man-into-the-verse-movie-review-miles-morales">
						<img src="https://cdn.vox-cdn.com/thumbor/92TojRtpfC9LA5cCXnmgM3a-t0I=/0x0:2040x1360/920x613/filters:focal(807x264:1133x590):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/62419018/SpiderVerse_cropped.0.jpg" style="border: 6px solid #1a1a1a; padding: 5px; width: 100%;">
					</a>
					<table class="table" style="background-color: #f2f2f2;">
						<thead>
							<tr>
								<th style="color: black;">Spider-Man: Into the Spider-Verse is dazzling, hilarious, and unique</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>

	</div>

</body>
</html>

