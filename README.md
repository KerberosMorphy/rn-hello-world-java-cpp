# Tutoriel React Native avec C++ pour Android

Tutoriel pour création d'une application React Native utilisant du code C++ avec Android.

## À propos de ce tutoriel
Les technologies avançant très rapidemente et tous modules n'étant pas mis à jour aussi régulièrement, des incompatibilités peuvent se créer au fil du temps. Dans le dossier de l'application servant d'example vous trouverez le fichier package.json qui contient les versions utilisez lors de la réalisation de ce tutoriel, si vous modifier votre propre fichier package.json vous pourrez modifier les versions que vous utilisez et donc retrouver la compatibilité des modules que j'ai utilisé. Notez toute fois qu'utiliser d'ancienne version peut vous rendre plus à risque, les mise à jour serve souvent à supprimer des failles de sécurité.

Ce tutoriel à été réaliser avec ces environnements suivants :

Windows 10 :

- Terminal de commande : PowerShell
- IDE : 
    - VSCode et/ou WebStorm
    - Android Studio

La partie concernant le code C++ via DJINNY n'a pas pu être réussis sur Windows, mais à été fait sur MacOS et Linux pour ensuite être transféré dans Windows.
## Prérequis
### Windows
- Installer [Chocolatey](https://chocolatey.org/)
- Installer Node.js, Python2, Java Developper Kit 8 depuis la commande : 
    ```shell
    $ choco install -y nodejs.install python2 jdk8
    ```
    - Si besoin, bous pouvez upgrader vos versions déjà installer :
    ```shell
    $ choco upgrade chocolatey
    ```
    ```shell
    $ choco upgrade -y nodejs.install python2 jdk8
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
*Testé sur kubuntu 18.10, tutoriel non terminé pour linux et non fonctionnel*
- Installer [Node.js](https://nodejs.org/en/download/package-manager/) selon la version de votre distribution Linux : 
    ```shell
    $ snap install nodejs --classic --channel=10/stable
    $ apt install npm
    ```
- Installer React Native depuis la commande : 
    ```shell
    $ npm install -g react-native-cli
    ```
- Installer [Java Developper Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) ou plus récent : 
    - Vous pouvez aussi utiliser [OpenJDK](http://openjdk.java.net/install/) comme alternative à JDK.
- Installer [Android Studio](https://developer.android.com/studio/)
    - Suivre ces [indications](https://facebook.github.io/react-native/docs/getting-started#1-install-android-studio) pour les paramètres :
    ```shell
    $ snap install android-studio --classic
- Suivre ces indications pour installer [Watchman](https://facebook.github.io/react-native/docs/getting-started#watchman)

## Créer votre application
*Toutes les commandes suivantes sont basées sur Windows et on été exécuté via PowerShell, vous pourriez avoir à les adapter si vous utilisez MacOS ou Linux*

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
$ react-native link react-native-my-library
```
Notez l'ajout du ````react-native-```` devant le nom de votre librairie, cela est fait automatiquement. 

### *NOTE IMPORTANTE* ###
_Chaque fois que vous modifiez le code de votre librairie, vous devrez absolument refaire l'étape précédante et vous aurez ensuite à redémarrer votre application tel que vu à l'étape **Créer votre application**._

_En cas d'erreur lors du ````react-native link```` exécutez la commande ````npm install```` dans la racine de votre programme et refaite la commande ````react-native link````._

_Si après l'installation de votre library vous n'arrivez plus à exécuter, cela peut être du au fait que le module android de votre library créé par React Native Create Library n'est plus à jour et cela crée une incompatibilité. Pour réglé ce problème, ouvrez le dossier ````android```` se trouvant dans votre dossier ````my-library```` avec Android Studio répondez non à toutes les questions pour être sur de ne rien briser dans votre code. Une fois ouvert allez dans ````Build````->````Clean Project````. Puis éexécutez votre programme, tout devrait fonctionner._

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
    callback.invoke("Hello World!");
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
        };

        /**
         * Votre fonction java s'exécutera dans le constructeur et
         * retournera son résultat à votre fonction JS
         **/
        RNMyLibrary.helloWorld((result) => {
            this.helloWorld(result)
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
     * Votre fonction JS mettra à jour le State de votre application
     * La mise à jour du State est ce qui indique à votre application
     * de faire un refresh.
     **/
    helloWorld(result) {
        this.setState({ helloWorld: result });
    }
}
```
Vous venez de créer votre première application utilisant des fonctionnalités Java!

_*Si votre application ne fonctionne pas, assurez-vous d'avoir bien réexécuté la commande ````npm install my-library```` dans la racine de votre application pour lui indiquer les mises à jour de votre module.*_

## Amélioration de votre code Java

Précédemment nous avons utilisé le ````Callback```` pour nous renvoyer notre ````Hello World!````, mais l'utilisation de ````Promise```` pourrait être préférable pour permettre l'utilisation de fonction asynchrone.

Cas d'exemple, votre application devient complexe et vous voulez appeler une fonction Java en utilisant son retour pour modifier votre State et rafraichir votre page. Vous constater que votre application n'est pas raffraichis après l'appel de votre fonction, lorsque vous intéragissez avec un autre fonction par la suite et que votre application se rafraichit vous remarquez que votre retour de fonction Java apparaît soudainement. Ce problème peut être du à la mauvaise synchronisation de vos différentes section de code, c'est à dire que votre application se rafraichit alors que votre fonciton Java n'a pas terminé de traîter l'information.

Sans allez plus en détails voici l'alternative de ````Callback````

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
      promise.resolve("Hello World!");
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
     * Votre fonction asynchrone JS appelera votre fonction JAVA et
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

## Utiliser du code C++
À suivre ...
## Bibliographie
- [Kyle Bank ReactCalculator Tutorial](https://kylewbanks.com/blog/react-native-tutorial-part-1-hello-react)
- [Kyle Bank ReactCalculator GitHub](https://github.com/KyleBanks/ReactCalculator)
- [React Native Getting Started Documentation](https://facebook.github.io/react-native/docs/getting-started)
- [React Native Native Modules Android Documentation](https://facebook.github.io/react-native/docs/native-modules-android)
- [Kerberos Morphy RN-Calculator](https://github.com/KerberosMorphy/RN-Calculator)
- [React Native with JNI & C](https://thebhwgroup.com/blog/react-native-jni)
- [Interaction of Kotlin with C++](https://proandroiddev.com/android-ndk-interaction-of-kotlin-and-c-c-5e19e35bac74)
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
