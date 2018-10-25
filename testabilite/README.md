# Testabilité

Pendant les exercices précédents, vous avez été confrontés à une
difficulté de testabilité avec le test de la méthode `enable()`.
Cette méthode a un problème de testabilité, car elle manque de
_controllabilité_ (une des heuristiques de testabilité).
En d'autre termes, les tests ne peuvent pas controller les valeurs de la
classe `Calendar`, `HOUR` et `MINUTES`.


Nous allons utiliser un bouchon pour contrôler l'heure.
Créez un bouchon réaliste devant être substitué à la classe `Calendar` pendant les tests.
Vous effectuerez avec ce stub le test de la méthode `enable()`.
Remodélisez et exécutez dynamiquement les tests.

**Questions**

  1. Quels sont les inconvénients de ce bouchon ?
  2. Reprécisez notre problème de testabilité  après avoir essayé de le résoudre avec ce bouchon.
  1. Améliorez la conception de la classe pour pouvoir prendre en compte depuis la classe de test votre classe `Calendar` sans modification des fichiers du projet.

## Première partie: Mockito

La classe `Calendar` que vous avez créée est une doublure.
Un bouchon permet de simuler le comportement d'une classe.
Telle que vous avez fait votre classe, l'heure simulée ne peut pas être
contrôlée (sauf si vous complexifiez  la classe en paramétrant son
constructeur, ce genre de solution peut être rapidement trop complexe et non maintenable).
Il existe des outils permettant de créer facilement, plus ou moins
automatiquement des doublures.
C'est le cas par exemple de Mockito.
Mockito permet de créer directement une doublure depuis la classe de
test et de faire varier dynamiquement pendant les tests le comportement
de la classe simulée.
Pour cela, il faut spécifier ce que la doublure doit retourner quand il intercepte des appels vers la classe simulée.

Ci-dessous un extrait de la spécification de Mockito.

  Création d'un bouchon de la classe `List`, puis appel de `add("first")` sur la liste simulée, puis vérification que l'appel a été effectué :

```java
// Importation de  Mockito de façon statique pour laisser le code plus clair:
import static org.mockito.Mockito.*;

// Création d'une doublure
List mockedList = mock(List.class);

// Utilisation de la doublure:
mockedList.add("first");

// Vérification
verify(mockedList).add("first");
```

  Création d'un simulacre de la classe `List`, puis définition  du comportement simulée
  de l'appel à `get(0)`, puis appel à `get(0)` simulé :

```Java
// Le simulacre, c'est-à-dire, la liste est demandée d'y aller chercher l'id 0, et retourner "first"
when(mockedList.get(0)).thenReturn("first");
when(mockedList.get(1)).thenThrow(new RuntimeException());

// La ligne suivante imprime "first"
System.out.println(mockedList.get(0));
```

**A faire**

  1. Utilisez  Mockito pour créer un mock de la classe Calendar dans les tests.
  Utilisez ce  mock pour tester la méthode `enable()`.
  N'oubliez pas que vous devez tester cette méthode dans les différents états valides de la classe `AlarmClock`.
  1. Corrigez la méthode `enable()`.


## Deuxième partie: Couverture de code

Des outils permettent de mesurer les qualités de test.
C'est un critère de testabilité d'un point de vue des tests vis à vis du logiciel testé.
Cela peut aussi révéler des difficultés de testabilité dans la conception du logiciel.

Utilisez Cobertura ou Emma pour évaluer la qualité de votre suite de tests.
Vous pouvez les utiliser sans aucune installation, directement à partir de la ligne de commandes:

```Shell
mvn cobertura:cobertura
mvn emma:emma
```

**A faire**
  1. Assurez une couverture maximale du code du projet à partir de vos tests.

**Questions**
  1. A quel problème de testabilité pourriez-vous être confrontés pour couvrir l'ensemble du code ?
