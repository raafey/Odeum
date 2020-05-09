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
    <title>Trivia</title>
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

    <?php
        $TrID = $_GET["triviaID"];
		$sqlQuery = "SELECT name FROM odeum.trivia WHERE trivia_id = '$TrID';";
		$Result = mysqli_query($conn, $sqlQuery);

		$RCheck = mysqli_num_rows($Result);
        $TrRow = mysqli_fetch_assoc($Result);
        $TriviaName = $TrRow["name"];
    ?>

	<div class='table'>
		<table class="table table-hover">
			<tbody>
                <tr><td><h1><?php echo $TriviaName; ?><h1></td></tr>
                <td>
                <button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#QuizModal">Start Quiz</button>
                        
                <div id="QuizModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title"><?php echo $TriviaName ?></h4>
                            </div>
                            <div class="modal-body">
                                <table class="table table-striped">
                                    <tbody>
                                        <form action="QuizScore.php" method="POST">
                                        <?php
                                            $sqlQuery = "SELECT * FROM odeum.question WHERE trivia_id = '$TrID';";
                                            $Result = $conn->query($sqlQuery);
                                            $QItr = 1;

                                            if ($Result->num_rows > 0)
                                            {
                                                while($row = $Result->fetch_assoc())
                                                {
                                                    $Question = $row["question"];
                                                    echo "<tr><td> <h4>$QItr. $Question</h4> </td></tr>";

                                                    ?>
                                                    
                                                    <tr><td>
                                                    <label class="radio"><input type="radio" name="<?php echo $row["id"]?>" id="Q<?php echo $row["id"]?>_A" value="A" checked/>
                                                    A. <?php echo $row["opt_a"]?> </label>

                                                    <label class="radio"><input type="radio" name="<?php echo $row["id"]?>" id="Q<?php echo $row["id"]?>_B" value="B" />
                                                    B. <?php echo $row["opt_b"]?> </label>

                                                    <?php
                                                    if ($row["opt_c"] != "")
                                                    {?>
                                                        <label class="radio"><input type="radio" name="<?php echo $row["id"]?>" id="Q<?php echo $row["id"]?>_C" value="C" />
                                                        <?php echo "C. ", $row["opt_c"]?> </label>
                                                    <?php
                                                    }
                                                    ?>

                                                    <?php
                                                    if ($row["opt_d"] != "")
                                                    {?>
                                                        <label class="radio"><input type="radio" name="<?php echo $row["id"]?>" id="Q<?php echo $row["id"]?>_D" value="D" />
                                                        <?php echo "D. ", $row["opt_d"]?> </label>
                                                    <?php
                                                    }
                                                    ?>

                                                    </td></tr>
                                                    <?php
                                                    $QItr++;
                                                }
                                            }
                                            else
                                            {
                                                echo "No Questions Present";
                                            }
                                        ?>
                                        <input type="hidden" name="QNum" value=<?php echo $QItr-1 ?>>
                                        <input type="hidden" name="TrID" value=<?php echo $TrID ?>>
                                        <tr><td><input type="submit" value="View Results" class="btn btn-danger btn-lg"/></td></tr>
                                        </form>
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
			</tbody>
		</table>
	</div>

</body>
</html>