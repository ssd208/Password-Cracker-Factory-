<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $login = $_POST["login"];
    $password = $_POST["password"];

    if ($login == 'admin' && $password == 'motdepassesecurise') {
        http_response_code(200); // Succès
        echo "Connexion réussie";
    } else {
        http_response_code(401); // Échec
        echo "Échec de la connexion : Login ou mot de passe incorrect";
    }
}
?>
