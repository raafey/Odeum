<?php
	include_once './DBConnect.php';
?>

<?php
    session_start();
	if ($_SESSION["access"] != "allowed") {
		header("Location: LoginPage.php");
	}
?>

<?php
	$TrID = $_POST["TrID"];
	$QNum = $_POST["QNum"];

	$sqlQuery = "SELECT * FROM odeum.question WHERE trivia_id = '$TrID';";
	$Result = $conn->query($sqlQuery);
	$QItr = 1;

	$Correct = 0;

	if ($Result->num_rows > 0)
	{
		while($row = $Result->fetch_assoc())
		{
			if($row["answer"] == $_POST[$row["id"]])
			{
				$Correct++;
			}
		}
	}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Trivia Results</title>
	<link rel="stylesheet" href="main.css" type="text/css">
    <link rel="icon" type="image/png" href="Images/Favicon.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
			<tbody>
				<?php
					echo "<tr> <td> <h1>You Answered ", $Correct," questions correctly</h1> </td> </tr>";
					if($Correct == 0)
						echo "<tr> <td> <h2>Better Luck Next Time</h2> </td> </tr>";
					else if($Correct > 0 && $Correct < $QNum)
						echo "<tr> <td> <h2>You Did Pretty Good</h2> </td> </tr>";
					else
						echo "<tr> <td> <h2>Congratulations. You're a Nerd</h2> </td> </tr>";
				?>
			</tbody>
		</table>
	</div>

</body>
</html>