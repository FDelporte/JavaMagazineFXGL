# FXGl example game as described on Oracle Java Magazine

A JavaFX/FXGL example game.

## About the project

A full description will be published on Oracle Java Magazine in different posts. For each post a separate branch is
created.

### Post 1 - "Building a game with JavaFX and FXGL"

Article: ["Look out, Duke! How to build a Java game with JavaFX and the FXGL library"](https://blogs.oracle.com/javamagazine/java-javafx-fxgl-game-development)

> Branch "main"

### Post 2 - "Controlling a JavaFX game with a joystick on Raspberry Pi"

> Branch "pi4j"

### Post 3 - "Publishing a JavaFX game to the Google Play Store"

> Branch "mobile"

## Basic Requirements

A list of the basic requirements can be found online in
the [Gluon Client documentation](https://docs.gluonhq.com/client/#_requirements).

## Quick instructions

### GraalVM on Mac

* Use sdkman - https://sdkman.io/
* Install GraalVM with `sdk install java 21.1.0.r16-grl`
* Set environment variable with `export GRAALVM_HOME=${SDKMAN_CANDIDATES_DIR}/java/21.1.0.r16-grl`
* Check variable with `echo $GRAALVM_HOME`

### Run the sample

    mvn javafx:run

### Run the sample as a native image

    mvn client:build client:run

### Run the sample as a native android image

    mvn -Pandroid client:build client:package client:install client:run

### Run the sample as a native iOS image

    mvn -Pios client:build client:package client:install client:run

## GitHub Actions

https://docs.gluonhq.com/#_the_gluon_client_plugin_for_maven

### Secrets

* ANDROID_KEYALIAS
* ANDROID_KEYALIAS_PASSWORD
* ANDROID_KEYSTORE_BASE64
* ANDROID_KEYSTORE_PASSWORD
* SERVICE_ACCOUNT_JSON
* GLUON_LICENSE

## Credits

* Cloud computer icon from [flaticon.com](https://www.flaticon.com) > [srip](https://www.flaticon.com/authors/srip)