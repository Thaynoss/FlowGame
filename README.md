# FlowGame - Groupe 17

Jeu de puzzle Flow développé en Java avec architecture MVC.

## 🚀 Pour lancer le jeu

### Compilation + Lancement (commande complète)

**Windows (PowerShell) :**
```powershell
javac -d bin -sourcepath src src/Main.java src/controleur/*.java src/Modele/*.java src/Vue/*.java; java -cp bin Main
```

**Linux/Mac :**
```bash
javac -d bin -sourcepath src src/Main.java src/controleur/*.java src/Modele/*.java src/Vue/*.java && java -cp bin Main
```

### Ou en deux étapes séparées

**1. Compiler :**
```bash
javac -d bin -sourcepath src src/Main.java src/controleur/*.java src/Modele/*.java src/Vue/*.java
```

**2. Lancer :**
```bash
java -cp bin Main
```

> **Note :** Exécutez ces commandes depuis le dossier racine du projet `FlowGame/`

## 🎮 Description

Le but du jeu est de relier les cases de symboles identiques avec l'aide d'un chemin.
Pour ce faire, cliquer d'abord sur un symbole, et rester appuyé pour tracer un chemin jusqu'à l'autre symbole.
Il faut alors relier toutes les cases de cette manière pour finir une partie.

## 📁 Structure du projet

```
FlowGame/
├── src/
│   ├── Main.java              # Point d'entrée principal
│   ├── controleur/            # Contrôleurs (MVC)
│   │   ├── ControleurGrille.java
│   │   └── ControleurMenu.java
│   ├── Modele/                # Modèles (MVC)
│   │   ├── Jeu.java
│   │   ├── ModeleCase.java
│   │   ├── ModeleMenu.java
│   │   ├── Chemin.java
│   │   ├── Level.java
│   │   └── CaseType.java
│   └── Vue/                   # Vues (MVC)
│       ├── VueGrille.java
│       ├── VueMenu.java
│       └── VueCase.java
└── bin/                       # Fichiers compilés (.class)
```

## 🏗️ Architecture MVC

- **Model** : Gère la logique métier et les données du jeu
- **View** : Gère l'affichage graphique (Swing)
- **Controller** : Gère les interactions utilisateur

## 👥 Auteurs

Groupe 17 - LIFAP7
 
