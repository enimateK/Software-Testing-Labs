# Tutoriel sur l' Analyse de mutation

## Objectifs

L'objectif de ce tutoriel est d'introduire les concepts liés à l'analyse de mutation et à son applications pratique, grâce à l'outil PIT.

Ce tutoriel dure environ 1h. 

## Préparation 

Lisez  attentivement ce texte sur [l'Analyse de mutation](https://sunye.github.io/java/pit/2017/12/15/analyse-mutation.html).

Ensuite, clonez le projet «Measures», disponible également sur GitLab:

```shell
git clone https://gitlab.univ-nantes.fr/sunye-g/measures.git
```

## Le projet «Measures»

Utilisez votre IDE préféré pour analyser le code du projet et de ses tests unitaires.
Ensuite, générez les rapports de projet grâce à la comande Maven suivante:

```shell
mvn -U test site
```

Il est important d'exécuter les tests avant la génération des pages web des rapports,
car Maven ne les exécutera pas automatiquement. 
Vous trouverez les résultars en consultant la page `./target/site/index.html`.

## Travail à faire

Lisez d'abord les rapports sur la couverture de code générés par JaCoCo, que vous trouvrez
en consultant la page `/target/site/jacoco/index.html`.

Lisez ensuite les rapports sur l'analyse de mutation générés par PIT, que
vous trouverez dans la page `./target/site/pit-reports/index.html`

Répondez ensuite aux questions suivantes:

1. Quelle est la couverture de code du projet ? 
2. Qu'est-ce que cette valeur représente ?
3. Quelle est le score de mutation (mutation coverage) du projet ?
4. Qu'est-ce que cette valeur représente ?
5. Pourquoi ces deux valeurs sont différentes ?
6. Qu'est-ce qu'elles peuvent dire sur la qualité des tests unitaires du projet ?
7. Est-ce que l'ajout de nouveaux tests unitaires permettrait d'améliorer la couverture de code ou le score de mutation ?
8. Comment pourriez-vous améliorer la qualité des tests ? 
9. Est-ce que l'amélioration aurait un impact sur la couverture de code ou sur le score de mutation ? 





