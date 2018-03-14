# TP Test fonctionnel

## Test fonctionnel


Pour effectuer le test fonctionnel d’une unité sous test, on réalise plusieurs étapes:
1. Identification des différentes configurations “valides” de l’objet.
La première étape est faite une fois pour toute l’unité sous test.
Les quatre étapes suivantes sont répétées pour chaque méthode sous test.

2.	Identification des variables qui forment chaque **donnée de test**, ainsi que les variables qui seront contrôlées par l’Oracle.

3.	Réalisation d'une  analyse partitionnelle pour chacune des variables formant les données de test, afin d’en déduire des **classes d’équivalences**.

4.	Création d'une table de décision décrivant le comportement attendu.

5.	Déduction  d'un ensemble fini “minimum” de **cas de test**.


Pour réaliser ce projet, vous aurez besoin de lire les spécifications de la classe `AlarmClock`, fournies sous la forme de
documentation de code Java (Javadoc). Elle se trouve à l'intérieur du dossier `apidocs`.
Vous pouvez l'ouvrir  dans votre IDE ou directement en ligne de commande:

```shell
firefox  file:apidocs/index.html
```

## Questions

1. Appliquez la première étape à l'interface `AlarmClock` et à sa _factory_, `AlarmClockFactory`.

2. Appliquez les 4 autres étapes pour créer des cas de test fonctionnels de la méthode de création `AlarmClockFactory::createAlarmClock()`.
En Java, on peut qualifier le plus grand et le plus petit entier par `Integer.MAX_VALUE` et  `Integer.MIN_VALUE`, respectivement.

3.	Répétez la procédure précédente pour créer des cas de test fonctionnels pour les méthodes de l'interface `AlarmClock` :
-	`selectRing(int ringtone)`
-	`activate()`
-	`addMin(int minutes)`
-	`switchOff(boolean snooze)`

4. Considérez à nouveau le test de la méthode  `selectRing(int ringtone)` en effectuant son test aux limites.


## Mise en œuvre

1. Utilisez les cas de test créés précédemment  et  
 [JUnit](http://junit.org/ "JUnit") pour écrire et exécuter vos tests.
 Pour que Maven puisse les retrouver, placez les classes de test dans le répertoire `src/test/java/`.
2.	Programmez ces cas de test dans l’ordre suivante:
    1. `AlarmClockFactory::createAlarmClock()`,
    2. `selectRing()`,
    3. `addMin()`,
    4. `activate()`,
    5. `switchOf()`.

## Observations

Vous devriez avoir des difficultés pour les 2 dernières méthodes qui seront étudiées pendant un autre TD.


## Amélioration des tests

Utilisez les tests paramétrés pour réduire le nombre de tests unitaires: [Parameterized tests](https://github.com/junit-team/junit4/wiki/Parameterized-tests).

Remplacez les assertions Java (ou JUnit) de vos tests par des assertions [AssertJ](http://joel-costigliola.github.io/assertj/index.html).
