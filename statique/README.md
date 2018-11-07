# Test statique

## Compilation et tests

Avant d’analyser le code source du projet, assurez-vous qu’il compile correctement et qu’il passe tous les tests
écrits par son auteur. 
Pour cela, utilisez Maven, à partir de la ligne de commandes:

    cd statique
    mvn compile

Le projet doit compiler correctement et réussira tous les tests unitaires.

# Premiers pas avec PMD

Pour commencer l’analyse du code source, vous allez utiliser l’outil PMD directement à partir de Maven, qui se
chargera de l’installer et d’exécuter l’analyse par défaut.

Avant toute chose, générez le site web du projet (vous n'aurez pas besoin de refaire 
cette étape) :

    mvn site

Puis pour appeler PMD, exécutez la commande suivante dans un terminal :

     mvn pmd:pmd -DtargetJdk=1.7

Lorsqu’il est exécuté sans aucune configuration, PMD applique quelques règles de base et génère un rapport dans
le format html. Ce rapport est placé dans à l’intérieur du projet à l'emplacement suivant 
: `target/site/pmd.html`

## Directives de sécurité

Vous allez maintenant configurer PMD pour qu’il vérifie que le code source respecte les directives de sécurité publiées par Sun. Ces directives sont disponibles sur l’adresse suivante: 
[http://www.oracle.com/technetwork/java/seccodeguide-139067.html](http://www.oracle.com/technetwork/java/seccodeguide-139067.html).
La configuration de PMD, ou plus précisément du plug-in Maven qui gère PMD, se fait dans le fichier pom.xml.
PMD n’est utilise qu’à la fin du cycle de construction du projet, pendant l’étape de génération des rapports du projet.
Modifiez le fichier `pom.xml` pour y ajouter la configuration du plug-in PMD, qui est présentée ci-dessous:

```xml
<project>
	<build>
		<plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-pmd-plugin</artifactId>
                <configuration>
                    <sourceEncoding>${project.build.sourceEncoding}</sourceEncoding>
                    <minimumTokens>100</minimumTokens>
                    <targetJdk>${maven.compiler.target}</targetJdk>
                    <rulesets>
                        <ruleset>/rulesets/java/sunsecure.xml</ruleset>
                    </rulesets>
                </configuration>
            </plugin>
		</plugins>
	</build>
</project>
```


Vous trouverez une description plus précise de la configuration sur le site de la fondation [Apache](http://maven.apache.org/plugins/maven-pmd-plugin/).

Comme précédement, vous pouvez appeler pmd avec la commande suivante :

    mvn pmd:pmd -DtargetJdk=1.7

Ouvrez le rapport généré par PMD et modifiez le code source du projet pour corriger les problèmes signalés.


## Règles de nommage

Modifiez à nouveau la configuration du plug-in, pour que PMD vérifie aussi que les conventions de codage de Sun sont respectées. Vous trouverez une description détaillée de ces conventions sur le site de [Sun](http://java.sun.com/docs/codeconv/).

Pour ajouter la vérification des règles de nommage à PMD, vous devez modifier à nouveau le fichier *pom.xml* et plus précisément, la balise `<rulesets>`:

```xml
<rulesets>
  <ruleset>/rulesets/java/sunsecure.xml</ruleset>
  <ruleset>/rulesets/java/naming.xml</ruleset>
  <ruleset>/rulesets/java/codesize.xml</ruleset>
</rulesets>
```


Régénérez les rapports du projet et corrigez le code source pour le faire respecter les conventions de codage de Sun.

Si vous utilisez Eclipse, pensez à utiliser le menu **Refactor** pour renommer les classes, les attributs et les méthodes.


## Autres règles

Modifiez la configuration du plug-in PMD pour que les règles suivantes soient prises en compte: "braces" et "unusedcode".

Après cette modification, régénérez le rapport de PMD et corrigez les problèmes retrouvés. 


# Extension de PMD

PMD permet de facilement ajouter de nouvelles règles. 
Une nouvelle règle doit hériter de la classe `net.sourceforge.pmd.lang.java.rule.AbstractJavaRule` et redéfinir une ou plusieurs méthodes `visit(ASTStatement node, Object data)`. 
De plus, tous les objets `node` implémentent l'interface `net.sourceforge.pmd.lang.ast.Node`. 
Celle-ci  fournit des méthodes utiles pour écrire les règles. 
Enfin, les règles doivent être décrites dans un *ruleSet*, qui est un fichier xml listant 
et décrivant un ensemble de règles. 

Un ensemble de règles pour PMD doit être défini et compilé sous la forme d'un plugin 
maven indépendant du code source à vérifier.
Un tel plugin de règles PMDs peut ainsi être réutilisé au sein de différents projets. 
On doit donc définir un nouveau projet maven dans lequel nous écrirons nos règles. 
Pour ce TP, on codera nos règles dans le projet `custompmdrules` situé dans 
le répertoire `statique`.
Puis on configurera le projet `statique` (qui contient le code source à analyser) de manière à appeler PMD avec les règles définies 
dans `custompmdrules`.


## Ajout d'une règle "chaque while doit avoir des accolades"

En premier lieu, nous allons voir comment configurer PMD pour utiliser une règle déjà 
codée dans le proket `custompmdrules`, et déjà déclairée dans une *ruleSet*.

On considère une règle autorisant uniquement les boucles while faisant usage d'accolades. 
La classe définissant cette règle et le fichier XML de configuration du ruleSet contenant cette règle sont présentés ci-après.

Cette règle considère une boucle *while* comme correcte si elle possède des accolades :

```java
while (baz) {
   buz.doSomething();
}
```

Et elle considère une boucle *while* comme incorrecte si elle n'en possède pas :
```
while (baz)
   buz.doSomething();
```

La règle est définie par le code suivant, que l'on trouve dans le fichier `custompmdrules/src/main/java/fr/univnantes/pmd/rules/WhileLoopsMustUseBracesRule.java`: 

```java
package fr.univnantes.pmd.rules;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileLoopsMustUseBracesRule extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {
        Node firstStmt;
        firstStmt = (Node)node.jjtGetChild(1);
        if (!hasBlockAsFirstChild(firstStmt)) {
            //ajout de la vioaltion
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasBlockAsFirstChild(Node node) {
        return (node.jjtGetNumChildren() != 0 && (node.jjtGetChild(0) instanceof ASTBlock));
    }
}
```

Cette règle est déclaré dans le *ruleSet* suivant, que l'on trouve dans le fichier 
`/statique/src/main/resources/rulesets/java/MyRulesSet.xml` :

```xml
<?xml version="1.0"?>
<ruleset name="My custom rules"
        xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 http://pmd.sourceforge.net/ruleset_2_0_0.xsd">
    <rule name="WhileLoopsMustUseBracesRule"
            message="Avoid using 'while' statements without curly braces"
            class="fr.univnantes.pmd.rules.WhileLoopsMustUseBracesRule">
        <description>
            Avoid using 'while' statements without using curly braces
        </description>
        <priority>3</priority>

        <example>
            <![CDATA[
                public void doSomething() {
                    while (true)
                    x++;
                }
            ]]>
        </example>
    </rule>
</ruleset>
```

Pour utiliser cette règle, trois choses sont nécessaires :

1. Compilez le projet `custompmdrules` qui comprend la règle, à l'aide de la commande `mvn install`. 
   Cela rendra le plugin disponible globalement et accessible par PMD.
   Cette action doit être effectuée à nouveau dès que la règle est changée et doit 
   être recompilée.
2. Dans le fichier `pom.xml` du projet `statique`, ajoutez à la configuration de PMD une dépendance au plugin 
   contenant la règle, pour que PMD y ait accès :
```xml
<build>
        <plugins>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-pmd-plugin</artifactId>
                ...
                <dependencies>
                    <dependency>
                        <groupId>fr.unantes</groupId>
                        <artifactId>custompmdrules</artifactId>
                        <version>1.0</version>
                    </dependency>
                </dependencies>
                ...
             <plugin>
            ...
        <plugins>
<build>
```
3. Toujours dans le fichier `pom.xml` du projet `statique`, ajoutez le *ruleSet* correspondant 
  au sein de PMD avec la ligne suivante :​	
```xml
	<ruleset>./src/main/resources/rulesets/java/MyRulesSet.xml</ruleset>
```

Puis exécutez PMD à nouveau, et constatez et corrigez la nouvelle erreur que cette règle doit trouver.


## Ecrivez les trois règles suivantes:

* Une violation est levée dès que deux boucles `for` sont imbriquées.
* Une violation est levée pour chaque `while(true)` ou `while(false)`.
* Raffinez la règle précédente en prenant en compte les possibilités d’échappement `break` ou `return`) dans la boucle `while`.

Notez que :
- Il faudra prendre soin à écrire ces règles au sein du projet `custompmdrules`, et à 
les ajouter au fichier xml contenant le `ruleSet`.
- Le projet fourni ne contient pas de code qui enfreint ces règles, vous devrez donc 
  ajouter du code artificiel au sein du projet `statique` pour tester vos règles. 

