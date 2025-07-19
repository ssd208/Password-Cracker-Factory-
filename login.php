
<?php
// Configuration des en-têtes pour permettre les requêtes CORS
header('Content-Type: text/html; charset=utf-8');
header('Access-Control-Allow-Origin: *');

// Vérification des identifiants
function checkCredentials($login, $password) {
    // Dans un cas réel, vous devriez utiliser une base de données et du hachage de mot de passe
    $valid_credentials = [
        'admin' => 'passer123',  // À ne pas faire en production
        'user1' => 'password123',
        'test' => 'test123'
    ];
    
    return array_key_exists($login, $valid_credentials) && $password === $valid_credentials[$login];
}

// Traitement du formulaire
$message = '';
$isAuthenticated = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $login = $_POST['login'] ?? '';
    $password = $_POST['password'] ?? '';
    
    if (empty($login) || empty($password)) {
        $message = 'Veuillez remplir tous les champs';
    } else if (checkCredentials($login, $password)) {
        $isAuthenticated = true;
        $message = 'Connexion réussie !';
    } else {
        $message = 'Échec de connexion';
    }
} else if ($_SERVER['REQUEST_METHOD'] === 'GET' && isset($_GET['login']) && isset($_GET['password'])) {
    // Pour la compatibilité avec OnlineTarget
    $login = $_GET['login'];
    $password = $_GET['password'];
    
    if (checkCredentials($login, $password)) {
        echo "Connexion réussie pour l'utilisateur: " . htmlspecialchars($login);
    } else {
        http_response_code(401);
        echo "Échec de l'authentification";
    }
    exit;
}
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            line-height: 1.6;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .message {
            padding: 10px;
            margin: 15px 0;
            border-radius: 4px;
        }
        .success {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
        }
        .error {
            background-color: #f2dede;
            color: #a94442;
            border: 1px solid #ebccd1;
        }
    </style>
</head>
<body>
    <h1>Connexion</h1>
    
    <?php if (!empty($message)): ?>
        <div class="message <?php echo $isAuthenticated ? 'success' : 'error'; ?>">
            <?php echo htmlspecialchars($message); ?>
        </div>
    <?php endif; ?>
    
    <?php if (!$isAuthenticated): ?>
        <form method="POST" action="">
            <div class="form-group">
                <label for="login">Nom d'utilisateur:</label>
                <input type="text" id="login" name="login" required>
            </div>
            <div class="form-group">
                <label for="password">Mot de passe:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit">Se connecter</button>
        </form>
    <?php endif; ?>
</body>
</html>