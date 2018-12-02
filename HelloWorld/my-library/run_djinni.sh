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
