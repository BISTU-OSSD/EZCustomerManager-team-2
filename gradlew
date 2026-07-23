#!/bin/sh

GRADLE_VERSION=8.5

if [ ! -d "./gradle/wrapper" ]; then
    mkdir -p ./gradle/wrapper
fi

if [ ! -f "./gradle/wrapper/gradle-wrapper.jar" ]; then
    curl -L -o ./gradle/wrapper/gradle-wrapper.jar https://raw.githubusercontent.com/gradle/gradle/v${GRADLE_VERSION}/gradle/wrapper/gradle-wrapper.jar
fi

if [ ! -f "./gradle/wrapper/gradle-wrapper.properties" ]; then
    echo "distributionBase=GRADLE_USER_HOME" > ./gradle/wrapper/gradle-wrapper.properties
    echo "distributionPath=wrapper/dists" >> ./gradle/wrapper/gradle-wrapper.properties
    echo "distributionUrl=https\\://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" >> ./gradle/wrapper/gradle-wrapper.properties
    echo "zipStoreBase=GRADLE_USER_HOME" >> ./gradle/wrapper/gradle-wrapper.properties
    echo "zipStorePath=wrapper/dists" >> ./gradle/wrapper/gradle-wrapper.properties
fi

exec java -cp "./gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
