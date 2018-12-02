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