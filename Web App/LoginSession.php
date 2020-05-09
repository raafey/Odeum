<?php
    include_once './DBConnect.php';
?>

<?php
    session_start();

    $UName = $_POST["username"];
    $Password = $_POST["password"];

    $_SESSION["username"] = $UName;
    $_SESSION["password"] = $Password;

    $sqlQuery = "SELECT username, password FROM user WHERE username = '$UName' AND password = '$Password';";
    $Result = mysqli_query($conn, $sqlQuery);

    $RCheck = mysqli_num_rows($Result);

    if ($RCheck > 0)
    {
        $Row = mysqli_fetch_assoc($Result);
        if ($Row["username"] == $UName && $Row["password"] == $Password)
        {
			$_SESSION["access"] = "allowed";
            header("Location: AccessSuccess.php");
        }
    }
    else
    {
		$_SESSION["access"] = "disallowed";
        $_SESSION["invalid"] = true;
        header("Location: LoginPage.php");
    }
?>