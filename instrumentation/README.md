# TP Instrumentation

[Spoon](http://spoon.gforge.inria.fr/) est un outil libre qui permet de transformer et d'analyser du code source Java. 
À partir des sources d'un programme, Spoon construit son arbre abstrait (AST) et permet de le modifier 
(modification, ajout ou suppression de noeuds de l'AST), pour ensuite (re)générer le code Java correspondant.


## Préparation

### Installation

Spoon peut-être utilisé sous la forme d'une dépendence Maven, simplement en ajoutant a votre pom.xml la dépendence suivante:

```xml
<dependency>
  <groupId>fr.inria.gforge.spoon</groupId>
  <artifactId>spoon-core</artifactId>
  <version>7.1.0</version>
</dependency>

```

### Principe de fonctionnement


Dans ce TP, nous allons utiliser Spoon afin d'analyser dynamiquement un programme (analyse de l'exécution d'un programme). 
Pour cela vous devez créer des *processeurs*, c'est à dire, des sous-classes de la classe `Processor`, qui permettent la
modification du code source de programmes.

Mais avant cela, nous allons étudier le fonctionnement d'un autre processeur, `LogProcessor`, qui remplace des appels à
`print()` par un appel à une classe de log. Par exemple, le code suivant:

```java
java.lang.System.out.println("Hello!");
```

sera remplacé par:

```java
LogWriter.out("Hello!", false);
```

Les processeurs sont des applications du patron de conception **Visitor**, qui traversent l'AST et appelent des méthodes
spécifiques chaque fois qu'un noeud de l'AST est visité.
Dans le cas du `LogProcessor`, il s'intéresse à la visite des noeuds `Invocation`, c'est à dire, à tous les appels 
de méthode d'un programme.

Le processeur va d'abord filtrer les appels des méthodes appelées "print()" sur des objects de type `PrintString` 
(le type de l'attribut `out` de la classe `System`).
Ensuite, il va remplacer cet appel de méthode, par un appel de la méthode `out()` de la classe `LogWriter`.

### Le LogProcessor

La class `LogProcessor` a besoin de deux méthodes pour réaliser sa tâche: `isToBeProcessed()` et `process()`.
Tout d'abord, la méthode `isToBeProcessed()` filtre tous les appels de `System.out.println()`, en retournant `true` 
lorsqu'un appel correspond:

```java
    @Override
    public boolean isToBeProcessed(CtInvocation ctInvocation) {
        try {
            CtTypeReference ctTypeReference = ctInvocation.getTarget().getType();

            if (PRINTSTREAM_REFERENCE.equals(ctTypeReference)) {
                // The statement analysed is a System.out
                CtExecutableReference ctExecutableReference = ctInvocation.getExecutable();
                if (isPrint(ctExecutableReference)) {
                    //The statement analysed is a System.out.println
                    return true;
                }
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
```

Ensuite, la méthode `process()` commente l'appel de `println()` et ajoute un appel au `LogWriter`.

```java
    @Override
    public void process(CtInvocation ctInvocation) {
        CtStatementList ctStatementList = new CtStatementListImpl<>();

        ctInvocation.insertBefore(getFactory().Code().createCodeSnippetStatement("/*")); //Open comment before the System.out.print

        ctStatementList.addStatement(getFactory().Code().createCodeSnippetStatement("*/")); //Close comment after System.out.print
        
        //Add the logger after the System.out.print
        ctStatementList.addStatement(getFactory().Code().createCodeSnippetStatement("LogWriter.out("+ctInvocation.getArguments().get(0).toString()+", "+isError(ctInvocation)+")"));
        ctInvocation.insertAfter(ctStatementList);
    }
```

### Application des processeurs

Pour appliquer le ou les processeurs à un programme source, Spoon doit d'abord charger les fichiers source, grâce à
un `Launcher`, qui est initialisée de la manière suivante:

```java
File projectToInstrument = new File("src/main/resources/example");

Launcher launcher = new MavenLauncher(projectToInstrument.getAbsolutePath(), MavenLauncher.SOURCE_TYPE.ALL_SOURCE);

launcher.addProcessor(new LogProcessor());
launcher.setSourceOutputDirectory(new File(projectToInstrument, "spoonedSources"));
launcher.setBinaryOutputDirectory(new File(projectToInstrument, "spoonedBinaries"));

launcher.getEnvironment().setShouldCompile(true);
launcher.run();
```

En executant la méthode `run()` du `Launcher`, le code source sera réécrit en fonction des processeurs dans le 
dossier `spoonedSources`, et ce nouveau code  sera compilé dans le dossier `spoonedBinaries`.

L'utilisation de la classe `MavenLauncher` à la place du `Launcher` standard permet à Spoon de gérer automatiquement 
le classpath lors de la compilation à partir du fichier `pom.xml` et ainsi d'alléger le code.

Le programme donné doit être compilé avant d'être utilisé.

### Exécution

- Lancez la commande `mvn package` qui produira le *ubber* jar `target/instrumentation-1.0-jar-with-dependencies.jar`.
- Ce jar pourra être exécuté, en passant en paramètre le programme à instrumenter. Par example:

```shell
java -jar instrumentation-1.0-jar-with-dependencies.jar src/main/resources/example
```

Cette commande instrumentera le projet *example*, en écrivant le code source modifié et les classes compilées dans les 
répertoires `spoonedSources` et `spoonedBinaries`.


### Exécution du programme transformé

Le  processor  `LogProcessor` remplace tous les appels de `System.out.println(String)` par un appel au logger `LogProcessor` qui redirige toutes les sorties console vers un fichier log. Ainsi, au lieu d'avoir `System.out.println("Hello, world!");` le fichier instrumenté contiendra `LogWriter.out("Hello, world!", false);`
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
        LogWriter.out("C.C(int i)", false);
        this.i = i;
    }

    public int mth1() {
        /*;
        java.lang.System.out.println("C.mth1()");
        */;
        LogWriter.out("C.mth1()", false);
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

### Appels de méthodes

Utilisez Spoon afin de compter tous les appels de méthode d'un programme donné.
Pour le projet *example*, le résultat doit être semblable à ceci:

```java
A.main(String[] args): 1 
A.mth1(int count): 1
B.mth1(int i): 5
C.C(int i): 5
C.mth1(): 5
B.mth2(): 1
```

### Arbre d'appels

Utilisez Spoon afin de construire l'arbre d'appel des méthodes d'un programme donné. 
Pour le projet *example*, le résultat doit être semblable à celui-ci:


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