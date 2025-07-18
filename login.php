
<?php
$login = $_GET['login'];
$password = $_GET['password'];

if ($login == 'admin' && $password == 'ap4b') {
    echo "Connexion réussie";
} else {
    echo "Échec de connexion";
}
?>