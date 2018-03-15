# TP Test structurel

## Test structurel

Utilisez les techniques de test structurel pour tester la classe `AlarmClock` et   
[JUnit](http://junit.org/ "JUnit") pour écrire et exécuter vos tests. 
Pour que Maven puisse les retrouver, placez vos classes de test dans le répertoire `src/test/java/`.

Vous devez tester:

1. le comportement nominal du constructeur.
2. lle comportement extra-nominal du constructeur.
3. les autres méthodes de la classe `AlarmClock` .

Considérez la classe java `AlarmClock`:

- Quels sont les paramètres de test (Données de Test + Oracle) de la méthode `selectRing(ring)`?
- Dessinez le graphe de contrôle associé à la méthode `selectRing(ring)`.
- Donnez tous les chemins du graphe de contrôle précédent.
- Donnez le plus petit ensemble possible de chemins permettant de satisfaire les critères suivants:
  - “touslesnoeuds” ;
  - “touslesarcs” ;
  - “tousleschemins”.

- Est-ce que ces chemins sont exécutables? Autrement dit, existe-il des données de test qui les sensibilisent?
- Comparez les cas de tests obtenus avec ceux obtenus par analyse fonctionnelle.
-  Suivez la démarche précédente afin de déterminer des cas de test pour les autres méthodes de la classe `AlarmClock`:
  - `AlarmClock(ring,hour,min)`
  - `addMin(addedMin)`
  - `setActive(active)`


## Couverture de code

Vérifiez l'efficacité de vos données de test grâce à l'outil [Cobertura](http://cobertura.sourceforge.net/), 
utilisable directement avec Maven:

```shell
mvn cobertura:cobertura
```

Ou alors, générez directement tous les rapports de développement du projet:

```shell
mvn site
```

Dans les deux cas, vous trouverez les résultats à l'intérieur du répertoire `target` du projet.