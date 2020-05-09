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
    <title>Welcome to Odeum</title>
	<link rel="stylesheet" href="main.css" type="text/css">
    <link rel="icon" type="image/png" href="Images/Favicon.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

	<style>
		.wrapper {
			margin-left: 6.5%;
		}
		.rating {
			display: inline-block;
		}
		.checked {
			color: orange;
		}
		.unchecked {
			color: #4d0000;
		}
	</style>
</head>

<body>
	.
	<div class = 'logo'>
        <a href="UserHome.php"><img src="Images/Odeum.png"/></a>
		<div class = 'logout'><a href = "LogoutSession.php">Logout</a></div>
    </div>

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

	<div class='table'>
		<table class="table table-hover">
			<thead>
			<th>Ticket ID</th>
			<th>Event Name</th>
			<th>Price</th>
			<th>Seat Number</th>
			<th>Hall</th>
			<th>Theatre</th>
			<th></th>
			<th></th>
			<th>Share</th>
			</thead>
			<tbody>
			
				<?php 
				$uname = $_SESSION["username"];

				$ticketSql = "SELECT * FROM odeum.ticket WHERE seatId IN (SELECT seatId FROM odeum.seating_plan WHERE userId=(SELECT userId FROM odeum.user WHERE username='$uname'))";
				$ticketResult = $conn->query($ticketSql);

				if ($ticketResult->num_rows > 0) {
					$index = 0;
					while($row = $ticketResult->fetch_assoc()) { ?>
						<tr>
							<td><?php 
							echo $row["ticketId"];
							?>
							</td>
						
							<td><?php 
							$ticketId = $row["ticketId"];
							$eventSql = "SELECT name FROM odeum.event WHERE eventId=(SELECT eventId FROM odeum.schedule WHERE schId=(SELECT schId FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId')))";
							$eventResult = $conn->query($eventSql);
							echo $eventResult->fetch_assoc()["name"];
							?>
							</td>

							<td><?php 
							$ticketId = $row["ticketId"];
							$priceSql = "SELECT Price FROM odeum.schedule WHERE schId=(SELECT schId FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId'))";
							$priceResult = $conn->query($priceSql);
							echo $priceResult->fetch_assoc()["Price"];
							?>
							</td>

							<td><?php 
							$ticketId = $row["ticketId"];
							$seatSql = "SELECT row,col FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId')";
							$seatResult = $conn->query($seatSql);
							$temp = $seatResult->fetch_assoc();
							echo $temp["row"],$temp["col"];
							?>
							</td>

							<td><?php 
							$ticketId = $row["ticketId"];
							$hallSql = "SELECT name FROM odeum.hall WHERE hallId=(SELECT hallId FROM odeum.schedule WHERE schId=(SELECT schId FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId')))";
							$hallResult = $conn->query($hallSql);
							echo $hallResult->fetch_assoc()["name"];
							?>
							</td>

							<td><?php 
							$ticketId = $row["ticketId"];
							$theatreSql = "SELECT name FROM odeum.theater WHERE theaterId=(SELECT theaterId FROM odeum.hall WHERE hallId=(SELECT hallId FROM odeum.schedule WHERE schId=(SELECT schId FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId'))))";
							$theatreResult = $conn->query($theatreSql);
							echo $theatreResult->fetch_assoc()["name"];
							?>
							</td>

							<td>
							<?php $_SESSION["ticket"]=$row["ticketId"];?>
							<a href="CancelTicket.php" class="btn btn-danger" role="button">Cancel Booking</a>
							</td>

							<td>
							<?php 
							$ticketId = $row["ticketId"];
							$eventSql = "SELECT eventId FROM odeum.schedule WHERE schId=(SELECT schId FROM odeum.seating_plan WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId'))";
							$eventResult = $conn->query($eventSql);
							$id = $eventResult->fetch_assoc()["eventId"];
							$_SESSION["eventId"]=$id;?>

							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#FeedbackModal">Give Feedback</button>
                        
							<div id="FeedbackModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header" style="background-color: #1a1a1a;">
											<button type="button" class="close" style="color: white;" data-dismiss="modal">&times;</button>
											<h4 class="modal-title" style="color: white;">Give Feedback</h4>
										</div>

										<div class="modal-body">
											<form action="Feedback.php" method="GET">
												<div class="form-group">
													<label for="feedback">Review the event</label>
													<input type="text" class="form-control" id="feedback" placeholder="Leave your review" name="feedback">
												</div>
												<div class="form-group">
													<label for="rating">Rate the event out of 10</label>
													<input type="number" min="1" max="10" class="form-control" id="rating" placeholder="Leave your rating" name="rating">
												</div>
												<div class="text-center">
													
													<button type="submit" class="btn btn-danger" style="width: 100%;">Submit</button>
													
												</div>
											</form>
										</div>

										<div class="modal-footer" style="background-color: #1a1a1a;">
											<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										</div>
									</div>
								</div>
							</div>

							</td>

							<td>
								<a href="https://www.facebook.com/sharer/sharer.php?u=URLENCODED_URL&t=TITLE"
									onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');return false;"
										target="_blank" title="Share on Facebook">
										<i class="fa fa-facebook fa-lg"></i>
								</a>

								<a href="https://twitter.com/share?url=URLENCODED_URL&via=TWITTER_HANDLE&text=TEXT"
									onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');return false;"
										target="_blank" title="Share on Twitter">
										<i class="fa fa-twitter fa-lg"></i>
								</a>

								<a href="https://plus.google.com/share?url=URLENCODED_URL"
									onclick="javascript:window.open(this.href, '', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=350,width=480');return false;"
										target="_blank" title="Share on Google+">
										<i class="fa fa-google-plus fa-lg"></i>
								</a>
								  
							</td>

						</tr>
					<?php
					}
					
				} else {?>
					<tr><td>You have not booked any tickets yet<td></tr>
				<?php
				}
				?>

			</tbody>
		</table>
	</div>

</body>
</html>