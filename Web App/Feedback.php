<?php
	include_once './DBConnect.php';
?>

<?php
    session_start();
	if ($_SESSION["access"] != "allowed") {
		header("Location: LoginPage.php");
	}

	$feedback = $_GET["feedback"];
	$eventId = $_SESSION["eventId"];
	$rating = $_GET["rating"];

	$getRatingSql = "SELECT rating FROM odeum.event WHERE eventId='$eventId'";
	$getRatingResult = $conn->query($getRatingSql);
	$oldrating = $getRatingResult->fetch_assoc()["rating"];
	$newrating = ($rating + $oldrating) / 2;
	$updateRatingSql = "UPDATE odeum.event SET rating = '$newrating' WHERE eventId='$eventId'";
	$conn->query($updateRatingSql);

	$feedbackSql = "SELECT feedback FROM odeum.event WHERE eventId = $eventId";
	$feedbackResult = $conn->query($feedbackSql);
	$oldfeedback = $feedbackResult->fetch_assoc()["feedback"];
	if ($oldfeedback === NULL || $oldfeedback == "" || !isset($oldfeedback) || $oldfeedback == "\0") {
		$oldfeedback = "";
	}
	else {
		$oldfeedback = $oldfeedback.",\n";
	}
	$newfeedback = $oldfeedback.$feedback;
	$setFeedbackSql = "UPDATE odeum.event SET feedback = '$newfeedback' WHERE eventId='$eventId'";
	$conn->query($setFeedbackSql);

	header("Location: ViewTickets.php");
?>