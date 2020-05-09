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
    <title>Select Seats</title>
    <link rel="icon" type="image/png" href="Images/Favicon.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <style>
			body {
				font-family: Helvetica;
				/*font-size: 95%;*/
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

			.table {
				width: 100%;
                height: 100%;
                background-color: white;

                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
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
	<nav class="navbar navbar-default" style="top:20px;">
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

    <?php 
		$ScID = $_GET["ScID"];
		$sqlQuery = "SELECT * FROM odeum.schedule WHERE schId = '$ScID';";
		$Result = mysqli_query($conn, $sqlQuery);

		$RCheck = mysqli_num_rows($Result);

		if ($RCheck > 0)
		{
			$ScRow = mysqli_fetch_assoc($Result);	
        }

        $eventID = $ScRow["eventId"];
		$sqlQuery = "SELECT * FROM odeum.event WHERE eventId = '$eventID';";
		$Result = mysqli_query($conn, $sqlQuery);

		$RCheck = mysqli_num_rows($Result);

		if ($RCheck > 0)
		{
			$EventRow = mysqli_fetch_assoc($Result);	
        }
	?>
    <div class="table">

        <table class="table table-hover">
            <thead><tr><th><h2>Select Seats</h2><th>
                <th>
                    <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#SeatModal">Select Seat</button>
                    
                    <div id="SeatModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Select Seat</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="MakeSeatTicket.php" method = "GET">
                                        <div class="form-group">
                                            <label for="Row">Row</label>
                                            <select name="Row">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                                <option value="4">4</option>
                                                <option value="5">5</option>
                                                <option value="6">6</option>
                                                <option value="7">7</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="Col">Column</label>
                                            <select name="Col">
                                                <option value="A">A</option>
                                                <option value="B">B</option>
                                                <option value="C">C</option>
                                                <option value="D">D</option>
                                                <option value="E">E</option>
                                                <option value="F">F</option>
                                                <option value="G">G</option>
                                                <option value="H">H</option>
                                                <option value="I">I</option>
                                                <option value="J">J</option>
                                                <option value="K">K</option>
                                                <option value="L">L</option>
                                                <option value="M">M</option>
                                                <option value="N">N</option>
                                            </select>
                                        </div>
                                        <input type="hidden" name="ScID" value=<?php echo $ScID ?>>
                                        <button type="submit" class="btn btn-danger">Book Seat</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
                <th>
                    <input type="button" class="btn btn-danger btn-lg" value="Complete Booking" onclick="go_home()">
                    <script>
                        function go_home()
                        {
                            location.href = "UserHome.php";
                        } 
                    </script>
                </th>
            </tr></thead>
        </table>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th></th>
                    <th style="padding-left:45px"><h4><strong>A</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>B</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>C</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>D</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>E</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>F</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>G</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>H</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>I</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>J</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>K</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>L</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>M</h4></strong></th>
                    <th style="padding-left:45px"><h4><strong>N</h4></strong></th>
                </tr>
            </thead>
            <tbody>
                <?php
                    $seatmono = "./Images/armchair-mono.png";
                    $seatcolor = "./Images/armchair.png";
                    $sql = "select * from odeum.seating_plan where schId='$ScID'";
                    $result = $conn->query($sql);

                    if ($result->num_rows > 0) {
                        $Seats = 0;
                        $Col = 1;
                        echo "<tr>";
                        while($row = $result->fetch_assoc()) {
                            $R = $row["row"];
                            $C = $row["col"];
                            $booked = $row["isBooked"];

                            if ($Seats > 14)
                            {
                                echo "</tr>";
                                $Seats = 0;
                                $Col = $Col + 1;
                                echo "<tr>";
                            }

                            if ($Seats == 0)
                            {
                                echo "<td><h4><strong>$Col</h4></strong></td>";
                                $Seats = $Seats + 1;
                            }
                            //echo "<td>$Seats</td>";
                            if ($booked == "T")
                                echo "<td><img src=$seatmono style=\"width:80%\"></td>";
                            else
                                echo "<td><img src=$seatcolor style=\"width:80%\"></td>";

                            $Seats = $Seats + 1;
                        }
                    }
                ?>
            </tbody>
        </table>
    </div>

</body>
</html>