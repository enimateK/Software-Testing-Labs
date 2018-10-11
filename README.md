# Software Testing

## Setup

### Cloning the GitLab project

First of all, you clone copy the project containing all labs from the University GitLab.
As you will see, all the labs are stored in different directories.

To clone the project, use the following git command:

```shell
git clone https://gitlab.univ-nantes.fr/sunye-g/software-testing-labs.git
```

### Initializing and building the project

You can open this project directly from NetBeans or from IntelliJ or import it from Eclipse.
Before opening the project, you must initialize it from the command line:


```shell
cd software-testing-labs
./scripts/initialize.sh
```

The initialization is very important for the continuation: it will install several needed artifacts.

### Tools

During the labs, you will use 3 different tools: Apache Maven, JUnit and IntelliJ IDEA.

- To understand Apache Maven, read the text «[Premiers pas avec Maven](https://sunye.github.io/java/maven/2018/01/14/maven.html)».
- To understand JUnit, read the text «[Les tests unitaires en Java](https://openclassrooms.com/courses/les-tests-unitaires-en-java)».
- IntelliJ IDEA is already installed in the CIE's PCs. It is available at the JetBrain's [website](https://www.jetbrains.com/idea/).


## Labs

1. [JUnit](junit/).
1. [Test statique](statique/).
1. [Test fonctionnel](fonctionnel/).
2. [Test structurel](structurel/).
2. [Instrumentation](instrumentation/).
3. [Analyse de Mutation](mutation/).

