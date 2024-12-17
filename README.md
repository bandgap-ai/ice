# The Eclipse Integrated Computational Environment (ICE) Main Repository

Please see [the website](https://www.eclipse.org/ice) for more information on the project.

## Notes on using Eclipse for ICE Development

The Maven build is the preferred build for ICE. Eclipse will often report errors that don't exist in the Maven build, and it may be necessary to run `Project->Clean...` or other tasks to get Eclipse to stop reporting ghost errors.

## Build Instructions

Checkout the project and execute

```bash
mvn clean install
```

Always run the `clean` target.

### Building BATS

The above build instructions will NOT include building `org.eclipse.ice.bats`.

This is due to the need to have an installation of Docker to run the BATS integration test.

In order to include BATS, you can instead run the `full` profile for the build instruction:

```bash
mvn clean install -P full
```

## Using the project generators

Once the build is complete, use the project generators to get started with a new Eclipse ICE project. From the shell, execute the following for a basic project:
```bash
mvn archetype:generate -DarchetypeGroupId=org.eclipse.ice -DarchetypeArtifactId=data-element-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.test -DartifactId=testApp
```

For a web form project, execute the following
```bash
mvn archetype:generate -DarchetypeGroupId=org.eclipse.ice -DarchetypeArtifactId=data-element-webform-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=com.test -DartifactId=testApp
```

The group and artifact ids above are examples and should be replaced with the correct ids for your use case.

## Building Executables

It is important to determine the type of executable required before attempting to build it. If the executable is going to be used for testing, it is best to build a JUnit test that can be run as part of the regular Maven lifecycle or from within an IDE. If a command line application is required, the @CommandLineApp from org.eclipse.ice.dev.annotations annotation can be applied to a method in the top-level domain class. This will automatically generate a main() method that calls the annotated method in a class named ${className}App, where ${className} is the name of the annotated class. After that, follow the shading method in [this article](https://www.baeldung.com/executable-jar-with-maven) to create an executable jar with all the dependencies using the `mvn clean compile` and `mvn package` commands, in that order. This will produce a jar file in the target/ directory with the name `<package-name>-shaded.jar` where `<package-name>` indicates the package name. This jar can be executed with `java -jar <package-name>-shaded.jar`.

# Data Element Example

There are a number of examples in the test and org.eclipse.ice.renderer packages. There is also a [gist available](https://code.ornl.gov/snippets/109).

# Commands Examples

Examples for using the Commands tools are available [in the demo bundle](https://github.com/eclipse/ice/tree/next/org.eclipse.ice.demo/src/org/eclipse/ice/demo/commands).

# Getting started on development

Contributions follow the standard Eclipse mechanisms and you should check out our [CONTRIBUTING.md](https://github.com/eclipse/ice/blob/master/CONTRIBUTING.md) file for more information. If you are looking at this, then you are most likely using Git and can start the process by issuing 
a pull request on our GitHub repo.

If you're using Eclipse for development, you need to install Lombok into the Eclipse environment so that Eclipse knows about the bytecode changes that Lombok makes during the build process. Instructions are available [at the Lombok site](https://projectlombok.org/setup/eclipse).

# Contacting the development team

Please submit a ticket or pull request to contact the team. Alternatively, use the [ice-dev@eclipse.org mailing list](https://accounts.eclipse.org/mailing-list/ice-dev) to post questions.
