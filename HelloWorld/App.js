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