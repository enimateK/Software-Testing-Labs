# TP Instrumentation

## Préparation

### Spoon

[Spoon](http://spoon.gforge.inria.fr/) permet de transformer et d'analyser du code source Java. À partir des sources d'un programme cible, Spoon construit son arbre abstrait (AST) et permet de le modifier (modification, ajout ou suppression de noeud), pour ensuite (re)générer le code Java correspondant.

Dans ce tp, nous allons utiliser Spoon afin d'analyser dynamiquement un programme (analyse de l'exécution d'un programme). Pour cela vous devez créer des `Processor` qui vont instrumenter le code source de programmes.

### Installation & Execution

Spoon peut-être utilisé sous la forme d'une dépendence Maven, simplement en ajoutant a votre pom.xml la dépendence suivante:

```xml
<dependency>
	<groupId>fr.inria.gforge.spoon</groupId>
	<artifactId>spoon-core</artifactId>
	<version>7.1.0</version>
</dependency>

```

La classe principale de Spoon, `Launcher`, est initialisée de cette manière:

```java
File projectToInstrument = new File("src/main/resources/example");

Launcher launcher = new MavenLauncher(projectToInstrument.getAbsolutePath(), MavenLauncher.SOURCE_TYPE.ALL_SOURCE);

launcher.addProcessor(new LogProcessor());
launcher.setSourceOutputDirectory(new File(projectToInstrument, "spoonedSources"));
launcher.setBinaryOutputDirectory(new File(projectToInstrument, "spoonedBinaries"));

launcher.getEnvironment().setShouldCompile(true);
launcher.run();
```

En executant la méthode `run()` du `Launcher`, le code source sera réécrit en fonction des processeurs dans le dossier `spoonedSources`, et ce code instrumenté sera compilé dans le dossier `spoonedBinaries`.

L'utilisation de la classe `MavenLauncher` à la place du `Launcher` standard permet à Spoon de gérer automatiquement le classpath lors de la compilation, allégeant l'instrumentation en analysant le `pom.xml`.

Le programme donné doit être compilé afin d'être utilisé. Lancer la commande `mvn package` produira le jar `target/instrumentation-1.0-jar-with-dependencies.jar`, qui pourra être exécuté, en passant en paramètre le programme à instrumenter. Example:

```shell
java -jar instrumentation-1.0-jar-with-dependencies.jar src/main/resources/example
```

Cette commande instrumentera le projet *example*, en écrivant le code source modifié et les classes compilées dans les répertoires `spoonedSources` et `spoonedBinaries`.

### Utilisation des processeurs

Le  processor  `LogProcessor` remplace tous les appels de `System.out.println(String)` par un appel au logger `LogProcessor` qui redirige toutes les sorties console vers un fichier log. Ainsi, au lieu d'avoir `System.out.println("Hello, world!");` le fichier instrumenté contiendra `vv.spoon.logger.LogWriter.out("Hello, world!", false);`
Le Listing 1 montre la classe `C` du projet *example* une fois instrumentée.

Puisque qu'un appel à une classe exterieure au programme a été ajouté (`LogWriter`), il est nécessaire de l'ajouter au classpath lors de l'execution.
Par exemple, pour executer la version instrumentée de `example.A` depuis le répertoire 'instrumentation':

```shell
 /instrumentation$ java -classpath "target/instrumentation-1.0-jar-with-dependencies.jar:src/main/resources/example/spoonedBinaries" example.A 2
```

Vous obtiendez alors un fichier nommé `log` avec le contenu du Listing 2.
```java
package example;
public class C  {
    private int i;
    public C(int i) {
        /*;
        java.lang.System.out.println("C.C(int i)");
        */;
        vv.spoon.logger.LogWriter.out("C.C(int i)", false);
        this.i = i;
    }

    public int mth1() {
        /*;
        java.lang.System.out.println("C.mth1()");
        */;
        vv.spoon.logger.LogWriter.out("C.mth1()", false);
        return 100 / (i);
    }
}
```
**Listing 1: Code de la classe `C` après intrumentation**


```java
INFO: A.main(String[] args)
INFO: A.mth1(int count)
INFO: B.mth1(int i)
INFO: C.C(int i)
INFO: C.mth1()
ERROR: error in A.mth1(int count)
INFO: B.mth1(int i)
INFO: C.C(int i)
INFO: C.mth1()
INFO: result = 100
INFO: B.mth2()
```

**Listing 2: Fichier de log obtenu après exécution du projet *example* intrumenté**

## Travail à réaliser

### Instrumentation de programmes

Dans cette partie, vous devez écrire deux processors qui modifient des programmes Java. Le pattern à suivre pour écrire un processor est le suivant: héritage de la classe `AbstractProcessor`, redéfinition des méthodes `isToBeProcessed()` (si filtrage) et `process()`, et création d'une classe `Main` pour lancer le processor. Lors de l'héritage, vous devez fournir un type Spoon (`CtBlock`, `CtLoop`, `CtInvocation`, etc.) correspondant à l'unique type d'élément de l'AST que votre processor va analyser. Tout cela peut se faire sur le modèle du `LogProcessor` qui est fourni. De nombreux exemples sont également disponibles sur le site de Spoon.

Pour une liste des types de l'AST, consultez la [doc](http://spoon.gforge.inria.fr/structural_elements.html) de Spoon:

### Appels de méthode

Utilisez Spoon afin de compter tous les appels de méthode d'un programme donné.Pour le projet *example*, le résultat doit être semblable à ceci:
```java
A.main(String[] args): 1 
A.mth1(int count): 1
B.mth1(int i): 5
C.C(int i): 5
C.mth1(): 5
B.mth2(): 1
```

### Arbre d'appels

Utilisez Spoon afin de construire l'arbre d'appel des méthodes d'un programme donné. Pour le projet *example*, le résultat doit être semblable à ceci:


```java
A.main(String[] args)
 |  A.mth1(int count)
 |  | B.mth1(int i)
 |  |  | C.C(int i)
 |  |  | C.mth1()
 |  | B.mth1(int i)
 |  |  | C.C(int i)
 |  |  | C.mth1()
 |  | B.mth2()
```



## Remerciements

La première version du code de l'exemple a été réalisée par [Simon Allier](simon.allier@inria.fr). 