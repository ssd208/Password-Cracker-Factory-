# Password-Cracker-Factory-
Implémentation de techniques de cassage de mots de passe via le patron de conception  « Fabrique »

## Rapport Succincte

Ce rapport présente une synthèse de l’architecture logicielle du projet, le choix des patrons de conception, les variantes implémentées et propose quelques pistes d’amélioration.

---

### 1. Architecture Logicielle

## 🧱 Architecture logicielle

L’architecture du projet repose sur une séparation claire des responsabilités et l’utilisation du patron de conception **Fabrique** (Factory Method et Abstract Factory). Le système est conçu pour être **modulaire** et **évolutif**, permettant de combiner dynamiquement deux types de stratégies d’attaque (Brute Force, Dictionnaire) avec deux types de cibles (locale, en ligne).

Chaque type d’attaque est encapsulé dans une classe spécifique qui implémente une interface commune, facilitant ainsi l’extension et le remplacement. De la même manière, les cibles d’authentification sont gérées via une abstraction commune permettant de manipuler indifféremment des cibles locales ou distantes.

La classe centrale du système (`CrackerApp`) utilise une fabrique pour instancier dynamiquement la bonne combinaison attaque + cible selon les arguments passés en ligne de commande.

#### Diagramme de Classes UML

> ![Diagramme UML principal](Password-Cracker-Factory- /Classe UML.PNG)  

### 2. Choix des Patrons de Conception
## Patrons de conception utilisés et justification

Le projet repose principalement sur l’utilisation des **patrons de conception**  Factory Method et  Abstract Factory afin de garantir une modularité et une extensibilité du système.

### 1. Patron Fabrique (Factory Method / Abstract Factory)

Nous avons utilisé une Fabrique pour instancier dynamiquement les bonnes **stratégies d’attaque** (Brute Force ou Dictionnaire) en fonction du type de **cible** (locale ou en ligne), selon les arguments fournis à l’exécution.

#### Justification :

- Permet de **séparer la logique d’instanciation** du reste du programme.
- Facilite l’**ajout de nouvelles variantes** (ex. : nouvelle attaque, nouvelle cible) sans modifier la structure existante.
- Respecte le **principe ouvert/fermé** du SOLID : ouvert à l’extension, fermé à la modification.
- Rend l’outil **modulaire** : chaque combinaison (attaque + cible) est encapsulée et interchangeable.

La **Fabrique** est responsable de créer une combinaison (`Stratégie d’attaque + Cible`)

### 3. Variantes Implémentées

Le système permet de simuler quatre variantes d’attaque en combinant deux types de stratégies (Brute Force et Dictionnaire) avec deux types de cibles (locale et en ligne). Ces variantes sont sélectionnées dynamiquement à l’aide des arguments fournis à l’exécution.

### 1. Brute Force + Cible Locale

Cette variante génère automatiquement toutes les combinaisons de caractères possibles (a-z, 0-9, etc.) jusqu’à une certaine longueur maximale.  
Le mot de passe généré est testé sur une cible locale simulée par un programme Java contenant les identifiants en dur.

-  Avantage : rapide à exécuter en local, sans dépendances réseau.
-  Limite : inefficace pour des mots de passe longs.

### 2. Brute Force + Cible en ligne

Même approche que ci-dessus, mais cette fois les tentatives sont envoyées vers un serveur distant via des requêtes HTTP.  
La réponse HTML permet de détecter si la tentative est réussie ou non.

- Simule une attaque réelle contre un formulaire web(login.php).
- Beaucoup plus lent à cause des temps de réponse réseau.

###  3. Dictionnaire + Cible Locale

Une liste de mots de passe potentiels est lue depuis un fichier texte (dictionnaire.txt).  
Chaque mot est testé sur la cible locale. C’est une méthode plus rapide et plus réaliste que le Brute Force, si le dictionnaire est bien choisi.

- Rapide et efficace si le mot de passe est dans le dictionnaire.
-  Échec garanti si le mot n’y figure pas.

### 4. Dictionnaire + Cible en ligne

Le dictionnaire est utilisé pour tester des mots de passe contre une cible en ligne.  
Chaque mot génère une requête HTTP vers un formulaire PHP distant(login.php).

-  Utilisée dans la pratique pour attaquer des services web.
- Peut être ralentie ou bloquée par des mécanismes de sécurité .

### 4. Pistes d’Amélioration

Plusieurs axes d'amélioration peuvent être envisagés pour enrichir et renforcer le projet :

###  1. Optimisation des performances

- Utiliser le **multithreading** pour accélérer le Brute Force.
- Ajouter une **barre de progression** ou un affichage du nombre de tentatives.
- Gérer des files de tâches pour répartir les requêtes en ligne.

###  2. Extension de la fabrique

- Créer une **interface graphique (GUI)** pour lancer les attaques sans ligne de commande.

### 3. Renforcement du côté "attaque en ligne"

- Gérer les **cookies/sessions HTTP**
- Ajouter la gestion des **CAPTCHA** ou mécanismes anti-bot (au moins leur détection)

### .4 Logs et reporting

- Ajouter un **système de logs** pour tracer toutes les tentatives (succès/échecs).
- Générer un **rapport final** (ex. : durée, mot de passe trouvé, nombre de tentatives…).

### 5. Amélioration des tests

- Intégrer des **tests automatiques** pour vérifier le comportement sur différentes cibles.

