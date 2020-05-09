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
    //Getting User ID
    $UName = $_SESSION["username"];
    $Password = $_SESSION["password"];

    $sqlQuery = "SELECT * FROM odeum.user WHERE username = '$UName' AND password = '$Password';";
    $Result = mysqli_query($conn, $sqlQuery);

    $RCheck = mysqli_num_rows($Result);

    if ($RCheck > 0)
    {
        $URow = mysqli_fetch_assoc($Result);	
    }
    $UID = $URow["userId"];

    $ScID = $_GET["ScID"];
    $Row = $_GET["Row"];
    $Col = $_GET["Col"];

    //Getting Seat ID
    $sqlQuery = "SELECT * FROM odeum.seating_plan WHERE row = '$Row' AND col = '$Col' AND schId = '$ScID' AND isBooked = 'F' and userId IS NULL;";
    $Result = mysqli_query($conn, $sqlQuery);

    $RCheck = mysqli_num_rows($Result);

    if ($RCheck > 0)
    {
        $SRow = mysqli_fetch_assoc($Result);	
    }
    else
    {
        echo "<script>\n";
        echo "alert(\"Seat Is Aleady Booked\");\n";
        echo "window.location='SelectSeats.php?ScID=$ScID'";
        echo "</script>";
    }
    $SeatID = $SRow["seatId"];

    //Getting Event ID
    $sqlQuery = "SELECT * FROM odeum.schedule WHERE schId = '$ScID';";
    $Result = mysqli_query($conn, $sqlQuery);

    $RCheck = mysqli_num_rows($Result);

    if ($RCheck > 0)
    {
        $ScRow = mysqli_fetch_assoc($Result);	
    }
    $EventID = $ScRow["eventId"];

    $sqlMakeTicket = "INSERT INTO odeum.ticket (seatId, eventId) VALUES ('$SeatID', '$EventID')";
    if (mysqli_query($conn, $sqlMakeTicket))
    {
        $sqlUpdateSeat = "UPDATE odeum.seating_plan SET isBooked = 'T', userId = '$UID' WHERE seatId = '$SeatID';";
        if (mysqli_query($conn, $sqlUpdateSeat))
        {
            header("Location: SelectSeats.php?ScID=$ScID");
        }
        else
        {
            echo "Error: " . $sqlMakeTicket . "<br>" . mysqli_error($conn);
        }
    }
    else
    {
        echo "Error: " . $sqlMakeTicket . "<br>" . mysqli_error($conn);
    }
?>