# FXGl example game as described on Oracle Java Magazine

A JavaFX/FXGL example game.

## About the project

A full description will be published on Oracle Java Magazine in different posts. For each post a separate branch is
created.

### Post 1 - "Building a game with JavaFX and FXGL"

Article: ["Look out, Duke! How to build a Java game with JavaFX and the FXGL library"](https://blogs.oracle.com/javamagazine/java-javafx-fxgl-game-development)

> Branch "main"

### Post 2 - "Controlling a JavaFX game with a joystick on Raspberry Pi"

Article: ["Look out, Duke! Part 2: Control a Java game with a Raspberry Pi"](https://blogs.oracle.com/javamagazine/post/look-out-duke-part-2-control-a-java-game-with-a-raspberry-pi-and-a-joystick)

> Branch "pi4j"

### Post 3 - "Building a JavaFX game as a native application"

For this article all dependencies have been pushed to the latest versions (of beginning of 2022).

> Branch "native"

Based on: https://docs.gluonhq.com/#platforms_linux_github_actions

## Basic Requirements

A list of the basic requirements can be found online in
the [Gluon Client documentation](https://docs.gluonhq.com/client/#_requirements).

## Quick instructions

Building on PC with GraalVM.

### GraalVM on Mac

* Use sdkman - https://sdkman.io/
* Install GraalVM with `sdk install java 22.0.0.2.r11-grl`
* Set environment variable with `export GRAALVM_HOME=${SDKMAN_CANDIDATES_DIR}/java/22.0.0.2.r11-grl`
* Check variable with `echo $GRAALVM_HOME`

### GraalVM on Linux

* Same as Mac
* Install additional libraries

```shell
sudo apt install libasound2-dev libavcodec-dev libavformat-dev libavutil-dev libgl-dev libgtk-3-dev libpango1.0-dev libxtst-dev
```

### Run the sample

```
mvn javafx:run
```

### Run the sample as a native android image

```
mvn -DconsoleProcessLog=true -Pandroid gluonfx:build gluonfx:package
```

### Build the sample as a native desktop application

```
mvn -Pdesktop gluonfx:build gluonfx:package
```

### Build the sample as an Android application

```
mvn -Pandroid gluonfx:build gluonfx:package
```

## Debug crashes on Android device

* Connect with USB cable
* Enable USB debug in Settings
* Install Android Studio on PC
* In terminal on PC:

```shell
$ cd ~/Android/Sdk/platform-tools
$ ./adbs logcat | grep magazine

```

## Google Console

### Create a service account

* https://console.cloud.google.com
*

## Google Play Store

### Create an app

* https://play.google.com/console
* "Create app"

### Users and permissions

## GitHub Actions

https://docs.gluonhq.com/#_the_gluon_client_plugin_for_maven

### Secrets

* ANDROID_KEYALIAS
* ANDROID_KEYALIAS_PASSWORD
* ANDROID_KEYSTORE_BASE64
* ANDROID_KEYSTORE_PASSWORD
* ANDROID_SERVICE_ACCOUNT_JSON
* GLUON_LICENSE

## Credits

* Cloud computer icon from [flaticon.com](https://www.flaticon.com) > [srip](https://www.flaticon.com/authors/srip)
