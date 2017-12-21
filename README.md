# project3

Cette application permet de jouer à deux jeux de logique. 
- Le jeu recherche+/-, il faut trouver une combinaison à l'aide d'indications +/-. 
- Le jeu du mastermind, c'est le même principe que le premier jeu, sauf que les indications sont bien placé ou présent.

Pour chaque jeu, il y a trois modes diffèrents :
- le mode challenger, il faut trouver la combinaison secrète de l'ordinateur.
- le mode défenseur, c'est l'ordinateur qui doit trouver la combinaison proposée par l'utilisateur.
- le mode duel, où l'ordinateur et l'utilisateur jouent tour à tour. Le premier qui trouve la combinaison de l'autre a gagné.

Il y a également un mode développeur pour tester le bon fonctionnement de l'application.

L'application fonctionne ainsi :

Un menu s'affiche pour proposer à l'utilisateur de choisir un des deux jeux (recherche+/- ou mastermind).
Un autre menu permet de sélectionner le mode de jeu (challenger, défenseur ou duel).
Si l'utilisateur perd l'application affiche la solution.
A la fin du programme, l'utilisateur peut soit rejouer au même mode ou à un autre, soit arrêter et retourner au premier menu.
Au retour du premier menu, l'utilisateur peut mettre fin à l'application.


Lancement et compilation du programme :

- Ouvrir une console Windows.
- Se positionner sur le répertoire où se trouve le code source de l'application.
- Compiler le code source à l'aide de la commande : 
javac -classpath lib\log4j-1.2.17.jar -d . src\p3\*.java

Création d'un fichier .jar :
- Faire un fichier MANIFEST.MF
Manifest-Version: 1.0
Main-Class: p3.Main
Class-Path: /workspace/p3/lib/log4j-1.2.17.jar

- Taper les lignes de commande suivantes :
jar cvmf META-INF\MANIFEST.MF P3.jar p3\*.class
et
java -jar P3.jar


Lancement du programme avec le mode développeur (la combinaison de IA est affichée au début du programme) :

- Ouvrir une console Windows.
- Faire les étapes pour la compilation.
- Saisir la commande java Main true


