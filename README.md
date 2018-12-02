# Tutoriel React Native avec C++ pour Android

Tutoriel pour la création d'une application React Native utilisant du code C++ avec Android. Les bases sont les mêmes pour une application iOS.

## À propos de ce tutoriel
Les technologies avançant très rapidement et tous modules n'étant pas mis à jour aussi régulièrement, des incompatibilités peuvent se créer au fil du temps. Dans le dossier de l'application servant d'exemple vous trouverez le fichier package.json qui contient les versions des modules utilisés lors de la réalisation de ce tutoriel. Vous pouvez modifier votre propre fichier package.json pour y modifier les versions que vous utilisez et donc retrouver la compatibilité des modules que j'ai utilisés. Notez toutefois qu'utiliser d'ancienne version peut rendre votre application plus à risque, les mises à jour servent souvent à supprimer des failles de sécurité.

Ce tutoriel a été réalisé avec ces environnements suivants :

Windows 10 :

- Terminal de commande : PowerShell
- IDE : 
- VSCode et/ou WebStorm
- Android Studio

La partie concernant le code C++ via JNI n'a pas pu être réussie sur Windows, mais a été faite sur MacOS et Linux pour ensuite être transférée dans Windows.
## Prérequis
### Windows
- Installer [Chocolatey](https://chocolatey.org/)
- Installer Node.js, Python2, Java Developper Kit 8 depuis la commande : 
    ```shell
    choco install -y nodejs.install python2 jdk8
    ```
    - Si besoin, vous pouvez upgrader vos versions déjà installées :
    ```shell
    choco upgrade chocolatey
    ```
    ```shell
    choco upgrade -y nodejs.install python2 jdk8
    ```
    - Vous pouvez aussi installer une version du [JDK plus récent](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK
- Installer React Native depuis la commande : 
    ```shell
    npm install -g react-native-cli
    ```
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](tps://facebook.github.io/react-native/docs/getting-started#2-install-the-android-sdk) pour les paramètres
### MacOS
*Testé sur MacOS pour la partie JNI seulement, tutoriel non terminé pour Mac*
- Installer [Homebrew](https://brew.sh/)
- Installer Node.js et Watchman depuis les commandes : 
    ```shell
    brew install node
    brew install watchman
    ```
- Installer React Native depuis la commande : 
    ```shell
    npm install -g react-native-cli
    ```
    - Si vous voyez une erreur du type *Cannot find module 'npmlog'*, essayez d'installer npm directement par la commande : 
    ```shell
    curl -0 -L https://npmjs.org/install.sh | sudo sh
    ```
- Installer [Java Developper Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) ou plus récent : 
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK : 
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](https://facebook.github.io/react-native/docs/getting-started#1-install-android-studio) pour les paramètres : 
### Linux
*Testé sur kubuntu 18.10, tutoriel non terminé pour linux*
- Installer [Node.js](https://nodejs.org/en/download/package-manager/) selon la version de votre distribution Linux : 
    ```shell
    snap install nodejs --classic --channel=10/stable
    apt install npm
    ```
- Installer React Native depuis la commande : 
    ```shell
    npm install -g react-native-cli
    ```
- Installer [Java Developper Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) ou plus récent : 
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK.
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](https://facebook.github.io/react-native/docs/getting-started#1-install-android-studio) pour les paramètres :
    ```shell
    snap install android-studio --classic
- Suivre ces indications pour installer [Watchman](https://facebook.github.io/react-native/docs/getting-started#watchman)

## Créer votre application
*Toutes les commandes suivantes sont basées sur Windows et ont été exécutées via PowerShell, vous pourriez avoir à les adapter si vous utilisez MacOS ou Linux*

Pour créer l'application, exécutez la commande suivante :
```shell
react-native init HelloWorld
```
Allez ensuite dans le répertoire de votre programme :
```shell
cd HelloWorld
```
Vous pouvez par la suite démarrer votre application pour Android :
```shell
react-native run-android
```
*Vous devez au préalable avoir un appareil Android connecté ou un émulateur Android en marche*

Dans votre répertoire vous devriez trouver le fichier **App.js**, ouvrez-le et remplacez le code par :
```javascript
import React, { Component } from 'react';
import { Text, View } from 'react-native';

export default class HelloWorldApp extends Component {
  render() {
    return (
      <View>
        <Text>Hello World!</Text>
      </View>
    );
  }
}
```
Vous avez terminé avec votre première application.
## Utiliser du code Java
Ici, nous créerons une librairie React Native pour utiliser du code Java, pour ce faire nous aurons besoin d'installer l'utilitaire de React Native simplifiant la procédure.
```shell
npm install -g react-native-create-library
```
Bien qu'elle peut-être créé n'importe où, nous la créerons directement dans le répertoire de notre application HelloWorld.
Nous débuterons donc par créer la librairie à l'aide de la commande suivante.
```shell
react-native-create-library my-library
```
Si vous allez dans le répertoire my-library que vous venez de créer, vous trouverez un fichier **README**, les instructions à l'intérieure peuvent-être utiles mais certaines informations semblent être erronées et mon causés des difficultés.

Revenez dans le répertoire de votre application Hello World et exécutez les commandes suivantes :
```shell
npm install my-library --save
react-native link react-native-my-library
```
Notez l'ajout du ````react-native-```` devant le nom de votre librairie, cela est fait automatiquement. 

### *NOTE IMPORTANTE* ###
_Chaque fois que vous modifiez le code de votre librairie, vous devrez absolument refaire l'étape précédente et vous aurez ensuite à redémarrer votre application tel que vu à l'étape **Créer votre application**._

_En cas d'erreur lors du ````react-native link```` exécutez la commande ````npm install```` dans la racine de votre programme et refaites la commande ````react-native link````._

_Si après l'installation de votre library vous n'arrivez plus à exécuter, cela peut être dû au fait que le module Android de votre librarie créé par React Native Create Library n'est plus à jour et cela crée une incompatibilité. Pour régler ce problème, ouvrez le dossier ````android```` se trouvant dans votre dossier ````my-library```` avec Android Studio, répondez non à toutes les questions pour être sûr de ne rien briser dans votre code. Une fois ouvert allez dans ````Build````->````Clean Project````. Puis exécutez votre programme, tout devrait fonctionner. Souvenez-vous de ce point, car il pourrait vous être utile à nouveau._

Maintenant à l'emplacement suivant dans votre librarie :

`my-library/android/src/main/java/com/reactlibrary/`

Et ouvrez le fichier **RNMyLibraryModule.java** :
```java
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNMyLibraryModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNMyLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMyLibrary";
  }
}
```
Nous y ajouterons la méthode suivante :
```java
  @ReactMethod
  public void helloWorld(Callback callback) {
    callback.invoke("Hello World From Java!");
  }
```
Notez bien que le **@ReactMethod** est essentiel pour toutes méthodes qui sera appelé depuis votre application.

Vous pouvez maintenant aller dans le fichier de votre application et ajouter l'importation suivante :
```javascript
import RNMyLibrary from './my-library';
```
Et modifier le corps de votre code pour l'utiliser
```javascript
import React, { Component } from 'react';
import { Text, View } from 'react-native';
import RNMyLibrary from './my-library';

export default class HelloWorldApp extends Component {

    constructor(props) {
        super(props);

        this.state = {
            helloWorld: ''
        };

        /**
         * Votre fonction java s'exécutera dans le constructeur et
         * retournera son résultat à votre fonction JS
         **/
        RNMyLibrary.helloWorld((result) => {
            this.helloWorldHandler(result)
        });
    }
    render() {
        return (
            <View>
                <Text>{this.state.helloWorld}</Text>
            </View>
        );
    }

    /**
     * Votre fonction JS mettra à jour le State de votre application.
     * La mise à jour du State est ce qui indique à votre application
     * de faire un refresh.
     **/
    helloWorldHandler(result) {
        this.setState({ helloWorld: result });
    }
}
```
Vous venez de créer votre première application utilisant des fonctionnalités Java!

_*Si votre application ne fonctionne pas, assurez-vous d'avoir bien réexécuté la commande ````npm install my-library```` dans la racine de votre application pour lui indiquer les mises à jour de votre module.*_

## Amélioration de votre code Java

Précédemment nous avons utilisé le ````Callback```` pour nous renvoyer notre ````Hello World!````, mais l'utilisation de ````Promise```` pourrait être préférable pour permettre l'utilisation de fonction asynchrone.

Cas d'exemple, votre application devient complexe et vous voulez appeler une fonction Java en utilisant son retour pour modifier votre State et rafraichir votre page. Vous constatez que votre application n'est pas rafraichie après l'appel de votre fonction, lorsque vous interagissez avec une autre fonction par la suite et que votre application se rafraichit vous remarquez que votre retour de fonction Java apparaît soudainement. Ce problème peut être dû à la mauvaise synchronisation de vos différentes sections de code, c'est-à-dire que votre application se rafraichit alors que votre c'est-à-dire Java n'a pas terminé de traiter l'information.

Sans allez plus en détail voici l'alternative de ````Callback````

**RNMyLibraryModule.java** :
```java
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class RNMyLibraryModule extends ReactContextBaseJavaModule {

  private static final String E_HELLO_WORLD_ERROR = "E_HELLO_WORLD_ERROR";

  private final ReactApplicationContext reactContext;

  public RNMyLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMyLibrary";
  }

  @ReactMethod
  public void helloWorld(Promise promise) {
      promise.resolve("Hello World From Java!");
  }
}
```

**App.js** :
```javascript
import React, { Component } from 'react';
import { Text, View } from 'react-native';
import RNMyLibrary from './my-library';

export default class HelloWorldApp extends Component {

    constructor(props) {
        super(props);

        this.state = {
            helloWorld: ''
        };

        /**
         * Votre fonction JS s'exécutera dans le constructeur
         **/
        this.helloWorldHandler();
    }
    render() {
        return (
            <View>
                <Text>{this.state.helloWorld}</Text>
            </View>
        );
    }

    /**
     * Votre fonction asynchrone JS appellera votre fonction JAVA et
     * attendra le retour avant de mettre à jour le State
     * pour ainsi activer le refresh.
     **/
    async helloWorldHandler() {
        try {
            let result = await RNMyLibrary.helloWorld();
            this.setState({ helloWorld: result });
        } catch (e) {
            console.error(e)
        }
    }
}
```

En résumé : [Promise](https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Objets_globaux/Promise) permet de gérer les situations de concurrence, il peut être utilisé autant dans vos librairies Java que dans vos fonctions JavaScript normales. Callback peut-être plus rapide et utilisé si rien ne dépend de son retour et/ou exécution.

## Utiliser du code C++

### Prérequis
React Native ne support pas nécessairement les dernières versions des SDK et NDK, il est donc préférable de suivre les indications sur le [site officiel](https://facebook.github.io/react-native/docs/building-from-source#prerequisites) pour savoir qu'elles sont les prérequis. Les liens pour le téléchargement de la dernière version NDK supporté sont donnés sur le [même site](https://facebook.github.io/react-native/docs/building-from-source#download-links-for-android-ndk). Notez que j'ai réalisé ce tutoriel avec la révision 18 alors que React Native suggérait la révision 17 sur leur site.

Nous allons importer un sous module dans votre projet, plus particulièrement dans **my-library**, ouvrez un terminal dans la racine de votre librairie et ajoutez DJINNI :
```shell
git submodule add https://github.com/dropbox/djinni.git deps/djinni
```
Nous allons générer la majeure partie de notre code grâce à Djinni, ce code pourrait être utilisé pour faire le pont entre C++ et Java (Android) et entre Objective-C et C++ (iOS).

Créez le fichier suivant :

**helloword.djinni**
```djinni
hello_world = interface +c {
  static create(): hello_world;
  get_hello_world(): string;
}
```
La méthode ````create()```` retournera une instance de notre objet C++ avec les méthodes que nous aurons indiqué dans ce fichier. La fonction ````get_hello_world()```` retournera la _String_ ````Hello Worlds!````.

Nous devons aussi créer le script qui s'occupera de générer notre code :

**run_djinni.sh**
```bash
#! /usr/bin/env bash

### Configuration

# Localisation du fichier djinni
djinni_file="helloworld.djinni"

# Namespace C++ namespace pour generated-src
namespace="helloworld"

# Préfix de class Objective-C pour generated-src
objc_prefix="HW"

# Nom du package Java pour generated-src, c'est le nom de package de notre module my-librairy
java_package="com.reactlibrary"


### Script

# le dossier de base
base_dir=$(cd "`dirname "0"`" && pwd)

# le dossier java de base
java_dir=$(echo $java_package | tr . /)

# dossier de sortie pour generated-src
cpp_out="$base_dir/generated-src/cpp"
objc_out="$base_dir/generated-src/objc"
jni_out="$base_dir/generated-src/jni"
java_out="$base_dir/generated-src/java/$java_dir"

# effacer les dossiers generated-src
rm -rf $cpp_out
rm -rf $jni_out
rm -rf $objc_out
rm -rf $java_out 

# exécution des commande djinni
deps/djinni/src/run \
   --java-out $java_out \
   --java-package $java_package \
   --ident-java-field mFooBar \
   --cpp-out $cpp_out \
   --cpp-namespace $namespace \
   --jni-out $jni_out \
   --ident-jni-class NativeFooBar \
   --ident-jni-file NativeFooBar \
   --objc-out $objc_out \
   --objc-type-prefix $objc_prefix \
   --objcpp-out $objc_out \
   --idl $djinni_file
```

Les étapes suivantes n'ont pu être réalisées sur Windows, vous pouvez compléter la suite sur MacOS ou Linux.

Pour MacOS ce [tutoriel](http://mobilecpptutorials.com/hello-world-app-part-1.html) est très bien expliqué.

Pour Linux, la méthode avec laquelle j'ai réussi est là suivant :
- Assurez-vous d'avoir installer git, gedit.
- Installer JDK8 en suivant ces [instructions](https://www.javahelps.com/2015/03/install-oracle-jdk-in-ubuntu.html)
    - Prenez garde à bien remplacer **161** par la version téléchargée.
- Dans un dossier vide faite :
    ```shell
    git init
    git submodule add https://github.com/dropbox/djinni.git deps/djinni
    ```
- Copier ensuite dans ce même dossier vos fichiers **helloworld.djinni** et **run_djinni.sh**
- Dans un terminal, exécuter votre script
    ```shell
    run ./run_djinni.sh
    ```
- Copiez votre dossier generated-src dans votre application

Pour exécuter les étapes précédentes, une VM Linux a été utilisée.

Sachez que vous pourriez aussi générer ces fichiers par vous même, voici à quoi ressemble le _wrapping_ C++ JNI faisant le pont entre C++ et Java/Objective-C :

**generated-src/jni/NativeHelloWorld.cpp**
```cpp
// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from helloworld.djinni

#include "NativeHelloWorld.hpp"  // my header
#include "Marshal.hpp"

namespace djinni_generated {

NativeHelloWorld::NativeHelloWorld() : ::djinni::JniInterface<::helloworld::HelloWorld, NativeHelloWorld>("com/reactlibrary/HelloWorld$CppProxy") {}

NativeHelloWorld::~NativeHelloWorld() = default;


CJNIEXPORT void JNICALL Java_com_reactlibrary_HelloWorld_00024CppProxy_nativeDestroy(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        delete reinterpret_cast<::djinni::CppProxyHandle<::helloworld::HelloWorld>*>(nativeRef);
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, )
}

CJNIEXPORT jobject JNICALL Java_com_reactlibrary_HelloWorld_00024CppProxy_create(JNIEnv* jniEnv, jobject /*this*/)
{
    try {
        DJINNI_FUNCTION_PROLOGUE0(jniEnv);
        auto r = ::helloworld::HelloWorld::create();
        return ::djinni::release(::djinni_generated::NativeHelloWorld::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

CJNIEXPORT jstring JNICALL Java_com_reactlibrary_HelloWorld_00024CppProxy_native_1getHelloWorld(JNIEnv* jniEnv, jobject /*this*/, jlong nativeRef)
{
    try {
        DJINNI_FUNCTION_PROLOGUE1(jniEnv, nativeRef);
        const auto& ref = ::djinni::objectFromHandleAddress<::helloworld::HelloWorld>(nativeRef);
        auto r = ref->get_hello_world();
        return ::djinni::release(::djinni::String::fromCpp(jniEnv, r));
    } JNI_TRANSLATE_EXCEPTIONS_RETURN(jniEnv, 0 /* value doesn't matter */)
}

}  // namespace djinni_generated

```
Dans votre librairie, nous allons créer un dossier ````src```` et à l'intérieur de lui le dossier ````cpp````. C'est dans celui ci que votre code C++ se trouvera et sera partagé avec Android et iOS.

Dans ce dossier nous allons créer les fichiers suivant :

**hello_world_impl.hpp**
```cpp
#pragma once

#include "hello_world.hpp"

namespace helloworld {

    class HelloWorldImpl : public helloworld::HelloWorld {

    public:

        // Constructeur
        HelloWorldImpl();

        // Méthode retournant une string
        std::string get_hello_world();

    };

}
```

**hello_world_impl.cpp**
```cpp
#include "hello_world_impl.hpp"
#include <string>

namespace helloworld {

    std::shared_ptr<HelloWorld> HelloWorld::create() {
        return std::make_shared<HelloWorldImpl>();
    }

    HelloWorldImpl::HelloWorldImpl() {

    }

    std::string HelloWorldImpl::get_hello_world() {
        std::string myString = "Hello World! ";
        return myString;
    }

}
```

La partie compliquée pour Android, nous devons modifier le fichier ````android/app/build.gradle```` de votre librairie pour qu'il ressemble au suivant en vous assurant de ne pas remplacer ce qui est déjà dans votre fichier. Donc, comparez et ajoutez ce qu'il manque.

**build.gradle**
```gradle

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath 'com.android.tools.build:gradle:1.3.1'
    }
}

apply plugin: 'com.android.library'

android {
    compileSdkVersion 23
    buildToolsVersion "23.0.1"

    defaultConfig {
        minSdkVersion 16
        targetSdkVersion 22
        versionCode 1
        versionName "1.0"
        externalNativeBuild {
            cmake {
                cppFlags "-std=c++11 -frtti -fexceptions"
            }
        }
    }
    sourceSets {
        main {
            java {
                srcDirs = [
                        "../generated-src/java",
                        "src/main/java"
                ]
            }
        }
    }
    externalNativeBuild {
        cmake {
            path "CMakeLists.txt"
        }
    }
    lintOptions {
        abortOnError false
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compile 'com.facebook.react:react-native:+'
}
  
```

Il faudra ensuite créer le fichier qui suit :

**CMakeList.txt**
```txt
cmake_minimum_required(VERSION 3.4.1)

file(GLOB helloworld_sources
    ../deps/djinni/support-lib/jni/*.cpp
    ../generated-src/jni/*.cpp
    ../src/cpp/*.cpp
)

add_library(helloworld SHARED ${helloworld_sources})

# include directories for header files
include_directories(
    ../deps/djinni/support-lib/
    ../deps/djinni/support-lib/jni/
    ../generated-src/cpp/
    ../generated-src/jni/
    ../src/cpp/
)

target_link_libraries(helloworld)
```

Si vous rencontrez des problèmes, assurez-vous que vos chemins d'accès inscrit représentent les vôtres.

Il ne reste maintenant plus qu'à modifier notre classe ````RNMyLibraryModule```` pour terminer notre application :

**RNMyLibaryModule.java**
```java

package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

public class RNMyLibraryModule extends ReactContextBaseJavaModule {

  private HelloWorld cppApi;

  private final ReactApplicationContext reactContext;

  static {
    System.loadLibrary("helloworld");
  }

  public RNMyLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    cppApi = HelloWorld.create();
  }

  @Override
  public String getName() {
    return "RNMyLibrary";
  }

  @ReactMethod
  public void helloWorld(Promise promise) {
    String myString = cppApi.getHelloWorld();
    promise.resolve(myString);
  }
}
```

Un dernier ````npm install my-library```` dans la racine de notre application et nous pouvons exécuter notre application qui nous affichera notre message depuis du code **C++**!

## Bibliographie
- [Kyle Bank ReactCalculator Tutorial](https://kylewbanks.com/blog/react-native-tutorial-part-1-hello-react)
- [Kyle Bank ReactCalculator GitHub](https://github.com/KyleBanks/ReactCalculator)
- [React Native Getting Started Documentation](https://facebook.github.io/react-native/docs/getting-started)
- [React Native Native Modules Android Documentation](https://facebook.github.io/react-native/docs/native-modules-android)
- [Kerberos Morphy RN-Calculator](https://github.com/KerberosMorphy/RN-Calculator)
- [React Native with JNI & C](https://thebhwgroup.com/blog/react-native-jni)
- [Interaction of Kotlin with C++](https://proandroiddev.com/android-ndk-interaction-of-kotlin-and-c-c-5e19e35bac74)
- [Mobile C++ Tutorial](http://mobilecpptutorials.com/) (voir _**Hello World App Part [1](http://mobilecpptutorials.com/hello-world-app-part-1.html)-[2](http://mobilecpptutorials.com/hello-world-app-part-2.html)-[3](http://mobilecpptutorials.com/hello-world-app-part-3.html))**_
## Licence
```
MIT License

Copyright (c) 2018 Benoit Verret, Assistyv

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
