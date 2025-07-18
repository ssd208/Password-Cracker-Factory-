<?php
$login = $_GET['login'];
$password = $_GET['password'];

if ($login == 'admin' && $password == 'aaae') {
    echo "Connexion réussie";
} else {
    echo "Échec de connexion";
}
?>
