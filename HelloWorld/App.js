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