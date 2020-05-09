<?php
	include_once './DBConnect.php';
?>

<?php
    session_start();
	if ($_SESSION["access"] != "allowed") {
		header("Location: LoginPage.php");
	}

	$ticketId=$_SESSION["ticket"];

	$resetSeatSql = "UPDATE odeum.seating_plan SET isBooked='F',userId=NULL WHERE seatId=(SELECT seatId FROM odeum.ticket WHERE ticketId='$ticketId')";
	$conn->query($resetSeatSql);

	$deleteTicketSql = "DELETE FROM odeum.ticket WHERE ticketId='$ticketId'";
	$conn->query($deleteTicketSql);

	header("Location: ViewTickets.php");
?>