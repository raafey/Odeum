<?php
    include_once './DBConnect.php';
?>

<?php
    session_start();

    $Name = $_POST["name"];
    $UName = $_POST["username"];
    $Email = $_POST["email"];
    $Password = $_POST["password"];
    $RPassword = $_POST["Rpassword"];
    $Address = $_POST["address"];
    $Contact = $_POST["contactno"];
    $DOB = $_POST["dob"];

    if($Password != $RPassword)
    {
        $_SESSION["RPassError"] = true;
        header("Location: NewAccount.php");
    }

    $sqlQueryUName = "SELECT username from user WHERE username = '$UName';";
    $Result = mysqli_query($conn, $sqlQueryUName);

    $RCheck = mysqli_num_rows($Result);

    if ($RCheck > 0)
    {
        $_SESSION["UNameError"] = true;
        header("Location: NewAccount.php");
    }

    $_SESSION["name"] = $Name;
    $_SESSION["username"] = $UName;
    $_SESSION["email"] = $Email;
    $_SESSION["password"] = $Password;
    $_SESSION["address"] = $Address;
    $_SESSION["contactno"] = $Contact;
    $_SESSION["dob"] = $DOB;

    /*echo "<p>$Name</p>";
    echo "<p>$UName</p>";
    echo "<p>$Email</p>";
    echo "<p>$Password</p>";
    echo "<p>$Address</p>";
    echo "<p>$Contact</p>";
    echo "<p>$DOB</p>";*/

    $sqlInsertQ = "INSERT INTO user (username, name, email, address, contact, dateOfBirth, password) VALUES ('$UName', '$Name', '$Email', '$Address', '$Contact', '$DOB', '$Password');";

    if (mysqli_query($conn, $sqlInsertQ))
    {
        header("Location: AccessSuccess.php");
    }
    else
    {
        echo "Error: " . $sqlInsertQ . "<br>" . mysqli_error($conn);
    }

?>