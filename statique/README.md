
# Test statique

## Compilation et tests
Avant d’analyser le code source du projet, assurez-vous qu’il compile correctement et qu’il passe tous les tests
écrits par son auteur. 
Pour cela, utilisez Maven, à partir de la ligne de commandes:

    cd tp-test-statique
    mvn compile

Le projet doit compiler correctement, mais ne réussira pas tous les tests unitaires.

# Premiers pas avec PMD
Pour commencer l’analyse du code source, vous allez utiliser l’outil PMD directement à partir de Maven, qui se
chargera de l’installer et d’exécuter l’analyse par défaut.
Dans la ligne de commandes, exécutez la commande suivante:

     mvn pmd:pmd -DtargetJdk=1.7

Lorsqu’il est exécuté sans aucune configuration, PMD applique quelques règles de base et génère un rapport dans
le format html. Ce rapport est placé dans à l’intérieur du projet, dans le dossier target/site/pmd.html

## Directives de sécurité
Vous allez maintenant configurer PMD pour qu’il vérifie que le code source respecte les directives de sécu-
rité publiées par Sun. Ces directives sont disponibles sur l’adresse suivante: 
[http://www.oracle.com/technetwork/java/seccodeguide-139067.html](http://www.oracle.com/technetwork/java/seccodeguide-139067.html).
La configuration de PMD, ou plus précisément du plug-in Maven qui gère PMD, se fait dans le fichier pom.xml. PMD n’est utilise qu’à la fin du cycle de construction du projet, pendant l’étape de génération des rapports du projet.  Éditez le fichier pom.xml pour y ajouter la configuration du plug-in PMD, qui est présentée ci-dessous:

```xml
<project>
	<reporting>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-pmd-plugin</artifactId>
				<version>3.6</version>
				<configuration>
					<sourceEncoding>utf-8</sourceEncoding>
					<minimumTokens>100</minimumTokens>
					<targetJdk>1.7</targetJdk>
					<rulesets>
						<ruleset>/rulesets/java/sunsecure.xml</ruleset>
					</rulesets>
				</configuration>
			</plugin>
		</plugins>
	</reporting>
</project>
```


Vous trouverez une description plus précise de la configuration sur le site de la fondation [Apache](http://maven.apache.org/plugins/maven-pmd-plugin/).

Demandez ensuite à Maven de générer les rapports du projet, grâce à la commande suivante:

    mvn site

Ouvrez le rapport généré par PMD et modifiez le code source du projet pour corriger les problèmes qui signalés par PMD.

## Règles de nommage
Modifiez à nouveau la configuration du plug-in, pour que PMD vérifie aussi que les conventions de codage de Sun sont respectées. Vous trouverez une description détaillée de ces conventions sur le site de [Sun](http://java.sun.com/docs/codeconv/).

Pour ajouter la vérification des règles de nommage à PMD, vous devez modifier à nouveau le fichier *pom.xml* et plus précisément, la balise `<rulesets>`:

```xml
<rulesets>
	<ruleset>/rulesets/java/sunsecure.xml</ruleset>
	<ruleset>/rulesets/java/naming.xml</ruleset>
</rulesets>
```


Régénérez les rapports du projet et corrigez le code source pour le faire respecter les conventions de codage de Sun.

Si vous utilisez Eclipse, pensez à utiliser le menu **Refactor** pour renommer les classes, les attributs et les méthodes.


## Autres règles
Modifiez la configuration du plug-in PMD pour que les règles suivantes soient prises en compte: "braces" et "unusedcode".

Après cette modification, régénérez le rapport de PMD et corrigez les problèmes retrouvés. 

# Extension de PMD

PMD permet de facilement ajouter de nouvelles règles. 
Les nouvelles règles doivent hériter de la classe `net.sourceforge.pmd.lang.java.rule.AbstractJavaRule` et redéfinir une ou plusieurs méthodes `visit(ASTStatement node, Object data)`. De plus, tous les objets `node` implémentent l'interface `net.sourceforge.pmd.lang.ast.Node`. Celle-ci  fournit des méthodes utiles pour écrire les règles.  Enfin, les règles doivent être décrites dans un ruleSet. 

Un tutoriel se trouve à l’adresse suivante: [http://pmd.sourceforge.net/pmd-5.0.5/howtowritearule.html](http://pmd.sourceforge.net/pmd-5.0.5/howtowritearule.html). 

## Exemple de règle "chaque while doit avoir des accolades"

On définit une règle autorisant uniquement les boucles while faisant usage d'accolades. La classe définissant cette règle et  le fichier XML de configuration du ruleSet contenant cette règle sont présentés ci-dessous.

Boucle *while* correcte:

```java
while (baz) {
   buz.doSomething();
}
```

Boucle *while* incorrecte:

```
while (baz)
   buz.doSomething();
```


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



```xml
<!-- src/main/resources/rulesets/java/MyRulesSet.xml -->
<?xml version="1.0"?>
<ruleset name="MyRules"
    xmlns="http://pmd.sourceforge.net/ruleset/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/ruleset/2.0.0 http://pmd.sourceforge.net/ruleset_2_0_0.xsd">
    <rule name="WhileLoopsMustUseBracesRule"
          message="Avoid using 'while' statements without curly braces"
          class="vv.tp3.WhileLoopsMustUseBracesRule">
      <description>
      Avoid using 'while' statements without using curly braces
      </description>
        <priority>3</priority>

      <example>
<![CDATA[
    public void doSomething() {
      while (x < 100)
          x++;
    }
]]>
      </example>
    </rule>
</ruleset>
```


Pour utiliser cette règle, ajoutez la ligne suivante au fichier `pom.xml`:
​	
```xml
	<ruleset>./src/main/resources/rulesets/java/MyRulesSet.xml</ruleset>
```


## Ecrivez les trois règles suivantes:

* Une violation est levée dès que deux boucles `for` sont imbriquées.
* Une violation est levée pour chaque `while(true)` ou `while(false)`.
* Raffinez la règle précédente en prenant en compte les possibilités d’échappement `break` ou `return`) dans la boucle `while`.



