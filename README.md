# Tutoriel React Native avec C++ pour Android

Tutoriel pour création d'une application React Native utilisant du code C++ avec Android.
Ce tutoriel est basé sur [Kyle Banks Tutorial](https://kylewbanks.com/blog/react-native-tutorial-part-1-hello-react)

## Prérequis
### Windows
- Installer [Chocolatey](https://chocolatey.org/)
- Installer Node.js, Python2, Java Developper Kit 8 depuis la commande : 
    ```shell
    $ choco install -y nodejs.install python2 jdk8
    ```
    - Vous pouvez aussi intaller une version du [JDK plus récent](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK
- Installer React Native depuis la commande : 
    ```shell
    $ npm install -g react-native-cli
    ```
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](tps://facebook.github.io/react-native/docs/getting-started#2-install-the-android-sdk) pour les paramètres
### MacOS
- Installer [Homebrew](https://brew.sh/)
- Installer Node.js et Watchman depuis les commandes : 
    ```shell
    $ brew install node
    $ brew install watchman
    ```
- Installer React Native depuis la commande : 
    ```shell
    $ npm install -g react-native-cli
    ```
    - Si vous voyez une erreur du type *Cannot find module 'npmlog'*, essayez d'installer npm directement par la commande : 
    ```shell
    $ curl -0 -L https://npmjs.org/install.sh | sudo sh
    ```
- Installer [Java Developper Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) ou plus récent : 
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK : 
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](https://facebook.github.io/react-native/docs/getting-started#1-install-android-studio) pour les paramètres : 
### Linux
- Installer [Node.js](https://nodejs.org/en/download/package-manager/) selon la version de votre distribution Linux : 
- Installer React Native depuis la commande : 
    ```shell
    npm install -g react-native-cli
    ```
- Installer [Java Developper Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) ou plus récent : 
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK : 
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](https://facebook.github.io/react-native/docs/getting-started#1-install-android-studio) pour les paramètres : 
- Suivre ces indications pour installer [Watchman](https://facebook.github.io/react-native/docs/getting-started#watchman)

## Créer votre application
*Toutes les commandes suivantes sont basées sur Windows, vous pourriez avoir à les adapter si vous utilisez MacOS ou Linux*

Pour créer l'application exécutez la commande suivante :
```shell
$ react-native init HelloWorld
```
Allez ensuitre dans le répertoire de votre programme :
```shell
$ cd HelloWorld
```
Vous pouvez par la suite démarrer votre application pour Android :
```shell
$ react-native run-android
```
*Vous devez au préalable avoir un appareil Android connecté ou un émulateur Android en marche*

Dans votre répertoire vous devriez trouver le fichier **App.js**, ouvrez le et remplacez le code par :
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
Ici, nous créerons une librarie React Native pour utiliser du code Java, pour se faire nous aurons besoin d'installer l'utilitaire de React Native simplifiant la procédure.
```shell
$ npm install -g react-native-create-library
```
Bien qu'elle peut-être créé n'importe où, nous la créerons directement dans le répertoire de notre application HelloWorld.
Nous débuterons donc par créer la librairie à l'aide de la commande suivante.
```shell
$ react-native-create-library my-library
```
Si vous allez dans le répertoire my-library que vous venez de créer, vous trouverez un fichier **README**, les instructions à l'intérieure peuvent-être utile mais il y a certaine erreur.

Revenez dans le répertoire de votre application Hello World et exécuter les commandes suivantes :
```shell
$ npm install my-library --save
$ react-native link my-library
```
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

  private final int TEST = 42;

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
    callback.invoke('Hello World!');
  }
```
Notez bien que le **@ReactMethod** est essentiel pour toutes méthodes qui sera appelé depuis votre application.

Vous pouvez maintenant allez dans le fichier de votre application et ajouter l'importation suivante :
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
        }

        RNMyLibrary.helloWorld((result) => {
            this.helloWorld()
            });
    }
    render() {
        return (
        <View>
            <Text>RNMyLibrary.helloWorld()</Text>
        </View>
        );
    }

    helloWorld(result) {
        this.setState({ helloWorld: result });
    }
}
```
Vous venez de créer votre première application utilisant des fonctionnalités Java!
### Note Importante ###
Il se peut que votre application ne compile pas, cela pourrait être du au fait qu'Android a changé certaine de leur commande, allez voir le fichier **README** de votre librairie, le point numéro 3 conernant Android. Ouvrez le fichier dont il est fait mention et modifier le mot ***compile*** par le mot ***implementation***. 

Si vous avez encore des problèmes, c'est que ce tutoriel doit être mis à jour et que vous devrez faire des recherches sur [Google](https://www.google.ca/) et [Stack Overflow](https://stackoverflow.com/) pour plus d'aide.
## Utiliser du code C++
À suivre ...
## License
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
