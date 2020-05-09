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
    <title>Book Event</title>
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

            .table {
                width: 100%;
                height: 100%;

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
		$eventID = $_GET["eventID"];
		$sqlQuery = "SELECT * FROM odeum.event WHERE eventId = '$eventID';";
		$Result = mysqli_query($conn, $sqlQuery);

		$RCheck = mysqli_num_rows($Result);

		if ($RCheck > 0)
		{
			$Row = mysqli_fetch_assoc($Result);	
        }
	?>
	<div class="table">

        <table class="table table-hover">
            <thead><tr><th>Title<th></tr></thead>
            <tr>
                <td><h2><?php echo $Row["name"]?></h2></td>
            </tr>
        </table>

		<table class="table table-hover">
            <thead>
				<tr>
					<th></th>
					<th>Genre</th>
					<th>Rating</th>
					<th>Synopsis</th>
					<th>Feedback</th>
					<th>Cast</th>
					<th>Theater</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
                    <td><img src="<?php echo $Row["imageURL"]?>"></td>
                    <td><?php echo $Row["genre"]?></td>
                    <td><?php echo $Row["rating"]?></td>
                    <td><?php echo $Row["synopsis"]?></td>
                    <td><?php echo $Row["feedback"]?></td>
                    <td><?php echo $Row["cast"]?></td>
                    <td><?php $theaterId=$Row["theaterId"]; 
                    $theater = "select name from odeum.theater where theaterId='$theaterId'";
                    $tresult = $conn->query($theater);
                    echo $tresult->fetch_assoc()["name"]; ?></td>
                    <td> <a href="<?php echo $Row["trailerUrl"]?>" target="_blank"> Watch the Trailer </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#SeatModal">Select Time</button>
                        
                        <div id="SeatModal" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">Select Time</h4>
                                    </div>
                                    <div class="modal-body">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Time</th>
                                                    <th>Price</th>
                                                    <th>Hall</th>
                                                    <th>Availability</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <?php
                                                echo "<tr>";
                                                $sql = "select * from odeum.schedule where eventId='$eventID'";
                                                $result = $conn->query($sql);

                                                if ($result->num_rows > 0) {
                                                    // output data of each row
                                                    while($row = $result->fetch_assoc()) {
                                                        $ScId = $row["schId"];
                                                        $date = $row["date"];
                                                        $time = $row["time"];
                                                        $price = $row["Price"];
                                                        $hallId = $row["hallId"];
                                                        echo "<td>$date</td>";
                                                        echo "<td>$time</td>";
                                                        echo "<td>$price</td>";

                                                        $hall = "select * from odeum.hall where hallId='$hallId'";
                                                        $hallresult = $conn->query($hall);
                                                        $hallname = $hallresult->fetch_assoc()["name"];
                                                        echo "<td>$hallname</td>";
                                                        $hallfree = $hallresult->fetch_assoc()["isFree"];
                                                        //if ($hallfree == "T")
                                                        //{
                                                            echo "<td>Available</td>";
                                                            echo "<td>
                                                                <form action=\"SelectSeats.php\" method=\"GET\">
                                                                    <input type=\"hidden\" name=\"ScID\" value=$ScId>
                                                                    <input type=\"submit\" value=\"Select Seats\" class=\"btn btn-danger\">
                                                                </form>
                                                            </td>";
                                                        //}
                                                        /*else
                                                        {
                                                            echo "<td>Full</td>";
                                                        }*/
                                                    }
                                                }
                                                else
                                                {
                                                    echo "<p>No schedules found</p>";
                                                }
                                                echo "</tr>";
                                            ?>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
			</tbody>
		</table>
	</div>

</body>
</html>

