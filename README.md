# Fantome App API

Ce module contient l'API du projet Fantome App. Il est écrit en Spring et utilise une base de donnée PSQL.

## Installation pour le développeur

### Prérequis

- Java 21 (`sdkman` conseillé)
- Maven
- Docker

### Installation

Le module embarque la dépendance `spring-boot-docker-compose`. Ainsi, le développeur n'a pas besoin de lancer le fichier `docker-compose.yaml`, démarrer l'application le fera pour lui.

On peut donc directement lancer l'application. Il faut utiliser le profil spring `local` pour le poste de dev (pour cela la variable d'environnement `SPRING_PROFILES_ACTIVE=local` ou la propriété Maven `-Dspring.profiles.active=local`).

L'application doit s'exécuter sans erreurs.

### Structure du code

Le code utilise une structure classique et les packages sont organisés par type d'objets.

### Keycloak

Afin de gérer l'authentification et l'authorisation, Keycloak est utilisé comme fournisseur OIDC. Ainsi, le comportement par défaut est de gérer les rôles applicatifs via Keycloak. Un utilisateur par défaut est disponible via les identifiants suivants :
`user`:`password`. Pour configurer les rôles dans Keycloak, se référer à de la [documentation](https://tomas-pinto.medium.com/keycloak-clients-and-roles-a-tutorial-b334147f1dbd). Il est possible de créer des rôles du client par défaut en modifier le fichier `realm.json`.

Si un besoin existe de s'adapter à un autre fournisseur OIDC, créer un nouveau `Converter` qui permet de construire un `ConnectedUser` en fonction du `JWT` entré (se référer à la documentation du fournisseur OIDC).

Un point d'API est disponible pour récupérer les informations de l'utilisateur connecté (`/api/users/me`).

### Gestion d'erreurs

La gestion des erreurs de l'API se fait via l'objet `ControllerHandler`. Ce dernier permet de "catcher" les exceptions et les afficher proprement pour l'utilisateur. Ainsi, on peut simplement jeter des exceptions depuis des services ou les controllers. Ces erreurs seront toujours récupérées et traitées par les handlers.

Il existe deux types d'erreur :
- L'erreur `TechnicalException`. Cette dernière doit être utilisée quand un problème technique survient. (exemple : erreur S3, erreur de connection BDD, erreur HTTP, …)
- L'erreur `FunctionalException`. Celle-ci concerne les erreurs liées au métier (exemple : impossible de sauvegarder l'objet A, car le champ "monChamp" est obligatoire.)

Chaque erreur fonctionnelle (resp. technique) doit se trouver dans le fichier `FunctionalRule` (resp. `TechnicalRule`).

### Editorconfig

Ce module utilise un fichier `.editorconfig`. Ce dernier permet d'unifier le formatage de code, sans dépendre d'une librairie, et ce, pour tous les langages utilisés.

Il est donc préférable de ne pas utiliser de formateur de code à part celui-ci. La majorité des IDEs sont compatibles avec ce type de fichier sans nécessiter l'installation d'extensions.